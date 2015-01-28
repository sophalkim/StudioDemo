package ssk.project.studiodemo.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import ssk.project.studiodemo.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
	
	static List<String> convertStringtoArrayList(String str) {
		String x = str.replaceAll("\\.", "");
		List<String> items = Arrays.asList(x.split("\\s*,\\s*"));
		return items;
	}
	
	public void displayAnagramPairs() {
		List<String> list = convertStringtoArrayList(anagramString);
		for (int i = 0; i < list.size(); i++) {
			String word1 = list.get(i).toString();
			for (int j = i + 1; j < list.size(); j++) {
				String word2 = list.get(j).toString();
				if (isAnagram(word1, word2)) {
					anagramList += word1 + ", " + word2 + "\n";
				}
			}
		}
	}
	
	private static boolean isAnagram(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		str1 = sortCharacters(str1.toLowerCase());
		str2 = sortCharacters(str2.toLowerCase());
		if (str1.equals(str2))
			return true;
		return false;
	}
	
	private static String sortCharacters(String str) {
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		return String.valueOf(charArray);
	}
	
	public String returnAnagramFile() {
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
		return anagramString;
	}
	
	public void createFile(String anagramList) {
		String filename = "anagramSorted";
		File file = new File(getActivity().getFilesDir(), filename);
		Log.i("File", "" + getActivity().getFilesDir());
		FileOutputStream outputStream;
		
		try {
			outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.write(anagramList.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
