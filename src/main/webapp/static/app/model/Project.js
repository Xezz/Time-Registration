Ext.define('TR.model.Project', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'projectID', type: 'long'},
        {name: 'name', type 'string'},
        {name: 'description', type: 'string'},
        {name: 'creationDate', type: 'date', dateFormat: 'time'},
        {name: 'lastUpdatedDate', type: 'date', dateFormat: 'time'}
    ]
});