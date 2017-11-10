<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">

    </head>

    <body oncontextmenu="return false">

        <div class="page-container">
            <h1>login</h1>
            <form action="login" method="post">
                <div>
                    <input type="text" name="id" placeholder="UserID" autocomplete="off"/>
                </div>
                <div>
                    <input type="password" name="pwd"  placeholder="Password" oncontextmenu="return false" onpaste="return false" />
                </div>
                <button id="submit" type="submit">Sign in</button>
            </form>
            <br>
            <a href="register.jsp">New? Register here.</a>
            <!-- <div class="connect">
                <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
                <p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
            </div> -->
        </div>
        <div class="alert" style="display:none">
            <h2>消息</h2>
            <div class="alert_con">
                <p id="ts"></p>
                <p style="line-height:70px"><a class="btn">确定</a></p>
            </div>
        </div>

        <!-- Javascript -->
        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript" type="text/javascript"></script>
        <script src="supersized.3.2.7.min.js"></script>
        <script src="supersized-init.js"></script>
        <script>
        $(".btn").click(function(){
            is_hide();
        })
        var u = $("input[name=id]");
        var p = $("input[name=pwd]");
        $("#submit").live('click',function(){
            if(u.val() == '' || p.val() =='')
            {
                $("#ts").html("ID或密码不能为空~");
                is_show();
                return false;
            }
            else{
                var reg = /^[0-9A-Za-z]+$/;
                if(!reg.exec(u.val()))
                {
                    $("#ts").html("ID格式不正确");
                    is_show();
                    return false;
                }
            }
        });
        window.onload = function()
        {
            $(".connect p").eq(0).animate({"left":"0%"}, 600);
            $(".connect p").eq(1).animate({"left":"0%"}, 400);
        }
        function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
        function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
        </script>
    </body>
</html>