/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Leetop.desktop.StartMenu', {
    extend: 'Ext.panel.Panel',

    requires: [
        'Ext.menu.Menu',
        'Ext.toolbar.Toolbar'
    ],

    ariaRole: 'menu',

    cls: 'x-menu ux-start-menu',

    defaultAlign: 'bl-tl',

    iconCls: 'user',

    floating: true,

    shadow: true,

    width: 300,
    
    frame : true,
    
    maxHeight : 450,
    
    minHeight : 300,

    initComponent: function() {
        var me = this, menu = me.menu;
		
        me.menu = new Ext.menu.Menu({
            cls: 'ux-start-menu-body',
            border: false,
            floating: false,
            autoScroll : true,
            items: menu,
            listeners : {
            	add : me.onMenuItemAdd,
            	scope : me
            }
        });
        me.menu.layout.align = 'stretch';
		
        me.items = [{
            height: 300,
            title : me.user,
            iconCls : 'user',
            layout: 'fit',
            //autoHeight : true,
            items : [me.menu]
        }];

        Ext.menu.Manager.register(me);
        me.callParent();
        // TODO - relay menu events

        me.toolbar = new Ext.toolbar.Toolbar(Ext.apply({
            dock: 'right',
            cls: 'ux-start-menu-toolbar',
            vertical: true,
            width: 100
        }, me.toolConfig));

        me.toolbar.layout.align = 'stretch';
        me.addDocked(me.toolbar);

        delete me.toolItems;

        me.on('deactivate', function () {
            me.hide();
        });
    },
    
    afterLayout: function () {
        var me = this;
        me.callParent();
        me.el.on('contextmenu', function(e){
        	e.stopEvent();
        }, me);
        me.menu.el.on('contextmenu', me.onStartMenuContextMenu, me);
    },
    
    onStartMenuContextMenu : function(e){
    	var me = this, t = e.getTarget(), item = me.getMenuItemFromEl(t);
    	if (item) {
            e.stopEvent();
            //me.startContextMenu.theItem = item;
            //me.startContextMenu.showAt(e.getXY());
        }
    },

    getMenuItemFromEl: function (el) {
        var c = this.menu.getChildByElement(el);
        return c || null;
    },

    addMenuItem: function() {
        var cmp = this.menu;
        cmp.add.apply(cmp, arguments);
    },
    
    onMenuItemAdd : function(bar,item){
    	var me = this;
    	Ext.apply(item,{ 
            tooltip: { text: item.text, align: 'bl-tl' },
            handler: function(){
            	me.app.createWindow(item.module,item.text);
            }
        });
    },

    addToolItem: function() {
        var cmp = this.toolbar;
        cmp.add.apply(cmp, arguments);
    },

    showBy: function(cmp, pos, off) {
        var me = this;

        if (me.floating && cmp) {
            me.layout.autoSize = true;
            me.show();

            // Component or Element
            cmp = cmp.el || cmp;

            // Convert absolute to floatParent-relative coordinates if necessary.
            var xy = me.el.getAlignToXY(cmp, pos || me.defaultAlign, off);
            if (me.floatParent) {
                var r = me.floatParent.getTargetEl().getViewRegion();
                xy[0] -= r.x;
                xy[1] -= r.y;
            }
            me.showAt(xy);
            me.doConstrain();
        }
        return me;
    }
}); // StartMenu
