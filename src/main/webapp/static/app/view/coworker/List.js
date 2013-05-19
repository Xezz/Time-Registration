Ext.define('TR.view.coworker.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.coworkerlist',
    title: 'All Coworkers',
    store: 'Coworkers',

    initComponent: function() {

        this.columns = [
            {header: 'ID', dataIndex: 'coworkerId', width: 80},
            {header: 'Vorname', dataIndex: 'firstName', flex: 1},
            {header: 'Nachname', dataIndex: 'lastName', flex: 1},
            {header: 'Erstellt am', dataIndex: 'creationDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')},
            {header: 'Bearbeitet am', dataIndex: 'lastUpdatedDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')}
        ];
        this.callParent(arguments);
    }
});