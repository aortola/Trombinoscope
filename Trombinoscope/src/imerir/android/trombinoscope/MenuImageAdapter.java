package imerir.android.trombinoscope;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class MenuImageAdapter extends BaseAdapter {
    private Context mContext;
 
    public Integer[] mThumbIds = {
    		R.drawable.bouton4,R.drawable.bouton5,
    		R.drawable.bouton2, R.drawable.bouton3
    };
 
    public MenuImageAdapter(Context c){
        mContext = c;
    }
 
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        return imageView;
    }
 
}