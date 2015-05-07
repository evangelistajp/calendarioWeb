package br.edu.ifpb.pweb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Feriado {
	
	@Id		
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String descricao;
	private int dia;
	private int mes;
	@Column(nullable=true)
	private int ano;
		
	public Feriado() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescicao() {
		return descricao;
	}

	public void setDescicao(String descicao) {
		this.descricao = descicao;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Feriado [id=" + id + ", descricao=" + descricao + "]";
	}
	
	

}
