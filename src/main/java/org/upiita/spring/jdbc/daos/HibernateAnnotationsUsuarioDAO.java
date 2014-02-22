package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

//Se registra en el contexto de spring
@Component("usuarioDAOA")
public class HibernateAnnotationsUsuarioDAO implements UsuarioDAO {

	@Autowired
	SessionFactory sessionFactory;

	public Usuario buscaUsuarioPorId(String usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void creaUsuario(Usuario usuario) {
		Session session = sessionFactory.openSession();
		session.beginTransaction(); //inicia una transaccion
		session.save(usuario); //guardamos al usuario
		session.getTransaction().commit(); //realiza los cambios
		session.close(); //se cierra la sesion de hibernate
		
	}

	public void modificaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public Usuario buscaPorUsuarioYPassword(String usuarioId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}


}
