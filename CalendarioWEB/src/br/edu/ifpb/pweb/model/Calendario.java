package br.edu.ifpb.pweb.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.crypto.Data;


@Entity
public class Calendario {
	
	@Id		
	@GeneratedValue
	private long id;
	private int ano;
	@OneToMany
	private Collection<Feriado> feriados = new ArrayList<Feriado>();

	
	public Calendario() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Collection<Feriado> getFeriados() {
		return feriados;
	}


	public void setFeriados(Collection<Feriado> feriados) {
		this.feriados = feriados;
	}

	

	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	@Override
	public String toString() {
		return "Calendario [id=" + id + ", feriados=" + feriados + "]";
	}
	
	
	

}
