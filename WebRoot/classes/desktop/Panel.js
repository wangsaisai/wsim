Ext.define('Leetop.desktop.Panel', {
    extend: 'Ext.panel.Panel',

    alias: 'widget.desktop',

    uses: [ 'Leetop.desktop.View',
	        'Leetop.desktop.TaskBar',
	        'Leetop.desktop.Wallpaper',
	        'Leetop.desktop.FitAllLayout'
   	],

    activeWindowCls: 'ux-desktop-active-win',
    inactiveWindowCls: 'ux-desktop-inactive-win',
    lastActiveWindow: null,

    border: false,
    html: '&#160;',
    layout: 'fitall',

    xTickSize: 1,
    yTickSize: 1,

    app: null,

    shortcuts: null,
    
    /*ghostTpl: [
			//'<div class="ux-desktop-shortcut-column">',          
			'<tpl for=".">',
			        '<div class="ux-desktop-shortcut" id="{name}-shortcut">',
			            '<div class="ux-desktop-shortcut-icon {iconCls}">',
			                '<img src="',Ext.BLANK_IMAGE_URL,'" title="{name}">',
			            '</div>',
			            '<div class="ux-desktop-shortcut-text">',
			            	'<div class="ux-desktop-shortcut-text-inner">{name}</div>',
			            '</div>',
			            //'<span class="ux-desktop-shortcut-text">{name}</span>',
			        '</div>',
			        '<tpl if="xindex % 9 == 0">',
			        	'</div><div class="ux-desktop-shortcut-column">',
			        '</tpl>',   
			'</tpl>'
			//'</div>'
    ],*/


    taskbarConfig: null,

    windowMenu: null,

    initComponent: function () {
    	
        var me = this;

        Ext.MessageBox.updateProgress(0.40,'40%','<br/>正在初始化系统菜单...');
		me.initContextMenu();
        
        Ext.MessageBox.updateProgress(0.65,'65%','<br/>正在初始化任务栏...');
        me.bbar = me.taskbar = new Leetop.desktop.TaskBar(me.taskbarConfig);
        Ext.apply(me.taskbar,{
        	windowMenu : me.windowMenu,
        	quickStartMenu : me.quickStartMenu
        });
        //me.taskbar.startMenu.startContextMenu = me.startContextMenu;

        me.windows = new Ext.util.MixedCollection();
        
        me.items = [
            { xtype: 'wallpaper', id: me.id+'_wallpaper' }/*,
            me.createView()*/
        ];
        
        me.callParent();

        var wallpaper = me.wallpaper;
        me.wallpaper = me.items.getAt(0);
        if (wallpaper) {
            me.setWallpaper(wallpaper, me.wallpaperStretch);
        }
        
        Ext.EventManager.onWindowResize(me.refreshView, this, {delay:100});
    },
    
    initContextMenu : function(){
    	var me = this;
    	me.windowMenu = new Ext.menu.Menu(me.createWindowMenu());
        me.quickStartMenu = new Ext.menu.Menu(me.createQuicStartMenu());
        me.startContextMenu = new Ext.menu.Menu(me.createStartContextMenu());
        me.contextMenu = new Ext.menu.Menu(me.createDesktopMenu());
        me.windowHeaderContextMenu = new Ext.menu.Menu(me.createWindowHeaderContextMenu());
    },
    
    afterRender: function () {
        var me = this;
        me.callParent();
        me.el.on('contextmenu', me.onDesktopContextMenu, me);
    },
    
    initView : function(){
    	var me = this;
    	Ext.MessageBox.updateProgress(0.8,'80%','<br/>正在初始化桌面图标...');
    	me.view = Ext.create('Leetop.desktop.View',{
            desktop : me,
            store : me.shortcuts,
            app : me.app
        });
        me.add(me.view);
        me.view.init();
    },
    
    refreshView : function(){
    	var me = this;
		me.view.refresh();
    },
    
    createDesktopMenu: function () {
        var me = this, ret = {
            items: me.contextMenuItems || []
        };

		ret.items.push([{text : '查看',
	       					menu : [{text : '图标'
	       							},{
	       							  text : '卡片'
	       						   }]
	       					},
	       				  {text: '窗口布局',
	       				   iconCls : 'icon-layout-shortcut',
		    			   menu:[{ text: '平铺', 
		    			   		 iconCls : 'icon-title-shortcut',
		    				 	 handler: me.tileWindows, 
		    				 	 scope: me, 
		    				 	 minWindows: 1 
		    				 },{ text: '重叠', 
		    				 	 iconCls : 'icon-cascade-shortcut',
		    					 handler: me.cascadeWindows, 
		    				     scope: me, 
		    				     minWindows: 1 
		    				 }
		    			   ]},
		    			   {   text: '排序方式',
		    			   	   iconCls : 'icon-sort-shortcut',
		        			   menu:[{ text: '名称', 
		      				 	 handler: function(){
		      				 		 me.view.sortShortCut('name');
		      				 	 }, 
		      				 	 scope: me
		      				 },{ text: '时间', 
		      					handler: function(){
		     				 		 me.view.sortShortCut('iconCls');
		     				 	 },
		      				     scope: me
		      				 },{ text: '类型', 
		      					 handler: function(){
		      						me.view.sortShortCut('module');
		      					 },
		      				     scope: me
		      				 },{ text: '位置', 
		      					 handler: function(){
		      						me.view.sortShortCut('index');
		      					 },
		      				     scope: me
		      				 }]
		      				}]);
		ret.items.push({text : '刷新',
						iconCls : 'icon-refresh',
						menu : [{
		    				text : '桌面',
		    				handler : me.refreshDesktop,
		    				scope: me
		    			},{
		    				text : '页面',
		    				handler : function(){
		    					window.location.reload();
		    				},
		    				scope: me
		    			}]}
            );
		ret.items.push('-'); 
        ret.items.push([{text: '新建',
			        	 menu:[{   text: '文件夹',
			        	 		   iconCls : 'icon-folder',
			        	 		   handler : function(){
			        	 		   		me.view.store.add({
			        	 		   			name : '新建文件夹',
			        	 		   			iconCls : 'folder-shortcut',
			        	 		   			handler : function(){
			        	 		   				Ext.Msg.alert('这是一个文件夹!');
			        	 		   			}
			        	 		   		});
			        	 		   },
		  				 	  	   scope: me
			      			   },{ text: '文档',
			      			       iconCls : 'icon-word',
			      				   scope: me
			      			   },{ text: '表格', 
			      			       iconCls : 'icon-excel',
			      				   scope: me
			      			   },{ text: '演示文稿', 
			      			       iconCls : 'icon-ppt',
			      				   scope: me
			      			}]
			      		},{   text: '添加',
			        		  menu:[{ text: '应用程序',
			        		  		  iconCls : 'icon-app',
			      				 	  scope: me
					      			},{ text: '网址', 	
					      				iconCls : 'icon-net-address',
					      				scope: me
					      			}]
			      		}]);
  		ret.items.push('-');		
        ret.items.push({ text: '个性化', 
        				 iconCls : 'icon-personality',
         				 handler: me.app.onSettings, 
         				 scope: me.app 
         			}
            );
         
        return ret;
    },
    createWindowHeaderContextMenu: function () {
        var me = this;
        return {
        	items : [{
        				text : '还&nbsp;&nbsp;&nbsp;原',
        				handler : function(){
        					me.restoreWindow(me.getActiveWindow());
        				}
        			},{
        				text : '最小化',
        				handler : function(){
        					me.minimizeWindow(me.getActiveWindow());
        					me.updateActiveWindow();
        				}
        			},{
        				text : '最大化',
        				handler : function(){
        					me.getActiveWindow().maximize(); 
        				}
        			},'-',{
        				text : '关&nbsp;&nbsp;&nbsp;闭',
        				handler : function(){
        					me.getActiveWindow().destroy(); 
        				}
        			}]
        };
    },

    createWindowMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                { text: '还&nbsp;&nbsp;&nbsp;原', 
                  handler: me.onWindowMenuRestore, 
                  scope: me },
                { text: '最小化', 
                  handler: me.onWindowMenuMinimize, 
                  scope: me },
                { text: '最大化', 
                  handler: me.onWindowMenuMaximize, 
                  scope: me 
                },'-',
                { text: '关&nbsp;&nbsp;&nbsp;&nbsp;闭', 
                  handler: me.onWindowMenuClose, 
                  scope: me 
                }
            ],
            listeners: {
                beforeshow: me.onWindowMenuBeforeShow,
                hide: me.onWindowMenuHide,
                scope: me
            }
        };
    },
    
    createQuicStartMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                /*{ text: '打开', 
                  handler: function(){
                	  me.onQuickStartBtnClick(me.quickStartMenu.theBtn);
                  }, 
                  scope: me 
                },*/{ 
                   text: '从快速启动栏中移除', 
                   handler: function(){
                	   me.onQuickStartBtnRemove(me.quickStartMenu.theBtn);
                   },
                   scope: me 
                }
            ]
        };
    },
    
    createStartContextMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                { text: '打开', 
                  handler: function(){
                	  me.onQuickStartBtnClick(me.quickStartMenu.theBtn);
                  }, 
                  scope: me 
                },{ 
                   text: '移除', 
                   handler: function(){
                	   me.onQuickStartBtnRemove(me.quickStartMenu.theBtn);
                   },
                   scope: me 
                }
            ]
        };
    },

    //------------------------------------------------------
    // Event handler methods

    onQuickStartBtnClick : function(btn){
    	if(Ext.isFunction(btn.handler)){
    		Ext.Function.defer(btn.handler,1,btn.scope);
    	}
    },
    
    onQuickStartBtnRemove : function(btn){
    	var me = this;
    	me.taskbar.quickStart.remove(btn);
    	me.taskbar.quickStart.doLayout();
    },
    onDesktopContextMenu: function (e) {
        var me = this, menu = me.contextMenu;
        e.stopEvent();
        if (!menu.rendered) {
            menu.on('beforeshow', me.onDesktopMenuBeforeShow, me);
        }
        menu.showAt(e.getXY());
        menu.doConstrain(me);
    },

    onDesktopMenuBeforeShow: function (menu) {
        var me = this, count = me.windows.getCount();
        menu.items.get(1).menu.items.each(function (item) {
            var min = item.minWindows || 0;
            item.setDisabled(count < min);
        });
    },

    onWindowHeaderContextMenu: function (e) {
        var me = this, menu = me.windowHeaderContextMenu;
        e.stopEvent();
        win.toFront();
        menu.showAt(e.getXY());
        menu.doConstrain(me);
    },

    onWindowAfterrender : function(w){
    	var me = this;
    	w.header.el.on('contextmenu',function(e){
    		var me = this, menu = me.windowHeaderContextMenu;
    		if (!menu.rendered) {
	            menu.on('beforeshow', me.onWindowHeaderContextMenuBeforeShow, me);
	        }
	        e.stopEvent();
	        w.toFront();
	        menu.showAt(e.getXY());
	        menu.doConstrain(me);
    	},me);
    },

    onWindowClose: function(win) {
        var me = this;
        me.windows.remove(win);
        me.taskbar.removeTaskButton(win.taskButton);
        me.updateActiveWindow();
    },

    //------------------------------------------------------
    // Window context menu handlers

    onWindowHeaderContextMenuBeforeShow: function (menu) {
        var me= this, win = me.getActiveWindow(),items = menu.items.items;
        items[0].setDisabled(win.maximized !== true); // Restore
        //items[1].setDisabled(win.minimized === true); // Minimize
        items[2].setDisabled(win.maximized === true || win.maximizable === false); // Maximize
    },
    
    onWindowMenuBeforeShow: function (menu) {
        var items = menu.items.items, win = menu.theWin;
        items[0].setDisabled(win.maximized !== true && win.hidden !== true); // Restore
        items[1].setDisabled(win.minimized === true); // Minimize
        items[2].setDisabled(win.maximized === true || win.hidden === true || !win.maximizable === true); // Maximize
    },

    onWindowMenuClose: function () {
        var me = this, win = me.windowMenu.theWin;

        win.close();
    },

    onWindowMenuHide: function (menu) {
        menu.theWin = null;
    },

    onWindowMenuMaximize: function () {
        var me = this, win = me.windowMenu.theWin;
        if(win.maximizable){
        	win.maximize();
        }
    },

    onWindowMenuMinimize: function () {
        var me = this, win = me.windowMenu.theWin;

        win.minimize();
    },

    onWindowMenuRestore: function () {
        var me = this, win = me.windowMenu.theWin;

        me.restoreWindow(win);
    },

    //------------------------------------------------------
    // Dynamic (re)configuration methods

    refreshDesktop : function(){
    	var me = this;
    	me.el.mask('正在刷新页面...');
    	me.view.store.loadData(me.app.shortcutsData);
    	me.view.store.sort('index', 'ASC');
    	me.view.refresh();
    	me.el.unmask();
    },
    
    getWallpaper: function () {
        return this.wallpaper.wallpaper;
    },

    setTickSize: function(xTickSize, yTickSize) {
        var me = this,
            xt = me.xTickSize = xTickSize,
            yt = me.yTickSize = (arguments.length > 1) ? yTickSize : xt;

        me.windows.each(function(win) {
            var dd = win.dd, resizer = win.resizer;
            dd.xTickSize = xt;
            dd.yTickSize = yt;
            resizer.widthIncrement = xt;
            resizer.heightIncrement = yt;
        });
    },

    setWallpaper: function (wallpaper, stretch) {
    	if(this.getWallpaper() !== wallpaper){
        	this.wallpaper.setWallpaper(wallpaper, stretch);
        }
        return this;
    },

    //------------------------------------------------------
    // Window management methods

    cascadeWindows: function() {
        var x = 0, y = 0,
            zmgr = this.getDesktopZIndexManager();

        zmgr.eachBottomUp(function(win) {
            if (win.isWindow && win.isVisible() && !win.maximized) {
                win.setPosition(x, y);
                x += 20;
                y += 20;
            }
        });
    },

    createWindow: function(config, cls) {
        var me = this, win, cfg = Ext.applyIf(config || {}, {
                stateful: false,
                isWindow: true,
                constrainHeader: true,
                minimizable: true,
                maximizable: true,
                iconCls : config.iconCls || 'icon-app'
            });
        cls = cls || Ext.window.Window;
        win = me.add(new cls(cfg));

        me.windows.add(win);
        win.taskButton = me.taskbar.addTaskButton(win);
        win.animateTarget = win.taskButton.el;

        win.on({
            activate: me.updateActiveWindow,
            beforeshow: me.updateActiveWindow,
            deactivate: me.updateActiveWindow,
            minimize: me.minimizeWindow,
            destroy: me.onWindowClose,
            afterrender : me.onWindowAfterrender,
            scope: me
        });

        win.on({
            afterrender: function () {
                win.dd.xTickSize = me.xTickSize;
                win.dd.yTickSize = me.yTickSize;

                if (win.resizer) {
                    win.resizer.widthIncrement = me.xTickSize;
                    win.resizer.heightIncrement = me.yTickSize;
                }
            },
            single: true
        });

        // replace normal window close w/fadeOut animation:
        win.doClose = function ()  {
            win.el.disableShadow();
            win.el.fadeOut({
                listeners: {
                    afteranimate: function () {
                        win.destroy();
                    }
                }
            });
        };

        return win;
    },

    getActiveWindow: function () {
        var win = null,
            zmgr = this.getDesktopZIndexManager();

        if (zmgr) {
            // We cannot rely on activate/deactive because that fires against non-Window
            // components in the stack.

            zmgr.eachTopDown(function (comp) {
                if (comp.isWindow && !comp.hidden) {
                    win = comp;
                    return false;
                }
                return true;
            });
        }

        return win;
    },

    getDesktopZIndexManager: function () {
        var windows = this.windows;
        // TODO - there has to be a better way to get this...
        return (windows.getCount() && windows.getAt(0).zIndexManager) || null;
    },

    getWindow: function(id) {
        return this.windows.get(id);
    },

    minimizeWindow: function(win) {
        win.minimized = true;
        win.hide();
    },

    restoreWindow: function (win) {
        if (win.isVisible()) {
            win.restore();
            win.toFront();
        } else {
            win.show();
        }
        return win;
    },

    tileWindows: function() {
        var me = this, availWidth = me.body.getWidth(true);
        var x = me.xTickSize, y = me.yTickSize, nextY = y;

        me.windows.each(function(win) {
            if (win.isVisible() && !win.maximized) {
                var w = win.el.getWidth();

                // Wrap to next row if we are not at the line start and this Window will
                // go off the end
                if (x > me.xTickSize && x + w > availWidth) {
                    x = me.xTickSize;
                    y = nextY;
                }

                win.setPosition(x, y);
                x += w + me.xTickSize;
                nextY = Math.max(nextY, y + win.el.getHeight() + me.yTickSize);
            }
        });
    },

    updateActiveWindow: function () {
        var me = this, activeWindow = me.getActiveWindow(), last = me.lastActiveWindow;
        if (activeWindow === last) {
            return;
        }
        if (last) {
            if (last.el.dom) {
                last.addCls(me.inactiveWindowCls);
                last.removeCls(me.activeWindowCls);
            }
            last.active = false;
        }

        me.lastActiveWindow = activeWindow;

        if (activeWindow) {
            activeWindow.addCls(me.activeWindowCls);
            activeWindow.removeCls(me.inactiveWindowCls);
            activeWindow.minimized = false;
            activeWindow.active = true;
        }

        me.taskbar.setActiveButton(activeWindow && activeWindow.taskButton);
    }
});
