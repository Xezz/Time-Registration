Ext.define('TR.view.customer.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.customerlist',
    title: 'Kunden - Liste',
    store: 'Customers',

    initComponent: function() {

        this.columns = [
            {header: 'ID', dataIndex: 'customerId', width: 80},
            {header: 'Name', dataIndex: 'name', flex: 1},
            {header: 'Beschreibung', dataIndex: 'customerInfo', width: 180},
            {header: 'Erstellt am', dataIndex: 'creationDate', flex: 1, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')},
            {header: 'Bearbeitet am', dataIndex: 'lastUpdatedDate', flex: 1, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')}
        ];
        this.callParent(arguments);
    }
});