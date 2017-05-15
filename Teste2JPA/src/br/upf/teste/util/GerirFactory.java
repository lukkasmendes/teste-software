package br.upf.teste.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerirFactory {
	static EntityManagerFactory factory = null;
	
	public static EntityManager getEntityManager(){
		if ((factory == null) || (!factory.isOpen())){
			factory = Persistence.createEntityManagerFactory("Teste2JPA");
		}
		return factory.createEntityManager();
	}
}
