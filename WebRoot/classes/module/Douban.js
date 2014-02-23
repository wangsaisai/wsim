/*!
* Ext JS Library 4.0
* Copyright(c) 2006-2011 Sencha Inc.
* licensing@sencha.com
* http://www.sencha.com/license
*/

// From code originally written by David Davis (http://www.sencha.com/blog/html5-video-canvas-and-ext-js/)

Ext.define('Leetop.module.Douban', {
    extend: 'Leetop.system.Module',

    id:'douban',
    windowId: 'douban-window',

    tipWidth: 160,
    tipHeight: 96,

    init : function(){
        this.launcher = {
            text: '豆瓣FM',
            iconCls:'douban-shortcut-small',
            handler : this.createWindow,
            scope: this
        };
    },

    createWindow : function(iconCls){
        var me = this, desktop = me.app.getDesktop(),
            win = desktop.getWindow(me.windowId);

        if (!win) {
            win = desktop.createWindow({
                id: me.windowId,
                title: '豆瓣FM',
                width: 430,
                height: 221,
                iconCls: 'douban-shortcut-small',
                animCollapse: false,
                border: false,
                maximizable : false,
                resizable : false,
                layout: 'fit',
                items: [
                    {
                    	xtype : 'panel',
                    	html : '<iframe id="douoban_iframe" scrolling="auto" frameborder="no" hidefocus="" ' +
                    			'allowtransparency="true" src="http://douban.fm/partner/webqq" style="width: 100%; height: 100%;">'
                    }
                ],
                listeners : {
                	'afterrender' : function(){
                		var panel = win.items.get(0);
                		panel.el.mask('正在加载豆瓣FM,请稍候...');
                		panel.body.first('iframe', true).onload = function(){
                			panel.el.unmask();
                		};
                	}
                }
            });
        }
        
        win.show();

        return win;
    }
});
