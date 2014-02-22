package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;


public class TestSpringHibernate {

	public static void main(String[] args) {
		
		//1.Creamos el contexto de Spring
		@SuppressWarnings("resource")
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		//2. Nos traemos el bean HibernateUsuarioDAO
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDaAO");
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId("2");
		usuario.setNombre("Ismael");
		usuario.setPassword("123");		
		usuarioDAO.creaUsuario(usuario);
		
		System.out.println("datos guardados");
		usuario.setPassword("456");
		usuarioDAO.modificaUsuario(usuario);
		System.out.println("datos modificados");
		/**
		Si quiero ver en consola el query, en el archivo contexto.xml hay una opción
		<entry key="hibernate.show_sql" value="true" />
		en value se pone true para mostrar la consulta
		*/
		
		//una vez creado el usuario, ahora lo buscamos en la base
		//buscamos usuario con id="1"
		Usuario usuarioDesdeBD = usuarioDAO.buscaUsuarioPorId(usuario.getUsuarioId());
		System.out.println("Usuario por id: " + usuarioDesdeBD);
		
		//Probando el criterio
		Usuario usuarioIdPass = usuarioDAO.buscaPorUsuarioYPassword("1", "123");
		System.out.println("Usuario pr id y password: " + usuarioIdPass);
		
		//Prueba del criterio like
		List<Usuario> usuarios =  usuarioDAO.buscaPorNombre("%a%");
		System.out.println("Usuario por nombre: " + usuarios);
		
		

	}

}
