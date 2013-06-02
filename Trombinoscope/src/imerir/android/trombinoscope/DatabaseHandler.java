package imerir.android.trombinoscope;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

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
	
	
    public DatabaseHandler(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(PROFIL_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(PROFIL_TABLE_DROP);
		onCreate(db);
	}
}
