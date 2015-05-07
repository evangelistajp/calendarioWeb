package br.edu.ifpb.pweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb.controller.ControllerFacade;
import br.edu.ifpb.pweb.dao.DAO;
import br.edu.ifpb.pweb.exception.CadastroException;
import br.edu.ifpb.pweb.exception.ErroLogicoException;
import br.edu.ifpb.pweb.model.Usuario;


@WebServlet("/executa")
public class FrontCrontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Usuario us = null;
	private String nome;
	private String email;
	private String senha;
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");
		
		DAO.open();
		
		if (op == null) {
			throw new IllegalArgumentException("Ação inválida");
		}
		switch (op) {
		case "login":
			//Resgata parâmetros da requisição
				
				this.email = request.getParameter("email");
				this.senha = request.getParameter("senha");
				try {
					us = ControllerFacade.realizaLogin(email, senha);
					
					if (us != null) {
						System.out.println("Usuario Logado com sucesso");
						if (us.isIsadmin()) {
							System.out.println(us);
							HttpSession session = request.getSession(true);
							session.setAttribute("admin", us.getEmail());
							//request.setAttribute("admin", us.getEmail());
							request.getRequestDispatcher("admin.jsp").forward(request, response);
						}else{
							System.out.println(us);
							HttpSession session = request.getSession(true);
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
		case "cadastrar":
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
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			break;
		case "mudarsenha":
			
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
