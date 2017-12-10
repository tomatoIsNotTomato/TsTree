<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>modify</title>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/style.css">

<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
	type="text/javascript" type="text/javascript"></script>
<script src="supersized.3.2.7.min.js"></script>
<script src="supersized-init.js"></script>

<style type="text/css">
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
	position: fixed;
	top: 0;
	width: 100%;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li
 
a
:hover
:not
 
(
.active
 
)
{
background-color
:
 
#111
;


}
.searchBox {
	float: right;
	margin-right: 170px;
	margin-top: 0px;
	width: 100px;
	height: 35px;
}

.searchBox input {
	float: right;
	margin-top: 7px;
	width: 100px;
	height: 25px;
	font-size: 15px;
	border-radius: 6px;
	border: 1px solid #3d3d3d; /* browsers that don't support rgba */
	border: 1px solid rgba(255, 255, 255, .15);
}

.searchBox button {
	width: 60px;
	height: 25px;
	margin-top: 5px;
	border-radius: 10px;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	border-radius: 6px;
	border: 0px;
	padding: 0;
	background: ##848484;
}

.herf {
	display: block;
	width: 340px;
	height: 30px;
	border-radius: 6px;
	border: 0px;
	float: left;
	background-color: #ef4300;
	text-decoration: none;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	font-weight: 700;
	margin-left: 10px;
	color: #fff;
}

h1 {
	font: 40px "Trebuchet MS", Arial, Helvetica, sans-serif;
	float: left;
	margin-left: 50px;
	margin-top: 100px;
}


.basic-grey {
    margin-top: 120px;
    margin-left: auto;
    margin-right: auto;
    max-width: 500px;
    background: #F7F7F7;
    padding: 25px 15px 25px 10px;
    font: 12px Georgia, "Times New Roman", Times, serif;
    color: #888;
    text-shadow: 1px 1px 1px #FFF;
    border: 1px solid #E4E4E4;
}

.basic-grey h1 {
    
    font-size: 25px;
    padding: 0px 0px 10px 40px;
    display: block;
    border-bottom: 1px solid #E4E4E4;
    margin: -10px -15px 30px -10px;;
    color: #888;
}

.basic-grey h1>span {
    display: block;
    font-size: 11px;
}

.basic-grey label {
    display: block;
    margin: 0px;
}

.basic-grey label>span {
    float: left;
    width: 20%;
    text-align: right;
    padding-right: 10px;
    margin-top: 10px;
    color: #888;
}

.basic-grey input[type="text"], .basic-grey textarea, .basic-grey select
    {
    border: 1px solid #DADADA;
    color: #888;
    height: 30px;
    margin-bottom: 16px;
    margin-right: 6px;
    margin-top: 2px;
    outline: 0 none;
    padding: 3px 3px 3px 5px;
    width: 70%;
    font-size: 12px;
    line-height: 15px;
    box-shadow: inset 0px 1px 4px #ECECEC;
    -moz-box-shadow: inset 0px 1px 4px #ECECEC;
    -webkit-box-shadow: inset 0px 1px 4px #ECECEC;
}

.basic-grey textarea {
    padding: 5px 3px 3px 5px;
}

.basic-grey textarea {
    height: 100px;
}

.basic-grey .button {
    background: #E27575;
    border: none;
    padding: 10px 25px 10px 25px;
    color: #FFF;
    box-shadow: 1px 1px 5px #B6B6B6;
    border-radius: 3px;
    text-shadow: 1px 1px 1px #9E3F3F;
    cursor: pointer;
}

.basic-grey .button:hover {
    background: #CF7A7A
}
</style>
</head>
<body>

	<ul>
		<li><img src=images/tstree.jpg width="120px"
			style="float: left; margin-top: 3px"></li>
		<li><a href="About.jsp" style="float: right">About us</a></li>

		<li>
			<form action="search" method="post" class="searchBox" >
                <table>
                <tr><td style="width:200">
                    <input type="text" name="lastName"  style="float:left" placeholder="姓" autocomplete="off"/></td><td> <input type="text" name="firstName"  style="float:left" placeholder="名" autocomplete="off" />
                </td><td>
                <button id="submit" type="submit">search</button></tr>
                </table>
            </form>
		</li>
	</ul>
	<h1>消息列表...</h1>
	<br>
	<s:iterator value="messages" id="bn">
	<form action="leaveMsg" method="post" class="basic-grey">
		
				<img style="float:left"
					src=<s:property value="#bn.get(\"pictureUrl\")"></s:property>
					width="80" height="80" />
				<br>
				<s:property value="#bn.get(\"name\")" />
				<s:property value="#bn.get(\"sendTime\")" />
					<s:property value="#bn.get(\"mess\")" />
					<input type="hidden" name="id" value="${bn.fromID}" /> 
					
						<input type="hidden" name="history" value="${bn.mesID}" />
            
					<textarea id="message" name="message"
						placeholder="Reply"></textarea>
					<label> <span>&nbsp;</span> <input type="submit"
						class="button" value="Send" />
					</label>
				
			
		</form>
	</s:iterator>
	

</body>
</html>