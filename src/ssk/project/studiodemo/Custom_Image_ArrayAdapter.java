package ssk.project.studiodemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import ssk.project.studiodemo.GetBitmapFromURL.onTaskComplete;
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

public class Custom_Image_ArrayAdapter extends ArrayAdapter<String> {
	private final Context mContext;
	private final String[] values;
	public static Bitmap bitmap = null;

	public Custom_Image_ArrayAdapter(Context context, String[] values) {
		super(context, R.layout.custom_row, values);
		mContext = context;
		this.values = values;
		GetBitmapFromURL bitmapTask = new GetBitmapFromURL();
		bitmapTask.execute("http://sokim209.appspot.com/images/flower2.jpg");
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_row, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.image_view_1);
		textView.setText(values[position]);
		GetBitmapFromURL bitmapTask = new GetBitmapFromURL();
		try {
			bitmap = bitmapTask.execute("http://sokim209.appspot.com/images/flower2.jpg").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageView.setImageBitmap(bitmap);
//		imageView.setImageResource(R.drawable.pencil);
		return rowView;
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

}


