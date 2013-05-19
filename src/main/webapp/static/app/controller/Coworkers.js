Ext.define('TR.controller.Coworkers', {
    extend: 'Ext.app.Controller',
    stores: ['Coworkers'],
    models: ['Coworker'],
    views: [
        'coworker.List',
        'coworker.Edit'
    ],

    init: function() {
        this.control({
            'viewport > coworkerlist' : {
                itemdblclick: this.editCoworker
            },
            'coworkeredit button[action=save]': {
                click: this.updateCoworker
            }
        });
    },

    editCoworker: function(grid, record) {
        var view = Ext.widget('coworkeredit');

        view.down('form').loadRecord(record);
    },

    updateCoworker: function(button) {
        // Query component for parent of type window
        // Query the received window for a component it contains of type form
        // Receive the record from the form
        // Receive the values the user typed into the form (This is an array of field->value pairs)
        // Finally update the record with the new values, close the form window, and sync the Store
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

            record.set(values);
            win.close();
            this.getCoworkersStore().sync();
    }
});
