package imerir.android.trombinoscope;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AjoutProfil extends Activity {
	
	private ProfilDAO pDao;
	private Profil p;
	private ImageView img;
    private EditText et1;
    private EditText et2;
   	private EditText et3;
	private Button b;
	private Button rst;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_profil_formulaire);
        p = new Profil();
        p.setImg("null");
        img = (ImageView) findViewById(R.id.imageView1);
        b = (Button) findViewById(R.id.button1);
        rst = (Button) findViewById(R.id.reset); 
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        b.setOnClickListener(
        	new OnClickListener() {
        	    @Override
        	    public void onClick(View v) {
        	    	Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        	        startActivityForResult(intent, 0);
        	    }
        	}
        );
        
        rst.setOnClickListener(
            	new OnClickListener() {
            	    @Override
            	    public void onClick(View v) {
            	    	resetFields();
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
						Toast.makeText(getBaseContext(),"Profil enregistré !",Toast.LENGTH_SHORT).show();
						resetFields();
					}
            	}
        );     	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Bitmap bit;
    	Uri uri=(Uri) data.getData();
        BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
    	bit = BitmapFactory.decodeFile(getRealPathFromURI(uri),options);
    	Matrix mtx = new Matrix();
    	mtx.postRotate(90);
    	bit = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), mtx, true);
    	img.setImageBitmap(bit);
    	p.setImg(getRealPathFromURI(uri));
    }
    
	private void showTestDialog(String s){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Test");
		alertDialogBuilder.setMessage(s);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        //A remplacer, mais par quoi ????
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
	
	private void resetFields(){
		img.setImageResource(R.drawable.photo_profil_vide);
		et1.setText("");
		et2.setText("");
		et3.setText("");
		p.setImg("null");
	}


}
