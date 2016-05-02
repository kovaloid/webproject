<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>

<html>
<head>
    <title>Title</title>


</head>
<body>


Hello!

<c:out value="${sessionScope['rs'].getInt(1)}" />

<br><br><br>

<c:set var="salary" scope="request" value="${40}" /> <br>
<c:out value="${requestScope.salary}" /><br>
<c:set var="salary" scope="session" value="${30}" /><br>
<c:out value="${sessionScope.salary}" /><br>


<jsp:useBean id="userbean" class="com.epam.project.JSPSetBean" scope="request" />

<mytag:bodyjspset num="${userbean.size}">
    ${userbean.element}
</mytag:bodyjspset>





</body>
</html>
