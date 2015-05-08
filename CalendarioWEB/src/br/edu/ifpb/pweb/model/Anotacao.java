package br.edu.ifpb.pweb.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Anotacao {
	
	@Id		
	@GeneratedValue
	private long id;
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne(cascade = CascadeType.DETACH)
	private Usuario usuario;
	
	
	public Anotacao() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", descricao=" + descricao + ", data="
				+ data + ", usuario=" + usuario + "]";
	}
	
	
	
	
	

}
