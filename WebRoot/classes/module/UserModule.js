
Ext.define('Leetop.module.UserModule', {
    extend: 'Leetop.system.Module',

    id:'user_module',
    windowId: 'user_module_win',

    tipWidth: 160,
    tipHeight: 96,

    init : function(){
        this.launcher = {
            text: '个人信息管理',
            handler : this.createWindow,
            scope: this
        };
    },

    createWindow : function(){
        var me = this, desktop = me.app.getDesktop(),
            win = desktop.getWindow(me.windowId);

        if (!win) {
            win = desktop.createWindow({
                id: me.windowId,
                title: '个人信息管理',
                iconCls : me.app.createSmallIconCls('t-shortcut'),
                width: 1000,
                height: desktop.view.getHeight() -50,
                animCollapse: false,
                maximizable : true,
                border: false,
                layout: 'fit',
                items: [
                    {
                    	xtype : 'panel',
                    	html : '<iframe id="user_module_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                    		'src="https://'+serverIP+':8443/wsim/module/user/userMain.jsp"' +  
                    		'style="width: 100%; height: 100%;">'
                    }
                ],
                listeners : {
                	'afterrender' : function(){
                		var panel = win.items.get(0);
                		panel.el.mask('正在打开页面,请稍候...');
                		panel.body.first('iframe', true).onload = function(){
                			panel.el.unmask();
                		};
                	}
                }
            });
        }

        win.show();
        //win.maximize();

        return win;
    }
});
