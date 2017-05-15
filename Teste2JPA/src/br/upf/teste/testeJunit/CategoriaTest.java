package br.upf.teste.testeJunit;

import static org.junit.Assert.*;

import org.junit.Test;

import br.upf.teste.geral.Categoria;
import br.upf.teste.geral.Produto;
import junit.framework.Assert;

public class CategoriaTest {
	//testa se a descrição está retoranando corretamente
	@Test
	public void testGetDescricao() {
		Categoria cat = new Categoria();
		cat.setDescricao("bebidas");
		
		Assert.assertEquals("bebidas", cat.getDescricao());
	}
	
}
