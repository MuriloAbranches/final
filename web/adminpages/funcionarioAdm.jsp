<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Funcionários</title>

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
                        <li class="active"><a href="funcionarioAdm.jsp"><i class="fa fa-edit"></i> Funcionários</a></li>
                        <li><a href="pacienteAdm.jsp"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li><a href="agendamentoAdm.jsp"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        <li><a href="agendaAdm.jsp"><i class="fa fa-edit"></i> Agenda Médica</a></li>
                    </ul>

                    <!--Mensagens -->
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
                        <h1>Funcionários</h1>
                        <ol class="breadcrumb">
                            <li><a href="index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Funcionários</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <div class="col-lg-6"> <!--12-->
                        <h2>Funcionário</h2>

                        <form method="post" action="">
                            <div class="form-group">
                                <label>Cargo</label>
                                <select class="form-control">
                                    <option>Selecione</option>
                                    <option>Gerente</option>
                                    <option>Secretário</option>
                                    <option>Médico</option>
                                </select>
                            </div>

                            <div class="form-group">

                                <label>Nome Completo:
                                    <input name="nomePaciente" type="text" id="nomePaciente" size="60" maxlength="150" class="form-control"/></label><br />
                                <label>CPF:
                                    <input name="cpf" type="text" id="cpf" size="25"  maxlength="11" class="form-control"/></label>
                                <label>RG:
                                    <input name="rg" type="text" id="rg" size="26" class="form-control" /></label><br />
                                <label>CRM:
                                    <input name="crm" type="text" id="crm" size="25"  maxlength="20" class="form-control" disabled=""/></label><br /><br />
                                <label>Contato:</label><br />
                                <label>DDD:
                                    <input name="dddtel" type="tel" id="dddtel" size="1" maxlength="4"  class="form-control" /></label> 
                                <label>Telefone:
                                    <input name="telefone" type="tel" id="telefone" size="17" maxlength="11"  class="form-control" /></label><br />
                                <label>DDD:
                                    <input name="dddcel" type="tel" id="dddcel" size="1" maxlength="4"  class="form-control" /></label> 
                                <label>Celular:
                                    <input name="celular" type="tel" id="celular" size="17" maxlength="11"  class="form-control" /></label> 
                                <label>Email:
                                    <input name="email" type="email" id="email" size="60" maxlength="50" class="form-control"/></label><br />
                            </div>


                        </form>

                        <form method="get" action="">
                            <div class="form-group">
                                <label>Endereço:</label><br />
                                <label>CEP:
                                    <input name="cep" type="text" id="cep" value="" size="10" maxlength="9"
                                           onblur="pesquisacep(this.value)" class="form-control" /></label>
                                <button type="submit" class="btn btn-default>"><i class="fa fa-search"></i> Buscar CEP</button><br />
                                <p class="help-block">Informe o CEP</p>
                                <label>Rua:
                                    <input name="rua" type="text" id="rua" size="60" class="form-control"/></label><br />
                                <label>Número:
                                    <input name="numero" type="text" id="numero" size="8"  class="form-control"/></label>
                                <label>Bairro:
                                    <input name="bairro" type="text" id="bairro" size="43" class="form-control" /></label><br />
                                <label>Cidade:
                                    <input name="cidade" type="text" id="cidade" size="49"  class="form-control" /></label> 
                                <label>Estado:
                                    <input name="uf" type="text" id="uf" size="2"  class="form-control"/></label><br />
                            </div>
                        </form>

                        <div  class="form-action">
                            <button type="submit" class="btn btn-default">Salvar</button>
                        </div>

                    </div>
                    <!---------------------------------->
                    <div class="col-lg-6"> <!--12-->
                        <h2>Funcionários</h2>
                        <form method="get" action="">
                            <div class="form-group">
                                <label>Buscar por CPF:
                                    <input name="cpfBusca" type="text" id="cpfBusca" value="" size="10" maxlength="11" class="form-control" /></label>
                                <button type="submit" class="btn btn-default>"><i class="fa fa-search"></i> Buscar</button><br />

                                <h4>Lista de Funcionários</h4>

                                <div class="table-responsive">
                                    <table class="table table-hover table-striped tablesorter">
                                        <thead>
                                            <tr>
                                                <th>Nome</th>
                                                <th>CPF</th>
                                                <th>RG</th>
                                                <th>Telefone</th>
                                                <th>Email</th>
                                                <th>Ação</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>
                                                <td>12345</td>
                                                <td>47214587</td>
                                                <td>joao@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>
                                                <td>333333</td>
                                                <td>987462</td>
                                                <td>maria@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>
                                                <td>12345</td>
                                                <td>47214587</td>
                                                <td>joao@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>
                                                <td>333333</td>
                                                <td>987462</td>
                                                <td>maria@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>
                                                <td>12345</td>
                                                <td>47214587</td>
                                                <td>joao@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Maria</td>
                                                <td>2614568</td>
                                                <td>333333</td>
                                                <td>987462</td>
                                                <td>maria@hotmail.com</td>
                                                <td>
                                                    <button>Excluir</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>João</td>
                                                <td>1265</td>
                                                <td>12345</td>
                                                <td>47214587</td>
                                                <td>joao@hotmail.com</td>
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

        <!-- Page Specific Plugins -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
        <script src="js/morris/chart-data-morris.js"></script>
        <script src="js/tablesorter/jquery.tablesorter.js"></script>
        <script src="js/tablesorter/tables.js"></script>
        <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
        <script src="js/flot/jquery.flot.js"></script>
        <script src="js/flot/jquery.flot.tooltip.min.js"></script>
        <script src="js/flot/jquery.flot.resize.js"></script>
        <script src="js/flot/jquery.flot.pie.js"></script>
        <script src="js/flot/chart-data-flot.js"></script>
    </body>
</html>
