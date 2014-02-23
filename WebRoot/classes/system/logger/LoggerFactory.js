Ext.define('Leetop.system.logger.LoggerFactory',{
	mixins: {
	    observable: 'Ext.util.Observable'
	},
	
	requires: [
        'Leetop.system.logger.Logger'
    ],
	
    constructor: function (config) {
        var me = this;
        me.addEvents(
            'ready',
            'beforeunload'
        );

        me.mixins.observable.constructor.call(this, config);

        if (Ext.isReady) {
            Ext.Function.defer(me.init, 10, me);
        } else {
            Ext.onReady(me.init, me);
        }
    },
    
    init : Ext.emptyFunction,
    
    statics : {
    	getLogger : function(){
    		return Ext.create('Leetop.system.logger.Logger',arguments[0]);
    	}
    }

});