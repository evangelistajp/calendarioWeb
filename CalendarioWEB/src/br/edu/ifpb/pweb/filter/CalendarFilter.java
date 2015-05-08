package br.edu.ifpb.pweb.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb.controller.ControllerFacade;
import br.edu.ifpb.pweb.model.Anotacao;
import br.edu.ifpb.pweb.model.Feriado;
import br.edu.ifpb.pweb.model.Usuario;

/**
 * Servlet Filter implementation class CalendarFilter
 */
@WebFilter()
public class CalendarFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CalendarFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//		System.out.println("Filter");
//		List<Feriado> feriado =  new ArrayList<Feriado>();
//		feriado = ControllerFacade.buscaFeriados();
//		
//		request.setAttribute("feriados", feriado);
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("okfilter");
	}

}
