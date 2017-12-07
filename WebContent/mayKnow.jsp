<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <body>
      <h1>你本科时期可能认识的老师</h1>     
        <table align="center" border="1">
        <s:iterator value="a1" var="via"> 
           <caption>用户基本信息</caption>
           <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   <h1>你本科时期可能认识的同学</h1> 
   <table align="center" border="1">
        <s:iterator value="a2" var="via"> 
           <caption>用户基本信息</caption>
           <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   <h1>你研究生时期可能认识的老师</h1> 
   <table align="center" border="1">
        <s:iterator value="a3" var="via"> 
           <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   <h1>你研究生时期可能认识的学生</h1> 
   <table align="center" border="1">
        <s:iterator value="a4" var="via"> 
           <caption>用户基本信息</caption>
            <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   
   <h1>你博士时期可能认识的老师</h1> 
   <table align="center" border="1">
        <s:iterator value="a5" var="via"> 
           <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   <h1>你博士时期可能认识的学生</h1> 
   <table align="center" border="1">
        <s:iterator value="a6" var="via"> 
           <caption>用户基本信息</caption>
            <tr><td>名字：：<s:property value="name"/></td></tr>
           <tr><td>id：<s:property value="ID"/></td></tr>
           <tr><td>email：<s:property value="email"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   
   <h1>跟你headline相同的有：</h1> 
   <table align="center" border="1">
        <s:iterator value="a7" var="via"> 
           <caption>用户基本信息</caption>
           <tr><td>用户id：<s:property value="id"/></td></tr>
           <tr><td>用户姓：<s:property value="firstNmae"/></td></tr>
           <tr><td>用户名：<s:property value="lastName"/></td></tr>
           <tr><td>用户headline：<s:property value="headline"/></td></tr>
           <tr><td>用户location：<s:property value="location"/></td></tr>
           <tr><td>用户industry：<s:property value="industry"/></td></tr>
           <tr><td>用户email-address：<s:property value="email-address"/></td></tr>
           <tr><td>用户picture-url：<s:property value="picture-url"/></td></tr>
           <tr><td>用户public-profile-url：<s:property value="public-profile-url"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   
   <h1>跟你位置相同的有：</h1> 
   <table align="center" border="1">
        <s:iterator value="a8" var="via"> 
           <caption>用户基本信息</caption>
           <tr><td>用户id：<s:property value="id"/></td></tr>
           <tr><td>用户姓：<s:property value="firstNmae"/></td></tr>
           <tr><td>用户名：<s:property value="lastName"/></td></tr>
           <tr><td>用户headline：<s:property value="headline"/></td></tr>
           <tr><td>用户location：<s:property value="location"/></td></tr>
           <tr><td>用户industry：<s:property value="industry"/></td></tr>
           <tr><td>用户email-address：<s:property value="email-address"/></td></tr>
           <tr><td>用户picture-url：<s:property value="picture-url"/></td></tr>
           <tr><td>用户public-profile-url：<s:property value="public-profile-url"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   
   <h1>跟你industry相同的有：</h1> 
   <table align="center" border="1">
        <s:iterator value="a9" var="via"> 
           <caption>用户基本信息</caption>
           <tr><td>用户id：<s:property value="id"/></td></tr>
           <tr><td>用户姓：<s:property value="firstNmae"/></td></tr>
           <tr><td>用户名：<s:property value="lastName"/></td></tr>
           <tr><td>用户headline：<s:property value="headline"/></td></tr>
           <tr><td>用户location：<s:property value="location"/></td></tr>
           <tr><td>用户industry：<s:property value="industry"/></td></tr>
           <tr><td>用户email-address：<s:property value="email-address"/></td></tr>
           <tr><td>用户picture-url：<s:property value="picture-url"/></td></tr>
           <tr><td>用户public-profile-url：<s:property value="public-profile-url"/></td></tr>
           <tr><td><a href="note.jsp?"/></a>给Ta留言</td></tr>
        </s:iterator>
   </table><br/>
   
   
   
    </body>
</html>