$(function () {
    $('.multi_input').each(function (index) {
        $(this).attr('readonly', true);
    });

    $(document).on('click', '.add_element_multi', function () {
        var parent = $(this).parent();
        addElement('', parent);
    });

    $(document).on('click', '.remove_element_multi', function () {
        $(this).parent().remove();
    });

    $(document).on('keypress', '.input-multi', function (e) {
        if (e.which == 13) {
            var parent = $(this).parent().parent();
            addElement('', parent);
        }
    });

    $('.multi_input').on('click', function () {
        var elem = $(this);
        var values = elem.val().split(';');
        var tmpl = '<div></div>';
        $("<div class='dialog'><button class='btn-success add_element_multi'>Add</button></button><p>" + tmpl + "</p></div>").dialog({
            resizable: true,
            height: 400,
            width: 650,
            modal: true,
            open: function () {
                var div = $(this);
                $(this).attr('type', elem.attr('data-type'));
                $(values).each(function (i, v) {
                    addElement(v, div);
                });
            },
            buttons: [{
                text: "Ok",
                class: 'btn-success',
                click: function () {
                    var isValid = true;
                    $('.input-multi').each(function () {
                        if ($(this).attr('error')) {
                            isValid = false;
                        }
                    });

                    if (isValid) {
                        var vals_str = $(this).find('input.from');
                        var input_arr = [];
                        $(vals_str).each(function (key, value) {
                            var item = $(this).val();
                            var item2 = $('input.to').eq(key).val();
                            if (item && item2!='' && item2!=undefined) {
                                input_arr.push(item + '-' + item2);
                            }
                            else
                                input_arr.push(item);
                        });

                        elem.val(input_arr.join(';'));
                        $(this).dialog("close");
                    }
                }
            }, {
                text: 'Cancel',
                class: 'btn-danger',
                click: function () {
                    $(this).dialog("close");
                }
            }]
        });
    });
});

function addElement(val, main_div) {
    var current_type = main_div.attr('type');
    var ip = {
        mask: "9{1,3}.9{1,3}.9{1,3}.9{1,3}",
        greedy: false,
        oncomplete: function () {
            $(this).removeAttr('error');
            $(this).css('border-color', '');
        },
        onincomplete: function () {
            $(this).attr('error', 1);
            $(this).css('border-color', 'red');
        },
        showMaskOnFocus: true,
        showMaskOnHover: true,
        placeholder: '0'
    };
    var email = {
        mask: "*{1,20}[.*{1,20}][.*{1,20}][.*{1,20}]@*{1,30}[.*{2,10}][.*{1,5}]",
        //showMaskOnFocus: false,
        //showMaskOnHover: false,
        placeholder: '',
        greedy: false,
        oncomplete: function () {
            $(this).removeAttr('error');
            $(this).css('border-color', '');
        },
        onincomplete: function () {
            $(this).attr('error', 1);
            $(this).css('border-color', 'red');
        },
        onBeforePaste: function (pastedValue, opts) {
            pastedValue = pastedValue.toLowerCase();
            return pastedValue.replace("mailto:", "");
        },
        definitions: {
            '*': {
                validator: "[0-9A-Za-z!#$%&'*+/=?^_`{|}~\-]",
                cardinality: 1,
                casing: "lower"
            }
        }
    };

    var type = false;
    switch (current_type) {
        case 'ip' :
            type = ip;
            break;
        case 'email' :
            type = email;
            break;
    }

    var div = $('<div></div>').attr({
        'class': 'form-inline'
    });

    var button = $('<button>X</button>').attr({
        'class': 'btn btn-danger remove_element_multi'
    });

    var input = $('<input />').attr({
        'class': 'form-control input-field input-multi from'
    });

    var input2 = '';
    if (type == ip) {
        input2 = $('<input />').attr({
            'class': 'form-control input-field input-multi to'
        });
    }

    div.append($('<p />')).append(button).append(input).append(input2);
    main_div.children('.add_element_multi').after(div);
    if (type == ip) {
        Inputmask(type).mask(input);
        Inputmask(type).mask(input2);
        var vals = explode('-', val);
        var val1 = vals[0];
        var val2 = vals[1];
        input.val(val1);
        input2.val(val2);

    }
    else{
        Inputmask(type).mask(input);
        input.val(val);
    }

    //div.append('<div class="form-inline"><button class="btn btn-danger remove_element_multi">X</button><input class="form-control input-field input-multi" value="' + val + '"/></div>');
}

function explode(delimiter, string) {	// Split a string by string
    //
    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +   improved by: kenneth
    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)

    var emptyArray = {0: ''};

    if (arguments.length != 2
        || typeof arguments[0] == 'undefined'
        || typeof arguments[1] == 'undefined') {
        return null;
    }

    if (delimiter === ''
        || delimiter === false
        || delimiter === null) {
        return false;
    }

    if (typeof delimiter == 'function'
        || typeof delimiter == 'object'
        || typeof string == 'function'
        || typeof string == 'object') {
        return emptyArray;
    }

    if (delimiter === true) {
        delimiter = '1';
    }

    return string.toString().split(delimiter.toString());
}