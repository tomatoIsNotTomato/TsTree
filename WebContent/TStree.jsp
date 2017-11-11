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
        
        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript" type="text/javascript"></script>
        <script src="supersized.3.2.7.min.js"></script>
        <script src="supersized-init.js"></script>
        
         <style type="text/css">
   
    h1 {
        font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
        padding: 10px 10px 10px 20px;
        display: block;
        background: #C0E1FF;
        margin-top:80px;
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
            font-family:simsun;  
            font-size:16px;  
            width:120;  
            height:auto;  
            position:absolute;   
            text-align:center;  
            border-style:solid;  
            border-width:1px;  
            background-color:white;  
            border-radius:5px;    
        }
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
      
      .active {
          background-color: #4CAF50;
      }
    </style>
    
    </head>
<body>

<div class="page-container">
            
            <form action="search" method="post">
                <div>
                    <input type="text" name="nameOrId" placeholder="Name or ID" autocomplete="off"/>
                </div>
                <button id="submit" type="submit">search</button>
            </form>
            <br>
        </div>
        
        <s:a href="suppleInfo.jsp?ID=%{#request.ID}">Add a Node</s:a>
        
        <s:iterator value="%{#request.tree}" id="bl" status="bn" >
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
    <script>
        var rowlist =<%=request.getAttribute("jsonString")%>;
        var edge = new Array(rowlist.length); 
        for (var i = 0; i < rowlist.length; i++) {
        	edge[i] = JSON.parse(rowlist[i]);
        };
        var nodes = {};

        var img_w = 100;
        var img_h = 100;
        var rad = 60;

                
        links.forEach(function(link) { 
            console.log(nodes);
            link.source = nodes[link.source]   
                ||
                (nodes[link.source] = { name: link.source }); //(填加节点数据)  

            link.target = nodes[link.target] || (nodes[link.target] = { name: link.target });
        });


        var width = 960,
            height = 500;

        var force = d3.layout.force()
            .nodes(d3.values(nodes))
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
            .data(["teacher", "student"])
            .enter().append("svg:marker")
            .attr("id", String)
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", 15)
            .attr("refY", -1.5)
            .attr("markerWidth", 6)
            .attr("markerHeight", 6)
            .attr("orient", "auto")
            .append("svg:path")
            .attr("d", "M0,-5L10,0L0,5");



        //(2)根据连线类型引用上面创建的标记  
        var path = svg.append("svg:g").selectAll("path")
            .data(force.links())
            .enter().append("svg:path")
            .attr("class", function(d) { return "link " + d.type; })
            .attr("marker-end", function(d) { return "url(#" + d.type + ")"; });

        var node = svg.selectAll(".node")
            .data(force.nodes())
            .enter().append("g")
            .attr("class", "node")
            .on("mouseover", mouseover)
            .on("mouseout", mouseout)
            .call(force.drag);


        //设置圆点的半径，圆点的度越大weight属性值越大，可以对其做一点数学变换                               
        function radius(d) {
            if (!d.weight) { //节点weight属性没有值初始化为1（一般就是叶子了）  
                d.weight = 1;
            }
            return Math.log(d.weight + 5) * 20;
        }



        node.append("circle")
            .attr("r", function(d) { //设置圆点半径    

                return radius(d);
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
                    .attr("xlink:href", imgPath);
                    console.log("url(#catpattern" + i + ")");
                return "url(#catpattern" + i + ")";
            });

        node.append("text")
            .attr("x", (function(d){return 10 - radius(d);}))
            .attr("y", (function(d){return radius(d)+20;}))
            .attr("dy", ".35em")
            .text(function(d) { return d.name; });

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

        function mouseover() {
            d3.select(this).select("circle").transition()
                .duration(750)
                .attr("r", function(d) { //设置圆点半径                        
                    return radius(d) + 10;
                })
                ;

            tooltip.html("我是小可爱\n!!")  
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
    
    
    
</body>
</html>