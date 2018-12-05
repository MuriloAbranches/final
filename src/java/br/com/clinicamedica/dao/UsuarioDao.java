package br.com.clinicamedica.dao;

import br.com.clinicamedica.model.Usuario;


public interface UsuarioDao {

    public boolean createUsuario(Usuario usuario);

    public boolean deleteUsuario(Usuario usuario);

    public Usuario readUsuario(Usuario usuario);

    public boolean updateUsuario(Usuario usuario);
    
    public Usuario autenticaUsuario(Usuario usuario);

}
