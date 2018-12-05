<%@page import="br.com.clinicamedica.dao.impl.ConsultaDaoImpl"%>
<%@page import="br.com.clinicamedica.dao.ConsultaDao"%>
<%@page import="br.com.clinicamedica.model.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.clinicamedica.dao.impl.PacienteDaoImpl"%>
<%@page import="br.com.clinicamedica.dao.PacienteDao"%>
<%@page import="br.com.clinicamedica.model.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Clínica Médica</title>
        <meta name="description" content="Clínica Médica">
        <meta name="keywords" content="Clínica Médica,Médico,Clínicas,Saúde,Consultas Médicas">

        <link rel="shortcut icon" href="img/logo1.png" >
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Raleway|Candal">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <%
            String PC_login = null;
            HttpSession PC_sessao = request.getSession(false);
             List<Consulta> lista= (ArrayList<Consulta>) PC_sessao.getAttribute("lista");        
    
    %>
     
    <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
        <!--banner-->
        <section id="banner" class="banner">
            <div class="bg-color"> 
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="container">
                        <div class="col-md-12">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="#"><img src="img/logo11.png" class="img-responsive" style="width: 140px; margin-top: -53px;"></a>
                            </div>
                            <div class="collapse navbar-collapse navbar-right" id="myNavbar">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="#banner">Inicio</a></li>
                                  
                                    <li class=""><a href="#service1">Meus Agendamentos</a></li>

                                    <li class=""><a href="#service2">Pré Agendamento</a></li>
                                    <li class=""><a href="index.html">Sair</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="container">
                    <div class="row">
                        <div class="banner-info">
                            <div class="banner-logo text-center">
                                <img src="img/logo11.png" class="img-responsive">
                            </div>
                            <div class="banner-text text-center">
                                <h1 class="white">Agende sua consulta agora mesmo!!</h1>
                                <p>Médicos e especialistas prontos para <br> o melhor atendimento possível.</p>
                                <a href="#contact" class="btn btn-appoint">Agende aqui.</a>
                            </div>
                            <div class="overlay-detail text-center">
                                <a href="#service"><i class="fa fa-angle-down"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/ banner-->
       

        <!-- teste-->
        <section id="service1" class="section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-4">
                        <h2 class="ser-title">Meus Agendamentos</h2>
                        <hr class="botm-line">
                        <p>Lista de todos os agendamentos de consultas</p>
                    </div>
                    <div class="col-md-8 col-sm-8">

                        <form method="get" action="">
                            <div class="form-group col-md-12 col-sm-12">
                              

                                <h4>Lista de Agendamentos</h4>

                                <div class="table-responsive">
                                    <table class="table table-hover table-striped tablesorter">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Especialidade</th>
                                                <th>Médico</th>
                                                <th>Data/Hora</th>
                                               
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (Consulta consulta : lista) {
                                        %>  
                                        <tr>
                                            <td><%= consulta.getId()%></td>
                                           
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
                        </form>
                    </div>


                </div>
            </div>
        </section>
        <!-- Teste-->
        <!-- teste -->
        <section id="service2" class="section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-4">
                        <h2 class="ser-title">Pré Agendamento</h2>
                        <hr class="botm-line">
                        <p>Serviço não disponivel no momento</p>
                    </div>
                    <div class="col-md-8 col-sm-8">

                        <form method="get" action="">

                            <div class="form-group col-md-6 col-sm-6">
                                <label>Especialidade</label>
                                <select class="form-control">
                                    <option>Ortopedia</option>
                                    <option>Oftalmologia</option>
                                    <option>Ginecologia</option>
                                    <option>Geriatria</option>
                                    <option>Cardiologia</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6 col-sm-6">
                                <label>Médico</label>
                                <select class="form-control">
                                    <option>João</option>
                                    <option>Maria</option>
                                    <option>José</option>
                                    <option>Bruna</option>
                                    <option>Ana</option>
                                </select>
                            </div>
                            <div  class="form-action col-md-12 col-sm-12">
                                <button type="submit" class="btn btn-default">Salvar</button>
                            </div>
                        </form>


                    </div>

                </div>
            </div>
        </section>
        <!-- teste -->

        <!--footer-->
        <footer id="footer">
            <div class="top-footer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-sm-4 marb20">
                            <div class="ftr-tle">
                                <h4 class="white no-padding">Sobre nós</h4>
                            </div>
                            <div class="info-sec">
                                <p>Somos uma empresa de grandes responsabilidades com nossos clientes.</p>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4 marb20">
                            <div class="ftr-tle">
                                <h4 class="white no-padding">Quick Links</h4>
                            </div>
                            <div class="info-sec">
                                <ul class="quick-info">
                                    <li><a href="index.html"><i class="fa fa-circle"></i>Inicio</a></li>
                                    <li><a href="#service"><i class="fa fa-circle"></i>Serviços</a></li>
                                    <li><a href="#contact"><i class="fa fa-circle"></i>Agendamento</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4 marb20">
                            <div class="ftr-tle">
                                <h4 class="white no-padding">Redes sociais</h4>
                            </div>
                            <div class="info-sec">
                                <ul class="social-icon">
                                    <li class="bglight-blue"><i class="fa fa-facebook"></i></li>
                                    <li class="bgred"><i class="fa fa-google-plus"></i></li>
                                    <li class="bgdark-blue"><i class="fa fa-linkedin"></i></li>
                                    <li class="bglight-blue"><i class="fa fa-twitter"></i></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-line">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            © Copyright Clínica Médica. All Rights Reserved
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!--/ footer-->

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.easing.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

    </body>

</html>
