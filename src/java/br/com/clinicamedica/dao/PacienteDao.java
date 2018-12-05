package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Paciente;
import java.util.List;

public interface PacienteDao {

    public boolean createPaciente(Paciente paciente);

    public boolean deletePaciente(Paciente paciente);

    public Paciente readPaciente(Paciente paciente);

    public boolean updatePaciente(Paciente paciente);

    public List<Paciente> listPaciente();

    public boolean activatePaciente(Paciente paciente);
    
    public Paciente readPacienteById(Paciente paciente);
}
   
