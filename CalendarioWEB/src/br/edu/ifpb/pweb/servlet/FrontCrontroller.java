package br.edu.ifpb.pweb.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb.controller.ControllerFacade;
import br.edu.ifpb.pweb.dao.DAO;
import br.edu.ifpb.pweb.exception.CadastroException;
import br.edu.ifpb.pweb.model.Anotacao;
import br.edu.ifpb.pweb.model.Feriado;
import br.edu.ifpb.pweb.model.Usuario;


@WebServlet("/executa")
public class FrontCrontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Usuario us = null;
	Anotacao an = null;
	Feriado fe = null;
	private String nome;
	private String email;
	private String senha;
	private String data;
	private String id;
	private String desc;
	private boolean fixo;
	
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String op = request.getParameter("op");
		
		DAO.open();
		
		if (op == null) {
			throw new IllegalArgumentException("Ação inválida");
		}
		switch (op) {
		case "login":
			//Resgata parâmetros da requisição
			System.out.println("login");
			this.email = request.getParameter("email");
			this.senha = request.getParameter("senha");
			try {
				us = ControllerFacade.realizaLogin(email, senha);
					
				if (us != null) {
					System.out.println("Usuario Logado com sucesso");
					if (us.isIsadmin()) {
						System.out.println(us);
						session = request.getSession(true);
						session.setAttribute("admin", us.getEmail());
						//request.setAttribute("admin", us.getEmail());
						request.getRequestDispatcher("admin.jsp").forward(request, response);
					}else{
						System.out.println(us);
						session = request.getSession(true);
						session.setAttribute("usuario", us.getEmail());
						//request.setAttribute("usuario", us.getEmail());
						request.getRequestDispatcher("usuario.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				request.setAttribute("erro", "Usuário ou senha inexistente ");
				request.getRequestDispatcher("index.jsp").forward(request, response);

				//System.out.println(e.getMessage());
			}
			break;
		case "cadastrarusuario":
			System.out.println("cadastrar Usuário");
			this.nome = request.getParameter("nome");
			this.email = request.getParameter("email");
			this.senha = request.getParameter("senha");
			try {
				us = ControllerFacade.cadastrarUsuario(nome, email, senha);
				request.setAttribute("ok", "--> cadastrado com sucesso <-- ");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} catch (CadastroException e) {
				request.setAttribute("erro", "--> Usuario já cadastrado <--");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				System.out.println("-->" + e.getMessage());
			}
				
			break;
			
		case "logout":
			
			System.out.println("logout");
			session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			break;
		case "mudarsenha":
			System.out.println("Mudar Senha");
			String email = request.getParameter("email");
			String novasenha = request.getParameter("novasenha");
			String novasenha2 = request.getParameter("novasenha2");
			
			if(novasenha.equals(novasenha2)){
				try {
							
					us= ControllerFacade.mudarsenha(email,novasenha);	
							
				}catch(Exception e)	{
					request.setAttribute("erro", "Erro: ");
					request.getRequestDispatcher("mudarSenha.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("erro", "Senhas não confere");
				request.getRequestDispatcher("mudarSenha.jsp").forward(request, response);
			}
			
			request.setAttribute("ok", "Senhas Alterada com sucesso");
			request.getRequestDispatcher("mudarSenha.jsp").forward(request, response);
			
			break;	
		case "excluirusuario":
			System.out.println("Excluir Usuário");
			this.email = request.getParameter("email");
		    try {
		    	us= ControllerFacade.excluirusuario(this.email);
		    	request.setAttribute("ok", "Usuario excluido com sucesso");
		    	request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} catch (Exception e) {
				request.setAttribute("erro", "Email não corresponde a conta: ");
				request.getRequestDispatcher("excluirusuario.jsp").forward(request, response);
			}	

			break;
		case "cadastrarAnotacao":
			System.out.println("cadastro de Anotação");
			this.email = request.getParameter("email");
			String desc = request.getParameter("desc");
			String data = request.getParameter("data");
			
			System.out.println(data);
			try {
				an = ControllerFacade.addAnotacao(this.email,desc, data);
				request.setAttribute("ok", "Anotação add");
		    	request.getRequestDispatcher("gerenciarAnotacao.jsp").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			break;
		case "cadastrarFeriado":
				System.out.println("cadastro de Feriado");
				
				this.nome = request.getParameter("nome");
				this.data = request.getParameter("data");
				this.fixo = request.getParameter("fixo") != null;
				
				System.out.println(this.fixo);
				
				System.out.println(this.data);
				try {
					fe = ControllerFacade.addFeriado(this.nome, this.data, this.fixo );
					request.setAttribute("ok", "Feriado add");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				break;	
			
		case "anotacoes":
			session = request.getSession();
			
			if(session.getAttribute("usuario") != null) {
				
				this.email = (String) session.getAttribute("usuario");
				Usuario usuario = ControllerFacade.buscaUsuario(this.email);
				
				List<Anotacao> anotacoes = new ArrayList<Anotacao>();
				
				anotacoes = usuario.getAnotacoes();
								
				request.setAttribute("anotacoes", anotacoes);
			}
			
			//request.setAttribute("feriados", listaFeriados);
			request.getRequestDispatcher("listarAnotacao.jsp").forward(request, response);
			
			break;
		case "feriados":
			
			List<Feriado> feriados = new ArrayList<Feriado>();
			
			feriados = ControllerFacade.buscaFeriados();
			
			request.setAttribute("feriados", feriados);
			
			request.getRequestDispatcher("listarFeriado.jsp").forward(request, response);
			
			break;
		case "feriadosAll":
			
			List<Feriado> feriadosall = new ArrayList<Feriado>();
			
			feriados = ControllerFacade.buscaFeriados();
			
			request.setAttribute("feriados", feriadosall);
			
			request.getRequestDispatcher("listar.jsp").forward(request, response);
			
			break;	
		case "buscarAnotacao":
			
				this.id = request.getParameter("id");
								
				try{
					
					an = ControllerFacade.buscaranotacao(this.id);
					System.out.println(an);
					request.setAttribute("anotacao", an);
					request.setAttribute("data", an.getData());
					request.getRequestDispatcher("editarAnotacao.jsp").forward(request, response);
				
				} catch (Exception e){
					
				}
				
			break;
		case "buscarFeriado":
			
			this.id = request.getParameter("id");
							
			try{
				
				fe = ControllerFacade.buscarFeriado(this.id);
				System.out.println(fe);
				request.setAttribute("feriado", fe);
				request.setAttribute("data", fe.getData());
				request.getRequestDispatcher("editarFeriado.jsp").forward(request, response);
			
			} catch (Exception e){
				
			}
			
		break;	
		case "excluirAnotacao":
			
			this.id = request.getParameter("id");
			
			try {
				an = ControllerFacade.excluiranotacao(this.id);
				
				request.setAttribute("ok", "Anotação excluida");
		    	request.getRequestDispatcher("gerenciarAnotacao.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;	
		case "excluirferiado":
			
			this.id = request.getParameter("id");
			
			try {
				an = ControllerFacade.excluirFeriados(this.id);
				
				request.setAttribute("ok", "Feriado excluido");
		    	request.getRequestDispatcher("admin.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;	
			
		case "editarAnotacao":
			
			this.desc = request.getParameter("desc");
			this.data = request.getParameter("data");
			this.id = request.getParameter("id");
			System.out.println(this.data);				
			try{
				
				an = ControllerFacade.editarAnotacao(this.id, this.desc, this.data);
				request.setAttribute("ok", "Anotação alterada");
		    	request.getRequestDispatcher("gerenciarAnotacao.jsp").forward(request, response);
			
			} catch (Exception e){
				
			}
			
		break;	
		
		case "editarFeriado":
			
			System.out.println("editar feriado");
			this.nome = request.getParameter("nome");
			this.data = request.getParameter("data");
			this.id = request.getParameter("id");
			//System.out.println(this.data);				
			try{
				System.out.println("try");				
				fe = ControllerFacade.editarFeriado(this.id, this.nome, this.data);
				request.setAttribute("ok", "Feriado alterada");
		    	request.getRequestDispatcher("admin.jsp").forward(request, response);
			
			} catch (Exception e){
				
			}
		
		default:
			break;
		}	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doRequest(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doRequest(request, response);
	}

}
