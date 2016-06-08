<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Struts2 ErrorPage</title>
</head>
<body>
出错了
<br/>
<pre><s:property value="exceptionStack"></s:property></pre>
</body>
</html>
