<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.subtitle.about_page" var="about_subtitle" />
    <fmt:message bundle="${loc}" key="local.content.about_page.name" var="name_text" />
    <fmt:message bundle="${loc}" key="local.content.about_page.year" var="year_text" />
    <fmt:message bundle="${loc}" key="local.content.about_page.address" var="address_text" />
    <fmt:message bundle="${loc}" key="local.content.about_page.name_value" var="name_value_text" />
    <fmt:message bundle="${loc}" key="local.content.about_page.address_value" var="address_value_text" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${about_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="../../../img/favicon.png" />
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="about" />
</jsp:include>

<div class="main">
    <div class="well">
        <h2>${about_subtitle}</h2>
        <table class="table">
            <tr>
                <td rowspan="4" align="center">
                    <img src="${pageContext.request.contextPath}/img/cars.jpg" height="150" width="200" />
                </td>
                <td>
                    <b>${name_text}</b>
                </td>
                <td>
                    ${name_value_text}
                </td>
            </tr>
            <tr>
                <td>
                    <b>${address_text}</b>
                </td>
                <td>
                    ${address_value_text}
                </td>
            </tr>
            <tr>
                <td>
                    <b>${year_text}</b>
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
