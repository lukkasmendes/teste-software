package br.upf.teste.geral;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity

public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "genProdutoId")
	@SequenceGenerator(name = "genProdutoId", sequenceName = "seqProdutoId", allocationSize = 1)
	private Integer id;
	
	private String descricao;
	
	private Float valor;
	
	@ManyToOne
	private Categoria categoria;

	
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
