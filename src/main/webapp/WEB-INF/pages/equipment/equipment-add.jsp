<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<html>
<head>
    <title>equipmentInfo</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            设备管理
            <small>交通设备</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="/web/equipmentInfo/list">设备列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <!-- 正文区域 -->
    <section class="content">
        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">设备信息</div>
            <form id="editForm" action="/web/equipmentInfo/edit" method="post">
                <input type="hidden" id="id" name="id" value="${equipmentInfo.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">设备编号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备编号" name="modelNumber" value="${equipmentInfo.modelNumber}">
                    </div>
                    <div class="col-md-2 title">设备名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备名称" name="name" value="${equipmentInfo.name}">
                    </div>
                    <div class="col-md-2 title">设备所在横坐标</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备所在横坐标" name="chartX" value="${equipmentInfo.chartX}">
                    </div>
                    <div class="col-md-2 title">设备所在纵坐标</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备所在纵坐标" name="chartY" value="${equipmentInfo.chartY}">
                    </div>
                    <div class="col-md-2 title">设备所在街道地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备所在街道地址" name="address" value="${equipmentInfo.address}">
                    </div>
                    <div class="col-md-2 title">连接设备url</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="连接设备url" name="url" value="${equipmentInfo.url}">
                    </div>
                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${equipmentInfo.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${equipmentInfo.state==1?'checked':''} name="state" value="1">启用</label></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->
        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->
    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../../css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>
