package imerir.android.trombinoscope;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter.ViewBinder;

public class MyViewBinder implements ViewBinder {
	@Override
	public boolean setViewValue(View view, Object data,
			String textRepresentation) {
		if ((view instanceof ImageView) & (data instanceof String)) {
			ImageView iv = (ImageView) view;
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 8;
				Bitmap bm = BitmapFactory.decodeFile((String) data, options);
		    	Matrix mtx = new Matrix();
		    	mtx.postRotate(90);
		    	bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mtx, true);
				iv.setImageBitmap(bm);
			} catch (OutOfMemoryError oom) {
				throw oom;
			}
			return true;
		}
		return false;
	}
}
