package br.edu.ifpb.pweb.dao;

import java.util.List;

import br.edu.ifpb.pweb.model.Feriado;

public class DAOFeriado extends DAO<Feriado>{
	

	public Feriado findByID(String id){
		return  (Feriado) findByQuery("select i from Feriado i where i.id = '" + id +"'");
	}

	public Feriado findByAno(int ano){
		return (Feriado) super.findAllByQuery("select i from Feriado i where i.Ano = '" +"'");
	}

	public List<Feriado> findByAll(){
		return readAll();
		
	}

}
