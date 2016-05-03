<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.menu.cars_button" var="cars_button" />
    <fmt:message bundle="${loc}" key="local.menu.drivers_button" var="drivers_button" />
    <fmt:message bundle="${loc}" key="local.menu.routes_button" var="routes_button" />
    <fmt:message bundle="${loc}" key="local.menu.journal_button" var="journal_button" />
    <fmt:message bundle="${loc}" key="local.main_wall.head" var="wall_head" />
    <fmt:message bundle="${loc}" key="local.main_wall.text_1" var="wall_text_1" />
    <fmt:message bundle="${loc}" key="local.main_wall.text_2" var="wall_text_2" />
    <fmt:message bundle="${loc}" key="local.main_wall.text_3" var="wall_text_3" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="img/favicon.png" />
</head>
<body>

<jsp:include page="blocks/header_block.jsp">
    <jsp:param name="page" value="main" />
</jsp:include>
<jsp:include page="blocks/login_block.jsp" />
<jsp:include page="blocks/lang_block.jsp" />

<div class="main">
    <div class="well">

        <div class="col-xs-1 col-md-4">
            <a href="#" class="thumbnail">
                <img src="img/logo.jpg">
            </a>
        </div>

        <h2>${wall_head}</h2>
        <p>${wall_text_1}</p>
        <p>${wall_text_2}</p>
        <p>${wall_text_3}</p>

        <br/>

        <br><br>
        <div class="alert alert-warning menu">
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="cars"/>
                <input type="submit" class="btn btn-success" value="${cars_button}"/>
            </form>
            <br/>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="drivers"/>
                <input type="submit" class="btn btn-success" value="${drivers_button}"/>
            </form>
            <br/>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="routes"/>
                <input type="submit" class="btn btn-success" value="${routes_button}"/>
            </form>
            <br/>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="journal"/>
                <input type="submit" class="btn btn-success" value="${journal_button}"/>
            </form>
        </div>

    </div>
</div>

</body>
</html>
