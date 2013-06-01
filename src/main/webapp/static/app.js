Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'TR',

    appFolder: 'app',
    controllers: [
      'Coworkers',
      'Customers',
      'Projects',
      'TimeSpans'
    ],
    //requires: ['TR.ux.form.DateTimePicker'],

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
                    width: 800,
                    split: true
                }, {
                    xtype: 'timespanlist',
                    region: 'south',
                    height: 250,
                    split: true
                }
            ]
        });
    }
});