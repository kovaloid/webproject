<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
<fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
<fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
<fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>
<fmt:message bundle="${loc}" key="local.data.time_out_text" var="time_out"/>
<fmt:message bundle="${loc}" key="local.data.time_in_text" var="time_in"/>
<fmt:message bundle="${loc}" key="local.data.choose_car_text" var="choose_car"/>
<fmt:message bundle="${loc}" key="local.data.choose_route_text" var="choose_route"/>
<fmt:message bundle="${loc}" key="local.data.time_day" var="day"/>
<fmt:message bundle="${loc}" key="local.data.time_month" var="month"/>
<fmt:message bundle="${loc}" key="local.data.time_year" var="year"/>

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
                                <input placeholder="${month}" class="form-control margin" type="text" name="month_out"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_out"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>

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
                                <input placeholder="${month}" class="form-control margin" type="text" name="month_in"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_in"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>

                    <select title="num" required name="num" class="form-control margin">
                        <option selected disabled>${choose_car}</option>

                        <jsp:useBean id="cars_set" class="com.epam.project.beans.CarSetBean" scope="session"/>
                        <c:forEach var="i" begin="0" end="${cars_set.size}">
                            <option value="${cars_set.listIDs[i]}">
                                <c:out value="${cars_set.listIDs[i]} - ${cars_set.listNames[i]}"/>
                            </option>
                        </c:forEach>

                    </select>
                    <select title="route" required name="route" class="form-control margin">
                        <option selected disabled>${choose_route}</option>

                        <jsp:useBean id="routes_set" class="com.epam.project.beans.RouteSetBean" scope="session"/>
                        <c:forEach var="i" begin="0" end="${routes_set.size}">
                            <option value="${routes_set.listIDs[i]}">
                                <c:out value="${routes_set.listIDs[i]} - ${routes_set.listNames[i]}"/>
                            </option>
                        </c:forEach>

                    </select>
                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>
            <div class="alert alert-warning">
                <form action="main" method="get" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="data"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>

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
                                <input placeholder="${month}" class="form-control margin" type="text" name="month_out"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_out"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>

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
                                <input placeholder="${month}" class="form-control margin" type="text" name="month_in"
                                       size="1" maxlength="2">
                            </td>
                            <td>
                                <input placeholder="${year}" class="form-control margin" type="text" name="year_in"
                                       size="2" maxlength="4">
                            </td>
                        </tr>
                    </table>


                    <select title="num" required name="num" class="form-control margin">
                        <option selected disabled>${choose_car}</option>

                        <c:forEach var="i" begin="0" end="${cars_set.size}">
                            <option value="${cars_set.listIDs[i]}">
                                <c:out value="${cars_set.listIDs[i]} - ${cars_set.listNames[i]}"/>
                            </option>
                        </c:forEach>

                    </select>
                    <select title="route" required name="route" class="form-control margin">
                        <option selected disabled>${choose_route}</option>

                        <c:forEach var="i" begin="0" end="${routes_set.size}">
                            <option value="${routes_set.listIDs[i]}">
                                <c:out value="${routes_set.listIDs[i]} - ${routes_set.listNames[i]}"/>
                            </option>
                        </c:forEach>

                    </select>
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