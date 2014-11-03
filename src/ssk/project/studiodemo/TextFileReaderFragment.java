package ssk.project.studiodemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TextFileReaderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static TextFileReaderFragment newInstance() {
		TextFileReaderFragment fragment = new TextFileReaderFragment();
		return fragment;
	}

	public TextFileReaderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.sound_layout, container,
				false);
		final TextView tv = (TextView) rootView.findViewById(R.id.textFileReaderTextView);
		tv.setText("A local text file is selected.");
		Button button = (Button) rootView.findViewById(R.id.textFileReaderButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputStream is = getResources().openRawResource(R.raw.test);
		        InputStreamReader isr = new InputStreamReader(is);
		        BufferedReader bf = new BufferedReader(isr);
		        String line = null;
		        StringBuilder sb = new StringBuilder();
		        try {
					while ((line = bf.readLine()) !=null) {
						sb.append(line + "\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		        tv.setText(sb);
				
			}
		});
		return rootView;
	}
}
