Ext.define('TR.view.project.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.projectlist',
    title: 'Projekte - Liste',
    store: 'Projects',

    initComponent: function() {

        this.columns = [
            {header: 'ID', dataIndex: 'projectId', width: 80},
            {header: 'Name', dataIndex: 'name', flex: 1},
            {header: 'Beschreibung', dataIndex: 'description', width: 280},
            {header: 'Erstellt am', dataIndex: 'creationDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')},
            {header: 'Bearbeitet am', dataIndex: 'lastUpdatedDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')}
        ];

        this.callParent(arguments);
    },

    dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                {
                    xytpe: 'button',
                    text: 'Neuer Eintrag',
                    action: 'add'
                }
            ]
        }
    ]

});