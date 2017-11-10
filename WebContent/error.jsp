<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>testD3-8-drawBar.html</title>
        <script type="text/javascript" src="d3.js"></script>
    <style type="text/css">
        </style>
    </head>
    <body>
        <script type="text/javascript">
            //SVG高宽
            var w = 500;
            var h = 100;
            var barPadding = 1;

            var dataset = [ 5, 10, 13, 19, 21, 25, 22, 18, 15, 13,
                            11, 12, 15, 20, 18, 17, 16, 18, 23, 25 ];

            //创建SVG
            var svg = d3.select("body")
                        .append("svg")
                        .attr("width", w)
                        .attr("height", h);

            svg.selectAll("rect")
               .data(dataset)
               .enter()
               .append("rect")
               .attr("x", function(d, i) {
                    return i * (w / dataset.length);
               })
               .attr("y", function(d) {
                    return h - (d * 4);
               })
               .attr("width", w / dataset.length - barPadding)
               .attr("height", function(d) {
                    return d * 4;
               })
               .attr("fill", function(d) {
                    return "rgb(0, 0, " + (d * 10) + ")";
               });

            svg.selectAll("text")
               .data(dataset)
               .enter()
               .append("text")
               .text(function(d) {
                    return d;
               })
               .attr("text-anchor", "middle")
               .attr("x", function(d, i) {
                    return i * (w / dataset.length) + (w / dataset.length - barPadding) / 2
               })
               .attr("y", function(d) {
                    return h - (d * 4) + 14;
               })
               .attr("font-family", "sans-serif")
               .attr("font-size", "11px")
               .attr("fill", "white");
        </script>

    </body>
</html>