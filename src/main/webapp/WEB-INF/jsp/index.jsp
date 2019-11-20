<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <form method="post" action="users">
        <spring:message code="app.login"/>: <select name="userId">
        <option value="100000" selected><spring:message code="common.user"/></option>
        <option value="100001"><spring:message code="common.admin"/></option>
    </select>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>