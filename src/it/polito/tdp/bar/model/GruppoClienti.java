package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;


public class GruppoClienti {
	
	static private int idGruppo = 0; 
	private int id;
	private LocalTime time; //istante di arrivo del gruppo
	private int nPersone;
	private Duration durata; //permanenza al tavolo
	private float toll;

	public GruppoClienti(LocalTime time, int nPersone, Duration durata, float toll) {
		super();
		this.id = ++idGruppo;
		this.time = time;
		this.nPersone = nPersone;
		this.durata = durata;
		this.toll = toll;
	}

	public static int getIdGruppo() {
		return idGruppo;
	}

	public static void setIdGruppo(int idGruppo) {
		GruppoClienti.idGruppo = idGruppo;
	}

	public int getId() {
		return id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public float getToll() {
		return toll;
	}

	public void setToll(float toll) {
		this.toll = toll;
	}

	@Override
	public String toString() {
		return  Integer.toString(id);
	}
	
	
	
	

}
