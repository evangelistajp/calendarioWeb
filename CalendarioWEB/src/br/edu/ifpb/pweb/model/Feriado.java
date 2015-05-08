package br.edu.ifpb.pweb.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Feriado {
	
	@Id		
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date data;
	private boolean fixo;
		
	public Feriado() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isFixo() {
		return fixo;
	}

	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}

	@Override
	public String toString() {
		return "Feriado [id=" + id + ", nome=" + nome + ", data=" + data
				+ ", fixo=" + fixo + "]";
	}

	
	
	

}
