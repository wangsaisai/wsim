/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Leetop.module.AccordionWindow', {
    extend: 'Leetop.system.Module',

    requires: [
        'Ext.data.TreeStore',
        'Ext.layout.container.Accordion',
        'Ext.toolbar.Spacer',
        'Ext.tab.Panel',
        'Ext.tree.Panel'
    ],

    id:'acc-win',

    init : function(){
        this.launcher = {
            text: 'wsim',
            iconCls:'fit',
            handler : this.createWindow,
            scope: this
        };
    },

    createTree : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-tree',
            title: '我的好友',
            rootVisible:false,
            lines:false,
            autoScroll:true,
            useArrows : true,
            tools:[{
                type: 'refresh',
                handler: function(c, t) {
                    tree.setLoading(true, tree.body);
                    var root = tree.getRootNode();
                    root.collapseChildren(true, false);
                    Ext.Function.defer(function() { // mimic a server call
                        tree.setLoading(false);
                        root.expand(true, true);
                    }, 1000);
                }
            }],
            
            
            store : createStore(),
			
			listeners:{'itemdblclick':tree_event}
            

        });

        return tree;
    },
    createTrees : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-trees',
            title: '我的群组',
            rootVisible:false,
            lines:false,
            autoScroll:true,
           
            tools:[{
                type: 'refresh',
                handler: function(c, t) {
                    tree.setLoading(true, tree.body);
                    var root = tree.getRootNode();
                    root.collapseChildren(true, false);
                    Ext.Function.defer(function() { // mimic a server call
                        tree.setLoading(false);
                        root.expand(true, true);
                    }, 1000);
                }
            }],
            
            
            store : createStores(),
			
			listeners:{'itemdblclick':grouptree_event}
            

        });

        return tree;
    },
    createTreeperinfo : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-treeperinfo',
            title: '信息管理',
            rootVisible:false,
            lines:false,
            autoScroll:true,
            
           
            store :  Ext.create('Ext.data.TreeStore', {
                root: {
                    
                    expanded: true,
                    children:[{
                        text:'查找用户',
                        
                        leaf:true
                        
                    },{
                        text:'修改姓名',
                        leaf:true
                        
                    },
                    {
                        text:'修改密码',
                        leaf:true
                        
                    }]
                }
            }),
			
			listeners:{'itemdblclick':perinfo_event}
            

        });

        return tree;
    },
    createTreefriendman1 : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-treefriendman1',
            title: '好友管理aaa',
            rootVisible:false,
            columnWidth:'100%',
            hidden:true,
            lines:false,
            autoScroll:true,
           
           
            store :  Ext.create('Ext.data.TreeStore', {
                root: {
                    
                    expanded: true,
                    children:[{
                        text:'添加好友分组',
                        
                        leaf:true
                        
                    },
                    {
                        text:'显示所有好友',
                        leaf:true
                        
                    },
                    {
                        text:'未处理的好友请求',
                        leaf:true
                        
                    },
                    
                    {
                        text:'添加好友',
                        leaf:true
                        
                    }]
                }
            }),
           
			listeners:{'itemdblclick':friendman_event}
            

        });

        return tree;
    },
    createTreefriendman : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-treefriendman',
            title: '好友管理',
            rootVisible:false,
            columnWidth:'100%',
          
            lines:false,
            autoScroll:true,
           
           
            store :  Ext.create('Ext.data.TreeStore', {
                root: {
                    
                    expanded: true,
                    children:[{
                        text:'添加好友分组',
                        
                        leaf:true
                        
                    },
                    {
                        text:'显示所有好友',
                        leaf:true
                        
                    },
                  
                    {
                        text:'未处理的好友请求',
                        leaf:true
                        
                    },
                    {
                        text:'添加好友',
                        leaf:true
                        
                    }]
                }
            }),
           
			listeners:{'itemdblclick':friendman_event}
            

        });

        return tree;
    },
    createTreegroupman : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-treegroupman',
            title: '群组管理',
            rootVisible:false,
            lines:false,
            autoScroll:true,
            
           
            store :  Ext.create('Ext.data.TreeStore', {
                root: {
                    
                    expanded: true,
                    children:[{
                        text:'创建群',
                        
                        leaf:true
                        
                    },{
                        text:'搜索群',
                        leaf:true
                        
                    },
                    {
                        text:'查看加入的群',
                        leaf:true
                        
                    },
                    {
                        text:'查看未处理的群消息',
                        leaf:true
                        
                    }]
                }
            }),
			
			listeners:{'itemdblclick':groupman_event}
            

        });

        return tree;
    },
    createTreemessageman : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'im-treemessageman',
            title: '消息管理',
            rootVisible:false,
            lines:false,
            autoScroll:true,
           
          
            store : Ext.create('Ext.data.TreeStore', {
                root: {
                    
                    expanded: true,
                    children:[{
                        text:'查询好友消息',
                        width:300,
                        leaf:true
                        
                    },{
                        text:'查询群组消息',
                        leaf:true
                        
                    }]
                }
            }),
			
			listeners:{'itemdblclick':messageman_event}
            

        });

        return tree;
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('acc-win');

        if (!win) {
            win = desktop.createWindow({
                id: 'acc-win',
                title: myName,
                width: 250,
                height: 600,
                x:850,
                iconCls: 'fit',
                animCollapse: false,
                constrainHeader: true,
                bodyBorder: true,
                tbar: {
                    xtype: 'toolbar',
                    ui: 'plain',
                    items: [{
                        tooltip:{title:'Rich Tooltips', text:'Let your users know what they can do!'},
                        iconCls:'connect'
                    },
                    '-',
                    {
                        tooltip:'Add a new user',
                        iconCls:'user-add',
                        handler:function(){
                        	Ext.create('Ext.window.Window', {
                        	    title: '添加好友',
                        		
                        	    height: 250,
                        	    width: 300,
                        		shadow:true,
                        		
                        	    layout: 'card',
                        		items: [
                        	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
                                		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                                  		'src="https://'+serverIP+':8443/wsim/module/user/searchUser.jsp"' +  
                                  		'style="width: 100%; height: 100%;">' }
                        	       
                        	    ]
                        	    
                        	}).show();
                        	}
                        
                    },
                    ' ',
                    {
                        tooltip:'Remove the selected user',
                        iconCls:'user-delete',
                        handler:function(){
                        	Ext.create('Ext.window.Window', {
                        	    title: '删除好友',
                        		
                        	    height: 335,
                        	    width: 500,
                        		shadow:true,
                        		maximizable:true,
                        	    layout: 'card',
                        		items: [
                        	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
                                		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                                  		'src="https://'+serverIP+':8443/wsim/displayFriends"' +  
                                  		'style="width: 100%; height: 100%;">' }
                        	       
                        	    ]
                        	    
                        	}).show();
                        }
                    }]
                },

                layout: 'accordion',
                border: false,
                items: [ 
                         
                         
                        {
                          xtype: 'tabpanel',    
                          activeTab:0,  
                          title:'聊天面板',
                          items:[
                      this.createTree(),
                      
                      this.createTrees()
                      ]
                        },
                        {},
                        this.createTreefriendman1(),
                        this.createTreefriendman(),
                        
                        this.createTreeperinfo(),
                        
                        this.createTreegroupman(),
                        
                        this.createTreemessageman()
                     /*   {
                        	
                              title: '信息管理',
                              
                              
                          html:'<iframe id="user_module_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                  		'src="https://127.0.0.1:8443/wsim/module/user/userMain.jsp"' +  
                  		'style="width: 100%; height: 100%;">',
                            autoScroll:true
                           
                        },
                        {
                            title: '好友管理',
                            html : '<iframe id="friend_module_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                  		'src="https://'+serverIP+':8443/wsim/module/friend/friendMain.jsp"' +  
                  		'style="width: 100%; height: 100%;">'
                        },
                        {
                            title: '群组管理',
                            html : '<iframe id="group_module_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                  		'src="https://'+serverIP+':8443/wsim/module/group/groupMain.jsp"' +  
                  		'style="width: 100%; height: 100%;">'
                        },
                        {
                            title: '消息管理',
                            html : '<iframe id="message_module_iframe" scrolling="auto" ' + 
                    		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
                  		'src="https://'+serverIP+':8443/wsim/module/message/messageMain.jsp"' +  
                  		'style="width: 100%; height: 100%;">'                      }
                        
                        */
                  ]
            });
        }

        win.show();
        return win;
    }
});

