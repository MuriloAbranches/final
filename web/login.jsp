<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
    <head>

        <meta charset="UTF-8">

        <title>Clínica Médica - Entrar</title>

        <link rel="shortcut icon" href="img/logo1.png" >
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>

        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />

        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Raleway|Candal">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/stylelogin.css">

    </head>
    <body>
        <%
            HttpSession sessao = request.getSession(true);
            if (!sessao.isNew()) {
                String Mens_Login = (String) sessao.getAttribute("mensagem");
                if (Mens_Login != null) {
        %>
             <h4 style="color: red;text-align: center" ><%=Mens_Login%></h4>
        <%
                }
            }
            
        %>
         <div class="row col-md-12"></div>
        <div class="row ">
            <div class="login-card">
                <h1>Entrar</h1><br>
                <form class="form-group" action="autenticador">
                    <input type="text" name="email" placeholder="Email">
                    <input type="password" name="senha" placeholder="Senha">
                    <input type="submit" name="login" class="login login-submit" value="Entrar">
                </form>

            </div>
        </div>


        <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

    

    </body>
</html>
