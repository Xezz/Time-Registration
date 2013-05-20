Ext.define('TR.view.project.List', {
    // Subclass Grid->Panel
    extend: 'Ext.grid.panel',
    // make this widget be callable by the name 'projectlist'
    alias: 'widget.projectlist',
    // Title to be shown by the Component
    title: 'Projekte - Liste',
    // The Store to be used
    store: 'Projects',

    // The method that is called by the container when this widget is loaded
    initComponent: function() {
        // This is a grid, so it has columns. Let's set them up
        // So we decide what we display of the model we get from the Store
        // header is the descriptive text at the top
        // dataIndex is the JSON fieldname
        // renderer is a function to be called to transform the data into a displayvalue
        // width, flex and so on describe how to size the rows
        this.columns = [
            {header: 'ID', dataIndex: 'projectID', width: 80},
            {header: 'Name', dataIndex: 'name', flex: 1},
            {header: 'Beschreibung', dataIndex: 'description', width 280},
            {header: 'Erstellt am', dataIndex: 'creationDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')},
            {header: 'Bearbeitet am', dataIndex: 'lastUpdatedDate', width: 180, renderer: Ext.util.Format.dateRenderer('d.M.Y H:i')}
        ]
    }
});