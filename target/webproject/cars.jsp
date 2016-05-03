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
    <fmt:message bundle="${loc}" key="local.subtitle.cars" var="cars_subtitle"/>

    <fmt:message bundle="${loc}" key="local.data.header" var="data_header"/>
    <fmt:message bundle="${loc}" key="local.data.add_button" var="add_button"/>
    <fmt:message bundle="${loc}" key="local.data.update_button" var="update_button"/>
    <fmt:message bundle="${loc}" key="local.data.remove_button" var="remove_button"/>

    <fmt:message bundle="${loc}" key="local.data.choose_driver_text" var="choose_driver"/>
    <fmt:message bundle="${loc}" key="local.data.choose_color_text" var="choose_color"/>
    <fmt:message bundle="${loc}" key="local.data.choose_mark_text" var="choose_mark"/>
    <fmt:message bundle="${loc}" key="local.data.car_num" var="car_num"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${cars_subtitle} - ${page_title}</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="img/favicon.png"/>
</head>
<body>

<jsp:include page="blocks/header_block.jsp"/>
<jsp:include page="blocks/login_block.jsp"/>
<jsp:include page="blocks/lang_block.jsp"/>


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
                    <input placeholder="${car_num}" class="form-control margin" type="text" name="num"><br/>
                    <select title="color" required name="color" class="form-control margin">
                        <option selected disabled>${choose_color}</option>
                        <option value="синий">синий</option>
                        <option value="красный">красный</option>
                        <option value="желтый">желтый</option>
                        <option value="зеленый">зеленый</option>
                        <option value="оранжевый">оранжевый</option>
                        <option value="коричневый">коричневый</option>
                        <option value="черный">черный</option>
                        <option value="белый">белый</option>
                    </select>
                    <select title="mark" required name="mark" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>
                        <option value="Газель">Газель</option>
                        <option value="Мерседес">Мерседес</option>
                        <option value="Форд">Форд</option>
                        <option value="Лада">Лада</option>
                        <option value="Волга">Волга</option>
                    </select>
                    <select title="driver" required name="driver" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>


                        <jsp:useBean id="drivers_set" class="com.epam.project.beans.DriverSetBean" scope="session"/>
                        <c:forEach var="i" begin="0" end="${drivers_set.size}">
                            <option value="${drivers_set.listIDs[i]}">
                                <c:out value="${drivers_set.listIDs[i]} - ${drivers_set.listNames[i]}"/>
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
                    <input placeholder="${car_num}" class="form-control margin" type="text" name="num"><br/>
                    <select title="color" required name="color" class="form-control margin">
                        <option selected disabled>${choose_color}</option>
                        <option value="синий">синий</option>
                        <option value="красный">красный</option>
                        <option value="желтый">желтый</option>
                        <option value="зеленый">зеленый</option>
                        <option value="оранжевый">оранжевый</option>
                        <option value="коричневый">коричневый</option>
                        <option value="черный">черный</option>
                        <option value="белый">белый</option>
                    </select>
                    <select title="mark" required name="mark" class="form-control margin">
                        <option selected disabled>${choose_mark}</option>
                        <option value="Газель">Газель</option>
                        <option value="Мерседес">Мерседес</option>
                        <option value="Форд">Форд</option>
                        <option value="Лада">Лада</option>
                        <option value="Волга">Волга</option>
                    </select>
                    <select title="driver" required name="driver" class="form-control margin">
                        <option selected disabled>${choose_driver}</option>


                        <c:forEach var="i" begin="0" end="${drivers_set.size}">
                            <option value="${drivers_set.listIDs[i]}">
                                <c:out value="${drivers_set.listIDs[i]} - ${drivers_set.listNames[i]}"/>
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


<div class="main">
    <div class="well">

        <jsp:useBean id="cars_rs" class="com.epam.project.beans.ResultSetBean" scope="session"/>
        <mytag:printtable bean="${cars_rs}"/>

    </div>
</div>

</body>
</html>

