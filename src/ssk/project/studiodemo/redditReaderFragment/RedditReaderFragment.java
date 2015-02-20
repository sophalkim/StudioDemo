package ssk.project.studiodemo.redditReaderFragment;

import java.util.ArrayList;
import java.util.List;

import ssk.project.studiodemo.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RedditReaderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	SwipeRefreshLayout swipeLayout;
	ListView listView;
    ArrayAdapter<Post> adapter;
    static Handler handler;
     
    String subreddit;
    static List<Post> posts;
    PostsHolder postsHolder;

    public static Fragment newInstance(String subreddit){
        RedditReaderFragment pf=new RedditReaderFragment();
        posts=new ArrayList<Post>();
        handler=new Handler();
        pf.subreddit=subreddit;
        pf.postsHolder=new PostsHolder(pf.subreddit);        
        return pf;
    }
     
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swipe_refresh_layout, container, false);
        setSwipeLayout(rootView);
        listView = (ListView) rootView.findViewById(R.id.listview1);
        new getJSONTask().execute();
        return rootView;
    }
    
    public void setSwipeLayout(View rootView) {
    	swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, 
	            android.R.color.holo_green_light, 
	            android.R.color.holo_orange_light, 
	            android.R.color.holo_red_light);
    }	
     
    /**
     * This method creates the adapter from the list of posts
     * , and assigns it to the list.
     */
    private void createAdapter(){
        // Make sure this fragment is still a part of the activity.
        if(getActivity()==null) return;
        adapter=new ArrayAdapter<Post>(getActivity() ,R.layout.post_item, posts){
            @Override
            public View getView(int position,View convertView, ViewGroup parent) {
            	if(convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.post_item, parent, false);
                }
                TextView postTitle;
                postTitle=(TextView)convertView.findViewById(R.id.post_title);
                postTitle.setText(posts.get(position).title);
                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
	public void onRefresh() {
// 		posts.clear() does work, it creates and empty listview
    	posts.clear();
//    	Adding more information to posts works, let try clearing posts object
    	new Thread(){
            public void run(){
                posts.addAll(postsHolder.fetchPosts());
            }
    	}.start();
		new Handler().postDelayed(new Runnable() {
	        @Override public void run() {
	            swipeLayout.setRefreshing(false);
	            adapter.notifyDataSetChanged();
	        }
	    }, 3000);
	}
    
    private class getJSONTask extends AsyncTask<String, Void, List<Post>> {
    	
		@Override
		protected List<Post> doInBackground(String... params) {
			if(posts.size()==0){
				posts.addAll(postsHolder.fetchPosts());
			}
			return posts;
		}
		
		@Override
		protected void onPostExecute(List<Post> posts) {
			createAdapter();
		}
    	
    }
}
