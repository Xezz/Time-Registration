Ext.define('TR.view.timespan.Create', {
    extend: 'Ext.window.Window',
    alias: 'widget.timespancreate',
    title: 'Zeitspanne - Erstellen',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'textfield',
                        name: 'durationInMinutes',
                        fieldLabel: 'Dauer (m)'
                    }, {
                        xtype: 'datefield',
                        name: 'startTime',
                        format: 'd.m.Y',
                        value: new Date(),
                        fieldLabel: 'Startzeitpunkt'
                        // If I would be able to pack those two together, then use:
                        //submitFormat:
                    }, {
                        xtype: 'timefield',
                        // if 2 fields have the same name, they are returned as an array eg: startTime[]
                        name: 'startTime',
                        format: 'H:i',
                        value: new Date(),
                        fieldLabel: 'Startuhrzeit'
                    }, {
                       // Select a Customer this belongs to
                       xtype: 'combobox',
                       name: 'projectId',
                       fieldLabel: 'Projekt',
                       displayField: 'name',
                       valueField: 'projectId',
                       queryMode: 'local',
                       forceSelection: true,
                       store: 'Projects'
                   }, {
                       // Select a Customer this belongs to
                       xtype: 'combobox',
                       name: 'coworkerId',
                       fieldLabel: 'Mitarbeiter',
                       displayField: 'lastName',
                       valueField: 'coworkerId',
                       queryMode: 'local',
                       forceSelection: true,
                       value: 1,
                       store: 'Coworkers'
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