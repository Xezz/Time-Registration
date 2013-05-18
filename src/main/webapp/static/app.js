Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'TR',

    appFolder: 'app',
    controllers: [
      'Coworkers'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                xtype: 'coworkerlist',
                }
            ]
        });
    }
});