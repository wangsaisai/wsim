
Ext.define('Leetop.desktop.TaskBar', {
    extend: 'Ext.toolbar.Toolbar', // TODO - make this a basic hbox panel...

    requires: [
        'Leetop.desktop.StartMenu',
        'Ext.ux.BoxReorderer'
    ],

    alias: 'widget.taskbar',

    cls: 'ux-taskbar',

    /**
     * @cfg {String} startBtnText
     * The text for the Start Button.
     */
    startBtnText: '&nbsp;开&nbsp;始',

    initComponent: function () {
        var me = this;
        

        me.startMenu = new Leetop.desktop.StartMenu(me.startConfig);

        me.quickStart = new Ext.toolbar.Toolbar(me.getQuickStart());

        me.windowBar = new Ext.toolbar.Toolbar(me.getWindowBarConfig());

        me.tray = new Ext.toolbar.Toolbar(me.getTrayConfig());
        
        me.items = [
            {
                xtype: 'button',
                cls: 'ux-start-button',
                iconCls: 'ux-start-button-icon',
                menu: me.startMenu,
                menuAlign: 'bl-tl',
                text: me.startBtnText
            },
            '-',
            me.quickStart,
            {
                xtype: 'splitter', html: '&#160;',
                height: 14, width: 2, // TODO - there should be a CSS way here
                cls: 'x-toolbar-separator x-toolbar-separator-horizontal ux-toolbar-splitter'
            },
            //'-',
            me.windowBar,
            '-',
            me.tray
        ];

        me.callParent();
    },

    afterLayout: function () {
        var me = this;
        me.callParent();
        me.windowBar.el.on('contextmenu', me.onButtonContextMenu, me);
        me.quickStart.el.on('contextmenu', me.onQuickButtonContextMenu, me);
        //me.startMenu.menu.el.on('contextmenu',me.onStartMenuContextMenu,me);
    },

    /**
     * This method returns the configuration object for the Quick Start toolbar. A derived
     * class can override this method, call the base version to build the config and
     * then modify the returned object before returning it.
     */
    getQuickStart: function () {
        var me = this, ret = {
            minWidth: 20,
            width: 80,
            items: [],
            enableOverflow: true,
            defaults: {
            	reorderable: true
	        },
	        plugins : Ext.create('Ext.ux.BoxReorderer'),
            listeners : {
            	add : me.onQuickStartAdd,
            	scope : me
            }
        };

        Ext.each(this.quickStart, function (item) {
            ret.items.push({
                tooltip: { text: item.name, align: 'bl-tl' },
                //tooltip: item.name,
                overflowText: item.name,
                iconCls: item.iconCls,
                module: item.module,
                handler: item.handler || me.onQuickStartClick,
                scope: item.scope || me,
                isReady : true
            });
        });

        return ret;
    },
    
    onQuickStartAdd : function(bar,item){
    	var me = this;
    	if(!item.isReady){
	    	Ext.apply(item,{ 
	            tooltip: { text: item.name, align: 'bl-tl' },
	            //tooltip: item.name,
	            overflowText: item.name,
	            iconCls: item.iconCls,
	            module: item.module,
	            handler: item.handler || me.onQuickStartClick,
	            scope: item.scope || me
	        });
    	}
    },

    /**
     * This method returns the configuration object for the Tray toolbar. A derived
     * class can override this method, call the base version to build the config and
     * then modify the returned object before returning it.
     */
    getTrayConfig: function () {
        var ret = {
            width: 80,
            items: this.trayItems
        };
        delete this.trayItems;
        return ret;
    },

    getWindowBarConfig: function () {
        return {
            flex: 1,
            cls: 'ux-desktop-windowbar',
            items: [ '&#160;' ],
            defaults: {
            	reorderable: true
	        },
	        plugins : Ext.create('Ext.ux.BoxReorderer'),
            layout: { overflowHandler: 'Scroller' }
        };
    },

    getWindowBtnFromEl: function (el) {
        var c = this.windowBar.getChildByElement(el);
        return c || null;
    },
    
    getQuickStartBtnFromEl: function (el) {
        var c = this.quickStart.getChildByElement(el);
        return c || null;
    },

    onQuickStartClick: function (btn) {
        this.app.createWindow(btn.module,btn.name);
    },
    
    
    onQuickButtonContextMenu : function(e){
    	var me = this, t = e.getTarget(), btn = me.getQuickStartBtnFromEl(t);
        if (btn) {
            e.stopEvent();
            me.quickStartMenu.theBtn = btn;
            me.quickStartMenu.showBy(t);
            //me.quickStartMenu.doConstrain(me.app.desktop.getEl());
        }
    },
    
    onButtonContextMenu: function (e) {
        var me = this, t = e.getTarget(), btn = me.getWindowBtnFromEl(t);
        if (btn) {
            e.stopEvent();
            me.windowMenu.theWin = btn.win;
            me.windowMenu.showBy(t);
        }
    },

    onWindowBtnClick: function (btn) {
        var win = btn.win;

        if (win.minimized || win.hidden) {
            win.show();
        } else if (win.active) {
            win.minimize();
        } else {
            win.toFront();
        }
    },

    addTaskButton: function(win) {
    	var me = this;
        var config = {
            //iconCls: smallIconCls,
        	iconCls : win.iconCls,
            enableToggle: true,
            toggleGroup: 'all',
            width: 140,
            text: Ext.util.Format.ellipsis(win.title, 20),
            listeners: {
                click: this.onWindowBtnClick,
                scope: this
            },
            win: win
        };

        var cmp = this.windowBar.add(config);
        cmp.toggle(true);
        return cmp;
    },

    removeTaskButton: function (btn) {
        var found, me = this;
        me.windowBar.items.each(function (item) {
            if (item === btn) {
                found = item;
            }
            return !found;
        });
        if (found) {
            me.windowBar.remove(found);
        }
        return found;
    },

    setActiveButton: function(btn) {
        if (btn) {
            btn.toggle(true);
        } else {
            this.windowBar.items.each(function (item) {
                if (item.isButton) {
                    item.toggle(false);
                }
            });
        }
    }
});

/**
 * @class Leetop.lib.TrayClock
 * @extends Ext.toolbar.TextItem
 * This class displays a clock on the toolbar.
 */
Ext.define('Leetop.lib.TrayClock', {
    extend: 'Ext.button.Button',

    alias: 'widget.trayclock',

    //cls: 'ux-desktop-trayclock',

    //html: '&#160;',
    
	id : 'desktop_trayclock',
	
    timeFormat: 'H:i',
    
    tpl: '{time}',

    initComponent: function () {
        var me = this;

        me.callParent();
        if (typeof(me.tpl) == 'string') {
            me.text = new Ext.XTemplate(me.tpl);
        }
    },

    afterRender: function () {
        var me = this;
        Ext.Function.defer(me.updateTime, 100, me);
        me.callParent();
    },

    onDestroy: function () {
        var me = this;

        if (me.timer) {
            window.clearTimeout(me.timer);
            me.timer = null;
        }

        me.callParent();
    },

    updateTime: function () {
        var me = this, time = Ext.Date.format(new Date(), me.timeFormat),
            text = me.tpl.apply({ time: time }),date = Ext.Date.format(new Date(), 'Y年m月d日  H时i分');
       // if (me.lastText != text) {
            me.setText(time);
            me.setTooltip({title : '系统时间',text : date,target: 'desktop_trayclock',align: 'bl-tl'});
           // me.lastText = text;
        //}
        me.timer = Ext.Function.defer(me.updateTime, 10000, me);
    }
});