var model = Ext.define("TreeModel", { //定义树节点数据模型
	extend : "Ext.data.Model",
	fields : [ {name : "id",type : "string"},
	           {name : "text",type : "string"}, 
	           {name : "iconCls",type : "string"}, 
	           {name : "leaf",type : "boolean"},
	           {name : "children", type : "jsonarray"}
	         ]
});

function createStore(){
    return  Ext.create('Ext.data.TreeStore', {
    	
    	defaultRootId : "1", //默认的根节点id
		   model : model,
		  
			   
			   proxy : {
				   type : "ajax", //获取方式
				   url : "/wsim/treeAction.action"
			   },
			   clearOnLoad : true
    })
}
function createStores(){
    return  Ext.create('Ext.data.TreeStore', {
    	
    	defaultRootId : "1", //默认的根节点id
		   model : model,
		  
			   
			   proxy : {
				   type : "ajax", //获取方式
				   url : "/wsim/groupTreeAction.action"
			   },
			   clearOnLoad : true
    })
}

function tree_event(node,event)  
{  

	if(event.data.leaf)
		{
	var id = event.data.id;
	
	var info = id.split('_');
	var receiver = info[1];
	var receiverName = event.data.text;
	
	
	createFriendChatWindow(receiver, receiverName);
		
	//createFriendChatWindow(receiver);
		}

};  



