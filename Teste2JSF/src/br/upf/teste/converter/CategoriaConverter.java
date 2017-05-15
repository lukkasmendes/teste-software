package br.upf.teste.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.upf.teste.geral.Categoria;
import br.upf.teste.util.GerirFactory;

@FacesConverter(value="catConverter")
public class CategoriaConverter implements Converter{
	@Override
	public Categoria getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = GerirFactory.getEntityManager();
				Categoria ret = em.find(Categoria.class, Integer.parseInt(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de Conversão da Categoria", "Categoria inválida."));
			}
		} else
			return null;
	}

	@Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		 if(object != null) {
			return String.valueOf(((Categoria) object).getId());
		 } else
		 	return null;
	}
}
