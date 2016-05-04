<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.error.403" var="error_text"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>403 Access Denied</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../img/favicon.png"/>
</head>
<body>

<jsp:include page="../blocks/header_block.jsp"/>

<div class="main">
    <div class="well">

        <div class="alert alert-danger" align="center">
            <h1>403 Access Denied</h1>
            <p>
                ${error_text}
            </p>
        </div>

    </div>
</div>

</body>
</html>



