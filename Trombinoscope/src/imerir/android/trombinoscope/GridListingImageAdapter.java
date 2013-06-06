package imerir.android.trombinoscope;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class GridListingImageAdapter extends BaseAdapter {
    private Context mContext;
 
    public String[] photos;
 
    public GridListingImageAdapter(Context c, String[] photos){
        mContext = c;
        this.photos=new String[photos.length];
        this.photos=photos;
    }
 
    public int getCount() {
        return photos.length;
    }
 
    @Override
    public Object getItem(int position) {
        return photos[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		Bitmap bm = BitmapFactory.decodeFile(photos[position], options);
    	Matrix mtx = new Matrix();
    	mtx.postRotate(90);
    	bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mtx, true);
		imageView.setImageBitmap(bm);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
        return imageView;
    }
 
}