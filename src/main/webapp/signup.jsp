<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.reg" var="reg_subtitle"/>
    <fmt:message bundle="${loc}" key="local.signin.reg" var="reg_text"/>
    <fmt:message bundle="${loc}" key="local.signup.enter_login" var="login_text"/>
    <fmt:message bundle="${loc}" key="local.signup.enter_password" var="password_text"/>
    <fmt:message bundle="${loc}" key="local.signup.enter_repeat" var="repeat_text"/>
    <fmt:message bundle="${loc}" key="local.signup.reg_button" var="reg_button"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${reg_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="img/favicon.png"/>
</head>
<body>

<jsp:include page="blocks/header_block.jsp"/>
<jsp:include page="blocks/login_block.jsp"/>
<jsp:include page="blocks/lang_block.jsp">
    <jsp:param name="page" value="signup" />
</jsp:include>

<div class="main">
    <div class="well">

        <h2 align="center">${reg_text}</h2>
        <br/>
        <form action="main" method="post" class="navbar-form" role="form">
            <input type="hidden" name="command" value="reg"/>
            <table align="center" width="60%">
                <tr>
                    <td>
                        ${login_text}:
                    </td>
                    <td>
                        <input title="${login_text}" class="form-control margin" type="text" name="username" size="30">
                    </td>
                </tr>
                <tr>
                    <td>
                        ${password_text}:
                    </td>
                    <td>
                        <input title="${password_text}" class="form-control margin" type="password" name="password" size="30">
                    </td>
                </tr>
                <tr>
                    <td>
                        ${repeat_text}:
                    </td>
                    <td>
                        <input title="${repeat_text}" class="form-control margin" type="password" name="repeat" size="30">
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <br/>
                        <button type="submit" class="btn btn-success margin">${reg_button}</button>
                    </td>
                </tr>
            </table>
        </form>

    </div>
</div>

</body>
</html>
