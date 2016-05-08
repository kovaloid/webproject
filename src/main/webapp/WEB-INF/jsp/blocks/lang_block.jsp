<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.lang.header" var="lang_header"/>
<fmt:message bundle="${loc}" key="local.lang.ru_button" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.lang.en_button" var="en_button"/>

<c:set var="url" scope="session" value="${param.page}" />

<div class="lang">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${lang_header}</h3>
        </div>
        <div class="panel-body">
            <form action="main" method="post" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="ru"/>
                <button type="submit" class="btn btn-success">
                    ${ru_button} <img src="${pageContext.request.contextPath}/img/RU.png" />
                </button>
            </form>
            <form action="main" method="post" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="en"/>
                <button type="submit" class="btn btn-success">
                    ${en_button} <img src="${pageContext.request.contextPath}/img/GB.png" />
                </button>
            </form>
        </div>
    </div>
</div>