function grouptree_event(node,event)  
{  
	var id = event.data.id;
	
	var info = id.split('_');
	var groupId = info[1];
	var groupName = event.data.text;
	
	
	createGroupChatWindow(groupId, groupName);

};  
function perinfo_event(node,event)  
{  
	var textName = event.data.text;
	if(textName=="查找用户")
		{
	Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 250,
	    width: 300,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/user/searchUser.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	
		}
	else if(textName=="修改姓名")
		{Ext.create('Ext.window.Window', {
		    title: textName,
			
		    height: 150,
		    width: 200,
			shadow:true,
			
		    layout: 'card',
			items: [
		        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
	        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
	          		'src="https://'+serverIP+':8443/wsim/module/user/changeName.jsp"' +  
	          		'style="width: 100%; height: 100%;">' }
		       
		    ]
		    
		}).show();
		}
	else if(textName=="修改密码")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 220,
	    width: 230,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/user/changePassword.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	}else if(textName=="修改默认登录状态")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 150,
	    width: 200,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/user_getUserStatus"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	}
};  
function friendman_event(node,event)  
{  
	var textName = event.data.text;
	if(textName=="添加好友分组")
		{
	Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 150,
	    width: 200,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/friend/addFriendGroup.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	
		}
	else if(textName=="搜索好友")
		{Ext.create('Ext.window.Window', {
		    title: textName,
			
		    height: 150,
		    width: 200,
			shadow:true,
			
		    layout: 'card',
			items: [
		        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
	        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
	          		'src="https://'+serverIP+':8443/wsim/module/friend/searchFriend.jsp"' +  
	          		'style="width: 100%; height: 100%;">' }
		       
		    ]
		    
		}).show();
		}
	else if(textName=="未处理的好友请求")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 335,
	    width: 500,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/friendRequest_displayUndealFriendRequest"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();}
	else if(textName=="显示所有好友")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 335,
	    width: 500,
		shadow:true,
		maximizable:true,
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/displayFriends"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	
	}else if(textName=="添加好友")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 250,
	    width: 300,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/user/searchUser.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	}

};  
function groupman_event(node,event)  
{  
	var textName = event.data.text;
	if(textName=="创建群")
		{
	Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 300,
	    width: 400,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/group/createGroup.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	
		}
	else if(textName=="搜索群")
		{Ext.create('Ext.window.Window', {
		    title: textName,
			
		    height: 250,
		    width: 300,
			shadow:true,
			
		    layout: 'card',
			items: [
		        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
	        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
	          		'src="https://'+serverIP+':8443/wsim/module/group/searchGroup.jsp"' +  
	          		'style="width: 100%; height: 100%;">' }
		       
		    ]
		    
		}).show();
		}
	else if(textName=="查看加入的群")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 400,
	    width: 530,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/groupUser_listGroupOfUser"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	}else if(textName=="查看未处理的群消息")
	{Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 350,
	    width: 500,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/groupRequest_displayUndealGroupRequest"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	}
};  
function messageman_event(node,event)  
{  
	
	var textName = event.data.text;
	if(textName=="查询好友消息")
		{
	Ext.create('Ext.window.Window', {
	    title: textName,
		
	    height: 500,
	    width: 650,
		shadow:true,
		
	    layout: 'card',
		items: [
	        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
          		'src="https://'+serverIP+':8443/wsim/module/message/searchFrame.jsp"' +  
          		'style="width: 100%; height: 100%;">' }
	       
	    ]
	    
	}).show();
	
		}
	else if(textName=="查询群组消息")
		{Ext.create('Ext.window.Window', {
		    title: textName,
			
		    height: 500,
		    width: 650,
			shadow:true,
			
		    layout: 'card',
			items: [
		        { html: '<iframe id="message_module_iframe" scrolling="auto" ' + 
	        		'frameborder="no" hidefocus="" allowtransparency="true" ' + 
	          		'src="https://'+serverIP+':8443/wsim/module/groupMessage/searchGroupMessage.jsp"' +  
	          		'style="width: 100%; height: 100%;">' }
		       
		    ]
		    
		}).show();
		}
};  
