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
    <fmt:message bundle="${loc}" key="local.subtitle.database_fail" var="database_fail_subtitle"/>
    <fmt:message bundle="${loc}" key="local.error.text" var="error_text"/>
    <fmt:message bundle="${loc}" key="local.error.database.message_text" var="message_text"/>
    <fmt:message bundle="${loc}" key="local.button.back" var="back_button"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${database_fail_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>

<div class="main">
    <div class="well">
        <div class="alert alert-danger" align="center">
            <h1>${error_text}!</h1>
            <p>${message_text}</p>
            <br/>
            <input type="button" class="btn btn-success margin" onclick="history.back();" value="${back_button}"/>
        </div>
    </div>
</div>

</body>
</html>