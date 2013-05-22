Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'TR',

    appFolder: 'app',
    controllers: [
      'Coworkers',
      'Customers',
      'Projects'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [
                {
                    xtype: 'coworkerlist',
                    region: 'north',
                    height: 250,
                    split: true

                }, {
                    xtype: 'customerlist',
                    region: 'center'
                }, {
                    xtype: 'projectlist',
                    region: 'west',
                    width: 120,
                    split: true
                }
            ]
        });
    }
});