<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.exception.exception_text" var="exception_text"/>
    <fmt:message bundle="${loc}" key="local.exception.message_text" var="message_text"/>
    <fmt:message bundle="${loc}" key="local.exception.unknown_exception" var="unknown_exception_text"/>
    <fmt:message bundle="${loc}" key="local.button.back" var="back_button"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${exception_text} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>

<div class="main">
    <div class="well">

        <div class="alert alert-danger" align="center">
            <h1>${exception_text}!</h1>
            <p>
                ${message_text} : <br/>
                <c:if test="${requestScope.exception != null}">
                    <c:out value="${requestScope.exception}"/>
                </c:if>
                <c:if test="${requestScope.exception == null}">
                    ${unknown_exception_text}
                </c:if>


                <%=exception.getMessage()%>
            </p>
            <br/>
            <input type="button" class="btn btn-success margin" onclick="history.back();" value="${back_button}"/>
        </div>

    </div>
</div>

</body>
</html>



