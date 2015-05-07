package br.edu.ifpb.pweb.dao;

import br.edu.ifpb.pweb.model.Usuario;

public class DAOUsuario extends DAO<Usuario>{
	
	public DAOUsuario(){
		super();
	}
	
	/*public Usuario readByEmail(String email){
		Query q = manager.createQuery("select p from Usuario p where p.email= '" + email +"'");
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}

	public Usuario readLogin(String email, String senha ){
		Query q1 = manager.createQuery("select p from  Usuario p where p.email= '"+email+"' and p.senha='"+senha+"'");
		try {
			return (Usuario) q1.getSingleResult();
		}catch (NoResultException e){
			return null;
		}*/
		
	
	public Usuario findByNome(String nome){
		return (Usuario) super.findByQuery("select u from Usuario u where u.nome = " + nome);
	}
	
	public Usuario readByEmail(String email){
		return (Usuario) super.findByQuery("select u from Usuario u where u.email = '" + email+"'");
	}
	
	public Usuario findBySenha(String s){
		return (Usuario) super.findByQuery("select u from Usuario u where u.senha = " + s);
	}
	
	public Usuario readLogin(String email, String senha){
		return (Usuario) super.findByQuery("select p from  Usuario p where p.email= '"+email+"' and p.senha='"+senha+"'");
	}

	}
	

