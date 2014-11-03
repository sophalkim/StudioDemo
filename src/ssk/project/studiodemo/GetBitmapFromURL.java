package ssk.project.studiodemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

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
	
	public interface onTaskComplete {
		public void getImage(Bitmap bitmap);
	}
}
