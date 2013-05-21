package imerir.android.trombinoscope;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AjoutProfil extends Activity {
	
	private ProfilDAO pDao;
	private Profil p;
	private ImageView img;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_profil_formulaire);
        p = new Profil();
        p.setImg(null);
        img = (ImageView) findViewById(R.id.imageView1);
        Button b= (Button) findViewById(R.id.button1);
        final EditText et1 = (EditText)findViewById(R.id.editText1);
        final EditText et2 = (EditText)findViewById(R.id.editText2);
        final EditText et3 = (EditText)findViewById(R.id.editText3);
        b.setOnClickListener(
        	new OnClickListener() {
        	    @Override
        	    public void onClick(View v) {
        	    	Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        	        startActivityForResult(intent, 0);
        	    }
        	}
        );
        
        pDao = new ProfilDAO(this);
        pDao.open();
        
        Button ajouter = (Button) findViewById(R.id.button2);
        ajouter.setOnClickListener(
            	new OnClickListener() {
					@Override
					public void onClick(View v) {			
						p.setNom(et1.getText().toString());
						p.setPrenom(et2.getText().toString());
						p.setGroupe(et3.getText().toString());
						pDao.ajouter(p);
					}
            	}
        );     	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Bitmap bit= (Bitmap) data.getExtras().get("data");
    	img.setImageBitmap(bit); 
    	p.setImg(bit);
    }
}
