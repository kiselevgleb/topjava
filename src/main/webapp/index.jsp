<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>

<h2>Users</h2>

<tr>

    <td><a href="meals?action=setUser&id=${1}">Admin</a></td>
    <td><a href="meals?action=setUser&id=${2}">User</a></td>
</tr>
</body>
</html>