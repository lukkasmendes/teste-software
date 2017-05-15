package br.upf.teste.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.teste.util.TrataException;
import br.upf.teste.geral.Categoria;
import br.upf.teste.geral.Produto;
import br.upf.teste.util.GerirFactory;

@ManagedBean
@SessionScoped
public class ProdutoCrud {
	private List<Produto> lista;
	private Produto objeto;


	public String incluir(){
		objeto = new Produto();
		return "ProdutoForm?faces-redirect=true";
	}

	
	public void inicializarLista(){
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from Produto").getResultList();
		em.close();
	}
	
	
	public List<Categoria> completeCategoria(String query) {
		EntityManager em = GerirFactory.getEntityManager();
		List<Categoria> results = em.createQuery("from Categoria").getResultList();
		em.close();
		return results;
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
		return "ProdutoList?faces-redirect=true";
	}
	
	public String alterar(Integer id) { 
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Produto.class, id);
		em.close();
		return "ProdutoForm?faces-redirect=true";
	}
	
	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Produto.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "ProdutoList?faces-redirect=true";
	}
	
	public ProdutoCrud() {
		super();
	}
	
	public String cancelar() {
		return "ProdutoList";
	}
	
	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public Produto getObjeto() {
		return objeto;
	}

	public void setObjeto(Produto objeto) {
		this.objeto = objeto;
	}
	
}
