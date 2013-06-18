package imerir.android.trombinoscope;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ProfilDAO extends DAOBase{

	public ProfilDAO(Context pContext) {
		super(pContext);
	}
	
	public static final String PROFIL_CLE="id";
	public static final String PROFIL_NOM="nom";
	public static final String PROFIL_PRENOM="prenom";
	public static final String PROFIL_GROUPE="groupe";
	public static final String PROFIL_IMG="picture";

	
	public static final String PROFIL_TABLE_NAME="Profil";
	public static final String PROFIL_TABLE_CREATE=
			"CREATE TABLE " + PROFIL_TABLE_NAME + "(" +
			PROFIL_CLE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			PROFIL_NOM + " TEXT," +
			PROFIL_PRENOM + " TEXT, " +
			PROFIL_GROUPE + " TEXT, " +
			PROFIL_IMG + " TEXT" +
			");";

	public static final String PROFIL_TABLE_DROP="DROP TABLE IF EXISTS "+PROFIL_TABLE_NAME+";";
	
	public void ajouter(Profil p){
		ContentValues cv = new ContentValues();
		cv.put(PROFIL_NOM, p.getNom());
		cv.put(PROFIL_PRENOM, p.getPrenom());
		cv.put(PROFIL_GROUPE, p.getGroupe());
		cv.put(PROFIL_IMG, p.getImg());
		pDb.insert(ProfilDAO.PROFIL_TABLE_NAME, null, cv);
	}
	
	public void supprimer(Profil p){
		pDb.delete(PROFIL_TABLE_NAME, PROFIL_CLE + " = ?", new String[] {String.valueOf(p.getId())});	
	}
	
	public void supprimerAll(){
		Cursor c = selectionnerTousLesProfils();
		c.moveToFirst();
		Profil p;
		do{
			p= new Profil();
			p.setId(c.getInt(0));
			pDb.delete(PROFIL_TABLE_NAME, PROFIL_CLE + " = ?", new String[] {String.valueOf(p.getId())});	
		}while(c.moveToNext());
	}
	
	public void modifier(Profil p){
		ContentValues cv = new ContentValues();
		cv.put(PROFIL_NOM, p.getNom());
		cv.put(PROFIL_PRENOM, p.getPrenom());
		cv.put(PROFIL_GROUPE, p.getGroupe());
		cv.put(PROFIL_IMG,p.getImg());
		pDb.update(PROFIL_TABLE_NAME, cv,PROFIL_CLE + " = ?", new String[] {String.valueOf(p.getId())});	
	}
	
	public Profil selectionnerUnProfil(int id){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+" WHERE "+PROFIL_CLE+"=?;", new String[] {String.valueOf(id)});
		  c.moveToFirst();
		return new Profil(c.getInt(0),c.getString(1),c.getString(2), c.getString(3),c.getString(4));
	}
	
	public Cursor selectionnerTousLesProfils(){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+";", null);
		return c;
	}
	
	public Cursor selectionnerLesProfilsParGroupe(String groupe){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+" WHERE "+PROFIL_GROUPE+"=?;", new String[] {groupe});
		return c;
	}

	public Cursor selectionnerLesProfilsParNom(String nom){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+" WHERE "+PROFIL_NOM+"=?;", new String[] {nom});
		return c;
	}
	
	public Cursor selectionnerLesProfilsParPrenom(String prenom){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+" WHERE "+PROFIL_PRENOM+"=?;", new String[] {prenom});
		return c;
	}
	
	public Cursor selectionnerProfilsCorrespondant(String nom, String prenom, String groupe, String photo){
		
		String where = " WHERE ";
		String var = "=?";
		String and = " AND ";
		String query ="";
		int i=0;
		Cursor c;
		ArrayList<String> params = new ArrayList<String>();
		
		if(nom!=null && nom.compareTo("")!=0){
			query=query+where+PROFIL_NOM+var;
			params.add(nom);
			i++;
		}
		
		
		if(prenom!=null && prenom.compareTo("")!=0){
	        if(i>0){
	        	i--;
	        	query=query+and;
	        }
			query=query+where+PROFIL_PRENOM+var;
			params.add(prenom);
			i++;
		}
		
		if(groupe!=null && groupe.compareTo("")!=0){
	        if(i>0){
	        	i--;
	        	query=query+and;
	        }
			query=query+where+PROFIL_GROUPE+var;
			params.add(groupe);
			i++;
		}
		
		if(photo!=null && photo.compareTo("no")!=0){
	        if(i>0){
	        	i--;
	        	query=query+and;
	        }
			query=query+where+PROFIL_IMG+"!='null'";
			i++;
		}else if(photo!=null && photo.compareTo("no")==0 ){
	        if(i>0){
	        	i--;
	        	query=query+and;
	        }
			query=query+where+PROFIL_IMG+"='null'";
			i++;
		}
	
		String[] test = new String[params.size()];
		for(int k=0;k<params.size();k++){
			test[k]=new String();
			test[k]=params.get(k);
		}
		
		if(i==0){
			c=selectionnerTousLesProfils();
		}else{
			c=pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+query+";", test);
		}

	//	c = "SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+query+";"+"|"+test[0];
		return c;
	}
}
