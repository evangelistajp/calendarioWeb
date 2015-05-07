package br.edu.ifpb.pweb.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.ifpb.pweb.dao.DAO;
import br.edu.ifpb.pweb.dao.DAOCalendario;
import br.edu.ifpb.pweb.dao.DAOFeriado;
import br.edu.ifpb.pweb.dao.DAOUsuario;
import br.edu.ifpb.pweb.exception.CadastroException;
import br.edu.ifpb.pweb.exception.ErroLogicoException;
import br.edu.ifpb.pweb.model.Calendario;
import br.edu.ifpb.pweb.model.Feriado;
import br.edu.ifpb.pweb.model.Usuario;



public class ControllerFacade {
	
	private static DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOCalendario daoCalendario = new DAOCalendario(); 
	private static DAOFeriado daoFeriado = new DAOFeriado(); 
	
	//private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	//private Usuario usuario;
	
	
	public static void open(){
		daoUsuario = new DAOUsuario();
		daoCalendario = new DAOCalendario();
		daoFeriado = new DAOFeriado();

		
	}

	public static Usuario cadastrarUsuario(String nome, String email, String senha) throws CadastroException {
		
		DAO.begin();
		
		Usuario us = daoUsuario.readByEmail(email);
		
		if (us != null) {
			throw new CadastroException("Usuario ja cadastrado: " + email +"\n");
		}
		us = new Usuario(nome, email, senha);
		
		daoUsuario.create(us);
		
		DAO.commit();
		
		return us;
	}
	
	//Realiza o login do Usuario Comum
	public static Usuario realizaLogin(String email, String senha) {
		
		DAO.begin();
		
		Usuario us = daoUsuario.readLogin(email, senha);
		
		if (us.isIsadmin()) {
			System.out.println("admin");
		}else{
			System.out.println("comum");
		}
		
		return us;
		
	}
	
	public static Usuario mudarsenha(String email, String senha){
		
        DAO.begin();
        
        Usuario usuario = daoUsuario.readByEmail(email); 
        System.out.println("usuario "+usuario);
	    
        
        if (usuario.isIsadmin()) {
	    
        	usuario.setSenha(senha);
	       	daoUsuario.update(usuario);
	    	 
	    	DAO.commit();
	    	System.out.println("Senha alterada com sucesso");
	    }
		
		return null;
	}

	public static Usuario excluirusuario(String emailexcluir) {
		
		DAO.begin();

		Usuario usuario = daoUsuario.readByEmail(emailexcluir);
		System.out.println("usuario " + usuario);
		
		if (usuario.isIsadmin()==false) {
			
			daoUsuario.delete(usuario);
			System.out.println("usuario excluido com sucesso");
			
			DAO.commit();
			

		}
		return null;

	}
	
	public Usuario findByNome(String nomeCalendario) {
		return daoUsuario.findByNome(nomeCalendario);
	}

	public Calendario findByDescricao(String feriado) {
		return daoCalendario.findByAno(feriado);
	}
	
	public void atualizarAdministrador (Usuario administrador, String senha ) {
		administrador.setSenha(senha);
		daoUsuario.update(administrador);
		
	}
	
	public void removerUsuario(Usuario usuario) {
	
	}
	
	
	public List<Feriado> findAllFeriados(){
		return daoFeriado.readAll();
	}


}

