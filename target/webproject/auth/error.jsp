<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.subtitle.cars" var="cars_subtitle" />
    <fmt:message bundle="${loc}" key="local.subtitle.drivers" var="drivers_subtitle" />
    <fmt:message bundle="${loc}" key="local.subtitle.routes" var="routes_subtitle" />
    <fmt:message bundle="${loc}" key="local.subtitle.journal" var="journal_subtitle" />
    <fmt:message bundle="${loc}" key="local.subtitle.reg" var="reg_subtitle" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${page_title}</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="../img/favicon.png" />
</head>
<body>

<jsp:include page="../blocks/header_block.jsp" />
<jsp:include page="../blocks/login_block.jsp" />
<jsp:include page="../blocks/lang_block.jsp" />


<div class="main">
    <div class="well">

        <div class="alert alert-danger">
            <strong>Error!</strong> Login failed, please try again.
        </div>

    </div>
</div>

</body>
</html>
