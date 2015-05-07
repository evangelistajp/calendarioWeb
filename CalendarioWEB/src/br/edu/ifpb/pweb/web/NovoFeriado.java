package br.edu.ifpb.pweb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb.controller.Acao;

public class NovoFeriado implements Acao{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String descricao = request.getParameter("descricao");
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		
		
		
		
		return null;
	}
	
	
}
