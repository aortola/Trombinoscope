package imerir.android.trombinoscope;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

public class GridListerProfils extends Activity {

	private ProfilDAO pDao;
	private Profil p;
	private Cursor c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_liste_profil);
		GridView gridView = (GridView) findViewById(R.id.grid_view_listing);

		ArrayList<HashMap<String, Object>> listeElemProfils = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		p = new Profil();
		p.setImg(null);
		pDao = new ProfilDAO(this);
		pDao.open();

		c = pDao.selectionnerTousLesProfils();
		c.moveToFirst();
		if (!c.isAfterLast()) {
			do {
				if(!c.getString(4).equals("null")){
					map = new HashMap<String, Object>();
					map.put("Nom", c.getString(1));
					map.put("Prenom", c.getString(2));
					map.put("Photo", c.getString(4));
					listeElemProfils.add(map);
				}
			} while (c.moveToNext());
			c.close();
		}

		String[] photos = new String[listeElemProfils.size()];
		for(int i=0;i<listeElemProfils.size();i++){
			photos[i]=new String();
			photos[i]=(String) listeElemProfils.get(i).get("Photo");
		}
		gridView.setAdapter(new GridListingImageAdapter(this,photos));
	}
}
