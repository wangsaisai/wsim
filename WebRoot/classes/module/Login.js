
Ext.define('Leetop.module.Login', {
    extend: 'Leetop.system.Module',

    requires: [
               'Ext.form.*',
               'Ext.Img',
               'Ext.tip.QuickTipManager'
    ],
    
    id:'login',
    windowId: 'login_win', 

    tipWidth: 160,
    tipHeight: 96,

    init : function(){
        this.launcher = {
            text: '登录',
            handler : this.createWindow,
            scope: this
        };
    },
    
    
    createForm : function() {
    	Ext.tip.QuickTipManager.init();
        var formPanel = Ext.widget('form', {
            renderTo: Ext.getBody(),
            frame: true,
            width: 350,
            bodyPadding: 10,
            bodyBorder: true,
            //title: 'Account Registration',

            defaults: {
                anchor: '100%'
            },
            fieldDefaults: {
                labelWidth: 110,
                labelAlign: 'left',
                msgTarget: 'none',
                invalidCls: '' //unset the invalidCls so individual fields do not get styled as invalid
            },

            /*
             * Listen for validity change on the entire form and update the combined error icon
             */
            listeners: {
                fieldvaliditychange: function() {
                    this.updateErrorState();
                },
                fielderrorchange: function() {
                    this.updateErrorState();
                }
            },

            updateErrorState: function() {
                var me = this,
                    errorCmp, fields, errors;

                if (me.hasBeenDirty || me.getForm().isDirty()) { //prevents showing global error when form first loads
                    errorCmp = me.down('#formErrorState');
                    fields = me.getForm().getFields();
                    errors = [];
                    fields.each(function(field) {
                        Ext.Array.forEach(field.getErrors(), function(error) {
                            errors.push({name: field.getFieldLabel(), error: error});
                        });
                    });
                    errorCmp.setErrors(errors);
                    me.hasBeenDirty = true;
                }
            },

            items: [{
                xtype: 'textfield',
                name: 'email',
                fieldLabel: 'Email',
                vtype: 'email',
                allowBlank: false
            }, {
                xtype: 'textfield',
                name: 'password',
                fieldLabel: 'Password',
                inputType: 'password',
                style: 'margin-top:15px',
                allowBlank: false,
                minLength: 1
            }],

            dockedItems: [{
                cls: Ext.baseCSSPrefix + 'dd-drop-ok',
                xtype: 'container',
                dock: 'bottom',
                layout: {
                    type: 'hbox',
                    align: 'middle'
                },
                padding: '10 10 5',

                items: [{
                    xtype: 'component',
                    id: 'formErrorState',
                    invalidCls: Ext.baseCSSPrefix + 'form-invalid-icon',
                    validCls: Ext.baseCSSPrefix + 'dd-drop-icon',
                    baseCls: 'form-error-state',
                    flex: 1,
//                    validText: 'Form is valid',
//                    invalidText: 'Form is invalid',
                    tipTpl: Ext.create('Ext.XTemplate', '<ul class="' + Ext.plainListCls + '"><tpl for="."><li><span class="field-name">{name}</span>: <span class="error">{error}</span></li></tpl></ul>'),

                    getTip: function() {
                        var tip = this.tip;
                        if (!tip) {
                            tip = this.tip = Ext.widget('tooltip', {
                                target: this.el,
                                title: 'Error Details:',
                                minWidth: 200,
                                autoHide: false,
                                anchor: 'top',
                                mouseOffset: [-11, -2],
                                closable: true,
                                constrainPosition: false,
                                cls: 'errors-tip'
                            });
                            tip.show();
                        }
                        return tip;
                    },

                    setErrors: function(errors) {
                        var me = this,
                            tip = me.getTip();

                        errors = Ext.Array.from(errors);

                        // Update CSS class and tooltip content
                        if (errors.length) {
                            me.addCls(me.invalidCls);
                            me.removeCls(me.validCls);
                            me.update(me.invalidText);
                            tip.setDisabled(false);
                            tip.update(me.tipTpl.apply(errors));
                        } else {
                            me.addCls(me.validCls);
                            me.removeCls(me.invalidCls);
                            me.update(me.validText);
                            tip.setDisabled(true);
                            tip.hide();
                        }
                    }
                }, {
                    xtype: 'button',
                    formBind: true,
                    disabled: true,
                    text: 'Login',
                    width: 140,
                    handler: function() {
                        var form = this.up('form').getForm();

                        // Normally we would submit the form to the server here and handle the response...
                        form.submit({
//                            clientValidation: true,
                        	waitTitle : '请稍后...',
     						waitMsg : '正在验证用户信息,请稍后...',
     						url: 'loginAjax.action',
    						method : 'post',
                            
                            failure: function(form, action) {
                           
//                            	createWindow();
//                            	alert("error");
                            	Ext.Msg.alert('登录出错',action.result.msg);
                            },
                            success: function(form, action) {	
                            	Ext.getCmp("login_win").close();
                            	userId = action.result.userId;
                      
                        //  	Ext.getCmp("acc-win").onload();
//                            	alert("success");
//                            	Ext.Msg.alert('登录结果',action.result); 
//                            	if(action.result.get("result")) {}
                            	openWSConn();
                            	
                            
                            }
                        });
                    }
                }]
            }]
        });
        
        return formPanel;
    },
    

    createWindow : function(){
        var me = this, desktop = me.app.getDesktop(),
            win = desktop.getWindow(me.windowId);

        if (!win) {
            win = desktop.createWindow({
                id: me.windowId,
                title: '登录',
                iconCls : me.app.createSmallIconCls('t-shortcut'),
                width: 300,
                height: 200,
                animCollapse: false,
                maximizable : true,
                border: false,
                layout: 'fit',
                items: [
                    this.createForm()
                ]
            });
        }

        win.show();
        //win.maximize();

        return win;
    }
});
