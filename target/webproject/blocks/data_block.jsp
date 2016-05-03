<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
<fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
<fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
<fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>

<div class="data">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">
            <form action="main" method="get" class="navbar-form" role="form">

                <input placeholder="name" class="form-control margin" type="text" name="first"><br/>
                <input placeholder="secname" class="form-control margin" type="text" name="second"><br/>


                <input type="hidden" name="command" value="data"/>
                <input type="hidden" name="do" value="add"/>
                <input type="submit" class="btn btn-success" value="${add_button}"/>
            </form>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="data"/>
                <input type="hidden" name="do" value="update"/>
                <input type="submit" class="btn btn-success" value="${update_button}"/>
            </form>
            <form action="main" method="get" class="navbar-form" role="form">
                <input type="hidden" name="command" value="data"/>
                <input type="hidden" name="do" value="remove"/>
                <input type="submit" class="btn btn-success" value="${remove_button}"/>
            </form>
            <p>Hello</p>
        </div>
    </div>
</div>