package imerir.android.trombinoscope;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Méthodes génériques d'ouverture et fermeture de la base 'databaseProfil.db'
 * @author Adri
 */
public abstract class DAOBase {

	  //Version courante de la base
	  protected final static int VERSION = 3;
	  //Nom de la base
	  protected final static String NOM = "databaseProfil.db";
	     
	  protected SQLiteDatabase pDb = null;
	  protected DatabaseHandler pHandler = null;
	     
	  public DAOBase(Context pContext) {
	    this.pHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
	  }
	     
	  /**
	   * Ouverture de la base
	   * @return SQLiteDatabase
	   */
	  public SQLiteDatabase open() {
	    pDb = pHandler.getWritableDatabase();
	    return pDb;
	  }
	     
	  /**
	   * Fermeture de la base
	   */
	  public void close() {
	    pDb.close();
	  }
	     
	  public SQLiteDatabase getDb() {
	    return pDb;
	  }
	}