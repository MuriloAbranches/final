package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Endereco;

public interface EnderecoDao {

    public boolean createEndereco(Endereco endereco);

    public boolean deleteEndereco(Endereco endereco);

    public Endereco readEndereco(Endereco endereco);

    public boolean updateEndereco(Endereco endereco);

    public Endereco readEnderecoByCep(Endereco endereco);
}
