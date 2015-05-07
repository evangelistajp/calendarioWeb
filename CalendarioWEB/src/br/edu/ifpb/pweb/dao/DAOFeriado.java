package br.edu.ifpb.pweb.dao;

import br.edu.ifpb.pweb.model.Feriado;

public class DAOFeriado extends DAO<Feriado>{
	

	public Feriado findByID(String e){
		return  (Feriado) findByQuery("select i from Feriado i where i.id = '" + e +"'");
	}

	public Feriado findByAno(int ano){
		return (Feriado) super.findAllByQuery("select i from Feriado i where i.Ano = '" +"'");
	}

	

}
