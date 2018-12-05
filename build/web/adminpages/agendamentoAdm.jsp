<%@page import="br.com.clinicamedica.model.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.clinicamedica.model.Especialidade"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Agendamentos</title>

        <link rel="shortcut icon" href="../img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="../adminpages/css/bootstrap.css" rel="stylesheet">

        <!-- Add custom CSS here -->
        <link href="../adminpages/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="../adminpages/font-awesome/css/font-awesome.min.css">
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
                        <li><a href="../administrativo/medicos"><i class="fa fa-edit"></i> Médicos</a></li>
                        <li><a href="../administrativo/pacientes"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li class="active"><a href="../administrativo/consultas"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        
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
                                <li><a href="#"><i class="fa fa-power-off"></i> Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        <h1>Agendamentos</h1>
                        <ol class="breadcrumb">
                            <li><a href="../adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Agendamentos</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <%
                        List<Especialidade> especialidades = (ArrayList<Especialidade>) request.getAttribute("listaEspecialidades");
                    %>
                    <div class="col-lg-6"> <!--12-->
                        <h2>Agendamento</h2>


                        <form method="POST" action="../consulta">
                            <div class="form-group">
                                <label>Buscar Paciente:
                                    <input name="cpfBusca" type="text" id="cpfBusca" value="" size="10" maxlength="11" class="form-control" /></label>
                                <p class="help-block">Informe o CPF</p>
                            </div>

                            <div>
                                <label>Especialidade</label>
                                <select class="form-control" name="especialidades">
                                    <%
                                        for (Especialidade especialidade : especialidades) {
                                    %> 
                                    <option value="<%= especialidade.getId()%>"> <%= especialidade.getNome()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <p class="help-block">Selecione a Especialidade</p> <br /> <br />
                                
                            </div>

                            <div  class="form-action">
                                <button type="submit" class="btn btn-default"  name="opcao" value="buscarPaciente">Agendar</button>
                            </div>
                        </form>


                    </div>
                    <!---------------------------------->
                    <div class="col-lg-6"> <!--12-->
                          <h3>Consultar Agendamento</h3>
                        <form method="POST" action="../consulta">
                            <div class="form-group">
                                <label>Consulta por ID:
                                    <input name="buscaConsulta" type="text" id="buscaConsulta" value="" size="10" maxlength="11" class="form-control" /></label>
                                <button type="submit" name="opcao" value="buscar" class="btn btn-default>"><i class="fa fa-search"></i> Buscar</button><br />
                            </div>
                        </form>

                        <div class="form-group">
                            <%
                                List<Consulta> consultas = (ArrayList<Consulta>) request.getAttribute("listaConsultas");
                            %>
                            <div class="table-responsive">
                                <table class="table table-hover table-striped tablesorter">
                                    <thead>
                                        <tr>
                                            <th>Consulta</th>
                                            <th>Paciente</th>
                                            <th>Especialidade</th>
                                            <th>Medico</th>
                                            <th>Data/Hora</th>
                                         </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (Consulta consulta : consultas) {
                                        %>  
                                        <tr>
                                            <td><%= consulta.getId()%></td>
                                            <td><%= consulta.getPaciente().getNomeCompleto()%></td>
                                            <td><%= consulta.getEspecialidade().getNome()%></td>
                                            <td><%= consulta.getMedico().getNomeCompleto()%></td>
                                            <td><%= consulta.getDataHoraConsulta().getDataConsulta()+" - "+ consulta.getDataHoraConsulta().getHoraConsulta() %></td>
                                        </tr>
                                        <%
                                            };
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                                    
                    </div>

                </div><!-- /.row -->
            </div><!-- /#page-wrapper -->
        </div><!-- /#wrapper -->

        <!-- JavaScript -->
        <script src="../adminpages/js/jquery-1.10.2.js"></script>
        <script src="../adminpages/js/bootstrap.js"></script>

    </body>
</html>
