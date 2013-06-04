package imerir.android.trombinoscope;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Rechercher extends Activity {

	private ProfilDAO pDao;
	private Profil p;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercher_profil_formulaire);
        
        Button rechercher = (Button)findViewById(R.id.button1);
        EditText nom = (EditText)findViewById(R.id.editText1);
        EditText prenom = (EditText)findViewById(R.id.editText2);
        EditText groupe = (EditText)findViewById(R.id.editText3);
        CheckBox avecPhoto = (CheckBox)findViewById(R.id.checkBox1);
        
        pDao = new ProfilDAO(this);
        pDao.open();
      rechercher.setOnClickListener(
        		new OnClickListener(){
        			@Override
					public void onClick(View v) {
        				
        		       
        				
        			}
        		} 		
        );

	}
}
