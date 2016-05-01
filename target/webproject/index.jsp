<%--
  Created by IntelliJ IDEA.
  User: 803337
  Date: 30.04.2016
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <title>Автобаза</title>
      <style type="text/css">
          .header {
              width: 100%;
              height: 110px;
              background: #ccc;
              padding: 5px;
              border: solid 1px black;
              float: left;
          }
          .login {
              width: 200px;
              height: 70px;
              background: #fc0;
              padding: 5px;
              border: solid 1px black;
              float: left;
              position: relative;
              top: 80px;
              left: 10px;
          }
          .footer {
              width: 200px;
              padding: 5px;
              border: solid 1px black;
              float: left;
              top: 80px;
              left: -70px;
          }
          .main {
              width: 200px;
              padding: 5px;
              border: solid 1px black;
              float: left;
              top: 80px;
              left: -70px;
          }
          .logo {
              width: 300px;
              padding: 5px;
              border: solid 1px black;
              float: left;
          }
          .title {
              width: auto;
              padding: 5px;
              border: solid 1px black;
              float: left;
              left: 350px;
          }
      </style>
  </head>
  <body>
  <div class="header">
      <div class="logo">
        <img src="img/logo.jpg" width="100" height="100">
      </div>
      <div class="title">Автобаза</div>
  </div>
  <div class="login">
      <form action="LoginController" method="post">
          <input type="text" name="username" placeholder="логин" /><br />
          <input type="password" name="password" placeholder="пароль" /><br />
          <input type="submit" value="Войти" />
      </form>
  </div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

  <a href="cars.jsp">cars</a>
  <a href="drivers.jsp">drivers</a>
  <form action="Cars" method="post">
      <input type="submit" value="Cars" />
  </form>


  </body>
</html>
