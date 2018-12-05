<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Agenda Médica</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>

        <script type="text/javascript">
            $("#dataAgenda").mask("00/00/0000");
            $("#horarioInicio, #horarioFim").mask("00:00");
        </script>

        <link rel="shortcut icon" href="../img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">

        <!-- Add custom CSS here -->
        <link href="css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    </head>

    <body>

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
                        <li><a href="index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="funcionarioAdm.jsp"><i class="fa fa-edit"></i> Funcionários</a></li>
                        <li><a href="pacienteAdm.jsp"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li><a href="agendamentoAdm.jsp"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        <li class="active"><a href="agendaAdm.jsp"><i class="fa fa-edit"></i> Agenda Médica</a></li>

                    </ul>

                    <!-- Mensagens-->
                    <ul class="nav navbar-nav navbar-right navbar-user">

                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-user"></i> Perfil</a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i> Inbox</a></li>
                                <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="fa fa-power-off"></i> Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        <h1>Agenda Médica</h1>
                        <ol class="breadcrumb">
                            <li><a href="index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Agenda Médica</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <div class="col-lg-6"> <!--12-->
                        <h2>Criar Agenda</h2>


                        <form method="get" action="">
                            <div class="form-group">

                                <label>Buscar Médico:
                                    <input name="cep" type="text" id="cep" value="" size="10" maxlength="11" class="form-control" /></label>
                                <button type="submit" class="btn btn-default>"><i class="fa fa-search"></i> Buscar</button><br />
                                <p class="help-block">Informe o Nome</p>

                                <label>Nome Completo:
                                    <input name="nomeMedico" type="text" id="nomeMedico" size="60" maxlength="150" class="form-control"/></label><br />
                                <label>CRM:
                                    <input name="crm" type="text" id="crm" size="25"  maxlength="11" class="form-control"/></label>
                                <label>Especialidade:
                                    <input name="especialidade" type="text" id="especialidade" size="26" class="form-control" /></label><br /><br />
                                <label>Agenda:</label><br />
                                <label>Data:
                                    <input name="dataAgenda" type="text" id="dataAgenda" size="25" class="form-control" placeholder="DD/MM/AAAA" maxlength="10" /></label><br />
                                <label>Horário Inicio:
                                    <input name="horarioInicio" type="text" id="horarioInicio" size="25" class="form-control" placeholder="HH:MM" /></label>
                                <label>Horário Fim:
                                    <input name="horarioFim" type="text" id="horarioFim" size="25" class="form-control" placeholder="HH:MM" /></label><br />
                                <label>Tempo estimado de atendimento:
                                    <input name="horarioAtendimento" type="text" id="horarioAtendimento" size="25" class="form-control" placeholder="Minutos" maxlength="2" /></label><br />
                            </div>
                        </form>


                        <div  class="form-action">
                            <button type="submit" class="btn btn-default">Salvar</button>
                        </div>
                    </div>
                    <!---------------------------------->
                    <div class="col-lg-6"> <!--12-->
                        <h3>Consultar Agendas</h3>
                        <form method="get" action="">
                            <div class="form-group">
                                <label>Consulta por Médico:
                                    <input name="cpfBusca" type="text" id="cpfBusca" value="" size="10" maxlength="11" class="form-control" /></label>
                                <button type="submit" class="btn btn-default>"><i class="fa fa-search"></i> Buscar</button><br />

                                <h4>Agendas</h4>

                                <div class="table-responsive">
                                    <table class="table table-hover table-striped tablesorter">
                                        <thead>
                                            <tr>
                                                <th>Médico</th>
                                                <th>Especialidade</th>
                                                <th>Data</th>


                                                <th>Ação</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>

                                                <td>47214587</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>

                                                <td>987462</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>

                                                <td>47214587</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>

                                                <td>987462</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>

                                                <td>47214587</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>

                                                <td>987462</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>

                                                <td>47214587</td>

                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>

                </div><!-- /.row -->
            </div><!-- /#page-wrapper -->
        </div><!-- /#wrapper -->

        <!-- JavaScript -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.js"></script>

    </body>
</html>
