/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Leetop.desktop.Desktop', {
    extend: 'Leetop.desktop.Base',

    requires: [
        'Leetop.desktop.ShortcutModel',
        'Leetop.desktop.Settings'
    ],
    
    init: function() {
        this.callParent();
    },
    
    activWindows : [],

    getModules : function(){
        return [{
		            text: 'Notepad',
		            iconCls:'notepad',
		            module : 'Leetop.module.Notepad',
		            scope: this
		        }];
    },
    
    shortcutsData : [
                    {
						name : '登录',
						iconCls : 'qq-shortcut',
						module : 'Leetop.module.Login',
						index : 1
					},{
						name : 'WSIM',
						iconCls : 'accordion-shortcut',
						module : 'Leetop.module.AccordionWindow',
						index : 20
					},  {
						name : '注册',
						iconCls : 'reg-shortcut',
						module : 'Leetop.module.RegisterModule',
						index : 23
					}, {
						name : '聊天室',
						iconCls : 'room-shortcut',
						module : 'Leetop.module.RoomModule',
						index : 24
					}
                ],

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            // cls: 'ux-desktop-black',

            contextMenuItems: [
                /* { text: '个性化', handler: me.onSettings, scope: me } */
            ],
            
            theme : theme,

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Leetop.desktop.ShortcutModel',
                data: me.shortcutsData
            }),

            wallpaper: 'classes/desktop/wallpapers/Wood-Sencha.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            user: 'wsim',
        	frame : true,
            autoHeight : true,
            toolConfig: {
                width: 85,
                items: [
                	{
                        //text:'<center>'+this.user+'</center>',
                		text:'&nbsp;',
                        scale: 'large',
                        iconAlign: 'top',
                        iconCls:'icon-user-face',
                        height : 50,
                        handler: me.onSettings,
                        scope: me
                    },'-',
                    {
                        text:'终&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;端',
                        iconCls:'icon-terminal',
                        handler: me.onSettings,
                        scope: me
                    },
                    {
                        text:'控&nbsp;制&nbsp;台',
                        iconCls:'icon-console',
                        handler: function(){
                       		 Leetop.Console.open();
                        }
                    },'-',
                    {
                        text:'设&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    {
                        text:'注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
    	var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: '显示桌面', iconCls: 'icon-display-desktop',handler : me.onDisplayDesktop,scope: me}/*,
                { name: 'Accordion Window', iconCls: 'accordion', module: 'acc-win' },
                { name: 'Grid Window', iconCls: 'icon-grid', module: 'grid-win' }*/
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('系统提示', '您确定要注销么?');
    },

    onSettings: function () {
        var dlg = new Leetop.desktop.Settings({
            desktop: this.desktop,
            iconCls : 'icon-personality'
        });
        dlg.show();
    },
    onDisplayDesktop: function () {
        var me = this;
        if(!me.toggle){
        	if(me.desktop.windows.getCount() > 0){
	        	me.toggle = true;
		        me.desktop.windows.each(function(win){
		        	me.activWindows.push(win.id);
		        	if(!win.minimized)
		        		win.minimize();
		        });
	        }
        }else{
        	Ext.each(me.activWindows,function(id){
	        	me.desktop.getWindow(id).show();
	        });
	        me.activWindows = [];
	        delete me.toggle;
	        me.toggle = false;
        }
    }
});
