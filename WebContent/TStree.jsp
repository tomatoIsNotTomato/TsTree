<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "/struts-tags" prefix = "s" %>
<!DOCTYPE html> 
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>TsTree</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">

        
           
       <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
        
        <script src="supersized.3.2.7.min.js"></script>
        <script src="supersized-init.js"></script>
        <script type="text/javascript" src="d3.js"></script>
        
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
   
   .mainSearch {
   float:right;
   margin-right:100px;
        margin-top:200px;
        width:250px;
        
   }
   
   .mainSearch input{
        float:right;
        
        width:270px;
        height:30px;
        font-size:15px;
        border-radius: 6px;
        border: 1px solid #3d3d3d; /* browsers that don't support rgba */
        border: 1px solid rgba(255,255,255,.15);
        
   }
   .mainSearch button{
        width:300px;
        height:30px;
        margin-top:5px;
        font-family: 'PT Sans', Helvetica, Arial, sans-serif;
        border-radius: 6px;
        border: 0px;
        padding: 0;
        background: #ef4300;
        
   }
   
   .herf {
        display:block;
        width:300px;
        height:30px;
        border-radius: 6px;
        border: 0px;
        float:right;
        background-color:#ef4300;
        text-decoration:none;
        font-family:'PT Sans', Helvetica, Arial, sans-serif;
        font-size: 14px;
	    font-weight: 700;
	    color: #fff;
   }
       
    h1 {
        font: 40px "Trebuchet MS", Arial, Helvetica, sans-serif;
        float:left;
        margin-left:50px;
        margin-top:5px;
         
    }
    .circleImg {
        stroke: #ff7f0e;
        stroke-width: 1.5px;
    }

    path.link {
        fill: none;
        stroke: #666;
        stroke-width: 1.5px;
    }

    path.link.student {
        stroke-dasharray: 0, 2 1;
    }

    .circle {
        width: "100px";
        height: "150px"
    }

    .node text {
        font: 10px sans-serif;
        pointer-events: none;
    }

    .tooltip{  
            font-family: "microsoft yahei", "simsun";
    font-size: 12px;
    width: 170px;
    height: auto;
    z-index: 2;
    position: absolute;
    background: #fff;
    opacity: 0.5;
    border-radius: 5px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, .2);    
        }
        
        
    .tooltip .title {
    color: #fff;
    padding: 5px;
    font-size: 14px;
    background-color: #333;
    border-radius: 5px 5px 0 0;
}
.tooltip .detail-info {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid #337ab7;
    color:#333
}


     .op {
            font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
            margin-right:0px;
            float:right;
            margin-top:7px;
        }
     svg{
     float:left;
     margin-top:10px;}
        
    
    </style>
    </head>
<body>
<ul>
          <li><img src = images/tstree.jpg width="120px" style="float:left; margin-top:3px" ></li>
           <li><a href="About.jsp" style="float:right">About us</a></li>
           <li><a href="<s:url action = "msgList"></s:url>">Msg</a></li>
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
            <h1><%=request.getAttribute("name")%>'s TsTree..</h1>
     
        <div class="page-container">
            <form action="showFullTree" method="post" class="mainSearch">
                 <input type="hidden" name="ID" value=<%=request.getAttribute("ID") %> >
                 <input type="text" name="count" placeholder="Iteration times" autocomplete="off"/>
                <button id="submit" type="submit">search</button>
            </form>
            <br>
        </div>
        
    <script>
    
    function getMsgCount(){  
               
               $.post("CheckUncheckedMsg.servlet",{}
            		   ,
            		   function(data,status){
            			 
            	   if (parseInt(data)>0){
                       alert(data);
                   }
            		   });
            
    }
    
    
    setInterval("getMsgCount()",2000);  
    	
    	
        var links =<%=request.getAttribute("tree")%>;
