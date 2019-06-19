package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Evento implements Comparable<Evento>{

	public enum Tipo_Evento{
		ARRIVO_GRUPPO_CLIENTI,
		TAVOLO_LIBERATO
	}
	
	private Tipo_Evento tipo;
	private LocalTime time;
	private GruppoClienti gruppo;
	private Tavoli tavolo;

	public Evento(LocalTime time, GruppoClienti gruppo) {
		super();
		this.time = time;
		this.tipo=Tipo_Evento.ARRIVO_GRUPPO_CLIENTI;
		this.gruppo=gruppo;
		this.tavolo=null;
	}
	
	public Evento(GruppoClienti gruppo,LocalTime time, Tavoli tavolo) {
		this.tipo=Tipo_Evento.TAVOLO_LIBERATO;
		this.time=time;
		this.gruppo=gruppo;
		this.tavolo=tavolo;
	}

	public Tipo_Evento getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_Evento tipo) {
		this.tipo = tipo;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public GruppoClienti getGruppo() {
		return gruppo;
	}

	public void setGruppo(GruppoClienti gruppo) {
		this.gruppo = gruppo;
	}

	public Tavoli getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavoli tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public int compareTo(Evento ev) {
		if(this.getTime().isAfter(ev.getTime()))
			return -1;
		else if(this.getTime().isBefore(ev.getTime()))
			return 1;
		else
			return 0;
	}

}
