package imerir.android.trombinoscope;

public class Profil {
	
	private int id;
	private String nom;
	private String prenom;
	private String groupe;
	private String img;
	
	public Profil(){
	}
	
	public Profil(int id, String nom, String prenom, String groupe,String img){
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

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getGroupe() {
		return groupe;
	}
}
