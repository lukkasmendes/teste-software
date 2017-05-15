package br.upf.teste.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.teste.geral.Categoria;
import br.upf.teste.util.GerirFactory;
import br.upf.teste.util.TrataException;

@ManagedBean
@SessionScoped
public class CategoriaCrud {
	private List<Categoria> lista;
	private Categoria objeto;
	
	public String incluir(){
		objeto = new Categoria();
		return "CategoriaForm?faces-redirect=true";
	}
	
	public String gravar(){
		EntityManager em = GerirFactory.getEntityManager();
		try {
			em.getTransaction().begin();
			objeto = em.merge(objeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", 
					new FacesMessage(TrataException.getErrorMessage(e)));
			return "";
		}finally{
			//independente do que acontece dentro do catch o finally sempre executa
			em.close();	
		}
		return "CategoriaList?faces-redirect=true";
	}
	
	public String alterar(Integer id) { 
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Categoria.class, id);
		em.close();
		return "CategoriaForm?faces-redirect=true";
	}
	
	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Categoria.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "CategoriaList?faces-redirect=true";
	}
	
	public String cancelar() {
		return "CategoriaList";
	}
	
	public void inicializarLista(){
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from Categoria").getResultList();
		em.close();
	}

	public List<Categoria> getLista() {
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}

	public Categoria getObjeto() {
		return objeto;
	}

	public void setObjeto(Categoria objeto) {
		this.objeto = objeto;
	}
}
