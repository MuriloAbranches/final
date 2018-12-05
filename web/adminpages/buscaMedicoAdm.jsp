<%@page import="br.com.clinicamedica.model.Medico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.clinicamedica.model.Especialidade"%>
<%@page import="java.util.List"%>
<%@page import="br.com.clinicamedica.model.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Clínica Médica - Médicos</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>

        <script type="text/javascript">
            $("#dataNascimento").mask("00/00/0000");
        </script>

        <link rel="shortcut icon" href="img/logo1.png" >
        <!-- Bootstrap core CSS -->
        <link href="../adminpages/css/bootstrap.css" rel="stylesheet">
        <link href="./adminpages/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <!-- Add custom CSS here -->
        <link href="./adminpages/css/sb-admin.css" rel="stylesheet" type="text/css"/>
        <link href="../adminpages/css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="../adminpages/font-awesome/css/font-awesome.min.css">
        <link href="./adminpages/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <%
            List<Especialidade> especialidades = (ArrayList<Especialidade>) request.getAttribute("listaEspecialidades");

            Medico buscaMedico = (Medico) request.getAttribute("buscaMedico");

            int selecionado = buscaMedico.getEspecialidade().getId();

            if (null != buscaMedico) {
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
                        <li class="active" ><a href="administrativo/medicos"><i class="fa fa-edit"></i> Médicos</a></li>
                        <li><a href="administrativo/pacientes"><i class="fa fa-edit"></i> Pacientes</a></li>
                        <li><a href="administrativo/consultas"><i class="fa fa-edit"></i> Agendamentos</a></li>
                        
                    </ul>

                    <!-- Mensagens -->
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
                        <h1>Médico</h1>
                        <ol class="breadcrumb">
                            <li><a href="adminpages/index.jsp"><i class="fa fa-dashboard"></i> Inicio</a></li>
                            <li class="active"><i class="fa fa-edit"></i> Médico</li>
                        </ol>

                    </div>
                </div><!-- /.row -->

                <div class="row">
                    <div class="col-lg-6"> <!--12-->
                        <h2>Médico</h2>

                        <form method="POST" action="medico">
                            <div class="form-group">

                                <label>Nome Completo:
                                    <input name="nomeCompleto" type="text" size="60" maxlength="150" class="form-control" value="<%= buscaMedico.getNomeCompleto()%>" /></label><br />
                                <label>CPF:
                                    <input name="cpf" type="text" size="25"  maxlength="11" class="form-control" value="<%= buscaMedico.getCpf()%>" readonly/></label>
                                <label>RG:
                                    <input name="rg" type="text" size="26" class="form-control" value="<%= buscaMedico.getRg()%>" readonly/></label><br />
                                <label>CRM:
                                    <input name="crm" type="text" id="crm" size="25"  maxlength="20" class="form-control" value="<%= buscaMedico.getCrm()%>" readonly/></label><br /><br />   
                                <label>Especialidade:</label><br>
                                <select class="form-control" name="especialidades">

                                    <%
                                        for (Especialidade especialidade : especialidades) {
                                    %>

                                    <option value="<%=especialidade.getId()%>"  
                                            <% if (especialidade.getId() == selecionado) {%> selected="selected" <% }%>
                                            ><%= especialidade.getNome()%> </option>

                                    <% }%> 
                                </select><br><br>

                                <label>Contato:</label><br />
                                <label>DDD:
                                    <input name="ddd" type="tel" size="1" maxlength="2"  class="form-control" value="<%= buscaMedico.getContato().getTelefone().getDdd()%>" /></label> 
                                <label>Telefone:
                                    <input name="telefone" type="tel" size="17" maxlength="9"  class="form-control" value="<%= buscaMedico.getContato().getTelefone().getNumero()%>"/></label><br /> 
                                <label>Email:
                                    <input name="email" type="email" size="60" maxlength="50" class="form-control" value="<%= buscaMedico.getContato().getEmail()%>"/></label><br />
                            </div>

                            <div class="form-group">
                                <label>Endereço:</label><br />
                                <label>CEP:
                                    <input name="cep" type="text" size="10" maxlength="9" class="form-control" value="<%= buscaMedico.getEndereco().getCep()%>"/></label><br />
                                <label>Rua:
                                    <input name="logradouro" type="text" size="60" class="form-control" value="<%= buscaMedico.getEndereco().getLogradouro()%>"/></label><br />
                                <label>Número:
                                    <input name="numero" type="text" size="8"  class="form-control" value="<%= buscaMedico.getEndereco().getNumero()%>"/></label>
                                <label>Bairro:
                                    <input name="bairro" type="text" size="43" class="form-control" value="<%= buscaMedico.getEndereco().getBairro()%>"/></label><br />
                                <label>Cidade:
                                    <input name="cidade" type="text" size="49"  class="form-control" value="<%= buscaMedico.getEndereco().getCidade()%>"/></label> 
                                <label>Estado:
                                    <input name="estado" type="text" size="2"  class="form-control" value="<%= buscaMedico.getEndereco().getEstado()%>"/></label><br />
                            </div>

                            <div  class="form-action">
                                <button type="submit" class="btn btn-default" name="opcao" value="alterar" >Salvar</button>
                                <button type="submit" class="btn btn-default" name="opcao" value="excluir" >Excluir</button>
                            </div>
                        </form>  
                        <%
                        } else {
                        %>
                        <h2>Não Encontrado!</h2>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
