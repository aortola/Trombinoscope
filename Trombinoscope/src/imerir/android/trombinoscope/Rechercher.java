package imerir.android.trombinoscope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Rechercher extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rechercher_profil_formulaire);
		Button rechercher = (Button) findViewById(R.id.button1);
		rechercher.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nom = (EditText) findViewById(R.id.editText1);
				EditText prenom = (EditText) findViewById(R.id.editText2);
				EditText groupe = (EditText) findViewById(R.id.editText3);
				CheckBox avecPhoto = (CheckBox) findViewById(R.id.checkBox1);
				Intent iRech = new Intent(v.getContext(), ListerProfils.class);
				iRech.putExtra("nom", nom.getText().toString());
				iRech.putExtra("prenom", prenom.getText().toString());
				iRech.putExtra("groupe", groupe.getText().toString());
				iRech.putExtra("photo", avecPhoto.isChecked());
				startActivity(iRech);
			}
		});
	}
}
