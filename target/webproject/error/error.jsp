<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>Error</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../img/favicon.png"/>
</head>
<body>

<jsp:include page="../blocks/header_block.jsp"/>

<div class="main">
    <div class="well">

        <div class="alert alert-danger" align="center">
            <h1>Error!</h1>
            <p>
                The exception : <br />
                <%= exception.getMessage() %>
            </p>
        </div>

    </div>
</div>

</body>
</html>



