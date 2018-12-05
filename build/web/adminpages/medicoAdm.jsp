<%@page import="br.com.clinicamedica.model.Especialidade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.clinicamedica.model.Medico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Médicos</title>

        <link rel="shortcut icon" href="../img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="../adminpages/css/bootstrap.css" rel="stylesheet">

        <!-- Add custom CSS here -->
        <link href="../adminpages/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="../adminpages/font-awesome/css/font-awesome.min.css">
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
                        <li><a href="../adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active"><a href="../administrativo/medicos"><i class="fa fa-edit"></i> Médicos</a></li>
                        <li><a href="../administrativo/pacientes"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li><a href="../administrativo/consultas"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        
                    </ul>

                    <!--Mensagens -->
                    <ul class="nav navbar-nav navbar-right navbar-user">
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= PC_login %> <b class="caret"></b></a>
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
                        <h1>Médicos</h1>
                        <ol class="breadcrumb">
                            <li><a href="../adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Médicos</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <%
                        List<Medico> medicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
                        List<Especialidade> especialidades = (ArrayList<Especialidade>) request.getAttribute("listaEspecialidades");
                    %>
                    <div class="col-lg-6"> <!--12-->
                        <h2>Médico</h2>      
                        <div class="form-group">
                            <form method="POST" action="../medico"> 
                                <label>Nome Completo:
                                    <input name="nomeCompleto" type="text" id="nomeCompleto" size="60" maxlength="150" class="form-control"/></label><br />
                                <label>CPF:
                                    <input name="cpf" type="text" id="cpf" size="25"  maxlength="11" class="form-control"/></label>
                                <label>RG:
                                    <input name="rg" type="text" id="rg" size="26" class="form-control" /></label><br />
                                <label>CRM:
                                    <input name="crm" type="text" id="crm" size="25"  maxlength="20" class="form-control"/></label><br /><br />
                                <label>Especialidade:</label><br>
                                <select class="form-control" name="especialidades">
                                    <%
                                        for (Especialidade especialidade : especialidades) {
                                    %> 
                                    <option value="<%= especialidade.getId()%>" ><%= especialidade.getNome()%></option>
                                    <%
                                        }
                                    %>
                                </select><br /><br />  
                                <label>Contato:</label><br />
                                <label>DDD:
                                    <input name="ddd" type="tel" id="ddd" size="1" maxlength="2"  class="form-control" /></label> 
                                <label>Telefone:
                                    <input name="telefone" type="tel" id="telefone" size="17" maxlength="9"  class="form-control" /></label><br /> 
                                <label>Email:
                                    <input name="email" type="email" id="email" size="60" maxlength="50" class="form-control"/></label><br />
                                <div class="form-group">
                                    <label>Endereço:</label><br />
                                    <label>CEP:
                                        <input name="cep" type="text" id="cep" value="" size="10" maxlength="9" class="form-control" /></label><br />
                                    <label>Rua:
                                        <input name="logradouro" type="text" id="logradouro" size="60" class="form-control"/></label><br />
                                    <label>Número:
                                        <input name="numero" type="text" id="numero" size="8"  class="form-control"/></label>
                                    <label>Bairro:
                                        <input name="bairro" type="text" id="bairro" size="43" class="form-control" /></label><br />
                                    <label>Cidade:
                                        <input name="cidade" type="text" id="cidade" size="49"  class="form-control" /></label> 
                                    <label>Estado:
                                        <input name="estado" type="text" id="estado" size="2"  class="form-control"/></label><br />
                                </div>
                                <div  class="form-action">
                                    <button type="submit" class="btn btn-default" name="opcao" value="salvar">Salvar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!---------------------------------->
                    <div class="col-lg-6"> <!--12-->
                        <h2>Consultar Médicos</h2>
                        <form method="POST" action="../medico">
                            <div class="form-group">
                                <label>Buscar por CRM:
                                    <input name="crmBusca" type="text" id="crmBusca" size="10" maxlength="11" class="form-control" /></label>
                                <button type="submit" name="opcao" value="buscar" class="btn btn-default>"><i class="fa fa-search"></i> Buscar</button><br />
                                <h4>Lista de Médicos</h4>
                                <div class="table-responsive">
                                    <table class="table table-hover table-striped tablesorter">
                                        <thead>
                                            <tr>
                                                <th>Nome</th>
                                                <th>CRM</th>
                                                <th>Especialidade</th>
                                                <th>Telefone</th>
                                                <th>Email</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (Medico medico : medicos) {
                                            %>     
                                            <tr>
                                                <td><%= medico.getNomeCompleto()%></td>
                                                <td><%= medico.getCrm()%></td>
                                                <td><%= medico.getEspecialidade().getNome()%></td>
                                                <td><%= medico.getContato().getTelefone().getDdd()%>-<%= medico.getContato().getTelefone().getNumero()%></td>
                                                <td><%= medico.getContato().getEmail()%></td>
                                            </tr>   
                                            <%
                                                }
                                            %>
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
        <script src="../adminpages/js/jquery-1.10.2.js"></script>
        <script src="../adminpages/js/bootstrap.js"></script>

        <!-- Page Specific Plugins -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
        <script src="../adminpages/js/morris/chart-data-morris.js"></script>
        <script src="../adminpages/js/tablesorter/jquery.tablesorter.js"></script>
        <script src="../adminpages/js/tablesorter/tables.js"></script>
        <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
        <script src="../adminpages/js/flot/jquery.flot.js"></script>
        <script src="../adminpages/js/flot/jquery.flot.tooltip.min.js"></script>
        <script src="../adminpages/js/flot/jquery.flot.resize.js"></script>
        <script src="../adminpages/js/flot/jquery.flot.pie.js"></script>
        <script src="../adminpages/js/flot/chart-data-flot.js"></script>
    </body>
</html>
