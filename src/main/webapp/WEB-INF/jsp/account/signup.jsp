<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.sign_up" var="reg_subtitle"/>
    <fmt:message bundle="${loc}" key="local.sign_in.sign_up_link" var="reg_text"/>
    <fmt:message bundle="${loc}" key="local.sign_up.enter_login_text" var="login_text"/>
    <fmt:message bundle="${loc}" key="local.sign_up.enter_password_text" var="password_text"/>
    <fmt:message bundle="${loc}" key="local.sign_up.enter_repeat_text" var="repeat_text"/>
    <fmt:message bundle="${loc}" key="local.sign_up.sign_up_button" var="reg_button"/>
    <fmt:message bundle="${loc}" key="local.input.if_empty" var="if_empty"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${reg_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="${pageContext.request.contextPath}/signup"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="${pageContext.request.contextPath}/signup"/>
</jsp:include>

<div class="main">
    <div class="well">
        <h2 align="center">${reg_text}</h2>
        <br/>
        <form action="main" method="post" class="navbar-form" role="form">
            <input type="hidden" name="command" value="signup"/>
            <table align="center" width="60%">
                <tr>
                    <td>
                        ${login_text}:
                    </td>
                    <td>
                        <input title="${login_text}" class="form-control margin" type="text" name="username" size="30"
                               required oninvalid="this.setCustomValidity('${if_empty}')"
                               oninput="setCustomValidity('')">
                    </td>
                </tr>
                <tr>
                    <td>
                        ${password_text}:
                    </td>
                    <td>
                        <input title="${password_text}" class="form-control margin" type="password" name="password"
                               size="30" required oninvalid="this.setCustomValidity('${if_empty}')"
                               oninput="setCustomValidity('')">
                    </td>
                </tr>
                <tr>
                    <td>
                        ${repeat_text}:
                    </td>
                    <td>
                        <input title="${repeat_text}" class="form-control margin" type="password" name="repeat"
                               size="30" required oninvalid="this.setCustomValidity('${if_empty}')"
                               oninput="setCustomValidity('')">
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
