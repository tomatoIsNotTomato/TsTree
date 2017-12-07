<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="MsgPane" method="post">
        请输入你的姓名：<br><input type="text" name="name"><br>
        请输入你的标题：<br><input type="text" name="title"><br>
        留言内容：<br><textarea rows="15" cols="20" name="message"></textarea><br><br>       
        <input type="submit" value="提交信息"><br>
</form>
</body>
</html>