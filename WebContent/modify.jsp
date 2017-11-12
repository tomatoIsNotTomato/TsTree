<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Basic Information</h1>
    name: ${param.name}
    ID: ${param.ID}
    tel: ${param.tel }
    relation: ${param.relation }
    period: ${param.period }
    
    <div class="page-container">
            <form action="delete" method="post">
                <div>
                    <input type="hidden" name="Name" value="${param.name}"/>
                    <input type="hidden" name="id" value="${param.ID}"/>
                    <input type="hidden" name="tel" value="${param.tel}"/>
                    <input type="hidden" name="period" value="${param.period}"/>
                    <input type="hidden" name="relation" value="${param.relation}"/>
                </div>
                
                <button id="submit" type="submit">delete this node</button>
            </form>
            <br>
        </div>
        
        <div class="page-container">
            
            <form action="showFullTree" method="post">
                <div>
                    <input type="hidden" name="ID" value=<%=request.getAttribute("ID") %> >
                    <input type="text" name="count" placeholder="迭代次数" autocomplete="off"/>
                </div>
                <button id="submit" type="submit">search</button>
            </form>
            <br>
        </div>
</body>
</html>