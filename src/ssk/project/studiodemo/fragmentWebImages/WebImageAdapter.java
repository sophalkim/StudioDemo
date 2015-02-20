package ssk.project.studiodemo.fragmentWebImages;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import ssk.project.studiodemo.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WebImageAdapter extends ArrayAdapter<String> {
	private final Context mContext;
	private final String[] values;
	public static Bitmap bitmap1 = null;
	private ViewHolder viewHolder;

	public WebImageAdapter(Context context, String[] values) {
		super(context, R.layout.custom_row, values);
		mContext = context;
		this.values = values;
		getImages();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_row, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView.findViewById(R.id.label);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view_1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//	    View rowView = inflater.inflate(R.layout.custom_row, parent, false);
		viewHolder.textView.setText(values[position]);
		if (position == 0) {
			viewHolder.imageView.setImageBitmap(bitmap1);
		} else {
			if (position == 1) {
				viewHolder.imageView.setImageResource(R.drawable.jacket);
			} else {
				viewHolder.imageView.setImageResource(R.drawable.cat_food);
			}
			
		}
		return convertView;
	}
	
	public void getImages() {
		GetBitmapFromURL bitmapTask = new GetBitmapFromURL();
		try {
			bitmap1 = bitmapTask.execute("http://sokim209.appspot.com/images/flower2.jpg").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	public class GetBitmapFromURL extends AsyncTask<String, Void, Bitmap> {

		Bitmap myBitmap;
		
		@Override
		protected Bitmap doInBackground(String... params) {
			try {
	            URL url = new URL(params[0]);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            InputStream input = connection.getInputStream();
	            myBitmap = BitmapFactory.decodeStream(input);
	            return myBitmap;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
		}	
		
	}
	
	static class ViewHolder {
		TextView textView;
		ImageView imageView;
	}

}


