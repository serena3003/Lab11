package it.polito.tdp.bar.model;

public class Tavoli {
	
	private int nPosti;
	private Stato_Tavolo stato;
	
	public enum Stato_Tavolo{
		OCCUPATO,
		LIBERO
	}

	public Tavoli(int nPosti) {
		this.nPosti = nPosti;
		this.stato = Stato_Tavolo.LIBERO;
	}

	public int getnPosti() {
		return nPosti;
	}

	public void setnPosti(int nPosti) {
		this.nPosti = nPosti;
	}

	public Stato_Tavolo getStato() {
		return stato;
	}

	public void setStato(Stato_Tavolo stato) {
		this.stato = stato;
	}
	
	

}
