<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Administrativo</title>

        <link rel="shortcut icon" href="../img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">

        <!-- Add custom CSS here -->
        <link href="css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
        <!-- Page Specific CSS -->
        <link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
    </head>

    <body>

        <%
            String PC_login = null;
            HttpSession PC_sessao = request.getSession(false);
            if (PC_sessao != null) {

                PC_login = (String) PC_sessao.getAttribute("Login");
                if (PC_login == null) {
                    PC_sessao.setAttribute("mensagem", "Acesso proibido sem login!");
                    response.sendRedirect("index.html");
                }
            } else {
                PC_sessao.removeAttribute("mensagem");
                PC_sessao.removeAttribute("Login");
            }
        %>

        <div id="wrapper">

            <!-- Sidebar -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">Clínica Médica</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li class="active"><a href="index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="../administrativo/medicos"><i class="fa fa-edit"></i> Médicos</a></li>
                        <li><a href="../administrativo/pacientes"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li><a href="../administrativo/consultas"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        
                    </ul>

                    <!-- Mensagens-->
                    <ul class="nav navbar-nav navbar-right navbar-user">

                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= PC_login %> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-user"></i> Perfil</a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i> Inbox</a></li>
                                <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="../index.html"><i class="fa fa-power-off"></i> Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        <h1>Administrativo</h1>
                        <ol class="breadcrumb">
                            <li class="active"><i class="fa fa-dashboard"></i> Inicio</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <div class="col-lg-3">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i class="fa fa-medkit fa-5x"></i>
                                    </div>
                                    <div class="col-xs-6 text-right">
                                        
                                    </div>
                                </div>
                            </div>
                            <a href="../administrativo/medicos">
                                <div class="panel-footer announcement-bottom">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            Médicos
                                        </div>
                                        <div class="col-xs-6 text-right">
                                            <i class="fa fa-arrow-circle-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i class="fa fa-user fa-5x"></i>
                                    </div>
                                    <div class="col-xs-6 text-right">
                                        
                                    </div>
                                </div>
                            </div>
                            <a href="../administrativo/pacientes">
                                <div class="panel-footer announcement-bottom">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            Pacientes
                                        </div>
                                        <div class="col-xs-6 text-right">
                                            <i class="fa fa-arrow-circle-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i class="fa fa-hospital-o fa-5x"></i>
                                    </div>
                                    <div class="col-xs-6 text-right">
                                        
                                    </div>
                                </div>
                            </div>
                            <a href="../administrativo/consultas">
                                <div class="panel-footer announcement-bottom">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            Agendamentos
                                        </div>
                                        <div class="col-xs-6 text-right">
                                            <i class="fa fa-arrow-circle-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    
                </div><!-- /.row -->                
            </div><!-- /#page-wrapper -->
        </div><!-- /#wrapper -->

        <!-- JavaScript -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.js"></script>

        <!-- Page Specific Plugins -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
        <script src="js/morris/chart-data-morris.js"></script>
        <script src="js/tablesorter/jquery.tablesorter.js"></script>
        <script src="js/tablesorter/tables.js"></script>

    </body>
</html>

