var FormDataField = function(options) {
    options = options || {};
    var _this = this;
    var formId = options.id || 'list-data-field';

    var registerEvents = function() {
        $('#' + formId).on('focus', 'input[data-field-type=Datetime]',
            function() {
//                $(this).datepicker({
//                    format : 'dd/mm/yyyy',
//                    firstDay : 1,
//                    defaultDate : new Date(),
//                    autoclose: true
//                }).on('show.bs.modal', function(event) {
//                    event.stopPropagation(); 
//                });

                $(this).datetimepicker({
                    locale: 'vi',
                    format: 'L'
                })
                .on('show.bs.modal', function(event) {
                    event.stopPropagation(); 
                });
       });
    };

    registerEvents();

    var reload = function(list, values) {
        var list = list || [];
        var html = '';
        for (var i = 0; i < list.length; i++) {
            var item = list[i];
            var require = item['isNull'] == 0 ? ' <font color="#FF0000">*</font>' : '';
            var type = item['fieldType'];
            var fieldName = item['fieldName'];
            html += '<div class="form-group">'
                    + '<label class="control-label col-md-4">'+ item['fieldName'] + require+ '</label>'
                    + '<div class="col-md-6 has-feedback"><input type="text" class="form-control" autocomplete="off" data-meta-id=""'
                    + ' data-empty="' + item['isNull'] + '" data-field-id="' + item['id'] + '" data-field-type="' + type + '" data-field-name="' + fieldName + '" />';
            if (type == 'Datetime') {
                html += '<i class="glyphicon glyphicon glyphicon-calendar form-control-feedback blue"></i>';
            }
            html += '</div></div>';
        }
        $('#' + formId).html(html);
        var values = values||[];
        for(var i = 0; i < values.length; i++) {
            var item = values[i];
            var $element = $('#' + formId + ' input[data-field-id='+item['fieldId']+']');
            if(!$element) {
                continue;
            }
            $element.val(item['fieldValue']);
            $element.attr('data-meta-id', item['id']);
        }
    };

    var validateFormInput = function() {
        var elements = $('#' + formId + ' input') || [];
        for (var i = 0; i < elements.length; i++) {
            var $this = $(elements[i]);
            var is_empty = $this.attr('data-empty') || 1;
            var value = $this.val().trim() || '';
            var type = $this.attr('data-field-type') || '';
            var name = $this.attr('data-field-name') || '';
            if (is_empty == 0 && !value) {
                $this.focus();
                new Toast('warning', name+': không được để trống');
                return false;
            }
            if(type == 'Datetime' && value && !validateDate(value)) {
                $this.focus();
                new Toast('warning', name+': Ngày tháng không hợp lệ');
                return false;
            }
            if(type == 'Number' && value && isNaN(value)) {
                $this.focus();
                new Toast('warning', name+': phải là kiểu số');
                return false;
            }
        }
        return true;
    }

    var getData = function() {
        var data = [];
        var elements = $('#' + formId + ' input') || [];
        for (var i = 0; i < elements.length; i++) {
            var $this = $(elements[i]);
            var is_empty = $this.attr('data-empty') || 1;
            var value = $this.val().trim() || '';
            var type = $this.attr('data-field-type') || '';
            var field_id = $this.attr('data-field-id');
            var meta_id = $this.attr('data-meta-id');
            data.push({
                id: meta_id,
                fieldValue: value,
                type: type,
                isNull: is_empty,
                fieldId: field_id
            });
        }
        return data;
    }

    var resetForm = function() {
        var elements = $('#' + formId + ' input') || [];
        for (var i = 0; i < elements.length; i++) {
            $(elements[i]).val('');
            if(i == 0) {
                $(elements[i]).focus();
            }
        }
    }

    function validateDate(text) {
        //var datetimeformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}( ?)((20|21|22|23|[0-1]?\d{1}):([0-5]?\d{1}))?$/;
        var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
        if (!text || !text.match(dateformat)) {
            return false;
        }
        // Test which seperator is used '/' or '-'
        var opera1 = text.split('/');
        var opera2 = text.split('-');
        lopera1 = opera1.length;
        lopera2 = opera2.length;
        // Extract the string into month, date and year
        if (lopera1 > 1) {
            var pdate = text.split('/');
        } else if (lopera2 > 1) {
            var pdate = text.split('-');
        }
        var dd = parseInt(pdate[0]);
        var mm = parseInt(pdate[1]);
        var yy = parseInt(pdate[2]);
        // Create list of days of a month [assume there is no leap year by
        // default]
        var ListofDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
        if (mm == 1 || mm > 2) {
            if (dd > ListofDays[mm - 1]) {
                return false;
            }
        }
        if (mm == 2) {
            var lyear = false;
            if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
                lyear = true;
            }
            if ((lyear == false) && (dd >= 29)) {
                return false;
            }
            if ((lyear == true) && (dd > 29)) {
                return false;
            }
        }
        return true;
    }

    return {
        reload : reload,
        validateFormInput : validateFormInput,
        getData : getData,
        resetForm: resetForm
    }
};