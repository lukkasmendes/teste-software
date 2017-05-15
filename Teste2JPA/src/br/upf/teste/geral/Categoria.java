package br.upf.teste.geral;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import br.upf.teste.util.GerirFactory;

/**
 * Entity implementation class for Entity: Categoria
 *
 */
@Entity

public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "genCategoriaId")
	@SequenceGenerator(name = "genCategoriaId", sequenceName = "seqCategoriaId", allocationSize = 1)
	private Integer id;
	private String descricao;
	private static final long serialVersionUID = 1L;
	

	public Categoria() {
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
	
	

   
}
