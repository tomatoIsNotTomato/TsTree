<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>完善个人信息</h3>
    <hr/>
    <form action="register" method="post">
    <h4>个人资料</h4>
    <table>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td><input type="text" name="sex"></td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td><input type="text" name="birthDate"></td>
        </tr>
        <tr>
            <td>籍贯：</td>
            <td><input type="text" name="place"></td>
        </tr>
        <tr>
            <td>职业：</td>
            <td><input type="text" name="job"></td>
        </tr>
        <tr>
            <td>联系电话：</td>
            <td><input type="text" name="phoneNumber"></td>
        </tr>
    </table>
    <h4>本科阶段</h4>
    <table>
        <tr>
            <td>入学年份：</td>
            <td><input type="text" name="bpDate"></td>
        </tr>
        <tr>
            <td>学校：</td>
            <td><input type="text" name="bpSchool"></td>
        </tr>
    </table>
    <h4>硕士阶段</h4>
    <table>
        <tr>
            <td>入学年份：</td>
            <td><input type="text" name="mpDate"></td>
        </tr>
        <tr>
            <td>学校：</td>
            <td><input type="text" name="mpSchool"></td>
        </tr>
    </table>
    <h4>博士阶段</h4>
    <table>
        <tr>
            <td>入学年份：</td>
            <td><input type="text" name="dpDate"></td>
        </tr>
        <tr>
            <td>学校：</td>
            <td><input type="text" name="dpSchool"></td>
        </tr>
    </table>
    <table>
    <tr>
            <td colspan="2">
            <input type="submit" value="提交">&nbsp;&nbsp;
            <input type="reset" value="重置">
            </td>
        </tr>
        </table>
    </form>
</body>
</html>