package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private SimpleWeightedGraph<Integer, DefaultWeightedEdge> grafo;
	private ArtsmiaDAO dao;
	private List<Adiacenza> archi;
	private List<Integer> percorsoBest;
	private int pesoMax;
	
	public Model() {
		dao = new ArtsmiaDAO();
		pesoMax = 0;
	}
	
	public List<String> getRuoli() {
		return dao.getRuoli();
	}
	
	public void creaGrafo(String ruolo) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getVertici(ruolo));
		archi = new ArrayList<>(dao.getArchi(ruolo));
		
		for(Adiacenza adiacenza : archi) {
			if(grafo.containsVertex(adiacenza.getArtista1()) && grafo.containsVertex(adiacenza.getArtista2())) {
				Graphs.addEdgeWithVertices(grafo, adiacenza.getArtista1(), adiacenza.getArtista2(), adiacenza.getPeso());
			}
		}
	}
	
	public Integer getNVertici() {
		return grafo.vertexSet().size();
	}
	 
	public Integer getNArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Adiacenza> artistiConnessi() {
		Collections.sort(archi);
		return archi;
	}
	
	public String calcolaPercorso(Integer idArtista) {
		String stampa = "";
		percorsoBest = new ArrayList<>();
		List<Integer> parziale = new ArrayList<>();
		parziale.add(idArtista);
		
		cerca(-1, parziale);
		
		stampa += "Numero di esposizioni per cui il percorso risulta massimo: " + pesoMax + "\n";
		for(Integer nextInteger : percorsoBest) {
			stampa += nextInteger + "\n";
		}
		return stampa;
	}

	private void cerca(int peso, List<Integer> parziale) {
		
		// Condizione di terminazione
		if(parziale.size() > percorsoBest.size() && peso != -1) {
			percorsoBest = new ArrayList<>(parziale);
			pesoMax = peso;
			//return;
		}
		
		Integer last = parziale.get(parziale.size()-1);
		for (Integer idNext : Graphs.neighborListOf(grafo, last)) {
			if(!parziale.contains(idNext) && parziale.size() == 1) {
				parziale.add(idNext);
				cerca((int)grafo.getEdgeWeight(grafo.getEdge(idNext, last)), parziale);
				parziale.remove(parziale.size()-1);		
				peso = 0; 
			}
			else if(!parziale.contains(idNext) && grafo.getEdgeWeight(grafo.getEdge(idNext, last)) == peso) {
				parziale.add(idNext);
				cerca((int)grafo.getEdgeWeight(grafo.getEdge(idNext, last)), parziale);
				parziale.remove(parziale.size()-1);
				peso = 0;
			}
			
		}
		
	}
}
