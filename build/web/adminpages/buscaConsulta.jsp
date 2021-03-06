<%@page import="br.com.clinicamedica.model.DataHoraConsulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.clinicamedica.model.Medico"%>
<%@page import="java.util.List"%>
<%@page import="br.com.clinicamedica.model.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clínica Médica - Agendamentos</title>

        <link rel="shortcut icon" href="img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="adminpages/css/bootstrap.css" rel="stylesheet">

        <!-- Add custom CSS here -->
        <link href="adminpages/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="adminpages/font-awesome/css/font-awesome.min.css">
    </head>

    <body>

        <%
            Consulta buscaConsulta = (Consulta) request.getAttribute("buscaConsulta");
            long crmSelecionado = buscaConsulta.getMedico().getCrm();
            int dataHoraSelecionado = buscaConsulta.getDataHoraConsulta().getId();
            List<DataHoraConsulta> listaDataHora = (List<DataHoraConsulta>) request.getAttribute("listaDataHora");
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
                        <li><a href="adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="administrativo/medicos"><i class="fa fa-edit"></i> Médicos</a></li>
                        <li><a href="administrativo/pacientes"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li class="active"><a href="administrativo/consultas"><i class="fa fa-edit"></i> Agendamentos</a></li>
                       
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
                        <h1>Agendamentos</h1>
                        <ol class="breadcrumb">
                            <li><a href="adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Agendamentos</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                
                <div class="row">
                    <div class="col-lg-6"> <!--12-->
                        <form method="POST" action="consulta">
                            <div class="form-group">
                                <input name="idConsulta" type="hidden" id="idConsulta" size="25" maxlength="150" class="form-control" value="<%= buscaConsulta.getId() %>" />
                                <input name="idDataAntigo" type="hidden" id="idDataAntigo" size="25" maxlength="150" class="form-control" value="<%= dataHoraSelecionado %>" />
                                <label>Nome Completo:
                                    <input name="nomePaciente" type="text" id="nomePaciente" size="25" maxlength="150" class="form-control" value="<%= buscaConsulta.getPaciente().getNomeCompleto() %>" readonly/></label>
                                <label>CPF:
                                    <input name="cpf" type="text" id="cpf" size="25"  maxlength="11" class="form-control" value="<%= buscaConsulta.getPaciente().getCpf() %>" readonly/></label><br />

                            </div>

                            <label>Especialidade:
                                    <input name="especialidade" type="text" id="especialidade" size="25"  maxlength="11" class="form-control" value="<%= buscaConsulta.getEspecialidade().getNome() %>" readonly/></label><br />
                            <%
                                List<Medico> medicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");

                            %>
                            <div class="form-group">
                                <label>Médico</label>
                                <select class="form-control" name="medico">
                                    <%                                        
                                        for (Medico medico : medicos) {
                                    %>     
                                    <option value="<%= medico.getCrm()%>" 
                                        <% if (medico.getCrm() == crmSelecionado) { %> selected="selected" <% }%> >
                                        <%= medico.getNomeCompleto()%></option>
                                        <%
                                            }
                                        %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Data/Hora Agendamento</label>
                                
                                <select class="form-control" name="dataHora">
                                     <%
                                        for (DataHoraConsulta dataHoraConsulta : listaDataHora) {
                                    %> 
                                    <option value="<%= dataHoraConsulta.getId()%>" <% if(dataHoraConsulta.getId() == dataHoraSelecionado){ %> selected="selected" <% } %> > <%= dataHoraConsulta.getDataConsulta()+" - "+ dataHoraConsulta.getHoraConsulta() %></option>
                                    <%
                                        }
                                    %>

                                </select>
                            </div>
                                    <div  class="form-action">
                                        <button type="submit" class="btn btn-default" name="opcao" value="alterar" >Salvar</button>
                                        <button type="submit" class="btn btn-default" name="opcao" value="excluir" >Excluir</button>
                                    </div>
                        </form>

                        
                    </div>
                    <!---------------------------------->
                </div><!-- /.row -->
            </div><!-- /#page-wrapper -->
        </div><!-- /#wrapper -->

        <!-- JavaScript -->
        <script src="adminpages/js/jquery-1.10.2.js"></script>
        <script src="adminpages/js/bootstrap.js"></script>

    </body>
</html>

