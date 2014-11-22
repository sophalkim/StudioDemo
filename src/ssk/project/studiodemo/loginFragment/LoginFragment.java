package ssk.project.studiodemo.loginFragment;

import ssk.project.studiodemo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

	public int screenW;
	public int screenH;	
	Button buttonLogin;
	EditText editTextUserName;
	EditText editTextPassword;
	
	public LoginFragment() {
	}
	
	public static LoginFragment newInstance() {
		LoginFragment loginFragment = new LoginFragment();	
		return loginFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		getDisplayInformation();
		View rootView = inflater.inflate(R.layout.layout_fragment_login, container, false);
		editTextUserName = (EditText) rootView.findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
		buttonLogin = (Button) rootView.findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = editTextUserName.getText().toString();
//				String password = editTextPassword.getText().toString();
				buttonLogin.setText("Cool");
//				buttonLogin.setLayoutParams(new RelativeLayout.LayoutParams(screenW / 2, screenH / 2));
				Toast.makeText(getActivity(), "Welcome " + username, Toast.LENGTH_LONG).show();
			}
		});
		return rootView;
	}
	
	/**
	 * Retrieves the device's display information and assigns it to screenW and screenH
	 */
	public void getDisplayInformation() {
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenW = metrics.widthPixels;
		screenH = metrics.heightPixels;
		Log.i("LoginFragment", "The screen width is: " + screenW);
		Log.i("LoginFragment", "The screen height is: " + screenH);
	}
	
}
