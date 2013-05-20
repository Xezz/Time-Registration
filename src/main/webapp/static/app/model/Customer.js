Ext.define('TR.model.Customer', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'customerId', type: 'long'},
        {name: 'name', type: 'string'},
        {name: 'customerInfo', type: 'string'},
        {name: 'creationDate', type: 'date', dateFormat: 'time'},
        {name: 'lastUpdatedDate', type: 'date', dateFormat: 'time'}
    ]
});