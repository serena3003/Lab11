package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Evento.Tipo_Evento;
import it.polito.tdp.bar.model.Tavoli.Stato_Tavolo;

public class Simulation {
	
	private PriorityQueue<Evento> queue=new PriorityQueue<>();
	private Random rand = new Random();
	
	//Stato del mondo
	private List<Tavoli> listTavoli;
	
	//Parametri di simulazione
	private int N_TAVOLI = 15;
	private int NC=2000;
	private int MAX_PERSONE= 10;
	private int MAX_INTERVALLO=10;
	private int MIN_PERMANENZA=60;
	private Duration permanenza;
	private LocalTime intervalloArrivo;
	private float tolleranza;
	
	private LocalTime oraInizio = LocalTime.of(8, 0);
	private LocalTime oraFine = LocalTime.of(20, 0);
	
	private int numeroTotaleClienti;
	private int numeroGruppiSoddisfatti;
	private int numeroGruppiInsoddisfatti;
	private int numeroClientiSoddisfatti;
	private int numeroClientiInsoddisfatti;
	
	public Simulation() {
				
	}
	
	public void init() {
		this.queue.clear();
		listTavoli = new ArrayList<Tavoli>(N_TAVOLI);
		listTavoli.add(new Tavoli(10));
		listTavoli.add(new Tavoli(10));
		listTavoli.add(new Tavoli(8));
		listTavoli.add(new Tavoli(8));
		listTavoli.add(new Tavoli(8));
		listTavoli.add(new Tavoli(8));
		listTavoli.add(new Tavoli(6));
		listTavoli.add(new Tavoli(6));
		listTavoli.add(new Tavoli(6));
		listTavoli.add(new Tavoli(6));
		listTavoli.add(new Tavoli(4));
		listTavoli.add(new Tavoli(4));
		listTavoli.add(new Tavoli(4));
		listTavoli.add(new Tavoli(4));
		listTavoli.add(new Tavoli(4));

		
		for(int i=0; i<NC; i++) {
			int nPers = rand.nextInt(MAX_PERSONE)+1; //persone in un gruppo 
			permanenza = Duration.ofMinutes(rand.nextInt(MIN_PERMANENZA)+60);
			intervalloArrivo = oraInizio.plus(Duration.ofMinutes(rand.nextInt(MAX_INTERVALLO)+1));
			tolleranza = rand.nextFloat();
			GruppoClienti gc = new GruppoClienti(intervalloArrivo, nPers, permanenza, tolleranza);
			queue.add(new Evento(intervalloArrivo, gc));
		}
		
		numeroTotaleClienti=0;
		numeroClientiSoddisfatti=0;
		numeroClientiInsoddisfatti=0;
		numeroGruppiSoddisfatti=0;
		numeroGruppiInsoddisfatti=0;
	}
	
	public void run() {
		
		while(!queue.isEmpty()) {
			
			Evento e = queue.poll();

			switch(e.getTipo()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				Tavoli tavolo = trovaTavolo(e.getGruppo().getnPersone());
				if(tavolo!= null) {
					e.setTavolo(tavolo);
					tavolo.setStato(Stato_Tavolo.OCCUPATO);
					numeroGruppiSoddisfatti++;
					numeroTotaleClienti = numeroTotaleClienti + e.getGruppo().getnPersone();
					numeroClientiSoddisfatti = numeroClientiSoddisfatti + e.getGruppo().getnPersone();
					
					GruppoClienti gc = e.getGruppo();
					System.out.println(gc.toString());
					LocalTime gcPermanenza = e.getTime().plus(gc.getDurata());
					System.out.println(gcPermanenza.toString());
					System.out.println(tavolo.toString());
					queue.add(new Evento(gc, gcPermanenza, tavolo));
				}
				else {
					if(e.getGruppo().getToll()!=0.0) {
						numeroTotaleClienti = numeroTotaleClienti + e.getGruppo().getnPersone();
						numeroClientiSoddisfatti = numeroClientiSoddisfatti + e.getGruppo().getnPersone();
					}
					else {
						numeroTotaleClienti = numeroTotaleClienti + e.getGruppo().getnPersone();
						numeroGruppiInsoddisfatti++;
						numeroClientiInsoddisfatti = numeroClientiInsoddisfatti + e.getGruppo().getnPersone();
						}
				}
				break;
			case TAVOLO_LIBERATO:
				e.getTavolo().setStato(Stato_Tavolo.LIBERO);
				break;
			}
		}
		
	}

	private Tavoli trovaTavolo(int nPers) {
		int postiTavoloMin = Integer.MAX_VALUE;
		Tavoli returnTable = null;
		for(Tavoli t : listTavoli) {
			if((t.getStato()==Stato_Tavolo.LIBERO) && (nPers >= 0.5 * t.getnPosti())) {
				if(t.getnPosti()<=postiTavoloMin) {
					returnTable=t;
					postiTavoloMin = t.getnPosti();
				}
			}
		}
		return returnTable;
	}

	public int getN_TAVOLI() {
		return N_TAVOLI;
	}

	public int getNumeroTotaleClienti() {
		return numeroTotaleClienti;
	}

	public int getNumeroGruppiSoddisfatti() {
		return numeroGruppiSoddisfatti;
	}

	public int getNumeroGruppiInsoddisfatti() {
		return numeroGruppiInsoddisfatti;
	}

	public int getNumeroClientiSoddisfatti() {
		return numeroClientiSoddisfatti;
	}

	public int getNumeroClientiInsoddisfatti() {
		return numeroClientiInsoddisfatti;
	}

}