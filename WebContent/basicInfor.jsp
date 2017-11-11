<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "/struts-tags" prefix = "s" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Basic Information</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">
        
        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript" type="text/javascript"></script>
        <script src="supersized.3.2.7.min.js"></script>
        <script src="supersized-init.js"></script>
    </head>
<body>

    <s:iterator id = "bs" value="%{#request.BasicInfo}">
        <div>
        <h1>Basic Information</h1>
        <s:property value="#bs"></s:property>
            <br>ID: <s:property value="#bs.get(\"ID\")"></s:property>
            <br>Name: <s:property value="#bs.get(\"name\")"></s:property>
            <br>Sex: <s:property value="#bs.get(\"sex\")"></s:property>
            <br>Birth Date: <s:property value="#bs.get(\"birthDay\")"/>
            <br>Job: <s:property value="#bs.get(\"job\")"></s:property>
            <br>PhoneNumber <s:property value="#bs.get(\"phoneNumber\")"></s:property>
            <br>Place: <s:property value="#bs.get(\"place\")"></s:property>
        </div>
    </s:iterator>
    
    <div class="page-container">
            
            <form action="getTree" method="post">
                <div>
                    <input type="hidden" name="ID" value = "%{#request.ID}"/>
                </div>
                <button id="submit" type="submit">search</button>
            </form>
            <br>
        </div>
</body>
</html>