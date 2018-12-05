package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Especialidade;
import java.util.List;

public interface EspecialidadeDao {

    public boolean createEspecialidade(Especialidade especialidade);

    public boolean deleteEspecialidade(Especialidade especialidade);

    public Especialidade readEspecialidade(Especialidade especialidade);

    public boolean updateEspecialidade(Especialidade especialidade);

    public List<Especialidade> listEspecialidade();

    public Especialidade readEspecialidadeByNome(Especialidade especialidade);
}
