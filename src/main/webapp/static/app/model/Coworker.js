Ext.define('TR.model.Coworker', {
    extend: 'Ext.data.Model',
    fields: [
                {name: 'coworkerId', type: 'long'},
                {name: 'firstName', type:'string'},
                {name: 'lastName', type: 'string'},
                {name: 'creationDate', type: 'date', dateFormat: 'time'},
                {name: 'lastUpdatedDate', type: 'date', dateFormat: 'time'}
            ]
});