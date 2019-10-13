<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<ul>
    <c:forEach items="${users}" var="user">
    <li><a href="users?action=setUser&id=${admin}">Admin</a></li>
    <li><a href="users?action=setUser&id=${user}">User</a></li>

</ul>
</body>
</html>