package imerir.android.trombinoscope;

import android.graphics.Bitmap;

public class Profil {
	
	private int id;
	private String nom;
	private String prenom;
	private String groupe;
	private Bitmap img;
	
	public Profil(int id, String nom, String prenom, String groupe,Bitmap img){
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.groupe=groupe;
		this.img=img;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setImg(Bitmap img) {
		this.img = img;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getGroupe() {
		return groupe;
	}
}
