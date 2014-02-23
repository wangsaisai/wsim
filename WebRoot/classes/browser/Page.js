Ext.define('Leetop.browser.Page',{
	extend : 'Ext.panel.Panel',
	
	iconCls : 'x-browser-page-icon',
	
	closable : true,
	
	title : '新标签页',
	
	layout : 'border',
	
	proxyURL : ctx+'/borwser/html?url=',
	
	loadCount : 0,
	
	statusTypes : {
		INIT : 'init',
		LOADING : 'loading',
		STOP :'stop',
		COMPLETE : 'complete'
	},
	
	initComponent : function(){
		var me = this;
		me.proxyPanel = Ext.create('Ext.panel.Panel',{
			border : false,
			region : 'center',
			//html : me.url ? me.buildIframeHTML(me.url) : null,
			split : true,
			listeners : {
				afterrender : me.onAfterRender,
				scope : me
			}
		});
		
		me.progressbar = Ext.create('Ext.ProgressBar',{
			text : '初始化...',
			flex : 1
		});
		me.items = [me.proxyPanel,
					{
						xtype : 'panel',
						autoHeight : true,
						region : 'south',
						border : false,
			    		split : true,
			    		items : [me.progressbar]
					}];
		me.on({
			activate : me.onActivate,
			beforedestroy : me.onBeforDestroy,
			scope : me
		});
		me.setStatus(me.statusTypes.INIT);
		me.callParent();
	},
	
	onAfterRender : function(){
		var me = this;
		if(me.url){
			me.access(me.url);
		}
	},
	
	onBeforDestroy : function(){
		var me = this;
		if(me.browser.tab.items.getCount() == 2){
			me.browser.app.getDesktop().getActiveWindow().destroy();
		}
	},
	
	onActivate : function(){
		var me = this;
		me.updatePageInfo();
	},
	
	buildIframeHTML : function(){
		return '<iframe  scrolling="auto" frameborder="no" hidefocus="" ' +
				'allowtransparency="true" style="width: 100%; height: 100%;">';
	},
	
	onProxyLoad : function(){
		var me = this;
		me.setStatus(me.statusTypes.COMPLETE);
		me.updateTitle(me.getProxyWindow().document.title);
		me.setIconCls('x-browser-page-icon');
		me.progressbar.reset();
		me.progressbar.updateProgress(1,'完成加载'+me.url+',耗时:'+(new Date().getTime() - me.startLoadTime)+'ms.');
		me.browser.onLoad(me);
	},
	
	updateTitle : function(title){
		var me = this,title = Ext.String.trim(title);
		if(me.record){
			me.record.set('title',title);
		}
		me.longTitle = title;
		me.setTitle(Ext.String.ellipsis(title,15));
		me.updatePageInfo();
	},

	initProxy : function(){
    	var me = this;
    	me.proxyPanel.update(me.buildIframeHTML());
		me.getProxy().onload = function(){
			me.onProxyLoad();
		};
    },
    
    isLoading : function(){
    	return (this.status == this.statusTypes.LOADING);
    },
    
    updatePageInfo : function(){
		var me = this;
		me.browser.updateTaskButtonTooltip(me.longTitle || me.title ,me.url);
		me.browser.updateTaskButtonText(me.title);
		me.browser.updateAddressValue(me.url);
	},
    
	getProxy : function(){
		return this.proxyPanel.body.first('iframe', true);
	},
	
	setURL : function(url){
		this.getProxy().src = this.proxyURL + url;
		this.url = url;
	},
	
	getURL : function(){
		return this.url;
	},
	
	setStatus : function(status){
		this.status = status;
	},
	
	getStatus : function(){
		return this.status;
	},
	
	getProxyWindow : function(){
		return this.getProxy().contentWindow;
	},
	
	stop : function(){
		var me = this;
		me.setIconCls('x-browser-page-icon');
		me.progressbar.reset();
		me.progressbar.updateProgress(0,'用户停止加载'+me.url);
		me.setStatus(me.statusTypes.STOP);
		me.getProxyWindow().stop();
	},
	
	refresh : function(){
		var me = this;
		me.access(me.url,true);
	},
	
	getHttpTitle : function(url,callbak,scope){
		var me = this;
		Ext.Ajax.request({
		    url: ctx+'/borwser/html',
		    params: {
		        url : url
		    },
		    success: function(response){
		    	callbak.call(scope,Ext.JSON.decode(response.responseText),url,true);
		    },
		    failure : function(){
		    	callbak.call(scope,'未知标题',url,true);
		    }
		});
	},
	
	access : function(url){
		var me = this;
		if(me.isLoading()){
			me.stop();
		}
		me.startLoadTime = new Date().getTime();
		if(!me.getProxy()){
			me.initProxy();
		}
		me.browser.address.onLoading();
		me.progressbar.wait({
			interval : 500,
			increment : 15,
			text : '正在连接服务器...'
		});
		var record = me.browser.historys.getById(url + 'ID'),exits = false;
		if(record){
			record.set('date',new Date());
			exits = true;
		}
		me.doHttpAccess(url,exits);
	},
	
	doHttpAccess : function(url,exits){
		var me = this, historys= me.browser.historys;
		me.progressbar.updateText('正在加载' + url + '...');
		if(!exits){
			historys.add({
				id : url + 'ID',
				url : url,
				date : new Date()
			});
			me.record =historys.getAt(historys.getCount() - 1);
		}
		me.setIconCls('x-browser-load-icon');
		me.setTitle("正在载入...");
		me.setStatus(me.statusTypes.LOADING);
		me.setURL(url);
	},
	
	initLinkTarget : function(){
		var links = document.links;
		for( var i = 0; i < links; i++ ){
			var link = links[i];
			link.target = "_self";
		}
	}
});