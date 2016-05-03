<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>

<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.routes" var="routes_subtitle"/>

    <fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
    <fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
    <fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
    <fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>

    <fmt:message bundle="${loc}" key="local.data.new_route" var="new_route"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${routes_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="img/favicon.png"/>
</head>
<body>

<jsp:include page="blocks/header_block.jsp"/>
<jsp:include page="blocks/login_block.jsp"/>
<jsp:include page="blocks/lang_block.jsp">
    <jsp:param name="page" value="main?command=routes" />
</jsp:include>

<div class="data">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">

            <div class="alert alert-success">
                <form action="main" method="get" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="data"/>
                    <input type="hidden" name="do" value="add"/>
                    <input placeholder="${new_route}" class="form-control margin" type="text" name="route_name"><br/>
                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>
            <div class="alert alert-warning">
                <form action="main" method="get" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="data"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input placeholder="${new_route}" class="form-control margin" type="text" name="route_name"><br/>
                    <input type="submit" class="btn btn-success" value="${update_button}"/>
                </form>
            </div>
            <div class="alert alert-danger">
                <form action="main" method="get" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="data"/>
                    <input type="hidden" name="do" value="remove"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input type="submit" class="btn btn-success" value="${remove_button}"/>
                </form>
            </div>

        </div>
    </div>
</div>


<div class="main">
    <div class="well">

        <jsp:useBean id="routes_rs" class="com.epam.project.beans.ResultSetBean" scope="session"/>
        <mytag:printtable bean="${routes_rs}"/>

    </div>
</div>

</body>
</html>

