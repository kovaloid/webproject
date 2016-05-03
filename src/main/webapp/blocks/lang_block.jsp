<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.lang.header" var="lang_header"/>
<fmt:message bundle="${loc}" key="local.lang.ru_button" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.lang.en_button" var="en_button"/>

<div class="lang">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${lang_header}</h3>
        </div>
        <div class="panel-body">
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="ru"/>
                <input type="submit" class="btn btn-success" value="${ru_button}"/>
            </form>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="en"/>
                <input type="submit" class="btn btn-success" value="${en_button}"/>
            </form>
        </div>
    </div>
</div>