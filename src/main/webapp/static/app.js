Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'AM',

    appfolder: 'app',

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                xtype: 'panel',
                title: 'Users',
                html: 'List of users will go here'
                }
            ]
        });
    }
});