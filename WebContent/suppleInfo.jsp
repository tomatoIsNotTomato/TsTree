<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "/struts-tags" prefix = "s" %>
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
            <h1>Add teacher or student</h1>
            <form action="supple" method="post">
                <div>
                    <input  type="hidden" name="ID" value = <s:property value="#parameters.ID[0]"/>/>
                </div>
                <div>
                    <input type="text" name="name" placeholder="name" autocomplete="off"/>
                </div>
                <div>
                    <input type="text" name="id" placeholder="ID(optional)" autocomplete="off"/>
                </div>
                <div>
                    <!-- <input type="text" name="relation" placeholder="关系" autocomplete="off"/> -->
                    <input type="radio" name="period" value="bachelor"/>bachelor
                    <input type="radio" name="period" value="master"/>master
                    <input type="radio" name="period" value="doctor" checked="checked" />doctor
                </div>
                
                <div>
                    <input type="password" name="tel"  placeholder="tel" oncontextmenu="return false" onpaste="return false" />
                </div>
                <div>
                    <!-- <input type="text" name="period" placeholder="时期" autocomplete="off"/> -->
                    <input type="radio" name="relation" value="teacher"/>teacher
                    <input type="radio" name="relation" value="student" checked="checked" />student
                </div>
                
                <button id="submit" type="submit">Sign in</button>
            </form>
            <br>
        </div>
        <div class="alert" style="display:none">
            <h2>消息</h2>
            <div class="alert_con">
                <p id="ts"></p>
                <p style="line-height:70px"><a class="btn">确定</a></p>
            </div>
        </div>


        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript" type="text/javascript"></script>
        <script src="supersized.3.2.7.min.js"></script>
        <script src="supersized-init.js"></script>
        <script>
        $(".btn").click(function(){
            is_hide();
        })
        var u = $("input[name=name]");
        var p = $("input[name=relation]");
        var q = $("input[name=period]");
        $("#submit").live('click',function(){
            if(u.val() == '' || p.val() =='' || q.val()=='')
            {
                $("#ts").html("请检查输入");
                is_show();
                return false;
            }
            
            }
        );
        
        function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
        function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
        </script>
    </body>
</html>