package ssk.project.studiodemo;

import ssk.project.studiodemo.AnagramFragment.AnagramFragment;
import ssk.project.studiodemo.database2.DataBaseActivity2;
import ssk.project.studiodemo.fragmentLoaderListView.FragmentLoaderListView;
import ssk.project.studiodemo.fragmentSoundPlayer.SoundFragment;
import ssk.project.studiodemo.fragmentTextFileReader.TextFileReaderFragment;
import ssk.project.studiodemo.fragmentWebImages.WebImagesFragment;
import ssk.project.studiodemo.gridview.GridViewFragment;
import ssk.project.studiodemo.loginFragment.LoginFragment;
import ssk.project.studiodemo.redditReaderFragment.RedditReaderFragment;
import ssk.project.studiodemo.viewPagerFragment.FragmentViewPager;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position) {
		case 1: 	fragmentManager.beginTransaction().replace(R.id.container,
					WebImagesFragment.newInstance()).commit();
					break;
		case 2: 	fragmentManager.beginTransaction().replace(R.id.container,
					RedditReaderFragment.newInstance("askreddit")).commit();
					break;
		case 3: 	fragmentManager.beginTransaction().replace(R.id.container,
					SoundFragment.newInstance(this)).commit();
					break;
		case 4: 	fragmentManager.beginTransaction().replace(R.id.container,
					TextFileReaderFragment.newInstance()).commit();
					break;	
		case 5: 	fragmentManager.beginTransaction().replace(R.id.container,
					FragmentViewPager.newInstance()).commit();
					break;
		case 6: 	fragmentManager.beginTransaction().replace(R.id.container,
					LoginFragment.newInstance()).commit();
					break;
		case 7: 	fragmentManager.beginTransaction().replace(R.id.container,
					AnagramFragment.newInstance()).commit();
					break;
		case 8:		fragmentManager.beginTransaction().replace(R.id.container,
					FragmentLoaderListView.newInstance()).commit();
					break;
		case 9:		fragmentManager.beginTransaction().replace(R.id.container,
					DataBaseActivity2.newInstance(this)).commit();
					break;
		case 10:	fragmentManager.beginTransaction().replace(R.id.container,
					GridViewFragment.newInstance()).commit();
					break;
		default: 	fragmentManager
					.beginTransaction()
					.replace(R.id.container,
					PlaceholderFragment.newInstance(position + 1)).commit();
					break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(getApplicationContext(), "Will add settings page later", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
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
				R.drawable.flower1,
				R.drawable.flower2,
				R.drawable.tiger,
				R.drawable.flower1,
				R.drawable.flower2,
				R.drawable.tiger,
		};

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			section = sectionNumber;
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView tv = (TextView) rootView.findViewById(R.id.section_label);
//			Random r = new Random();
//			tv.setText("Welcome to Section: " + r.nextInt(10));
			tv.setText("Welcome to Section: " + section);
			ImageView iv = (ImageView) rootView.findViewById(R.id.imageView1);
			iv.setImageResource(images[section - 1]);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
