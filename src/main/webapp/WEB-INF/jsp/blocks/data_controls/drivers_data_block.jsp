<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
<fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
<fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
<fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>
<fmt:message bundle="${loc}" key="local.data.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.data.second_name" var="second_name"/>

<div class="data">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">

            <div class="alert alert-success">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="drivers"/>
                    <input type="hidden" name="do" value="add"/>
                    <input placeholder="${first_name}" class="form-control margin" type="text" name="name"><br/>
                    <input placeholder="${second_name}" class="form-control margin" type="text" name="surname"><br/>

                    <select title="gender" required name="gender" class="form-control margin">
                        <option selected disabled>Выбери маршрут</option>
                        <option value="man">MAN</option>
                        <option value="woman">WOMAN</option>
                    </select><br/>
                    <input placeholder="phone" class="form-control margin" type="text" name="phone"><br/>
                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>
            <div class="alert alert-warning">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="drivers"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input placeholder="${first_name}" class="form-control margin" type="text" name="name"><br/>
                    <input placeholder="${second_name}" class="form-control margin" type="text" name="surname"><br/>
                    <select title="gender" required name="gender" class="form-control margin">
                        <option selected disabled>Выбери маршрут</option>
                        <option value="man">MAN</option>
                        <option value="woman">WOMAN</option>
                    </select><br/>
                    <input placeholder="phone" class="form-control margin" type="text" name="phone"><br/>
                    <input type="submit" class="btn btn-success" value="${update_button}"/>
                </form>
            </div>
            <div class="alert alert-danger">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="drivers"/>
                    <input type="hidden" name="do" value="remove"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input type="submit" class="btn btn-success" value="${remove_button}"/>
                </form>
            </div>

        </div>
    </div>
</div>