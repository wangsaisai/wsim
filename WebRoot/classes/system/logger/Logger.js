Ext.define('Leetop.system.logger.Logger',{
	
	mixins: {
	    observable: 'Ext.util.Observable'
	},
	
    constructor: function (config) {
        var me = this;
        
        me.mixins.observable.constructor.call(this, config);
        
        me.targetClass = config.$className;
        
        me.template = '{date}&nbsp;&nbsp;'+ me.targetClass + '&nbsp;&nbsp;[{type}]' + '&nbsp;&nbsp;{text}';

        if (Ext.isReady) {
            Ext.Function.defer(me.init, 10, me);
        } else {
            Ext.onReady(me.init, me);
        }
    },
    
    init : Ext.emptyFunction,
    
    type : {
    	INFO : 'INFO',
    	WARN : 'WARN',
    	ERROR : 'ERROR',
    	DEBUG : 'DEBUG'
    },
    
    icon : {
    	INFO : Ext.Msg.INFO,
    	WARN : Ext.Msg.WARNING,
    	ERROR : Ext.Msg.ERROR,
    	DEBUG : Ext.Msg.INFO
    },
    
    showMsgBox : function(text,icon){
		Ext.Msg.show({
			title : '调试信息',
			msg : text + '		',
			icon : icon,
			buttons : Ext.Msg.OK
		});

    },
    
    init : Ext.emptyFunction,
    
    info : function(text,show){
    	var me = this;
    	if(show){
    		me.showMsgBox(text,me.icon[me.type.INFO]);
    	}
    	Leetop.Console.updateConsole(me.format(me.type.INFO,text));
    	
    },
    
    error : function(text,show){
    	var me = this;
    	Leetop.Console.output(me.format(me.type.ERROR,text));
    	if(show){
    		me.showMsgBox(text,me.icon[me.type.ERROR]);
    	}
    },
    
    warn : function(text,show){
    	var me = this;
    	Leetop.Console.updateConsole(me.format(me.type.WARN,text));
    	if(show){
    		me.showMsgBox(text,me.icon[me.type.WARN]);
    	}
    },
    
    debug : function(text,show){
    	var me = this;
    	Leetop.Console.println(me.format(me.type.DEBUG,text));
    	if(show){
    		me.showMsgBox(text,me.icon[me.type.DEBUG]);
    	}
    },
    
   	format : function(type,text){
   		var me = this;
   		return me.template.replace('{type}',type).replace('{text}',text).replace('{date}',Ext.Date.format(new Date(), 'Y-m-d H:i:sO'));
   	}
    
});