Ext.define('TR.view.coworker.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.coworkerlist',
    title: 'All Coworkers',
    store: 'Coworkers',

    initComponent: function() {

        this.columns = [
            {header: 'Vorname', dataIndex: 'firstName', flex: 1},
            {header: 'Nachname', dataIndex: 'lastName', flex: 1}
        ];
        this.callParent(arguments);
    }
});