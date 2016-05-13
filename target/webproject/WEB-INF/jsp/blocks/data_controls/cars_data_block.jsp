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
<fmt:message bundle="${loc}" key="local.data.choose_driver_text" var="choose_driver"/>
<fmt:message bundle="${loc}" key="local.data.choose_color_text" var="choose_color"/>
<fmt:message bundle="${loc}" key="local.data.choose_mark_text" var="choose_mark"/>
<fmt:message bundle="${loc}" key="local.data.car_num" var="car_num"/>

<div class="data">
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
                        <option value="blue">синий</option>
                        <option value="red">красный</option>
                        <option value="yellow">желтый</option>
                        <option value="green">зеленый</option>
                        <option value="orange">оранжевый</option>
                        <option value="brown">коричневый</option>
                        <option value="black">черный</option>
                        <option value="white">белый</option>
                    </select>
                    <select title="brand" required name="brand" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>
                        <option value="Gazel">Газель</option>
                        <option value="Mercedez">Мерседес</option>
                        <option value="Ford">Форд</option>
                        <option value="Lada">Лада</option>
                        <option value="Volga">Волга</option>
                    </select>
                    <select title="driver" required name="driver_id" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>

                        <mytag:select_box bean="${sessionScope.drivers_select}" />

                    </select>
                    <!--<select title="driver" required name="ready" class="form-control margin">
                        <option selected disabled>готов ли?</option>
                        <option value="yes">готов</option>
                        <option value="no">не готов</option>

                    </select>--><br />
                    <label><input name="ready" type="radio" value="yes" checked> Исправен</label><br>
                    <label><input name="ready" type="radio" value="no"> Не исправен</label><br />


                    <input type="submit" class="btn btn-success" value="${add_button}"/>
                </form>
            </div>
            <div class="alert alert-warning">
                <form action="main" method="post" class="navbar-form" role="form">
                    <input type="hidden" name="command" value="cars"/>
                    <input type="hidden" name="do" value="update"/>
                    <input placeholder="ID" class="form-control margin" type="text" name="id"><br/>
                    <input placeholder="${car_num}" class="form-control margin" type="text" name="car_number"><br/>
                    <select title="color" required name="color" class="form-control margin">
                        <option selected disabled>${choose_color}</option>
                        <option value="blue">синий</option>
                        <option value="red">красный</option>
                        <option value="yellow">желтый</option>
                        <option value="green">зеленый</option>
                        <option value="orange">оранжевый</option>
                        <option value="brown">коричневый</option>
                        <option value="black">черный</option>
                        <option value="white">белый</option>
                    </select>
                    <select title="brand" required name="brand" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>
                        <option value="Gazel">Газель</option>
                        <option value="Mercedez">Мерседес</option>
                        <option value="Ford">Форд</option>
                        <option value="Lada">Лада</option>
                        <option value="Volga">Волга</option>
                    </select>
                    <select title="driver" required name="driver_id" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>


                        <mytag:select_box bean="${sessionScope.drivers_select}" />


                    </select>

                    <!--<select title="driver" required name="ready" class="form-control margin">
                        <option selected disabled>готов ли?</option>
                        <option value="yes">готов</option>
                        <option value="no">не готов</option>

                    </select>--><br />
                    <label><input name="ready" type="radio" value="yes" checked> Исправен</label><br>
                    <label><input name="ready" type="radio" value="no"> Не исправен</label><br />
                    
                    <input type="submit" class="btn btn-success" value="${update_button}"/>
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