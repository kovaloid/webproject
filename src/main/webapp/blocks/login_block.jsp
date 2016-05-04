<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.signin.header" var="signin_header"/>
<fmt:message bundle="${loc}" key="local.signin.login" var="login_text"/>
<fmt:message bundle="${loc}" key="local.signin.password" var="password_text"/>
<fmt:message bundle="${loc}" key="local.signin.enter" var="enter_button"/>
<fmt:message bundle="${loc}" key="local.signin.reg" var="reg_button"/>
<fmt:message bundle="${loc}" key="local.signin.welcome" var="welcome_text"/>

<c:set var="successUrl" scope="session" value="${param.page}" />

<div class="login">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${signin_header}</h3>
        </div>
        <div class="panel-body">



            <c:if test="${sessionScope.status eq 'in'}">
                <p>${welcome_text}, <c:out value="${sessionScope.login}" /></p>
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="logout"/>
                    <button type="submit" class="btn btn-success margin">Sign Out</button>
                </form>
            </c:if>

            <c:if test="${sessionScope.status ne 'in'}">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="login"/>
                    <input placeholder="${login_text}" class="form-control margin" type="text" name="username"><br/>
                    <input placeholder="${password_text}" class="form-control margin" type="password" name="password"><br/>
                    <button type="submit" class="btn btn-success margin">${enter_button}</button>
                    <a href="${pageContext.request.contextPath}/signup">${reg_button}</a>
                </form>
            </c:if>

        </div>
    </div>
</div>