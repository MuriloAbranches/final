package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Consulta;
import br.com.clinicamedica.model.Paciente;
import java.util.List;

public interface ConsultaDao {

    public boolean createConsulta(Consulta consulta);

    public boolean deleteConsulta(Consulta consulta);

    public Consulta readConsulta(Consulta consulta);

    public boolean updateConsulta(Consulta consulta);

    public List<Consulta> listConsulta();

    public boolean activateConsulta(Consulta consulta);
    
    public List<Consulta> listConsultaPaciente(Paciente paciente);
}
