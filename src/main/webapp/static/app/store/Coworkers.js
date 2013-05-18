Ext.define('TR.store.Coworkers', {
    extend: 'Ext.data.Store',
    model: 'TR.model.Coworker',
    data: [
        {firstName: 'Bastian', lastName: 'Koch'},
        {firstName: 'Werner', lastName: 'Metz'}
    ]
});