<%--         var nodes =<%=request.getAttribute("nodes")%>; --%>
        var nodes = <%=request.getAttribute("node")%>;
       
        console.log(nodes); 
        var mainname = '<%=request.getAttribute("name")%>';
        var mainID = <%=request.getAttribute("ID")%>;
        
        var img_w = 120;
        var img_h = 120;
        var rad = 60;


        var width = 960,
            height = 500;

        var force = d3.layout.force()
            .nodes(nodes)
            .links(links)
            .size([width, height])
            .linkDistance(300)
            .charge(-300)
            .on("tick", tick)
            .start();

        var svg = d3.select("body").append("svg")
            .attr("width", width)
            .attr("height", height);


        //(1)创建箭头  
        svg.append("svg:defs").selectAll("marker")
            .data(force.links())
            .enter().append("svg:marker")
            .attr("markerUnits","userSpaceOnUse")
            .attr("id", "resolved")
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", Math.log(15) * 20)
            .attr("refY", 5)
            .attr("markerWidth", 12)
            .attr("markerHeight", 12)
            .attr("orient", "auto")
            .append("svg:path")
            .attr("d", "M0,-5L10,0L0,5");

        var path = svg.append("svg:g").selectAll("path")
            .data(force.links())
            .enter().append("svg:path")
            .attr("marker-end", "url(#resolved)" );
        ;
        
        path.attr("id", function(d, i){return "link"+i;})
            .attr("class", function(d) { console.log(d);return "link "+d.relation; });

        var node = svg.selectAll(".node")
            .data(force.nodes())
            .enter().append("g")
            .attr("class", "node")
            .call(force.drag);
        
        var edges_text = svg.append("g").selectAll(".edgelabel")
        .data(force.links())
        .enter()
        .append("text")
        .style("pointer-events", "none")
        //.attr("class","linetext")
        .attr({  'class':'edgelabel',
                       'id':function(d,i){return 'edgepath'+i;},
                       'dx':120,
                       'dy':0
                       });

        //设置线条上的文字
        edges_text.append('textPath')
        .attr('xlink:href',function(d,i) {return '#link'+i})
        .style("pointer-events", "none")
        .text(function(d){return d.relation;});
 
        var timer = null;
        
        path.append("text");
        


        //设置圆点的半径，圆点的度越大weight属性值越大，可以对其做一点数学变换                               
        function radius(d) {
            
            return Math.log(d.wei + 5) * 20;
        }

        
        node.append("circle")
            .attr("r", function(d) { //设置圆点半径    
            	
                return radius(d);
            })
            .attr("id", function(d){
            	return "node";
            })
            .attr("fill", function(d, i) {

                //创建圆形图片
                var defs = svg.append("defs");

                var imgPath = d.name.substring(0, 5) + '.jpg';

                var catpattern = defs.append("pattern")
                    .attr("id", "catpattern" + i)
                    .attr("height", 1)
                    .attr("width", 1);


                catpattern.append("image")
                    .attr("x", -(img_w / 2 - rad))
                    .attr("y", -(img_h / 2 - rad))
                    .attr("width", img_w)
                    .attr("height", img_h)
                    .attr("xlink:href", d.pic);
                
                    console.log("url(#catpattern" + i + ")");
                return "url(#catpattern" + i + ")";
            })
            .on("mouseover", function(d) { //设置圆点半径    

                return mouseover(d);
            })
            .on("mouseout", mouseout)
            .on("dblclick", function(d){
            	clearTimeout(timer);
            	if (d.ID!="-0001"){
            	window.location.href="search?NameOrId="+d.ID;}
            }) 
            .on("click", function(d){
            	clearTimeout(timer);
                timer = setTimeout(function() { 
                	 for (var i = 0; i < links.length; i++) {
                	        if ((links[i].target.name==mainname && links[i].source.name==d.name)||(links[i].target.name==d.name && links[i].source.name==mainname)){
                	        	window.location.href="modify.jsp?sourceID="+<%=request.getAttribute("ID")%>+"&ID="+d.ID+"&name="+d.name+"&email="+d.email+"&relation="+d.relation+"&period="+d.period;
                	        	break;
                	        }
                	    };
                	
                }, 300);
            	
            }) ;

        node.append("text")
            .attr("x", (function(d){return 10 - radius(d);}))
            .attr("y", (function(d){return radius(d)+20;}))
            .attr("dy", ".35em")
            .text(function(d) { return d.name });
        

        function tick() { //打点更新坐标  
            path.attr("d", function(d) {
                var dx = d.target.x - d.source.x, //增量  
                    dy = d.target.y - d.source.y,
                    dr = Math.sqrt(dx * dx + dy * dy);
                return "M" + d.source.x + "," +
                    d.source.y + "A" + dr + "," +
                    dr + " 0 0,0 " + d.target.x + "," +
                    d.target.y;
            });

            //更新结点图片和文字
            node.attr("transform", function(d) {
                    return "translate(" + d.x + "," + d.y + ")";
                });
          
        }

        var tooltip = d3.select("body").append("div")  
                            .attr("class","tooltip") //用于css设置类样式  
                            .attr("opacity",1.0); 
        
        
        function mouseover(d) {
            /* d3.select(this).select("circle").transition()
                .duration(750)
                .attr("r", function(d) { //设置圆点半径                        
                    return radius(d) + 10;
                })
                ; */
            console.log(d);
                tooltip.html(function(){
                	if (d.name == mainname){
                		return "<div class=\"title\">information:</div><div class=\"detail-info\">Name:"+d.name+"<br/>ID:"+d.ID+"</div>";
                	}
                	else{
                		return "<div class=\"title\">information:</div><div class=\"detail-info\">Name:"+d.name+"<br/>ID:"+d.ID+"<br/>Email:"+d.email+"<br/>relation:my "+d.relation+"</div>";
                	}
                })
                    //设置tooltip的位置(left,top 相对于页面的距离)   
                            .style("left",(d3.event.pageX)+"px")  
                            .style("top",(d3.event.pageY+20)+"px")  
                            .style("opacity",1.0); 
        }

        function mouseout() {
            d3.select(this).select("circle").transition()
                .duration(750)
                .attr("r", function(d) { //恢复圆点半径                        
                    return radius(d);
                });
                tooltip.style("opacity",0.0);  
        }
        </script>
        
        <div class="op">
     <br>
     <s:a href="suppleInfo.jsp?ID=%{#request.ID}" cssClass="herf">Add a Node</s:a>
     
     <br>
     <s:a action="peopleMayKnow" cssClass="herf">people may know
     </s:a>
     <br>
     <s:a action="merge" cssClass="herf">Merge with My Tstree

     <s:param name="ID2"><s:property value="%{#request.ID}"/></s:param>
     <s:param name="name"><s:property value="%{#request.name}"/></s:param>
     </s:a>
     </div>
     <br>
     
</body>
</html>