Ext.define('TR.view.timespan.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.timespanedit',
    title: 'Zeitspanne - Bearbeiten',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'textfield',
                        name: 'firstName',
                        fieldLabel: 'Vorname'
                    }, {
                        xtype: 'textfield',
                        name: 'lastName',
                        fieldLabel: 'Nachname'
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Speichern',
                action: 'update'
            }, {
                text: 'Abbrechen',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});