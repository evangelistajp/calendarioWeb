package br.edu.ifpb.pweb.dao;

import java.util.List;

import br.edu.ifpb.pweb.model.Anotacao;

public class DAOAnotacao extends DAO<Anotacao>{
	
	public DAOAnotacao() {
		// TODO Auto-generated constructor stub
	}
	
	public  Anotacao findByID(String id){
		return  (Anotacao) super.findByQuery("select e from Anotacao e where e.id = '" + id+ "'");
	}
	
	public List<Anotacao> findByAll(){
		return readAll();
		
	}

}
