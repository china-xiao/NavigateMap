<%--
  Created by IntelliJ IDEA.
  User: glzx01
  Date: 2020/11/16
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

<script type="text/javascript" src="plugins/jQuery/jquery-2.2.3.min.js"></script>

<input type="button" style="width: 100px" height="100px" value="开启定时任务" onclick="addTask()"/>
<input type="button" style="width: 100px" height="100px" value="关闭定时任务" onclick="cancelTask()"/>

<script type="text/javascript">
    var task=true;
    function consoleValue() {
        console.info("定时任务已开启")
    }

    function addTask() {
        consoleValue();
        if(task){
            //setTimeout方法的意思就是每1秒中执行一次addTask()方法
            setTimeout("addTask()",1);
        }
        //task是全局变量，执行了取消方法后，在执行开启定时任务方法，表达式为false，定时任务不能继续执行，所以要设置为true
        task=true;
    }

    function cancelTask() {
        task=false;
    }

</script>