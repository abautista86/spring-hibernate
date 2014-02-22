package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.upiita.spring.jdbc.entidades.Usuario;

public interface UsuarioDAO {
	
	//Un cambio importante

	public Usuario buscaUsuarioPorId(String usuarioId);

	public void creaUsuario(Usuario usuario);
	
	public void modificaUsuario(Usuario usuario);
	
	public Usuario buscaPorUsuarioYPassword(String usuarioId, String password);
	
	public List<Usuario> buscaPorNombre(String nombre);

}
