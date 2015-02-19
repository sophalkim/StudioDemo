package ssk.project.studiodemo.fragmentWebImages;

import ssk.project.studiodemo.R;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WebImagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
     
	SwipeRefreshLayout swipeLayout;
	ListView listView;
	ArrayAdapter<String> adapter;
	TextView textView;
	ImageView imageView;  
     
    public static WebImagesFragment newInstance(){
        WebImagesFragment instance = new WebImagesFragment();        
        return instance;
    }
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swipe_refresh_layout, container, false);
        setSwipeLayout(rootView);
        String[] x = {"cool", "awesome", "amazing"};
        listView = (ListView) rootView.findViewById(R.id.listview1);
        New_Custom_Image_ArrayAdapter adapter = new New_Custom_Image_ArrayAdapter(getActivity(), x);
        listView.setAdapter(adapter);
        return rootView;
    }
    
    public void setSwipeLayout(View rootView) {
    	swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, 
	            android.R.color.holo_green_light, 
	            android.R.color.holo_orange_light, 
	            android.R.color.holo_red_light);
		textView = new TextView(getActivity());
		imageView = new ImageView(getActivity());
    }
    
    @Override
	public void onRefresh() {
		textView.setText("It's refreshing");
		listView.addFooterView(textView);
		new Handler().postDelayed(new Runnable() {
	        @Override public void run() {
	        	textView.setText("Done refreshing");
	            swipeLayout.setRefreshing(false);
	            imageView.setImageResource(R.drawable.ic_launcher);
	            listView.addHeaderView(imageView);
	        }
	    }, 3000);
	}
  
}
