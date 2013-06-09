package imerir.android.trombinoscope;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

		HashMap<String, String> stringParams = new HashMap<String, String>();
		HashMap<String, Boolean> booleanParams = new HashMap<String, Boolean>();

		if (this.getIntent().hasExtra("nom")) {
			stringParams.put("nom",
					this.getIntent().getExtras().getString("nom"));
		}
		if (this.getIntent().hasExtra("prenom")) {
			stringParams.put("prenom",
					this.getIntent().getExtras().getString("prenom"));
		}
		if (this.getIntent().hasExtra("groupe")) {
			stringParams.put("groupe",
					this.getIntent().getExtras().getString("groupe"));
		}
		if (this.getIntent().hasExtra("photo")) {
			booleanParams.put("photo",
					this.getIntent().getExtras().getBoolean("photo"));
		}
		Cursor c;
		if (stringParams.size() > 0 || booleanParams.size() > 0) {
			String photo = "no";
			if (booleanParams.size() > 0 && booleanParams.get("photo")) {
				photo = "yes";
			}
			// showTestDialog(stringParams.get("nom"));
			// showTestDialog(stringParams.get("prenom"));
			// showTestDialog(stringParams.get("groupe"));
			// showTestDialog(stringParams.get("photo"));
			//
			c = pDao.selectionnerProfilsCorrespondant(stringParams.get("nom"),
					stringParams.get("prenom"), stringParams.get("groupe"),
					photo);
			// showTestDialog(pDao.selectionnerProfilsCorrespondant(stringParams.get("nom"),
			// stringParams.get("prenom"), stringParams.get("groupe"),
			// photo));

			// c = pDao.selectionnerTousLesProfils();
		} else {
			c = pDao.selectionnerTousLesProfils();
		}

		c.moveToFirst();
		if (!c.isAfterLast()) {
			do {
				map = new HashMap<String, Object>();
				map.put("Id", c.getString(0));
				map.put("Nom", c.getString(1));
				map.put("Prenom", c.getString(2));
				map.put("Photo", c.getString(4));
				// showTestDialog(c.getString(1));
				listeElemProfils.add(map);
			} while (c.moveToNext());
			c.close();
		}

		final SimpleAdapter listeProfilVersViewAdapteur = new SimpleAdapter(
				this, listeElemProfils, R.layout.profil_element_liste,
				new String[] { "Nom", "Prenom", "Photo" }, new int[] {
						R.id.nom, R.id.prenom, R.id.img });
		listeProfilVersViewAdapteur.setViewBinder(new MyViewBinder());
		try {
			listeProfils.setAdapter(listeProfilVersViewAdapteur);
			
			
			class OnItemEditSupprListener implements OnItemClickListener{

				private ArrayList<HashMap<String, Object>> listeElemProfils ;
				
				public OnItemEditSupprListener(ArrayList<HashMap<String, Object>> listeElemProfils ){
					super();
					this.listeElemProfils=listeElemProfils;
				}
				
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int pos,
						long id) {
					showChoixDialog((String) this.listeElemProfils.get(pos).get("Id"), v.getContext());
				}
			}
			listeProfils.setOnItemClickListener(new OnItemEditSupprListener(listeElemProfils));
		} catch (OutOfMemoryError oom) {
			showTestDialog(oom.getMessage());
		}
	}

	private void showTestDialog(String s) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Test");
		alertDialogBuilder.setMessage(s);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	private void showChoixDialog(final String id, final Context c) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Que voulez-vous faire?");		
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setButton("Editer", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int which) {
			  Intent i = new Intent(c,EditerProfil.class);
			  i.putExtra("Id", id);
			  startActivity(i);
		    } }); 
		alertDialog.setButton2("Supprimer", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int which) {
				  
			    } }); 
		alertDialog.setButton3("Annuler", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int which) {
				  
			    } }); 
		alertDialog.show();
	}
	
}
