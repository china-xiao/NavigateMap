<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备历史信息列表</title>
    <meta name="description" content="设备历史信息列表">
    <meta name="keywords" content="设备历史信息列表">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            设备管理
            <small>设备历史信息列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">设备历史信息列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <form id="editForm" action="/web/roadInfo/list" method="post">
<%--                        <div class="pull-right">--%>
<%--                            <div class="form-group form-inline">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-default" title="查询" onclick='location.href="/web/roadInfo/list"'><i class="fa fa-file-o"></i> 查询</button>--%>
<%--                                    <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">查询</button>--%>
<%--                                    <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--    ${"${modelNumber}"=="${item.modelNumber}"?'selected':''}--%>
                        <div class="box-tools pull-left">
                            <select id="select1" class="form-control" name="modelNumber">
                                <option value="" >请选择</option>
                                <c:forEach items="${equip}" var="item">
                                    <option value="${item.modelNumber}" ${item.modelNumber eq modelNumber ? "selected":""}  >${item.name}</option>
                                </c:forEach>
                            </select>
                            <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">查询</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </form>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">
                                <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                            </th>
                            <th class="sorting">序号</th>
                            <th class="sorting">设备编号</th>
                            <th class="sorting">堵车信息</th>
                            <th class="sorting">风速</th>
                            <th class="sorting">烟雾</th>
                            <th class="sorting">雨水</th>
                            <th class="sorting">创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="info"  varStatus="st">
                            <tr>
                                <td><input type="checkbox" name="id" value="${info.id }"/></td>
                                <td>${st.count }</td>
                                <td>${info.modelNumber }</td>
                                <td>${info.duche } </td>
                                <td>${info.fengsu }</td>
                                <td>${info.yanwu }</td>
                                <td>${info.yudi }</td>
                                <td>${info.created }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box-footer">
                <jsp:include page="../common/page.jsp">
                    <jsp:param value="${ctx}/roadInfo/list?modelNumber=${modelNumber}" name="pageUrl"/>
                </jsp:include>
            </div>
        </div>
    </section>
</div>
</body>
</html>