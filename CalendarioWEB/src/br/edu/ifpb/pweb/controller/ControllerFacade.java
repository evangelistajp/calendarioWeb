package br.edu.ifpb.pweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.pweb.dao.DAO;
import br.edu.ifpb.pweb.dao.DAOAnotacao;
import br.edu.ifpb.pweb.dao.DAOCalendario;
import br.edu.ifpb.pweb.dao.DAOFeriado;
import br.edu.ifpb.pweb.dao.DAOUsuario;
import br.edu.ifpb.pweb.exception.CadastroException;
import br.edu.ifpb.pweb.exception.ErroLogicoException;
import br.edu.ifpb.pweb.model.Anotacao;
import br.edu.ifpb.pweb.model.Calendario;
import br.edu.ifpb.pweb.model.Feriado;
import br.edu.ifpb.pweb.model.Usuario;



public class ControllerFacade {
	
	private static DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOAnotacao daoAnotacao = new DAOAnotacao();
	private static DAOCalendario daoCalendario = new DAOCalendario(); 
	private static DAOFeriado daoFeriado = new DAOFeriado(); 
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	//private Usuario usuario;
	
	
	public static void open(){
		daoUsuario = new DAOUsuario();
		daoAnotacao = new DAOAnotacao();
		daoCalendario = new DAOCalendario();
		daoFeriado = new DAOFeriado();

		
	}

	public static Usuario cadastrarUsuario(String nome, String email, String senha) throws CadastroException {
		
		DAO.begin();
		
		List<Usuario> list = daoUsuario.readAll();
		
		Usuario us = daoUsuario.readByEmail(email);
		
		if (us != null) {
			throw new CadastroException("Usuario ja cadastrado: " + email +"\n");
		}
		
		System.out.println(list.size());
		if(list.size() == 0){
			us = new Usuario(nome, email, senha, true);
		}else{
			us = new Usuario(nome, email, senha, false);
		}
		
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
	
	public static Anotacao addAnotacao(String email, String desc, String data) throws ParseException {
		
		DAO.begin();
		
		Usuario usuario = daoUsuario.readByEmail(email);
		System.out.println("usuario " + usuario);
		
		System.out.println(data);
		Date datafor = format.parse(data);
		
		System.out.println(datafor);
		
		Anotacao an = new  Anotacao();
		an.setDescricao(desc);
		an.setData(datafor);
		an.setUsuario(usuario);
		
		usuario.setAnotacao(an);
		
		daoAnotacao.update(an);
		daoUsuario.update(usuario);
		
		DAO.commit();
		
		return an;
	}
	
	public static Feriado addFeriado(String nome, String data, boolean fixo) throws ParseException{
		
		DAO.begin();
		
		Date datafor = format.parse(data);
		
		System.out.println(datafor);
		
		Feriado fe = new Feriado();
		fe.setNome(nome);
		fe.setData(datafor);
 		fe.setFixo(fixo);
 		
 		daoFeriado.update(fe);
 		
 		DAO.commit();
 		
		return fe;
	}

	
	public static Anotacao editarAnotacao(String id, String desc, String data) throws ParseException{
		DAO.begin();
		
		
		Anotacao anotacao = daoAnotacao.findByID(id);
		
		anotacao.setDescricao(desc);
			
		Date datafor = format.parse(data);
		
		anotacao.setData(datafor);
		
		//Usuario us = anotacao.getUsuario();
		
		daoAnotacao.update(anotacao);
		//us.setAnotacao(anotacao);
				
		DAO.commit();
		
		return anotacao;		
	}
	
	public static Feriado editarFeriado(String id, String nome, String data) throws ParseException{
		DAO.begin();
		
		Feriado feriado = daoFeriado.findByID(id);
		System.out.println(feriado);
		
		feriado.setNome(nome);
		Date datafor = format.parse(data);
		feriado.setData(datafor);
		
		daoFeriado.update(feriado);
		
		DAO.commit();
		
		return feriado;
	}


	
	public static Anotacao excluiranotacao(String id){
		DAO.begin();
		
		
		Anotacao anotacao = daoAnotacao.findByID(id);
		
		Usuario us = anotacao.getUsuario();
		
		daoAnotacao.delete(anotacao);
		us.removerAnotacao(anotacao);
		
		DAO.commit();
		
		return null;	
		
	}
	public static Anotacao excluirFeriados(String id) {
		DAO.begin();
		
		Feriado feriado = daoFeriado.findByID(id);
		
		daoFeriado.delete(feriado);
		
		DAO.commit();
		
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
	
	public static Usuario buscaUsuario(String email) {
		
		DAO.begin();
		
		Usuario usuario = daoUsuario.readByEmail(email);
		System.out.println("usuario " + usuario);
		
		DAO.close();
		return usuario;
	}

	public static Anotacao buscaranotacao(String id) {
		DAO.begin();
				
		Anotacao anotacao = daoAnotacao.findByID(id);
		return anotacao;
	}

	public static Feriado buscarFeriado(String id) {
		DAO.begin();
		
		Feriado feriado = daoFeriado.findByID(id);
		return feriado;
	}

	public static List<Feriado> buscaFeriados() {
		System.out.println("busca feriados");
		return daoFeriado.findByAll();
	}

	








}

