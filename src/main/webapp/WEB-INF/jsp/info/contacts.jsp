<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.contacts_page" var="contacts_subtitle"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.name_surname" var="name_surname_text"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.age" var="age_text"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.position" var="position_text"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.phone" var="phone_text"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.name_surname_value" var="name_surname_value_text"/>
    <fmt:message bundle="${loc}" key="local.content.contacts_page.position_value" var="position_value_text"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${contacts_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp">
    <jsp:param name="page" value="contacts"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="contacts"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="contacts"/>
</jsp:include>

<div class="main">
    <div class="well">
        <h2>${contacts_subtitle}</h2>
        <table class="table">
            <tr>
                <td rowspan="4" align="center">
                    <img src="${pageContext.request.contextPath}/img/admin.jpg" width="110" height="150"/>
                </td>
                <td>
                    <b>${name_surname_text}</b>
                </td>
                <td>
                    ${name_surname_value_text}
                </td>
            </tr>
            <tr>
                <td>
                    <b>${age_text}</b>
                </td>
                <td>
                    22
                </td>
            </tr>
            <tr>
                <td>
                    <b>${position_text}</b>
                </td>
                <td>
                    ${position_value_text}
                </td>
            </tr>
            <tr>
                <td>
                    <b>${phone_text}</b>
                </td>
                <td>
                    8 (911) 760-37-24
                </td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
