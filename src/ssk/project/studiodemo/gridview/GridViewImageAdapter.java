package ssk.project.studiodemo.gridview;

import ssk.project.studiodemo.R;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewImageAdapter extends BaseAdapter {
	private Context mContext;
	
	// references to our images
	private Integer[] mThumbIds = {
		R.drawable.pencil, R.drawable.jacket,
		R.drawable.hamburger, R.drawable.flower1,
		R.drawable.flower2, R.drawable.tiger,
		R.drawable.pencil, R.drawable.jacket,
		R.drawable.hamburger, R.drawable.flower1,
		R.drawable.flower2, R.drawable.tiger,
		R.drawable.pencil, R.drawable.jacket,
		R.drawable.hamburger, R.drawable.flower1,
		R.drawable.flower2, R.drawable.tiger,
		R.drawable.pencil, R.drawable.jacket,
		R.drawable.hamburger, R.drawable.flower1,
		R.drawable.flower2, R.drawable.tiger,
	};
	
	public GridViewImageAdapter(Context c) {
		mContext = c;
	}
	
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
			int imageWidth = dm.widthPixels / 3;
			int imageHeight = dm.heightPixels / 4;
			imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setPadding(3, 3, 3, 3);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	
}
