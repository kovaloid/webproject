<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.error.other_error_1" var="text_1"/>
    <fmt:message bundle="${loc}" key="local.error.other_error_1" var="text_2"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>Error</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../img/favicon.png"/>
</head>
<body>

<jsp:include page="../blocks/header_block.jsp"/>

<div class="main">
    <div class="well">

        <div class="alert alert-danger" align="center">
            <h1>${text_1}!</h1>
            <p>
                ${text_2} : <br/>
                <%= exception.getMessage() %>
            </p>
        </div>

    </div>
</div>

</body>
</html>



