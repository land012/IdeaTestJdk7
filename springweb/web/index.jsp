<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
      <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
      <script type="text/javascript">
          jQuery(function(){
              <!-- jsonp实现1 begin -->
//              jQuery.getJSON("http://fin.shop.jd.net/rs/finance/queryPaymentPendingCount?callback=?", null, function(data){
//                  alert(data.pendingCount);
//              });
              <!-- jsonp实现1 end -->

              <!-- jsonp实现2 begin -->
//              jQuery.ajax({
//                  url : "http://fin.shop.jd.net/rs/finance/queryPaymentPendingCount",
//                  dataType : "jsonp",
//                  success : function(data) {
//                      alert(data.pendingCount);
//                  }
//              });
              <!-- jsonp实现2 end -->
          });


      </script>

      <!-- jsonp实现3 begin -->
      <script type="text/javascript">
          function getPendingCount(data) {
              alert(data.pendingCount);
          }
      </script>
      <%--<script type="text/javascript" src="http://fin.shop.jd.net/rs/finance/queryPaymentPendingCount?callback=getPendingCount"></script>--%>
      <!-- jsonp实现3 end -->
  </head>
  <body>
  this is springweb!
  <ul>
      <li><a href="/springmvc/hello" target="_blank">SpringMVC Hello World</a></li>
      <li><a href="/springmvc/greet" target="_blank">SpringMVC Greet World</a></li>
      <li><hr/></li>
      <li><a href="/servlet/hello" target="_blank">Servlet Hello Velocity</a></li>
      <li><a href="/servlet/greet" target="_blank">Servlet Greet Velocity</a></li>
      <li><a href="/servlet/velocity" target="_blank">Hello VelocityViewServlet</a></li>
  </ul>
  </body>
</html>