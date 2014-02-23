/*!
* Ext JS Library 4.0
* Copyright(c) 2006-2011 Sencha Inc.
* licensing@sencha.com
* http://www.sencha.com/license
*/

// From code originally written by David Davis (http://www.sencha.com/blog/html5-video-canvas-and-ext-js/)

Ext.define('Leetop.module.QQMusic', {
    extend: 'Leetop.system.Module',

    id:'qqmusic',
    windowId: 'qqmusic-window',

    tipWidth: 160,
    tipHeight: 96,

    init : function(){
        this.launcher = {
            text: 'QQ音乐',
            iconCls:'qmusic-small-shortcut',
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
                title: 'QQ音乐',
                width: 800,
                height: 600,
                iconCls: 'qmusic-small-shortcut',
                animCollapse: false,
                maximizable : true,
                border: false,
                layout: 'fit',
                items: [
                    {
                    	xtype : 'panel',
                    	html : '<iframe id="qqmap_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                    		'src="http://music.qq.com/musicbox/player/music_player_webqq.html"' +  
                    		'style="width: 100%; height: 100%;">'
                    }
                ]
            });
        }

        win.show();
        //win.maximize();

        return win;
    }
});
