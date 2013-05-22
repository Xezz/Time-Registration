Ext.define('TR.store.Projects', {
    extend: 'Ext.data.Store',
    model: 'TR.model.Project',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'project',
        reader: 'json',
        appendId: false,
        noCache: false,
        headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
        pageParam: undefined,
        startParam: undefined,
        limitParam: undefined
    }
});