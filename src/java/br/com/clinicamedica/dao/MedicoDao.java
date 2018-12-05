package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Especialidade;
import br.com.clinicamedica.model.Medico;
import java.util.List;

public interface MedicoDao {

    public boolean createMedico(Medico medico);

    public boolean deleteMedico(Medico medico);

    public Medico readMedico(Medico medico);

    public boolean updateMedico(Medico medico);

    public List<Medico> listMedico();

    public boolean activateMedico(Medico medico);

    public List<Medico> listMedicoByEspecialidade(Especialidade especialidade);
}
