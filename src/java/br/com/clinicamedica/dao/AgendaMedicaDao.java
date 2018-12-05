package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.AgendaMedica;
import java.util.List;

public interface AgendaMedicaDao {

    public boolean createAgendaMedica(AgendaMedica agendaMedica);

    public boolean deleteAgendaMedica(AgendaMedica agendaMedica);

    public AgendaMedica readAgendaMedica(AgendaMedica agendaMedica);

    public boolean updateAgendaMedica(AgendaMedica agendaMedica);

    public List<AgendaMedica> listAgendaMedica();

    public boolean activateAgendaMedica(AgendaMedica agendaMedica);
}
