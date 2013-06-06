package imerir.android.trombinoscope;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new MenuImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = null;
                if(position==0){
                    i= new Intent(getApplicationContext(),AjoutProfil.class);
                }else if(position==1){
                    i= new Intent(getApplicationContext(),ListerProfils.class);
                }else if(position==2){
                	i= new Intent(getApplicationContext(),Rechercher.class);
                }else if(position==3){
                	i= new Intent(getApplicationContext(),GridListerProfils.class);
                }
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	private void showTestDialog(String s) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Test");
		alertDialogBuilder.setMessage(s);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
