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
<fmt:message bundle="${loc}" key="local.data.cars.choose_driver_select" var="choose_driver"/>
<fmt:message bundle="${loc}" key="local.data.cars.choose_color_select" var="choose_color"/>
<fmt:message bundle="${loc}" key="local.data.cars.choose_brand_select" var="choose_mark"/>
<fmt:message bundle="${loc}" key="local.data.cars.car_number_input" var="car_num"/>

<div class="data_right">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${data_header}</h3>
        </div>
        <div class="panel-body">

            <div class="alert alert-success">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="cars"/>
                    <input type="hidden" name="do" value="add"/>
                    <input placeholder="${car_num}" class="form-control margin" type="text" name="car_number"><br/>
                    <select title="color" required name="color" class="form-control margin">
                        <option selected disabled>${choose_color}</option>
                        <c:forEach var="color" items="${sessionScope.colors_list}">
                            <option value="${color}">${color}</option>
                        </c:forEach>
                    </select>
                    <select title="brand" required name="brand" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>
                        <c:forEach var="car" items="${sessionScope.cars_list}">
                            <option value="${car}">${car}</option>
                        </c:forEach>
                    </select>
                    <select title="driver" required name="driver_id" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>

                        <mytag:select_box bean="${sessionScope.drivers_list}"/>

                    </select>

                    <br/>

                    <label><input name="ready" type="radio" value="yes" checked> Исправен</label><br>
                    <label><input name="ready" type="radio" value="no"> Не исправен</label><br/>


                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>

            <div class="alert alert-danger">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="cars"/>
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
                    <input type="hidden" name="command" value="cars"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input placeholder="${car_num}" class="form-control margin" type="text" name="car_number"><br/>
                    <select title="color" required name="color" class="form-control margin">
                        <option selected disabled>${choose_color}</option>

                        <c:forEach var="color" items="${sessionScope.colors_list}">
                            <option value="${color}">${color}</option>
                        </c:forEach>

                    </select>
                    <select title="brand" required name="brand" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>

                        <c:forEach var="car" items="${sessionScope.cars_list}">
                            <option value="${car}">${car}</option>
                        </c:forEach>

                    </select>
                    <select title="driver" required name="driver_id" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>


                        <mytag:select_box bean="${sessionScope.drivers_list}"/>


                    </select>

                    <br/>

                    <label><input name="ready" type="radio" value="yes" checked> Исправен</label><br>
                    <label><input name="ready" type="radio" value="no"> Не исправен</label><br/>

                    <input type="submit" class="btn btn-success" value="${update_button}"/>
                </form>
            </div>

        </div>
    </div>
</div>