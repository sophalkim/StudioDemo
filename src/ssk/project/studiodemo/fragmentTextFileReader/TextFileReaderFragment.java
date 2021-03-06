package ssk.project.studiodemo.fragmentTextFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ssk.project.studiodemo.R;
import ssk.project.studiodemo.R.id;
import ssk.project.studiodemo.R.layout;
import ssk.project.studiodemo.R.raw;

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
	
	TextView tv;
	
	public static TextFileReaderFragment newInstance() {
		TextFileReaderFragment fragment = new TextFileReaderFragment();
		return fragment;
	}

	public TextFileReaderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.text_file_reader_layout, container,
				false);
		tv = (TextView) rootView.findViewById(R.id.textFileReaderTextView);
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
