package imerir.android.trombinoscope;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListerProfils extends Activity {

	private ProfilDAO pDao;
	private Profil p;
	private ListView listeProfils;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste_profils);

		listeProfils = (ListView) findViewById(R.id.liste_profils);

		ArrayList<HashMap<String, Object>> listeElemProfils = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		p = new Profil();
		p.setImg(null);
		pDao = new ProfilDAO(this);
		pDao.open();
		Cursor c = pDao.selectionnerTousLesProfils();
		c.moveToFirst();
		if (!c.isAfterLast()) {		
			do {
				map = new HashMap<String, Object>();
				map.put("Nom", c.getString(1));
				map.put("Prenom", c.getString(2));
				map.put("Photo", c.getString(4));
				showTestDialog(c.getString(1));
				listeElemProfils.add(map);
			} while (c.moveToNext());
			c.close();
		}

		final SimpleAdapter listeProfilVersViewAdapteur = new SimpleAdapter(
				this, listeElemProfils,
				R.layout.profil_element_liste, new String[] { "Nom", "Prenom", "Photo"},
				new int[] { R.id.nom, R.id.prenom, R.id.img });
		listeProfilVersViewAdapteur.setViewBinder(new MyViewBinder());
		try{
		listeProfils.setAdapter(listeProfilVersViewAdapteur);
		}catch(OutOfMemoryError oom){
			showTestDialog(oom.getMessage());
		}

	}

	private void showTestDialog(String s){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Test");
		alertDialogBuilder.setMessage(s);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
