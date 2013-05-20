Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'TR',

    appFolder: 'app',
    controllers: [
      'Coworkers',
      'Customers'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [
                {
                    xtype: 'coworkerlist',
                    region: 'north',
                    height: 250

                }, {
                    xtype: 'customerlist',
                    region: 'center'
                }
            ]
        });
    }
});