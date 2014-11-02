package ssk.project.studiodemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SoundFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static int section = 0;
	
	private Integer[] images = {
			R.drawable.flower1,
			R.drawable.flower2,
			R.drawable.tiger,
			R.drawable.flower1
	};

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static SoundFragment newInstance() {
		SoundFragment fragment = new SoundFragment();
		return fragment;
	}

	public SoundFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.sound_layout, container,
				false);
		TextView tv = (TextView) rootView.findViewById(R.id.soundTextView);
		tv.setText("Click the Button to play a sound");
		return rootView;
	}
}
