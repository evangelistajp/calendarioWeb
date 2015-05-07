package br.edu.ifpb.pweb.dao;

import br.edu.ifpb.pweb.model.Calendario;


public class DAOCalendario extends DAO<Calendario> {
	
	public DAOCalendario(){
		super();
	}
	
	
	public Calendario findByID(String e){
		return (Calendario) super.findByQuery("select i from Calendario i where i.id = '" + e +"'");
	}

	public Calendario findByAno(String e){
		return (Calendario) super.findByQuery("select i from Calendario i where i.ano = '" + e +"'");
	}

}
