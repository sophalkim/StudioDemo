package ssk.project.studiodemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SoundFragment extends Fragment {

	SoundPool soundPool;
	int one;
	Context context;
	
	public static SoundFragment newInstance(Context context) {
		SoundFragment fragment = new SoundFragment(context);
		return fragment;
	}

	public SoundFragment(Context context) {
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		one = soundPool.load(context, R.raw.alabama, 1);
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.layout_fragment_sound_player, container,
				false);
		TextView tv = (TextView) rootView.findViewById(R.id.soundTextView);
		tv.setText("Click the Button to play a sound");
		Button button = (Button) rootView.findViewById(R.id.soundButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				soundPool.play(one, 1, 1, 1, 0, 1);
			}
			
		});
		return rootView;
	}
}
