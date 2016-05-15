<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.subtitle.cars" var="cars_button"/>
<fmt:message bundle="${loc}" key="local.subtitle.drivers" var="drivers_button"/>
<fmt:message bundle="${loc}" key="local.subtitle.routes" var="routes_button"/>
<fmt:message bundle="${loc}" key="local.subtitle.journal" var="journal_button"/>

<br><br>
<div class="alert alert-warning menu">
    <form action="main" method="get" class="navbar-form" role="form">
        <input type="hidden" name="command" value="cars"/>
        <input type="submit" class="btn btn-success" value="${cars_button}"/>
    </form>
    <br/>
    <form action="main" method="get" class="navbar-form" role="form">
        <input type="hidden" name="command" value="drivers"/>
        <input type="submit" class="btn btn-success" value="${drivers_button}"/>
    </form>
    <br/>
    <form action="main" method="get" class="navbar-form" role="form">
        <input type="hidden" name="command" value="routes"/>
        <input type="submit" class="btn btn-success" value="${routes_button}"/>
    </form>
    <br/>
    <form action="main" method="get" class="navbar-form" role="form">
        <input type="hidden" name="command" value="journal"/>
        <input type="submit" class="btn btn-success" value="${journal_button}"/>
    </form>
</div>