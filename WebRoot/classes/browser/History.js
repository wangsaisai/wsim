
Ext.define('Leetop.browser.History', {
    extend: 'Ext.data.Model',
    fields: [
       { name : 'id'},
       { name : 'url' },
       { name : 'date',  type: 'date', dateFormat: 'timestamp'},
       { name : 'title'}
    ]
});
