<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    
    
    
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.message" var="mes" />

</head>
<body>


<c:out value="${mes}" /> <br />
Hello!

<c:out value="${requestScope['rs'].getInt(1)}" />

<c:set var="salary" scope="request" value="${40}" /> <br>
<c:out value="${requestScope.salary}" /><br>
<c:set var="salary" scope="session" value="${30}" /><br>
<c:out value="${sessionScope.salary}" /><br>

</body>
</html>
