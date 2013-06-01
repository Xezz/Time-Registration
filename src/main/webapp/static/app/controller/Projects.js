Ext.define('TR.controller.Projects', {
    extend: 'Ext.app.Controller',
    stores: ['Projects'],
    model: ['Project'],
    views: ['project.List',
            'project.Create',
            'project.Edit'],
    // Three actions to handle?
    init: function() {
        // Listeners, Check also Component Query on ExtJs API docs
        this.control({
            // Event happened on project edit, button with action save
            'projectedit button[action=save]' : {
                click: this.updateProject
            },
            'projectcreate button[action=save]' : {
                click: this.persistProject
            },
            'projectlist button[action=add]' : {
                click: this.openNewProjectForm
            }
        });

    },

    openNewProjectForm: function() {
        console.log('openNewProjectForm clicked');
        var view = Ext.widget('projectcreate');
    },

    updateProject: function(button) {
        // We got the button that has fired the event we observed (maybe listened?)
        // get the required objects. window is the parent of the button, form is the
        // child of window and has the methods to get the underlying record and the forms values
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();
        // update the record with the new values
        record.setValues(values);
        // close the dialog
        win.close();
        // Tell the store to sync
        this.getProjectsStore().sync();
    },
    // TODO: Refactor to a function(button, isNew);
    persistProject: function(button) {
        // Persist project
        var win = button.up('window'),
            form = win.down('form'),
            values = form.getValues(),
            record = Ext.create('TR.model.Project', values);

        win.close();
        this.getProjectsStore().insert(0, record);
        this.getProjectsStore().sync();


    }


});