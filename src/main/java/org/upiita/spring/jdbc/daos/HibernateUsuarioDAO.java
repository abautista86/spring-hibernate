package org.upiita.spring.jdbc.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

//Le indico que quiero que la clase viva en el contexto de Spring
 @Component("usuarioDaAO")
public class HibernateUsuarioDAO implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Usuario buscaUsuarioPorId(String usuarioId) {
		//1. Inicio una session
		Session sesion = sessionFactory.openSession();
		//2. Se inicia una transacción
		sesion.beginTransaction();
		//3. guardo los datos
		Usuario usuario = (Usuario) sesion.get(Usuario.class, usuarioId);
		//4. Obtengo la transacción actual y guardo los cambios en la base
		sesion.getTransaction().commit();
		//5. Termino la transacción
		sesion.close();		
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		//1. Inicio una session
		Session sesion = sessionFactory.openSession();
		//2. Se inicia una transacción
		sesion.beginTransaction();
		//3. guardo los datos
		sesion.save(usuario);
		//4. Obtengo la transacción actual y guardo los cambios en la base
		sesion.getTransaction().commit();
		//5. Termino la transacción
		sesion.close();
		
	}

	public void modificaUsuario(Usuario usuario) {
		//1. Inicio una session
		Session sesion = sessionFactory.openSession();
		//2. Se inicia una transacción
		sesion.beginTransaction();
		//3. actualiza un registro existente
		sesion.update(usuario);
		//4. Obtengo la transacción actual y guardo los cambios en la base
		sesion.getTransaction().commit();
		//5. Termino la transacción
		sesion.close();
		
	}

	public Usuario buscaPorUsuarioYPassword(String usuarioId, String password) {
		//Esto se resuelve con criterios
		//1. Inicio una session
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		//2.Se obtiene el criterio
		Criteria criterio = sesion.createCriteria(Usuario.class);
		
		//Formamos el criterio de hibernate		
		criterio.add(Restrictions.eq("usuarioId", usuarioId)); //(entidad de la clase que hace referencia al campo de la tabla, propiedad)
		criterio.add(Restrictions.eq("password", password)); //(entidad de la clase que hace refeencia al campo de la tabla, propiedad)
		
		//Este es un ejemplo con condiciones OR
		// criterio.add(Restrictions.or(Restrictions.eq("usuarioId", usuarioId), Restrictions.eq("password", password)));
		
		//Si no encuentra nada, regresa null
		Usuario usuario = (Usuario) criterio.uniqueResult();
		
		sesion.getTransaction().commit();
		sesion.close();
		
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		
		List<Usuario> usuarios;
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		Criteria criterio = sesion.createCriteria(Usuario.class);
		//Formamos el criterio de hibernate		
		criterio.add(Restrictions.like("nombre", nombre));
		usuarios = criterio.list();
		sesion.getTransaction().commit();
		sesion.close();
		
		return usuarios;
	}

}
