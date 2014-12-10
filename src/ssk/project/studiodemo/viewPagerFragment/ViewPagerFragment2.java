package ssk.project.studiodemo.viewPagerFragment;

import ssk.project.studiodemo.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerFragment2 extends Fragment {
	
	ViewPager viewPager;
	private static int NUMBER_OF_VIEWS = 3;
	private CustomPagerAdapter adapter;
	
	public static ViewPagerFragment2 newInstance() {
		ViewPagerFragment2 fragment = new ViewPagerFragment2();
		return fragment;
	}
	
	public ViewPagerFragment2() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.view_pager_layout, container, false);
		adapter = new CustomPagerAdapter(getActivity());
		viewPager = (ViewPager) rootView.findViewById(R.id.awesomepager);
		viewPager.setAdapter(adapter);
		return rootView;
	}
	
	private class CustomPagerAdapter extends PagerAdapter {

		private Integer[] images = {
				R.drawable.hamburger,
				R.drawable.home_button,
				R.drawable.pencil
		};
		
		Context context;
		
		public CustomPagerAdapter(Activity activity) {
			context = activity;
		}
		
		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(images[position]);
			return imageView;
		}
		
		@Override
		public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView( (ImageView) view);
		}
		
		@Override
		public int getCount() {
			return NUMBER_OF_VIEWS;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
	
}
