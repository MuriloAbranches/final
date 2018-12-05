package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Contato;

public interface ContatoDao {

    public boolean createContato(Contato contato);

    public boolean deleteContato(Contato contato);

    public Contato readContato(Contato contato);

    public boolean updateContato(Contato contato);

    public Contato readContatoByEmail(Contato contato);
}
