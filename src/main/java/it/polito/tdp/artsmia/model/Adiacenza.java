package it.polito.tdp.artsmia.model;

public class Adiacenza implements Comparable<Adiacenza>{
	private Integer artista1;
	private Integer artista2;
	private int peso;
	
	public Adiacenza(Integer artista1, Integer artista2, int peso) {
		super();
		this.artista1 = artista1;
		this.artista2 = artista2;
		this.peso = peso;
	}

	public Integer getArtista1() {
		return artista1;
	}

	public void setArtista1(Integer artista1) {
		this.artista1 = artista1;
	}

	public Integer getArtista2() {
		return artista2;
	}

	public void setArtista2(Integer artista2) {
		this.artista2 = artista2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Adiacenza o) {
		return o.peso - this.peso;
	}

	@Override
	public String toString() {
		return artista1 + ", " + artista2 + " - " + peso;
	}
}
