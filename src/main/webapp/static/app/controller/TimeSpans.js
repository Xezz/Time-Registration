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
        view.down('form').loadRecord(record);
    },

    updateTimeSpan: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            formatDay = 'd.m.Y',
            formatTime = 'H:i',
            formatDate = formatDay + ' ' + formatTime,

            record = form.getRecord(),
            values = form.getValues(),
            valStartDayAndTime = values.startTime[0] + ' ' + values.startTime[1],
            startDayTime = Ext.Date.parse(valStartDayAndTime, formatDate);

        // get the Date as timestamp
        values.startTime = Ext.Date.format(startDayTime, 'time');
        record.set(values);
        win.close();
        this.getTimeSpansStore().sync();
    },

    saveTimeSpan: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            formatDay = 'd.m.Y',
            formatTime = 'H:i',
            formatDate = formatDay + ' ' + formatTime,
            values = form.getValues(),
            valStartDayAndTime = values.startTime[0] + ' ' + values.startTime[1],
            startDayTime = Ext.Date.parse(valStartDayAndTime, formatDate);

        values.startTime = Ext.Date.format(startDayTime, 'time');
        var record = Ext.create('TR.model.TimeSpan', values);


        win.close();
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