<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h3><a href="users">Users</a></h3>
<hr>
<h2>Meals</h2>

<head>
    <title>List meal </title>
</head>

<table border="1" bgcolor="#f0f8ff" width="70%" align="center">
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <c:forEach items="${list}" var="meal">
        <tr>
            <td><font color= ${meal.isExcess()}>${meal.getDateTimeWithOutT()}</font</td>
            <td><font color= ${meal.isExcess()}>${meal.getDescription()}</font</td>
            <td><font color= ${meal.isExcess()}>${meal.getCalories()}</font></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
