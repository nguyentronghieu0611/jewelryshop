var Pagination = function() {
        var _this = this;
        _this.limit = 10;

        var extend = function(options) {
            _this.totalPages = options['totalPages']||0;
            _this.size = options['size']||0;
            _this.currentPage = options['number']||0;
            _this.currentPage++;
            _this.totalRecords = options['totalElements']||0;
            _this.first = options['first'];
            _this.last = options['last'];
            _this.step = 5;
            if(_this.currentPage - _this.step <= 0) {
                _this.from = 1;
            } else {
                _this.from = _this.currentPage - _this.step;
            }
            if(_this.from + _this.step > _this.totalPages) {
                _this.to = _this.totalPages;
            } else {
                _this.to = _this.from+_this.step;
            }
        };

        var init = function(id, func, options) {
            _this.id = id||'page';
            _this.func = func;
            extend(options);
            create();
            registerEvents();
        };

        var create = function() {
            if(_this.totalPages == 0) {
                if(_this.first == false && _this.last == false) {
                    $('#'+_this.id).html('');
                    $('#'+_this.id).hide();
                    return;
                }
                if(_this.first == false || _this.last == false) {
                    var html = '<div style="text-align: right;"><ul class="pagination pagination-sm" style="vertical-align: middle;padding-left: 10px;margin: 0px;">';
                    if(_this.first === false) {
                        html += '<li id="'+_this.id+'_go_prev"><a href="#"><i class="fa fa-backward"></i></a></li>';
                    }
                    if(_this.last == false) {
                        html += '<li id="'+_this.id+'_go_next"><a href="#"><i class="fa fa-forward"></i></a></li>';
                    }
                    html += '</ul></div>';
                    $('#'+_this.id).html(html);
                    $('#'+_this.id).show();
                } else {
                    $('#'+_this.id).html('');
                    $('#'+_this.id).hide();
                }
                return;
            }
            $('#'+_this.id).show();
            var html = '<div class="pull-left form-inline"><span>Hiển thị </span><lable class="select"><select id="'+_this.id+'_cblimit" class="form-control">';
            var limits = [10, 20, 50, 100];
            for(i = 0; i < limits.length; i++) {
                if(limits[i] == _this.limit) {
                    html += '<option value="'+limits[i]+'" selected>'+limits[i]+'</option>';
                } else {
                    html += '<option value="'+limits[i]+'">'+limits[i]+'</option>';
                }
            }
            html += '</select></lable><span> bản ghi</span></div>';
            html += '<div class="pull-right"><div class="form-inline" style="display: inline-block;vertical-align: middle;"><span>Tổng số bản ghi '+_this.totalRecords+'</span></div>'
                       + '<ul class="pagination pagination-sm" style="vertical-align: middle;padding-left: 10px;margin: 0px;">';
            if(_this.first === false) {
                html += '<li id="'+_this.id+'_go_first"><a href="#"><i class="fa fa-fast-backward"></i></a></li>';
            }
            if(_this.currentPage > 1) {
                html += '<li id="'+_this.id+'_go_prev"><a href="#"><i class="fa fa-backward"></i></a></li>';
            }
            for(var i = _this.from; i <= _this.to; i++) {
                var page = i;
                if(i == _this.currentPage) {
                    html += '<li class="active"><a href="#">'+page+'</a></li>';
                } else {
                    html += '<li class="'+_this.id+'_go_page" data_page="'+page+'"><a href="#">'+page+'</a></li>';
                }
            }
            if(_this.currentPage >= 1 && _this.currentPage < _this.totalPages) {
                html += '<li id="'+_this.id+'_go_next"><a href="#"><i class="fa fa-forward"></i></a></li>';
            }
            if(_this.last == false) {
                html += '<li id="'+_this.id+'_go_last"><a href="#"><i class="fa fa-fast-forward"></i></a></li>';
            }
            html += '</ul></div>';
            $('#'+_this.id).html(html);
        };

        var registerEvents = function() {
            $('#'+_this.id+'_go_prev').on('click', goPrev);
            $('#'+_this.id+'_go_next').on('click', goNext);
            $('.'+_this.id+'_go_page').on('click', goPage);
            $('#'+_this.id+'_go_first').on('click', goFirst);
            $('#'+_this.id+'_go_last').on('click', goLast);
            $('#'+_this.id+'_cblimit').on('change', changeNumberLimit);
        };

        var goPrev = function() {
            if(_this.currentPage > 1) {
                _this.currentPage--;
            }
            _this.func();
        };

        var goNext = function() {
            _this.currentPage++;
            _this.func();
        };

        var goPage = function() {
            _this.currentPage = $(this).attr('data_page')||1;
            _this.func();
        };

        var goFirst = function() {
            _this.currentPage = 1;
            _this.func();
        };

        var goLast = function() {
            _this.currentPage = _this.totalPages;
            _this.func();
        };

        var changeNumberLimit = function() {
            _this.limit = $('#'+_this.id+'_cblimit').val()||20;
            _this.currentPage = 1;
            _this.func();
        };

        var reset = function() {
            _this.totalPages = 0;
            _this.size = 0;
            _this.currentPage = 1;
            _this.totalRecords = 0;
            _this.first = true;
            _this.last = true;
            _this.step = 5;
            _this.to = 0;
            _this.from = 0;
            _this.limit = 10;
            $('#'+_this.id).html('');
            $('#'+_this.id).hide();
        };

        return {
            size: function() {
                return _this.size;
            },
            totalPages: function() {
                return _this.totalPages;
            },
            currentPage: function() {
                return _this.currentPage;
            },
            limit: function() {
                return _this.limit;
            },
            init: init,
            reset: reset
        }
};
