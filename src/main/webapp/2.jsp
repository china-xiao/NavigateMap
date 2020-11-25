<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
<%--    <style type="text/css">--%>
<%--        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}--%>
<%--        #allmap{width:100%;height:500px;}--%>
<%--        p{margin-left:5px; font-size:14px;}--%>
<%--    </style>--%>
    <script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=vbGlLhf07pCtZTGj3fkIBLabctlD0q1j"></script>
    <script src="//libs.baidu.com/jquery/1.9.0/jquery.js"></script>
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <title>给多个点添加信息窗口</title>
<%--    <meta http-equiv="refresh" content="2">--%>
</head>
<body>
<div id="allmap"></div>
<%--<p>点击标注点，可查看由纯文本构成的简单型信息窗口</p>--%>
</body>
</html>
<script type="text/javascript">
    var task=true;
    // 百度地图API功能
    map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.417854,39.921988), 15);
    map.enableScrollWheelZoom(true);
    var opts = {
        width : 250,     // 信息窗口宽度
        height: 130,     // 信息窗口高度
        title : "" , // 信息窗口标题
        enableMessage:true//设置允许信息窗发送短息
    };

    // for(var i=0;i<data_info.length;i++){
    //     var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
    //     var content = data_info[i][2];
    //     map.addOverlay(marker);               // 将标注添加到地图中
    //     addClickHandler(content,marker);
    // }
    //添加定时任务,获取道路交通信息
    addTask();

    function addTask() {
        if(task){
            //setTimeout方法的意思就是每1秒中执行一次addTask()方法
            setTimeout("addTask()",20000);
        }
        //task是全局变量，执行了取消方法后，在执行开启定时任务方法，表达式为false，定时任务不能继续执行，所以要设置为true
        task=true;
        consoleValue();
    }

    function consoleValue() {
        console.info("定时任务已开启")
        //发送ajax获取信息
        ajaxHandle();
    }

    function addClickHandler(content,marker){
        marker.addEventListener("click",function(e){
            openInfo(content,e)}
        );
    }
    function openInfo(content,e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
    }
    function ajaxHandle() {
        $.ajax({
            url:"/web/sysRoleUser/getRoadInfo",
            type:"GET",
            data:{},
            async:"true",
            dataType:"json",
            contentType: "application/json;charset=utf-8",
            success:function(data){
                console.info("ajax返回数据data:"+data);
                var obj = eval("("+data+")");
                console.info("解析后的data:"+obj);
                map.clearOverlays();
                for(var i=0;i<obj.length;i++){
                    var marker = new BMap.Marker(new BMap.Point(obj[i].chartX,obj[i].chartY));  // 创建标注
                    var content = "设备编号:"+obj[i].modelNumber+'<br/>拥堵状况:'+obj[i].rTTI+'<br/>天气:'+obj[i].weather+'<br/>温度:'+obj[i].temperature;
                    map.addOverlay(marker);
                    // 将标注添加到地图中
                    addClickHandler(content,marker);
                }
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
    }
</script>











