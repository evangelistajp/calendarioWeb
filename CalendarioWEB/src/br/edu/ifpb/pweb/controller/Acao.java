package br.edu.ifpb.pweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
	
	public String execute(HttpServletRequest request, HttpServletResponse response);
		

}
