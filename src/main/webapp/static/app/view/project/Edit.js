Ext.define('TR.view.project.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.projectedit',
    title: 'Projekt - Bearbeiten',
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
                        name: 'description',
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