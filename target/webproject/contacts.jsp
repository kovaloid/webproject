<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.navpanel.contacts_page" var="contacts_subtitle" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${contacts_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="img/favicon.png" />
</head>
<body>

<jsp:include page="blocks/header_block.jsp">
    <jsp:param name="page" value="contacts" />
</jsp:include>
<jsp:include page="blocks/login_block.jsp" />
<jsp:include page="blocks/lang_block.jsp">
    <jsp:param name="page" value="contacts" />
</jsp:include>

<div class="main">
    <div class="well">

        <h2>Контакты</h2>
        <p>Ковалев Артем - 8(911)760-37-24</p>

    </div>
</div>

</body>
</html>
