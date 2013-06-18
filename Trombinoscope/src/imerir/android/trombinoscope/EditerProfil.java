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

public class EditerProfil extends Activity {
	
	private ProfilDAO pDao;
	private Profil p;
	private ImageView img;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edition_profil);
        p = new Profil();
        pDao = new ProfilDAO(this);
        pDao.open();
        p = pDao.selectionnerUnProfil(Integer.parseInt(this.getIntent().getStringExtra("Id")));
        if(!p.getImg().equals("null")){
            img = (ImageView) findViewById(R.id.imageViewEdit1);
    		BitmapFactory.Options options = new BitmapFactory.Options();
    		options.inSampleSize = 8;
    		Bitmap bm = BitmapFactory.decodeFile(p.getImg(), options);
        	Matrix mtx = new Matrix();
        	mtx.postRotate(90);
        	bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mtx, true);
    		img.setImageBitmap(bm);
        }
        Button b= (Button) findViewById(R.id.buttonEdit1);
        final EditText et1 = (EditText)findViewById(R.id.editTextEdit1);
        et1.setText(p.getNom());
        final EditText et2 = (EditText)findViewById(R.id.editTextEdit2);
        et2.setText(p.getPrenom());
        final EditText et3 = (EditText)findViewById(R.id.editTextEdit3);
        et3.setText(p.getGroupe());
        b.setOnClickListener(
        	new OnClickListener() {
        	    @Override
        	    public void onClick(View v) {
        	    	Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        	        startActivityForResult(intent, 0);
        	    }
        	}
        );
         
        Button editer = (Button) findViewById(R.id.buttonEdit2);
        editer.setOnClickListener(
            	new OnClickListener() {
					@Override
					public void onClick(View v) {			
						p.setNom(et1.getText().toString());
						p.setPrenom(et2.getText().toString());
						p.setGroupe(et3.getText().toString());
						pDao.modifier(p);
						Toast.makeText(getBaseContext(),"Profil édité !",Toast.LENGTH_SHORT).show();
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
}

