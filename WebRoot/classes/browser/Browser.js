
Ext.define('Leetop.browser.Browser', {
    extend: 'Leetop.system.Module',

    id:'browser',
    windowId: 'browser-window',

    tipWidth: 160,
    tipHeight: 96,
    
    closeAction : 'destory',
    
    standardProtocol : 'http://',
    
    securityProtocol : 'https://',
    
    blankAddress :  'http://www.baidu.com',
    
    requires: [
    	'Leetop.browser.Address',
    	'Leetop.browser.History',
    	'Leetop.browser.Page',
    	'Ext.ux.TabReorderer',
    	'Ext.ux.TabScrollerMenu',
    	'Ext.ux.TabCloseMenu'
    ],

    init : function(){
        this.launcher = {
            text: '浏览器',
            iconCls:'tt-shortcut-small',
            handler : this.createWindow,
            scope: this
        },
        this.searcher = {
			name : '百度',
			url : 'http://www.baidu.com/s?wd='
        };
        this.log = Leetop.getLogger(this);
    },
	
	createHistorys : function(){
		var me = this;
		me.historys = Ext.create('Ext.data.Store',{
        	model : 'Leetop.browser.History'
        });
        return me.historys;
	},
	
	doRefresh : function(){
		var me = this;
		me.getActivePage().refresh();
	},
    
	doStop : function(){
		var me = this;
		me.getActivePage().stop();
	},
	
	doSearch : function(){
		var me = this;
		me.access( me.searcher.url + me.address.getValue());
	},
	
	onLoad : function(p){
		var me = this;
		if(me.getActivePage() == p){
			if(!me.address.getValue()){
				me.address.setValue(p.src);
			}
			me.address.onLoad();
		}
	},
	
	access : function(url){
		var me = this;
		url = url || me.blankAddress;
		if(url.indexOf(me.standardProtocol) == -1 && url.indexOf(me.securityProtocol) == -1){
			url = me.standardProtocol + url;
		}
		me.address.setRawValue(url);
		me.getActivePage().access(url);
	},
	
	updateTaskButtonTooltip : function(title,url){
		this.app.getDesktop().getWindow(this.windowId).taskButton.setTooltip({
			title : title,
			text : url || title,
			align: 'bl-tl'
		});
	},
	
	updateTaskButtonText : function(title){
		this.app.getDesktop().getWindow(this.windowId).taskButton.setText(title);
	},
	
	updateAddressValue : function(value){
		this.address.setRawValue(value);
	},
	
    createWindow : function(){
        var me = this, desktop = me.app.getDesktop(),
        win = desktop.getWindow(me.windowId);
		me.historys = me.createHistorys();	
        if (!win) {
            win = desktop.createWindow({
                id: me.windowId,
                title: '浏览器',
                width: desktop.view.getWidth() - 100,
                height: desktop.view.getHeight() - 100,
                iconCls: 'tt-shortcut-small',
                animCollapse: false,
                maximizable : true,
                border: false,
                layout : 'border',
                items: [
                    me.createNavBar(),
                    me.createTabPanel()
                ]
            });
        }
        win.show();
        me.access(me.customeURL);
        return win;
    },
    
    getActivePage : function(){
		return this.tab.getActiveTab();
	},
	
	createNewPage : function(url){
		var me = this;
		panel = Ext.create('Leetop.browser.Page',{browser : me,url : Ext.isString(url) ? url : null});
		me.address.onInit();
		me.tab.insert(me.tab.items.length - 2,panel);
		me.tab.setActiveTab(panel);
		me.address.focus();
		me.address.selectText();
	},
    
    createPlusTab : function(){
    	var me = this;
    	return {
    		xtype : 'panel',
    		iconCls : 'x-browser-add-icon',
    		reorderable : false,
    		listeners : {
    			activate : me.createNewPage,
    			scope : me
    		}
    	};
    },
    
    createTabPanel : function(){
    	var me = this;
    	me.tab = Ext.create('Ext.TabPanel',{
                    	activItem : 0,
                    	region : 'center',
                    	split  : true,
                    	tabWidth : 115,
                    	enableTabScroll : true,
                    	animScroll  : true,
                    	minTabWidth : 35,
                    	autoScroll : false,
                    	resizeTabs : true,
                    	items :[
                    		Ext.create('Leetop.browser.Page',{browser : me}),
                    		{	xtype : 'panel',
                    		 	hidden : true,
                    		 	closeable : false,
                    		 	reorderable : false,
                    		 	listeners : {
                    				activate : function(){
                    					me.tab.setActiveTab(me.tab.items.getCount() - 4);
                    				}
                    		 	}
                    		},
	                    	me.createPlusTab()
                    	],
				        plugins: [
				        	Ext.create('Ext.ux.TabReorderer'),
				        	Ext.create('Ext.ux.TabCloseMenu',{
			        		  	closeTabText: '关闭标签页',
			        		  	closeOthersTabsText: '关闭其他标签页',
			        		  	closeAllTabsText: '关闭所有标签页',
			        		  	beforeMenu : me.createTabBeforeCM()
			        		  })
				        ]
                    });
        return me.tab;       
    },
    
	createAddress : function(){
		var me = this;
		me.address = Ext.create('Leetop.browser.Address',{
            		store : me.historys,
            		browser : me,
            		flex : 1,
            		cls : 'x-browser-white',
            		displayField : 'domain',
            		valueField : 'url',
            		triggerAction : 'all',
            		listeners : {
            			'select' : function(){
            				me.access(this.getValue());
            			}
            		}
                });
        return me.address;     
	},
	
    
	createTabBeforeCM : function(){
		var me = this;
		return [{
					text : '新建标签页',
					handler : function(){
						me.createNewPage();
					}
				},{
					text : '重新载入标签页',
					handler : me.doRefresh,
					scope : me
				},{
					text : '复制标签页',
					handler : function(){
						me.createNewPage(me.getActivePage().url);
					}
				},'-',{
					text : '添加到收藏夹'
				},{
					text : '设置为主页',
					handler : function(){
						me.blankAddress = me.getActivePage().url;
					}
				}];
	},
	
    createNavBar : function(){
    	var me = this;
    	return {
    		xtype : 'panel',
    		region : 'north',
    		autoHeight : true,
    		split : true,
    		tbar : Ext.create('Ext.toolbar.Toolbar', {
	            items: [' ',{
	                iconCls : 'x-browser-back-icon',
	                tooltip : {
	                	text : '后退'
	                }
	            },'-',{
	                iconCls : 'x-browser-next-icon',
	                tooltip : {
	                	text : '前进'
	                }
	            },' ',
	            me.createAddress(),
	            ' ',
	            {
	            	iconCls : 'x-browser-home-icon',
	            	handler : function(){
	            		me.access();
	            	},
	                tooltip : {
	                	text : '首页'
	                }	
	            },'-',{
	            	iconCls : 'x-browser-favorites-icon',
	                tooltip : {
	                	text : '收藏夹'
	                }	
	            },'-',{
	            	iconCls : 'x-browser-gear-icon',
	                tooltip : {
	                	text : '设置'
	                }
	            },'-',{
	            	iconCls : 'x-browser-plugin-icon',
	            	handler : function(){
	            		me.log.debug(new Date().getTime());
	            	},
	                tooltip : {
	                	text : '插件管理'
	                }	
	            }]
            })
    	};
    }
});
