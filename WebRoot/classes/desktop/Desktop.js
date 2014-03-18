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
					}, {
						name : '腾讯微博',
						iconCls : 't-shortcut',
						module : 'Leetop.module.QQWeiBo',
						index : 2
					}, {
						name : '浏览器',
						iconCls : 'tt-shortcut',
						module : 'Leetop.browser.Browser',
						index : 3
					}, {
						name : 'QQ空间',
						iconCls : 'qzone-shortcut',
						module : 'Leetop.module.Notepad',
						index : 4
					}, {
						name : 'QQ邮箱',
						iconCls : 'mail-shortcut',
						module : 'Leetop.module.Notepad',
						index : 5
					}, {
						name : 'QQ音乐',
						iconCls : 'qmusic-shortcut',
						module : 'Leetop.module.QQMusic',
						index : 6
					}, {
						name : '腾讯视频',
						iconCls : 'shipin-shortcut',
						module : 'Leetop.module.Notepad',
						index : 7
					}, {
						name : '腾讯朋友',
						iconCls : 'pengyou-shortcut',
						module : 'Leetop.module.Notepad',
						index : 8
					}, {
						name : 'QQ词典',
						iconCls : 'dic-shortcut',
						module : 'Leetop.module.Notepad',
						index : 9
					}, {
						name : 'QQ地图',
						iconCls : 'map-shortcut',
						module : 'Leetop.module.QQMap',
						index : 10
					}, {
						name : '网络硬盘',
						iconCls : 'nethard-shortcut',
						module : 'Leetop.module.Notepad',
						index : 11
					}, {
						name : 'chajian管理',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.Notepad',
						index : 12
					}, {
						name : '插件管理',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.Notepad',
						index : 13
					}, {
						name : '应用中心',
						iconCls : 'app-shortcut',
						module : 'Leetop.module.NotepadA',
						index : 14
					}, {
						name : '豆瓣FM',
						iconCls : 'douban-shortcut',
						module : 'Leetop.module.Douban',
						index : 15
					}, {
						name : '便     签',
						iconCls : 'bianqian-shortcut',
						module : 'Leetop.module.Notepad',
						index : 16
					}, {
						name : '时钟',
						iconCls : 'clock-shortcut',
						module : 'Leetop.module.Notepad',
						index : 17
					}, {
						name : '天气',
						iconCls : 'wether-shortcut',
						module : 'Leetop.module.Notepad',
						index : 18
					}, {
						name : '表格',
						iconCls : 'grid-shortcut',
						module : 'Leetop.module.GridWindow',
						index : 19
					}, {
						name : '即时通讯',
						iconCls : 'accordion-shortcut',
						module : 'Leetop.module.AccordionWindow',
						index : 20
					}, {
						name : '记事本',
						iconCls : 'notepad-shortcut',
						module : 'Leetop.module.Notepad',
						index : 21
					}, {
						name : '系统状态',
						iconCls : 'cpu-shortcut',
						module : 'Leetop.module.SystemStatus',
						index : 22
					},{
						name : '谷歌翻译',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.GoogleTranslater',
						index : 23
					},{
						name : '个人信息管理',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.UserModule',
						index : 24
					},{
						name : '好友管理',
						iconCls : 'friend-shortcut',
						module : 'Leetop.module.FriendModule',
						index : 25
					},{
						name : '群组管理',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.GroupModule',
						index : 26
					},{
						name : '消息管理',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.MessageModule',
						index : 27
					},{
						name : '五子棋',
						iconCls : 'plugin-shortcut',
						module : 'Leetop.module.FiveModule',
						index : 28
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

            wallpaper: 'classes/desktop/wallpapers/cloud.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            user: this.user,
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
