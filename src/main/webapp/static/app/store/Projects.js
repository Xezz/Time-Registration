Ext.define('TR.store.Projects', {
    extend: 'Ext.data.Store',
    model: 'TR.model.Project',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: '/coworker',
        reader: 'json',
        // TODO: Reevaluate this, might be useful to append the id as in /coworker/123
        appendId: false,
        // Enable Caching, since it seems to break requests..
        // TODO: Check if you can split this up, tho why add a parameter IF you want to cache, .....
        noCache: false,
        // Set correct headers, we accept json and we send json
        headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
        // No paging
        pageParam: undefined,
        // since we don't page we don't need to tell at which index we want to start
        startParam: undefined,
        // since we don't page we don't need to tell how many items we want to get
        limitParam: undefined
    }
});