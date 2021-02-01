function postRequest(options) {
    var header = 'X-CSRF-TOKEN';
    var token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        type : options['type']||"POST",
        contentType : "application/json",
        url : options['url'],
        data : JSON.stringify(options['data']),
        dataType : 'json',
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : options['success'],
        error : function(e) {
            options['error'](e.responseJSON);
        }
    });
}

function postFormRequest(options, formData) {
    var header = 'X-CSRF-TOKEN';
    var token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : options['url'],
        data : formData,
        dataType : 'json',
        processData: false,
        contentType: false,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : options['success'],
        error : function(e) {
            options['error'](e.responseJSON);
        }
    });
}

function getRequest(options) {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : options['url'],
        success : options['success'],
        error : function(e) {
            options['error'](e.responseJSON);
        }
    });
}

function downloadUrl(url, filename) {
    var anchor = document.createElement('a');
    anchor.href = url;
    anchor.target = '_blank';
    if(filename) {
        anchor.download = filename;
    }
    anchor.click();
}

function deleteRequest(options) {
    var header = 'X-CSRF-TOKEN';
    var token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        url : options['url'],
        success : options['success'],
        error : function(e) {
            options['error'](e.responseJSON);
        }
    });
}

function dialogConfirm(options) {
    bootbox.confirm({
        title: "Xác nhận",
        message: "Bạn có chắc chắn muốn xóa dữ liệu này?",
        buttons: {
            confirm: {
                label: '<i class="fa fa-check"></i> Đồng ý',
                className: 'btn-primary'
            },
            cancel: {
                label: '<i class="fa fa-times"></i> Hủy',
                className: 'btn-warning'
            }
        },
        callback: function (result) {
            if(result){
                options.callback();
            }
        }
    });
}

function escapeHtml(txt) {
    if(!txt) {
        return '';
    }
    return txt
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;")
         .replace(/\n/g, "<br/>");
}

function copyToClipboard(elementId){
	 // Create a "hidden" input
	  var aux = document.createElement("input");
	  // Assign it the value of the specified element
	  aux.setAttribute("value", document.getElementById(elementId).innerHTML);
	  // Append it to the body
	  document.body.appendChild(aux);
	  // Highlight its content
	  aux.select();
	  // Copy the highlighted text
	  document.execCommand("copy");
	  // Remove it from the body
	  document.body.removeChild(aux);
	  $('#modalView').modal('toggle');
}

/* BEGIN: Toast */
function Toast(type, msg) {
    this.type = type;
    this.css = 'toast-bottom-right';
    this.msg = msg;
    toastr[type](msg);
}

toastr.options.positionClass = 'toast-bottom-right';
toastr.options.extendedTimeOut = 500; //1000;
toastr.options.timeOut = 2000;
toastr.options.fadeOut = 250;
toastr.options.fadeIn = 250;
/* END: Toast */
$.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Invalid"
);

function bodauTiengViet(str){
    if(!str) {
        return '';
    }
    str= str.toLowerCase();
    str= str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g,"a");
    str= str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g,"e");
    str= str.replace(/ì|í|ị|ỉ|ĩ/g,"i");
    str= str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g,"o");
    str= str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u");
    str= str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y");
    str= str.replace(/đ/g,"d");
    return str;
}

function getQueryParams(param) {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get(param);
    return myParam;
}