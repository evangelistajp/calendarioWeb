package br.edu.ifpb.pweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class FeriadoFixo extends Feriado{
	
	private boolean isSubstituivel;
	@Column(nullable= true)
	private int diaSubstituta;
	@Column(nullable= true)
	private int mesSubstituta;
	@Column(nullable= false)
	private int anoSubstituta;
	
	
	public boolean isSubstituivel() {
		return isSubstituivel;
	}
	public void setSubstituivel(boolean isSubstituivel) {
		this.isSubstituivel = isSubstituivel;
	}
	public int getDiaSubstituta() {
		return diaSubstituta;
	}
	public void setDiaSubstituta(int diaSubstituta) {
		this.diaSubstituta = diaSubstituta;
	}
	public int getMesSubstituta() {
		return mesSubstituta;
	}
	public void setMesSubstituta(int mesSubstituta) {
		this.mesSubstituta = mesSubstituta;
	}
	public int getAnoSubstituta() {
		return anoSubstituta;
	}
	public void setAnoSubstituta(int anoSubstituta) {
		this.anoSubstituta = anoSubstituta;
	}
	
	
	@Override
	public String toString() {
		return "FeriadoFixo [isSubstituivel=" + isSubstituivel
				+ ", diaSubstituta=" + diaSubstituta + ", mesSubstituta="
				+ mesSubstituta + ", anoSubstituta=" + anoSubstituta + "]";
	}
	
	
	

}
