<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.title" var="page_title" />
    <fmt:message bundle="${loc}" key="local.navpanel.main_page" var="main_page" />
    <fmt:message bundle="${loc}" key="local.navpanel.about_page" var="about_page" />
    <fmt:message bundle="${loc}" key="local.navpanel.contacts_page" var="contacts_page" />
    <fmt:message bundle="${loc}" key="local.signin.header" var="signin_header" />
    <fmt:message bundle="${loc}" key="local.signin.login" var="login_text" />
    <fmt:message bundle="${loc}" key="local.signin.password" var="password_text" />
    <fmt:message bundle="${loc}" key="local.signin.enter" var="enter_button" />
    <fmt:message bundle="${loc}" key="local.signin.reg" var="reg_button" />
    <fmt:message bundle="${loc}" key="local.lang.header" var="lang_header" />
    <fmt:message bundle="${loc}" key="local.lang.ru_button" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.lang.en_button" var="en_button" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="author" content="Artem Kovalev" />
    <title>${page_title}</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../css/template.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="../img/favicon.png" />
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">${page_title}</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="../index.jsp">${main_page}</a></li>
                <li><a href="../about.jsp">${about_page}</a></li>
                <li><a href="../contacts.jsp">${contacts_page}</a></li>
            </ul>
        </div>
    </div>
</div>


<div class="lang">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${lang_header}</h3>
        </div>
        <div class="panel-body">
            <form action="DispatcherServlet" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="ru"/>
                <input type="submit" class="btn btn-success" value="${ru_button}"/>
            </form>
            <form action="DispatcherServlet" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="lang" value="en"/>
                <input type="submit" class="btn btn-success" value="${en_button}"/>
            </form>
        </div>
    </div>
</div>


<div class="login">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${signin_header}</h3>
        </div>
        <div class="panel-body">
            <p>Username: ${requestScope['user'].username}</p>
            <form action="DispatcherServlet" method="post" class="navbar-form" role="form">
                <input type="hidden" name="command" value="logout"/>
                <button type="submit" class="btn btn-success margin">${enter_button}</button>
            </form>
        </div>
    </div>
</div>


<div class="main">
    <div class="well">

        <div class="alert alert-success">
            <strong>Success!</strong> Welcome ${requestScope['user'].username}.
        </div>

    </div>
</div>

</body>
</html>
