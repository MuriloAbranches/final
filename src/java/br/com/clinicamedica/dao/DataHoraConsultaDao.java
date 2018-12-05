package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.DataHoraConsulta;
import java.util.List;

public interface DataHoraConsultaDao {

    public List<DataHoraConsulta> readDataHoraConsultaByFlagAtivo();

    public DataHoraConsulta readDataHoraConsulta(DataHoraConsulta dataHoraConsulta);

    public boolean updateDataHoraConsulta(DataHoraConsulta dataHoraConsulta);
    
    public List<DataHoraConsulta> readDataHoraById(DataHoraConsulta dataHoraConsulta);
}
