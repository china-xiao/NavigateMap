<%--
  Created by IntelliJ IDEA.
  User: glzx01
  Date: 2020/11/16
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>设置/获取地图中心点</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <style>
        body,
        html,
        #container {
            overflow: hidden;
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑";
        }
        .info {
            z-index: 999;
            width: auto;
            min-width: 16rem;
            padding: .75rem 1.25rem;
            margin-left: 1.25rem;
            position: fixed;
            top: 1rem;
            background-color: rgba(265, 265, 265, 0.9);
            border-radius: .25rem;
            font-size: 14px;
            color: #666;
            box-shadow: 0 2px 6px 0 rgba(27, 142, 236, 0.3);
        }
        #zoominput {
            height: 24px;
            width: 80px;
            padding-left: 8px;
        }
        #change-btn {
            height: 30px;
            background: #5679ea;
            border: 0;
            padding: 0 10px 0 10px;
            margin-right: 8px;
            cursor: pointer;
            border-radius: 2px;
            color: #fff;
            font-size: 14px;
        }
    </style>
    <script src="//api.map.baidu.com/api?type=webgl&v=1.0&ak=vbGlLhf07pCtZTGj3fkIBLabctlD0q1j"></script>
</head>
<body>
<div class = "info">
    <button id="change-btn1" onclick="setNewCenter()">随机地图中心点</button>
    <button id="change-btn2" onclick="getMapCenter()">获取当前中心点</button>
</div>
<div id="container"></div>
</body>
</html>
<script>
    var map = new BMapGL.Map('container');
    map.centerAndZoom(new BMapGL.Point(116.414, 39.915), 13);
    map.enableScrollWheelZoom(true);
    function setNewCenter() {
        var lng = 116.514 + Math.floor(Math.random() * 589828) / 1e6;
        var lat = 39.416 + Math.floor(Math.random() * 514923) / 1e6;
        var point = new BMapGL.Point(lng, lat);
        map.setCenter(point); // 设置地图中心点
    }
    function getMapCenter() {
        var cen = map.getCenter(); // 获取地图中心点
        alert('地图中心点: (' + cen.lng.toFixed(5) + ', ' + cen.lat.toFixed(5) + ')');
    }
</script>





