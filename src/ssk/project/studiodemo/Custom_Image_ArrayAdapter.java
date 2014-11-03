package ssk.project.studiodemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_Image_ArrayAdapter extends ArrayAdapter<String> {
	private final Context mContext;
	private final String[] values;

	public Custom_Image_ArrayAdapter(Context context, String[] values) {
		super(context, R.layout.custom_row, values);
		mContext = context;
		this.values = values;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_row, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.image_view_1);
		textView.setText(values[position]);
		GetBitmapFromURL getImages = new GetBitmapFromURL();
		Bitmap bitmap = getImages.execute("http://sokim209.appspot.com/images/flower2.jpg");
		imageView.setImageBitmap(bitmap);
		return rowView;
	}
	
	public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


