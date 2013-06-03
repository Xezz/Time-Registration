Ext.define('TR.controller.TimeSpans', {
    extend: 'Ext.app.Controller',
    stores: ['TimeSpans'],
    models: ['TimeSpan'],
    views: [
        'timespan.List',
        'timespan.Create',
        'timespan.Edit'
        ],

    init: function() {
        this.control({
            'timespanlist': {
                itemdblclick: this.openEditTimeSpanForm
            },
            'timespanlist button[action=add]': {
                click: this.openNewTimeSpanForm
            },
            'timespanlist button[action=delete]': {
                click: this.deleteSelectedTimeSpan
            },
            'timespancreate button[action=save]': {
                click: this.saveTimeSpan
            },
            'timespanedit button[action=update]': {
                click: this.updateTimeSpan
            }
        });
    },

    openNewTimeSpanForm: function() {
        var view = Ext.widget('timespancreate');
    },

    openEditTimeSpanForm: function(grid, record) {
        var view = Ext.widget('timespanedit');
        //var dblStartTime = {record.startTime, recordStartTime};
        // Hax workaround to make an array instead of a single value, to suit both datepicker and timepicker ...
        //record.startTime = dblStartTime;
        console.log('Opening TimeSpan for editing');
        console.log(record);
        view.down('form').loadRecord(record);
    },

    updateTimeSpan: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        console.log(values);
        win.close();
        this.getTimeSpansStore().sync();
    },

    saveTimeSpan: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            values = form.getValues();

        var record = Ext.create('TR.model.TimeSpan', values);


        win.close();
        console.log('Saving a new TimeSpan:');
        console.log(values);
        this.getTimeSpansStore().insert(0, record);
        this.getTimeSpansStore().sync();
    },

    deleteSelectedTimeSpan: function(button) {
        var grid = button.up('grid'),
            record = grid.getSelectionModel();
        if (record !== null) {
            this.getTimeSpansStore().remove(record.getSelection());
            this.getTimeSpansStore().sync();
        }
    }
});