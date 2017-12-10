<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        
        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript" type="text/javascript"></script>
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
            
            li a:hover:not(.active) {
                background-color: #111;
            } 
   .searchBox {
   float:right;
   margin-right:170px;
        margin-top:0px;
        width:100px;
        height:35px;
   }
   
   .searchBox input{
        float:right;
        margin-top:7px;
        width:200px;
        height:25px;
        font-size:15px;
        border-radius: 6px;
        border: 1px solid #3d3d3d; /* browsers that don't support rgba */
        border: 1px solid rgba(255,255,255,.15);
        
   }
   .searchBox button{
        width:60px;
        height:25px;
        margin-top:5px;
        border-radius:10px;
        font-family: 'PT Sans', Helvetica, Arial, sans-serif;
        border-radius: 6px;
        border: 0px;
        padding: 0;
        background: ##848484;
        
   }
   

       
    h1 {
        font: 40px "Trebuchet MS", Arial, Helvetica, sans-serif;
        float:left;
        margin-left:50px;
        margin-top:100px;
        color:#000;
        }
        
    
    .basic-grey {
    margin-top: 120px;
    margin-left: auto;
    margin-right: auto;
    max-width: 500px;
    
    padding: 25px 15px 25px 10px;
    font: 12px Georgia, "Times New Roman", Times, serif;
    color: #888;
    text-shadow: 1px 1px 1px #FFF;
    border: 1px solid #E4E4E4;
}

.basic-grey h4 {
    
    font-size: 25px;
    padding: 0px 0px 10px 40px;
    display: block;
    border-bottom: 1px solid #E4E4E4;
    margin: -10px -15px 30px -10px;;
    color: #000;
}

.basic-grey h4>span {
    display: block;
    font-size: 11px;
}



.basic-grey button {
    background: #E27575;
    border: none;
    padding: 10px 25px 10px 25px;
    color: #FFF;
    box-shadow: 1px 1px 5px #B6B6B6;
    border-radius: 3px;
    text-shadow: 1px 1px 1px #9E3F3F;
    cursor: pointer;
    width:80;
}

.basic-grey input{

    width: 100px;
    height: 42px;
    line-height:42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    *background-color:transparent;
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #FFF;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
 
}

.basic-grey button:hover {
    background: #CF7A7A
}
        </style>
</head>
<body>

<ul>
          <li><img src = images/tstree.jpg width="120px" style="float:left; margin-top:3px" ></li>
          <li><a href="About.jsp" style="float:right">About us</a></li>
           
          <li> <form action="search" method="post" class="searchBox">
                <table>
                <tr><td>
                    <input type="text" name="nameOrId" placeholder="Name or ID" autocomplete="off"/>
                </td><td>
                <button id="submit" type="submit">search</button></tr>
                </table>
            </form>
            </li>
        </ul>
            <h1>Modify...</h1><br>
            
            
    <div class="basic-grey" >
    <h4>name: ${param.name}
    <br>ID: ${param.ID}
    <br>Email: ${param.email }
    <br>relation: ${param.relation }
    <br>period: ${param.period }</h4>
    
    
    <div >
            <form action="delete" method="post">
                <div>
                    
                    <input type="hidden" name="name" value="${param.name}"/>
                    <input type="hidden" name="id" value="${param.ID}"/>
                    <input type="hidden" name="email" value="${param.email}"/>
                    <input type="hidden" name="period" value="${param.period}"/>
                    <input type="hidden" name="relation" value="${param.relation}"/>
                </div>
                
                <button id="submit" type="submit">delete this node</button>
            </form>
            <br>
        </div>
        
        <div style="margin-top:5px">
            <form action="update" method="post">
                <div>
                    <input type="hidden" name="ID" value = "${param.sourceID}"/>
                    <input type="hidden" name="preName" value="${param.name}"/>
                    <input type="hidden" name="id" value="${param.ID}"/>
                    <input type="hidden" name="email_old" value="${param.email}"/>
                    <input type="hidden" name="period" value="${param.period}"/>
                    <input type="hidden" name="relation" value="${param.relation}"/>
                    <input type="text" name="postLastName" placeholder="new last name"/>
                    <input type="text" name="postFirstName" placeholder="new first name"/>
                    <input type="text" name="email_new" placeholder="new email"/>
                   
                </div>
                <br>
                <button id="submit" type="submit">update</button>
            </form>
            <br>
        </div>
        </div>
       
</body>
</html>