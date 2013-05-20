package imerir.android.trombinoscope;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
			PROFIL_IMG + " BLOB" +
			");";

	public static final String PROFIL_TABLE_DROP="DROP TABLE IF EXISTS "+PROFIL_TABLE_NAME+";";
	
	public void ajouter(Profil p){
		ContentValues cv = new ContentValues();
		cv.put(PROFIL_NOM, p.getNom());
		cv.put(PROFIL_PRENOM, p.getPrenom());
		cv.put(PROFIL_GROUPE, p.getGroupe());
		ByteArrayOutputStream baops = new ByteArrayOutputStream() ;
		p.getImg().compress(Bitmap.CompressFormat.JPEG, 100, baops);
		cv.put(PROFIL_IMG,baops.toByteArray());
		pDb.insert(ProfilDAO.PROFIL_TABLE_NAME, null, cv);
	}
	
	public void supprimer(Profil p){
		pDb.delete(PROFIL_TABLE_NAME, PROFIL_CLE + " = ?", new String[] {String.valueOf(p.getId())});	
	}
	
	public void modifier(Profil p){
		ContentValues cv = new ContentValues();
		cv.put(PROFIL_NOM, p.getNom());
		cv.put(PROFIL_PRENOM, p.getPrenom());
		cv.put(PROFIL_GROUPE, p.getGroupe());
		ByteArrayOutputStream baops = new ByteArrayOutputStream() ;
		p.getImg().compress(Bitmap.CompressFormat.JPEG, 100, baops);
		cv.put(PROFIL_IMG,baops.toByteArray());
		pDb.update(PROFIL_TABLE_NAME, cv,PROFIL_CLE + " = ?", new String[] {String.valueOf(p.getId())});	
	}
	
	public Profil selectionnerUnProfil(int id){
		 Cursor c = pDb.rawQuery("SELECT "+PROFIL_CLE+", "+PROFIL_NOM+", "+PROFIL_PRENOM+", "+PROFIL_GROUPE+", "+PROFIL_IMG+" FROM "+PROFIL_TABLE_NAME+" WHERE "+PROFIL_CLE+"=?;", new String[] {String.valueOf(id)});
		  c.moveToFirst();
		  byte[] photo=c.getBlob(4);
		  ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
		  Bitmap img= BitmapFactory.decodeStream(imageStream);
		return new Profil(c.getInt(0),c.getString(1),c.getString(2), c.getString(3),img);
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
}
