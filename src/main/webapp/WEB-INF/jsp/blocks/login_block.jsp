<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.sign_in.header" var="signin_header"/>
<fmt:message bundle="${loc}" key="local.sign_in.login_input" var="login_text"/>
<fmt:message bundle="${loc}" key="local.sign_in.password_input" var="password_text"/>
<fmt:message bundle="${loc}" key="local.sign_in.log_in_button" var="enter_button"/>
<fmt:message bundle="${loc}" key="local.sign_in.log_out_button" var="log_out_button"/>
<fmt:message bundle="${loc}" key="local.sign_in.sign_up_link" var="reg_button"/>
<fmt:message bundle="${loc}" key="local.sign_in.welcome_text" var="welcome_text"/>
<fmt:message bundle="${loc}" key="local.input.if_empty" var="if_empty"/>
<fmt:message bundle="${loc}" key="local.sign_in.access_level_text" var="access_level_text"/>

<c:set var="successRedirectURL" scope="session" value="${param.page}"/>

<div class="login">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${signin_header}</h3>
        </div>
        <div class="panel-body">
            <c:if test="${sessionScope.status eq 'in'}">
                <jsp:useBean id="user" class="com.epam.project.beans.UserBean" scope="session"/>
                <p>${welcome_text}, ${user.login}</p>
                <p>${access_level_text}: ${user.role}</p>
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="logout"/>
                    <button type="submit" class="btn btn-success margin">${log_out_button}</button>
                </form>
            </c:if>
            <c:if test="${sessionScope.status ne 'in'}">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="login"/>
                    <input placeholder="${login_text}" class="form-control margin" type="text" name="login" required
                           oninvalid="this.setCustomValidity('${if_empty}')" oninput="setCustomValidity('')"><br/>
                    <input placeholder="${password_text}" class="form-control margin" type="password" name="password"
                           required oninvalid="this.setCustomValidity('${if_empty}')"
                           oninput="setCustomValidity('')"><br/>
                    <button type="submit" class="btn btn-success margin">${enter_button}</button>
                    <a href="${pageContext.request.contextPath}/signup">${reg_button}</a>
                </form>
            </c:if>
        </div>
    </div>
</div>