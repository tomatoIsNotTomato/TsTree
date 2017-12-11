<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>Add period Info</title>
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
        <h1>Period Information</h1>
        <form action="addPeriod" method="post">
       <div class="form2" style="display: block">
           
                <h3>Bachelor Period</h3>

                <div>
                    <input type="text" name="bpDate" placeholder="入学时间 e.g. 2009-09-09">
                </div>

                <div>
                    <input type="text" name="bpSchool" placeholder="学校 e.g.HIT">
                </div>
                <button id="next1" type="button">next</button>
            </div>
            
            <div class="form3" style="display: none">
                <h3>Master Period</h3>

                <div>
                    <input type="text" name="mpDate" placeholder="入学时间 e.g. 2009-09-09">
                </div>

                <div>
                    <input type="text" name="mpSchool" placeholder="学校 e.g.HIT">
                </div>
                <button id="next2" type="button">next</button>
            </div>
            <div class="form4" style="display: none">
                <h3>Doctor Period</h3>

                <div>
                    <input type="text" name="dpDate" placeholder="入学时间 e.g. 2009-09-09">
                </div>

                <div>
                    <input type="text" name="dpSchool" placeholder="学校 e.g.HIT">
                </div>

                <button id="submit" type="submit">Save</button>
            </div>
        </form>

    </div>

    <div class="alert" style="display: none">
        <h2>消息</h2>
        <div class="alert_con">
            <p id="ts"></p>
            <p style="line-height: 70px">
                <a class="btn">确定</a>
            </p>
        </div>
    </div>

    
    <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
        type="text/javascript" type="text/javascript"></script>
    <script src="supersized.3.2.7.min.js"></script>
    <script src="supersized-init.js"></script>
    <script>
        
        $("#next1").live('click', function() {
            $(".form2").hide({
                "left" : "-40%"
            }, 1000)
            $(".form3").show({
                "right" : "50%"
            }, 1000)
        });
        $("#next2").live('click', function() {
            $(".form3").hide({
                "left" : "-40%"
            }, 1000)
            $(".form4").show({
                "right" : "50%"
            }, 1000)
        });
        function is_hide() {
            $(".alert").animate({
                "top" : "-40%"
            }, 300)
        }
        function is_show() {
            $(".alert").show().animate({
                "top" : "45%"
            }, 300)
        }
    </script>
</body>
</html>

