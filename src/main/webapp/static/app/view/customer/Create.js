Ext.define('TR.view.customer.Create', {
    extend: 'Ext.window.Window',
    alias: 'widget.customerCreate',

    title: 'Customer - Create',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'textfield',
                        name: 'name',
                        fieldLabel: 'Name'
                    }, {
                        xtype: 'textarea',
                        name: 'customerInfo',
                        fieldLabel: 'Beschreibung'
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Speichern',
                action: 'save'
            }, {
                text: 'Abbrechen',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});