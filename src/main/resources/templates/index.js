function index(){
    this.load = doload();

    function doload() {
        initControl();
        // bindEvent();
    }

    function initControl() {
        debugger;
        getRequest({
            url : "/getUserName",
            success : function(data) {
                $('#usernamelogin').text(data);
            },
            error : function(e) {
                new Toast('error', 'Không có dữ liệu truy xuất!');
            }
        });
    }

    function bindEvent() {

    }
}