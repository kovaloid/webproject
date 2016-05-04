<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.navpanel.about_page" var="about_subtitle" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${about_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="img/favicon.png" />
</head>
<body>

<jsp:include page="blocks/header_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>
<jsp:include page="blocks/login_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>
<jsp:include page="blocks/lang_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>

<div class="main">
    <div class="well">

        <h2>Об автобазе</h2>

        <table class="table">
            <tr>
                <td rowspan="4" align="center">
                    <img src="${pageContext.request.contextPath}/img/cars.jpg" height="150" width="200" />
                </td>
                <td>
                    <b>Название</b>
                </td>
                <td>
                    Автобаза "Заря"
                </td>
            </tr>
            <tr>
                <td>
                    <b>Адрес</b>
                </td>
                <td>
                    СПб, ул. Ленина, д. 10
                </td>
            </tr>
            <tr>
                <td>
                    <b>Год основания</b>
                </td>
                <td>
                    1992
                </td>
            </tr>
            <tr>
                <td>
                    <b>E-mail</b>
                </td>
                <td>
                    <a href="mailto:kov3000@yandex.ru">kov3000@yandex.ru</a>
                </td>
            </tr>
        </table>

    </div>
</div>

</body>
</html>
