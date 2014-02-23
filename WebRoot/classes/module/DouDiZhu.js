
Ext.define('Leetop.module.DouDiZhu', {
    extend: 'Leetop.system.Module',

    id:'qq_doudizhu',
    windowId: 'qq_doudizhu_win',

    tipWidth: 160,
    tipHeight: 96,

    init : function(){
        this.launcher = {
            text: '欢乐斗地主',
            handler : this.createWindow,
            scope: this
        };
    },

    createWindow : function(){
        var me = this, desktop = me.app.getDesktop(),
            win = desktop.getWindow(me.windowId);

        if (!win) {
        	win = desktop.createWindow({
						id : me.windowId,
						title : '欢乐斗地主',
						iconCls : me.app.createSmallIconCls('plugin-shortcut'),
						width : 210,
						height : 235,
						animCollapse : false,
						maximizable : true,
						border : false,
						layout : 'fit',
						items : [
						/*
						 * { xtype : 'panel', html : '<iframe id="qqmap_iframe"
						 * scrolling="auto" ' + 'frameborder="no" hidefocus=""
						 * allowtransparency="true" ' +
						 * 'src="http://yxs.qq.com/"' + 'style="width: 100%;
						 * height: 100%;">' }
						 */
						 Ext.create('Ext.draw.Component', {
									viewBox : false,
									items : [{
												type : 'circle',
												fill : '#79BB3F',
												radius : 100,
												x : 100,
												y : 100,
												items : [{
													type : 'text',
										            text: '12',
										            fill: '#000',
													x : 60,
													y : 2
												}]
											},{
												type : 'text',
									            text: '12',
									            fill: '#000',
												x : 90,
												y : 10,
												font: '14px Arial bolder'
											},{
												type : 'text',
									            text: '1',
									            fill: '#000',
												x : 145,
												y : 25,
												font: '14px Arial bolder'
											},{
												type : 'text',
									            text: '2',
									            fill: '#000',
												x : 170,
												y : 60,
												font: '14px Arial bolder'
											},{
												type : 'text',
									            text: '3',
									            fill: '#000',
												x : 180,
												y : 100,
												font: '14px Arial bolder'
											},{
									            type: "path",
									            path: "M100 100 200 200",
									            stroke: "none",
									            'stroke-width': 2,
									            fill: "#f5ccb0"
									        }]
								})
                
                ]/*
					 * , listeners : { 'afterrender' : function(){ var panel =
					 * win.items.get(0); panel.el.mask('正在欢乐斗地主,请稍候...');
					 * panel.body.first('iframe', true).onload = function(){
					 * panel.el.unmask(); }; } }
					 */
            });
            /*win = desktop.createWindow({
                id: me.windowId,
                title: '欢乐斗地主',
                iconCls : me.app.createSmallIconCls('plugin-shortcut'),
                width: desktop.view.getWidth() -100,
                height: desktop.view.getHeight() -50,
                animCollapse: false,
                maximizable : true,
                border: false,
                layout: 'fit',
                items: [
                    {
                    	xtype : 'panel',
                    	html : '<iframe id="qqmap_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                    		'src="http://yxs.qq.com/"' +  
                    		'style="width: 100%; height: 100%;">'
                    }
                ],
                listeners : {
                	'afterrender' : function(){
                		var panel = win.items.get(0);
                		panel.el.mask('正在欢乐斗地主,请稍候...');
                		panel.body.first('iframe', true).onload = function(){
                			panel.el.unmask();
                		};
                	}
                }
            });*/
        }

        win.show();
        //win.maximize();

        return win;
    }
});
