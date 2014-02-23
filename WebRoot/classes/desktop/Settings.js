/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Leetop.desktop.Settings', {
    extend: 'Ext.window.Window',

    uses: [
	        'Leetop.desktop.Wallpaper',
	        'Leetop.desktop.WallpaperModel',
			'Leetop.desktop.theme.ThemeView',
			'Leetop.desktop.theme.ThemeModel',
	        'Leetop.desktop.theme.BackgroudView',
	        'Leetop.desktop.theme.BackgroudModel'
	    ],

    //layout: 'anchor',
    layout: 'fit',
    title: '个性化',
    //modal: true,
    width: 640,
    height: 480,
    border: false,

    initComponent: function () {
        var me = this;

        me.selected = me.desktop.getWallpaper();
        me.stretch = me.desktop.wallpaper.stretch;

        me.preview = Ext.create('widget.wallpaper');
        me.preview.setWallpaper(me.selected);
        me.tree = me.createTree();

        me.buttons = [
            { text: '确&nbsp;&nbsp;定', handler: me.onOK, scope: me },
            { text: '取&nbsp;&nbsp;消', handler: me.close, scope: me },
            { text: '应&nbsp;&nbsp;用', handler: me.onApply, scope: me }
        ];
        me.items = [{
        	xtype : 'tabpanel',
        	items : [{
		        		title : '主题',
		        		layout : 'fit',
		        		items : [me.createThemeView()]
			         },{
		        		xtype : 'panel',
		        		title : '背景图片(树状)',
		                anchor: '0 -30',
		                border: false,
		        		layout: 'anchor',
		        		items : [{
			                     anchor: '0 -30',
			                     border: false,
			                     layout: 'border',
			                     items: [
			                         me.tree,
			                         {
			                             xtype: 'panel',
			                             title: '预览',
			                             region: 'center',
			                             layout: 'fit',
			                             items: [ me.preview ]
			                         }
			                     ]
			                 },
			                 {
			                     xtype: 'checkbox',
			                     boxLabel: '强制填充',
			                     checked: me.stretch,
			                     listeners: {
			                         change: function (comp) {
			                             me.stretch = comp.checked;
			                         }
			                     }
			                 }
			             ]
		        	},{
		        		title : '背景图片(卡片)',
		        		layout : 'fit',
		        		items : [me.createBackgroudView()]
		        	}]
        }];
        /*me.items = [
            {
                anchor: '0 -30',
                border: false,
                layout: 'border',
                items: [
                    me.tree,
                    {
                        xtype: 'panel',
                        title: '预览',
                        region: 'center',
                        layout: 'fit',
                        items: [ me.preview ]
                    }
                ]
            },
            {
                xtype: 'checkbox',
                boxLabel: 'Stretch to fit',
                checked: me.stretch,
                listeners: {
                    change: function (comp) {
                        me.stretch = comp.checked;
                    }
                }
            }
        ];*/

        me.callParent();
    },
    createBackgroudStore : function(){
    	var me = this;
    	function child(name){
    		return {name : name,url : me.buildBackgroudURL(name),text : me.getTextOfWallpaper(name) };
    	}
    	me.backgroudStore = Ext.create('Ext.data.Store', {
                model: 'Leetop.desktop.theme.BackgroudModel',
                data: [{
	                	name : 'None',
	                	url : me.buildBackgroudURL(''),
	                	text : 'None'
		               },
		               child('Blue-Sencha.jpg'),
                       child('Dark-Sencha.jpg'),
                       child('Wood-Sencha.jpg'),
                       child('blue.jpg'),
                       child('desk.jpg'),
                       child('desktop.jpg'),
                       child('desktop2.jpg'),
                       child('sky.jpg'),
                       child('cloud.jpg'),
                       child('winodw 7.jpg'),
                       child('South Pole.jpg')]
            });
        return me.backgroudStore;
    },
    
    createThemeStore : function(){
    	var me = this;
    	function child(name,theme,background){
    		return {name : name,theme : theme,url : me.buildBackgroudURL(theme + '.jpg'),background :me.buildBackgroudURL(background) };
    	}
    	me.themeStore = Ext.create('Ext.data.Store', {
                model: 'Leetop.desktop.theme.ThemeModel',
                data: [child('默认风格','ext-all','cloud.jpg'),
		               child('现代风格','ext-all-access','sky.jpg'),
                       child('银灰风格','ext-all-gray','desktop2.jpg'),
                       child('默认风格','ext-all','cloud.jpg'),
		               child('现代风格','ext-all-access','sky.jpg'),
                       child('银灰风格','ext-all-gray','desktop2.jpg')]
            });
        return me.themeStore;
    },
    
    buildBackgroudURL : function(img){
    	var me = this;
    	if (img) {
            return 'classes/desktop/wallpapers/' + img;
        } else {
            return Ext.BLANK_IMAGE_URL;
        }
    },
    
    createBackgroudView : function(){
    	var me = this;
    	me.backgroudView = Ext.create('Leetop.desktop.theme.BackgroudView',{
    		store : me.createBackgroudStore(),
    		listeners : {
    			itemclick : me.onBackgroundItemClick,
    			afterrender: { fn: me.onBackgroudViewRender, delay: 100 },
    			scope : me
    		}
    	});
    	return me.backgroudView;
    },
    
    createThemeView : function(){
    	var me = this;
    	me.themeView = Ext.create('Leetop.desktop.theme.ThemeView',{
    		store : me.createThemeStore(),
    		listeners : {
    			itemclick : me.onThemeItemClick,
    			afterrender: { fn: me.onThemeViewRender, delay: 100 },
    			scope : me
    		}
    	});
    	return me.themeView;
    },
    
    onThemeItemClick : function(view,record){
    	var me = this,theme = me.desktop.theme;
    	if(theme !== record.data.theme){
    		Ext.util.CSS.swapStyleSheet('theme',ctx+'/lib/ext4/resources/css/'+ record.data.theme +'.css');
    		me.desktop.theme = record.data.theme;
    	}
    	me.selected = record.data.background;
    	me.desktop.setWallpaper(record.data.background);
    },
    
    onThemeViewRender: function () {
        var me = this,s = me.desktop.theme;
        if (s) {
            var record = me.backgroudStore.findRecord('theme',s);
            if(record){
            	me.themeView.getSelectionModel().select(me.themeStore.indexOf(record));
            }
        }
    },
    
    onBackgroudViewRender: function () {
        var me = this,s = me.desktop.getWallpaper();
        if (s) {
            var record = me.backgroudStore.findRecord('url',s);
            if(record){
            	me.backgroudView.getSelectionModel().select(me.backgroudStore.indexOf(record));
            }
        }
    },
    
    onBackgroundItemClick : function(view,record){
    	var me = this;
    	me.selected = record.data.url;
        if (me.selected) {
            me.desktop.setWallpaper(me.selected, me.stretch);
        }
    },

    createTree : function() {
        var me = this;

        function child (img) {
            return { img: img, text: me.getTextOfWallpaper(img), iconCls: '', leaf: true };
        }

        var tree = new Ext.tree.Panel({
            title: '桌面背景',
            rootVisible: false,
            lines: false,
            autoScroll: true,
            width: 150,
            region: 'west',
            split: true,
            minWidth: 100,
            listeners: {
                afterrender: { fn: this.setInitialSelection, delay: 100 },
                select: this.onSelect,
                scope: this
            },
            store: new Ext.data.TreeStore({
                model: 'Leetop.desktop.WallpaperModel',
                root: {
                    text:'Wallpaper',
                    expanded: true,
                    children:[
                        { text: "None", iconCls: '', leaf: true },
                        child('Blue-Sencha.jpg'),
                        child('Dark-Sencha.jpg'),
                        child('Wood-Sencha.jpg'),
                        child('blue.jpg'),
                        child('desk.jpg'),
                        child('desktop.jpg'),
                        child('desktop2.jpg'),
                        child('sky.jpg'),
                        child('cloud.jpg'),
                        child('winodw 7.jpg'),
                        child('South Pole.jpg')
                    ]
                }
            })
        });

        return tree;
    },
    
    getTextOfWallpaper: function (path) {
        var text = path, slash = path.lastIndexOf('/');
        if (slash >= 0) {
            text = text.substring(slash+1);
        }
        var dot = text.lastIndexOf('.');
        text = Ext.String.capitalize(text.substring(0, dot));
        text = text.replace(/[-]/g, ' ');
        return text;
    },

    onOK: function () {
        var me = this;
        if (me.selected) {
            me.desktop.setWallpaper(me.selected, me.stretch);
        }
        me.destroy();
    },

    onApply: function(){
    	var me = this;
        if (me.selected) {
            me.desktop.setWallpaper(me.selected, me.stretch);
        }
    },
    
    onSelect: function (tree, record) {
        var me = this;

        if (record.data.img) {
            me.selected = 'classes/desktop/wallpapers/' + record.data.img;
        } else {
            me.selected = Ext.BLANK_IMAGE_URL;
        }

        me.preview.setWallpaper(me.selected);
    },

    setInitialSelection: function () {
        var s = this.desktop.getWallpaper();
        if (s) {
            var path = 'desktop/Wallpaper/' + this.getTextOfWallpaper(s);
            this.tree.selectPath(path, 'text');
        }
    }
});
