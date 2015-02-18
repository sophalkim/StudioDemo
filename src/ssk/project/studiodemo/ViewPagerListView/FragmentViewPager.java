package ssk.project.studiodemo.ViewPagerListView;

import ssk.project.studiodemo.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentViewPager extends Fragment {
	
	ViewPager viewPager;
	private static int NUMBER_OF_VIEWS = 3;
	private CustomPagerAdapter adapter;
	
	public static FragmentViewPager newInstance() {
		FragmentViewPager fragment = new FragmentViewPager();
		return fragment;
	}
	
	public FragmentViewPager() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.view_pager_layout, container, false);
		adapter = new CustomPagerAdapter(this.getActivity());
		viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
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
			collection.addView(imageView, 0);
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
		
		@Override
		public void finishUpdate(ViewGroup container) {}
		
		@Override
		public void restoreState(Parcelable parcelable, ClassLoader classLoader) {}
		
		@Override
		public Parcelable saveState() {
			return null;
		}
		
		@Override
		public void startUpdate(ViewGroup container) {}
	}
	
}
