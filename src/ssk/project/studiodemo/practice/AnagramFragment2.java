package ssk.project.studiodemo.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ssk.project.studiodemo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AnagramFragment2 extends Fragment {

	public static final String TAG = "AnagramFragment2";
	TextView tv;
	String anagramString = "";
	String anagramList = "";
	
	public static AnagramFragment2 newFragment() {
		AnagramFragment2 af = new AnagramFragment2();
		return af;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
		View rootView = inflater.inflate(R.layout.layout_anagram_fragment, container);
		Button button = (Button) rootView.findViewById(R.id.textFileReaderButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputStream is = getResources().openRawResource(R.raw.anagramlist);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bf = new BufferedReader(isr);
				String line = null;
				StringBuilder sb = new StringBuilder();
				try {
					while ((line = bf.readLine()) != null) {
						sb.append(line + "\n");
						anagramString = sb.toString();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				displayAnagramPairs();
				tv.setText(anagramList);
				Toast.makeText(getActivity(), "Anagram File Created", Toast.LENGTH_LONG).show();
				createFile(anagramList);
			}
		});
		tv = (TextView) rootView.findViewById(R.id.textFileReaderTextView);
		tv.setText(returnAnagramFile());
		return rootView;
	}
	
	
}
