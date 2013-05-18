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

    }
});
