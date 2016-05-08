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
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="../../../img/favicon.png" />
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp">
    <jsp:param name="page" value="contacts" />
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="contacts" />
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="contacts" />
</jsp:include>

<div class="main">
    <div class="well">

        <h2>Контакты</h2>
        <table class="table">
            <tr>
                <td rowspan="4" align="center">
                    <img src="${pageContext.request.contextPath}/img/admin.jpg" width="110" height="150" />
                </td>
                <td>
                    <b>Ф.И.О.</b>
                </td>
                <td>
                    Ковалев Артем Дмитриевич
                </td>
            </tr>
            <tr>
                <td>
                    <b>Возраст</b>
                </td>
                <td>
                    22
                </td>
            </tr>
            <tr>
                <td>
                    <b>Должность</b>
                </td>
                <td>
                    Администратор
                </td>
            </tr>
            <tr>
                <td>
                    <b>Телефон</b>
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
