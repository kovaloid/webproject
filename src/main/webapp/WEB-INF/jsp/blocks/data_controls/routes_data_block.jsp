<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
<fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
<fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
<fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>
<fmt:message bundle="${loc}" key="local.data.routes.route_name_input" var="new_route"/>
<fmt:message bundle="${loc}" key="local.data.routes.length_input" var="length_input"/>
<fmt:message bundle="${loc}" key="local.data.routes.price_input" var="price_input"/>

<div class="data_right">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">
            <div class="alert alert-success">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="routes"/>
                    <input type="hidden" name="do" value="add"/>
                    <input placeholder="${new_route}" class="form-control margin" type="text" name="route_name"><br/>
                    <input placeholder="${length_input}" class="form-control margin" type="text" name="length"><br/>
                    <input placeholder="${price_input}" class="form-control margin" type="text" name="price"><br/>
                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>
            <div class="alert alert-danger">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="routes"/>
                    <input type="hidden" name="do" value="remove"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input type="submit" class="btn btn-success" value="${remove_button}"/>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="data_left">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">
            <div class="alert alert-warning">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="routes"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input placeholder="${new_route}" class="form-control margin" type="text" name="route_name"><br/>
                    <input placeholder="${length_input}" class="form-control margin" type="text" name="length"><br/>
                    <input placeholder="${price_input}" class="form-control margin" type="text" name="price"><br/>
                    <input type="submit" class="btn btn-success" value="${update_button}"/>
                </form>
            </div>
        </div>
    </div>
</div>