<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
<fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
<fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
<fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>
<fmt:message bundle="${loc}" key="local.data.journal.date_out_text" var="time_out"/>
<fmt:message bundle="${loc}" key="local.data.journal.date_in_text" var="time_in"/>
<fmt:message bundle="${loc}" key="local.data.journal.choose_car_select" var="choose_car"/>
<fmt:message bundle="${loc}" key="local.data.journal.choose_route_select" var="choose_route"/>
<fmt:message bundle="${loc}" key="local.data.journal.day_input" var="day"/>
<fmt:message bundle="${loc}" key="local.data.journal.month_select" var="monthSelect"/>
<fmt:message bundle="${loc}" key="local.data.journal.year_input" var="year"/>

<div class="data_right">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">

            <div class="alert alert-success">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="journal"/>
                    <input type="hidden" name="do" value="add"/>

                    <table>
                        <tr>
                            <td colspan="3">
                                ${time_out}:
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input placeholder="${day}" class="form-control margin" type="text" name="day_out"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <select title="${monthSelect}" required name="month_out" class="form-control">
                                    <option selected disabled>${monthSelect}</option>

                                    <c:forEach var="month" items="${sessionScope.months_list}">
                                        <option value="${month}">${month}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_out"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>

                    <select title="num" required name="car_id" class="form-control margin">
                        <option selected disabled>${choose_car}</option>

                        <mytag:select_box bean="${sessionScope.cars_list}" />

                    </select>
                    <select title="route" required name="route_id" class="form-control margin">
                        <option selected disabled>${choose_route}</option>

                        <mytag:select_box bean="${sessionScope.routes_list}" />

                    </select>
                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>

            <div class="alert alert-danger">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="journal"/>
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
                    <input type="hidden" name="command" value="journal"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>



                    <table>
                        <tr>
                            <td colspan="3">
                                ${time_in}:
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input placeholder="${day}" class="form-control margin" type="text" name="day_in"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <select title="${monthSelect}" required name="month_in" class="form-control">
                                    <option selected disabled>${monthSelect}</option>

                                    <c:forEach var="month" items="${sessionScope.months_list}">
                                        <option value="${month}">${month}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_in"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>


                    <input type="submit" class="btn btn-success" value="${update_button}"/>
                </form>
            </div>

        </div>
    </div>
</div>