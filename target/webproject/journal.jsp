<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>

<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.journal" var="journal_subtitle"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${journal_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="img/favicon.png"/>
</head>
<body>

<jsp:include page="blocks/header_block.jsp"/>
<jsp:include page="blocks/login_block.jsp">
    <jsp:param name="page" value="main?command=journal"/>
</jsp:include>
<jsp:include page="blocks/lang_block.jsp">
    <jsp:param name="page" value="main?command=journal"/>
</jsp:include>

<c:if test="${sessionScope.status eq 'in' and sessionScope.role eq 'admin'}">
    <jsp:include page="blocks/data/journal_data_block.jsp"/>
</c:if>

<div class="main">
    <div class="well">

        <c:if test="${sessionScope.status eq 'in'}">
            <jsp:useBean id="journal_rs" class="com.epam.project.beans.ResultSetBean" scope="session"/>
            <mytag:printtable bean="${journal_rs}"/>
        </c:if>

        <c:if test="${sessionScope.status ne 'in'}">
            <div class="alert alert-danger">
                <strong>Error!</strong> Login, please.
            </div>
        </c:if>

    </div>
</div>

</body>
</html>

