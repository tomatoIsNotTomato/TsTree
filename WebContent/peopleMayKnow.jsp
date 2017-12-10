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

li a:hover:not (.active ) {
	background-color: #111;
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
	width: 200px;
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
</style>
</head>
<body>

	<ul>
		<li><img src=images/tstree.jpg width="120px"
			style="float: left; margin-top: 3px"></li>
		<li><a href="About.jsp" style="float: right">About us</a></li>

		<li>
			<form action="search" method="post" class="searchBox">
				<table>
					<tr>
						<td><input type="text" name="nameOrId"
							placeholder="Name or ID" autocomplete="off" /></td>
						<td>
							<button id="submit" type="submit">search</button>
					</tr>
				</table>
			</form>
		</li>
	</ul>
	<h1>可能认识得人...</h1>
	<br>
<<<<<<< HEAD
	<s:iterator value="bmn" id="bn">
		<table>
			<tr>
				<td><a href="<s:url action="linkedInLogin"/>"><img
=======
	<s:iterator value="pmn" id="bn">
		<table>
			<tr>
				<td><a href="<s:url action="search?id=%{#bn.get(\"id\")}"/>"/><img
>>>>>>> cd0285e0054be44eff9477ee54567343b478f13a
						src=<s:property value="#bn.get(\"pictureUrl\")"></s:property>
						width="80" height="80" /></a></td>
			</tr>
			<tr>
				<td><s:property value="#bn.get(\"name\")" /></td>
<<<<<<< HEAD
			</tr>
		</table>
	</s:iterator>
=======
				<td><s:property value="#bn.get(\"id\")"/></td>
			</tr>
		</table>
	</s:iterator>
	<a href="<s:url action="getTree"/>">Skip</a>
>>>>>>> cd0285e0054be44eff9477ee54567343b478f13a



</body>
</html>