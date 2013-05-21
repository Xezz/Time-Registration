Ext.define('TR.view.project.Create', {
    // Form seems to be a window?
    extend: 'Ext.window.Window',
    alias: 'widget.projectcreate',
    title: 'Projekt - Erstellen',
    // Fill the dialog
    layout: 'fit',
    // Display once it's created. It's a dialog after all...
    autoShow: true,

    // method that gets called on creation to create all components
    initComponent: function() {
        // Set a collection of components for this widget
        this.items = [
            {
                // We only have a form with children and buttons, which seem to be their own field
                xtype: 'form',
                items: [
                    {
                        // Single line text input
                        xtype: 'textfield',
                        // Referenced by
                        name: 'name',
                        // Descriptive label for this input type
                        label: 'Name'
                    }, {
                        // Multi line text input
                        xtype: 'textarea',
                        name: 'description',
                        label: 'Beschreibung'
                    }
                ]
            }
        ];
        // WHY THE FUCK is there a semicolon after this array? Going by official example, so not gonna fool with it

        // Now the Buttons this "window" has
        // basically this just attaches buttons to the footer. convenient
        this.buttons = [
            {
                // The save button
                // Text shown by the button
                text: 'Speichern',
                // "event action" aka button[action=save]
                action: 'save'
            }, {
                // The cancel button
                // Text to show
                text: 'Abbrechen',
                // FIXME: Find a doc that explains the reasoning of the scope: / handler: values
                scope: this,
                handler: this.close
            }
        ];
        // SAME WTF as above, why a semicolon after these. JS or Framework specific?
        // call super? suppose so
        // ... super call has to be inside initComponents
        this.callParent(arguments);
    }
});