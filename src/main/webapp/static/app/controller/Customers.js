Ext.define('TR.controller.Customers', {
    extend: 'Ext.app.Controller',
    stores: ['Customers'],
    model: ['Customer'],
    views: ['customer.List', 'customer.Edit', 'customer.Create'],

    function init() {
        this.control({
            'viewport > customerlist' : {
                // do stuff here
            },
            'customeredit button[action=save]' : {
                click: this.saveCustomer
            },
            'customercreate button[action=save]' : {
                click: this.createCustomer
            }
        });
    },
    // TODO: Finish ExtJS after the whole RESTful service is done
    saveCustomer: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            values = form.getValues(),
            record = new Customer();


        record.set(values);
        win.close();
        this.getCustomerStore().insert(0, record);
        this.getCustomerStore().sync();
    },

    createCustomer: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        win.close();
        this.getCustomerStore().sync();
    }

});