<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.sign_up_fail" var="sign_up_fail_subtitle"/>
    <fmt:message bundle="${loc}" key="local.error.text" var="error_text"/>
    <fmt:message bundle="${loc}" key="local.content.account_error.sign_up_fail" var="sign_up_fail_text"/>
    <fmt:message bundle="${loc}" key="local.content.account_error.bad_login" var="bad_login_text"/>
    <fmt:message bundle="${loc}" key="local.content.account_error.bad_password" var="bad_password_text"/>
    <fmt:message bundle="${loc}" key="local.content.account_error.bad_repeat" var="bad_repeat_text"/>
    <fmt:message bundle="${loc}" key="local.content.account_error.other" var="other_error_text"/>
    <fmt:message bundle="${loc}" key="local.button.back" var="back_button"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${sign_up_fail_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>

<div class="main">
    <div class="well">
        <div class="alert alert-danger" align="center">
            <strong>${error_text}!</strong> ${sign_up_fail_text} <br/>
            <c:choose>
                <c:when test="${requestScope.result_signup == 'fail_username'}">
                    ${bad_login_text}
                </c:when>
                <c:when test="${requestScope.result_signup == 'fail_password'}">
                    ${bad_password_text}
                </c:when>
                <c:when test="${requestScope.result_signup == 'fail_repeat'}">
                    ${bad_repeat_text}
                </c:when>
                <c:otherwise>
                    ${other_error_text}
                </c:otherwise>
            </c:choose>
            <br/>
            <input type="button" class="btn btn-success margin" onclick="history.back();" value="${back_button}"/>
        </div>
    </div>
</div>

</body>
</html>

