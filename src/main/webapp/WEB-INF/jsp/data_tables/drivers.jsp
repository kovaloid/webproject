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
    <fmt:message bundle="${loc}" key="local.subtitle.drivers" var="drivers_subtitle"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${drivers_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="main?command=drivers"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="main?command=drivers"/>
</jsp:include>



<c:if test="${sessionScope.status eq 'in' and sessionScope.user.role eq 'admin'}">
    <jsp:include page="/WEB-INF/jsp/blocks/data_controls/drivers_data_block.jsp"/>
</c:if>

<div class="main">
    <div class="well">

        <c:if test="${sessionScope.status eq 'in'}">
            <mytag:print_table bean="${sessionScope.drivers_table}"/>
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

