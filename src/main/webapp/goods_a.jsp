<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="bootstrap-3.3.7-dist/bootstrap-table/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="layer-v3.1.1/layer/mobile/need/layer.css" rel="stylesheet" type="text/css">
    <script src="bootstrap-3.3.7-dist/jquery_3.2.1/jquery.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/bootstrap-table/bootstrap-table.js"></script>
    <script src="bootstrap-3.3.7-dist/bootstrap-table/bootstrap-table-zh-CN.js"></script>
    <script src="layer-v3.1.1/layer/layer.js"></script>


</head>
<body>
<div>
    <div class="layer_notice"></div>
    <%--工具栏--%>
    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
    </div>

        <%--表格--%>
    <table id="test-table" class="col-xs-12" data-toolbar="#toolbar"></table>

        <%--弹出层--%>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">操作</h4>
                    </div>
                    <div class="modal-body">

                        <div class="form-group">
                            <label for="txt_gid">商品id</label>
                            <input type="text" name="txt_gid" data-bind="value:gid" class="form-control"
                                   id="txt_gid" placeholder="商品id">
                        </div>
                        <div class="form-group">
                            <label for="txt_gname">商品名称</label>
                            <input type="text" name="txt_gname" data-bind="value:gname" class="form-control"
                                   id="txt_gname" placeholder="商品名称">
                        </div>
                        <div class="form-group">
                            <label for="txt_gpraice">商品价格</label>
                            <input type="text" name="txt_gpraice" data-bind="value:gpraice" class="form-control"
                                   id="txt_gpraice"
                                   placeholder="商品价格">
                        </div>
                        <div class="form-group">
                            <label for="txt_gsize">商品规格</label>
                            <input type="text" name="txt_gsize" data-bind="value:gsize" class="form-control" id="txt_gsize"
                                   placeholder="商品规格">
                        </div>
                        <div class="form-group">
                            <label for="txt_gkind">商品类型</label>
                            <input type="text" name="txt_gkind" data-bind="value:gkind" class="form-control" id="txt_gkind"
                                   placeholder="商品类型">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                        <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                    </div>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
    $(function() {
        initTable();
        var str = location.href;
        var name = str.indexOf("?");
        str = str.substr(name+1);
        var arr = str.split('?');
        //捕获页
        layer.msg('欢迎登陆，'+arr, {time: 3000, icon:6});
        function initTable() {
            $('#test-table').bootstrapTable({
                method: 'post',
                toolbar: '#toolbar',    //工具按钮用哪个容器
                striped: true,      //是否显示行间隔色
                cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,     //是否显示分页
                sortable: false,      //是否启用排序
                dataType: "json",
                direction: 'asc',
                sidePagination: "server",//表格分页的位置
                contentType: "application/x-www-form-urlencoded",
                pageNumber: 1,      //初始化加载第一页，默认第一页
                pageSize: 5,      //每页的记录行数
                pageList: [5, 10, 25, 50, 100],  //可供选择的每页的行数
                url: "/GoodsServlet?method=findbypage",//这个接口需要处理bootstrap table传递的固定参数
                queryParamsType: 'limit', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                                     // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
                //queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
                sidePagination: "server",   //分页方式：client客户端分页，server服务端分页
                //search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: true,  //显示下拉框勾选要显示的列
                //showRefresh: true,     //是否显示刷新按钮
                minimumCountColumns: 2,    //最少允许的列数
                //showColumns: true,  //显示下拉框勾选要显示的列
                //clickToSelect: true,    //是否启用点击选中行
                searchOnEnterKey: true,
                buttonsAlign:"right",  //按钮位置
                showToggle:true,       //是否显示详细视图和列表视图的切换按钮
                showRefresh: true,     //是否显示刷新按钮
                height: $(window).height(), //自定义表格的高度
                columns: [{
                    field: 'gid',
                    title: '商品id',
                    align: "center",
                    switchable: true
                }, {
                    field: 'gkind',
                    title: '商品类型',
                    align: "center",
                    switchable: true
                }, {
                    field: 'gname',
                    title: '商品名称',
                    align: "center",
                    switchable: true
                }, {
                    field: 'gpraice',
                    title: '商品价格',
                    align: "center",
                    switchable: true
                }, {
                    field: 'gsize',
                    title: '商品规格',
                    switchable: true,
                    align: "center",
                    sortable: true
                }, {
                    field: "操作",
                    title: "操作",
                    formatter: function (value, row, index) {
                        return ["<a class='btn btn-default' href='?method=findById&id[]=" + row.id + "&state=0'>查看</a>",
                            "<a class='btn btn-default' href='?method=findById&id[]=" + row.id + "&state=1'>编辑</a>",
                            "<a class='btn btn-default' href='?method=delete&row=" + row.id + "&state=2'>删除</a>"].join(" ");
                    },
                    align: "center"
                }],
                paginationPreText: "上一页",
                paginationNextText: "下一页",
            });
        }

        $('#btn_add').on("click", function () {
            $("#myModal").modal().on("shown.bs.modal", function () {
                $("#btn_submit").click(function(){
                    //获取表单内数据
                    var gid = $("#txt_gid").val();
                    var gname = $("#txt_gname").val();
                    var gkind = $("#txt_gkind").val();
                    var gpraice = $("#txt_gpraice").val();
                    var gsize = $("#txt_gsize").val();

                    $.post(
                        "/GoodsServlet?method=addGoods",
                        {gid:gid,gname:gname,gkind:gkind,gpraice:gpraice,gsize:gsize},
                        function(message){
                            if(message.indexOf("success") == 0){
                                //捕获页
                                layer.msg('添加成功', {time: 3000, icon:6});
                                $("#txt_gid").val('');
                                $("#txt_gname").val('');
                                $("#txt_gkind").val('');
                                $("#txt_gpraice").val('');
                                $("#txt_gsize").val('');
                            } else {
                                //捕获页
                                layer.msg('添加失败', {time: 3000, icon:6});
                            }
                        }
                    );

                });
            }).on('hidden.bs.modal', function () {
            });
        });
    })


</script>
</body>
</html>
