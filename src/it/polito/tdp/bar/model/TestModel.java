package it.polito.tdp.bar.model;

public class TestModel {

	public static void main(String[] args) {
		
		Simulation simulatore = new Simulation();
		simulatore.init();
		simulatore.run();
		
		System.out.println("Numero totale Gruppi Soddisfatti:  " + simulatore.getNumeroGruppiSoddisfatti());
		System.out.println("Numero totale Gruppi Insoddisfatti:  " + simulatore.getNumeroGruppiInsoddisfatti());
		System.out.println("Numero totale Clienti Soddisfatti:  " + simulatore.getNumeroClientiSoddisfatti());
		System.out.println("Numero totale Clienti Insoddisfatti:  " + simulatore.getNumeroClientiInsoddisfatti());
		System.out.println("Numero totale Clienti:  " + simulatore.getNumeroTotaleClienti());

	}

}
