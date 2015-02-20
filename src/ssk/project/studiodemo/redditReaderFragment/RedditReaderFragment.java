package ssk.project.studiodemo.redditReaderFragment;

import java.util.ArrayList;
import java.util.List;

import ssk.project.studiodemo.R;
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
    Handler handler;
     
    String subreddit;
    List<Post> posts;
    PostsHolder postsHolder;
     
    RedditReaderFragment(){
        handler=new Handler();
        posts=new ArrayList<Post>();
    }    
     
    public static Fragment newInstance(String subreddit){
        RedditReaderFragment pf=new RedditReaderFragment();
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
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
     
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {    
        super.onActivityCreated(savedInstanceState);
        initialize();
    }
    
    
     
    private void initialize(){
        // This should run only once for the fragment as the
        // setRetainInstance(true) method has been called on
        // this fragment
         
        if(posts.size()==0){
             
            // Must execute network tasks outside the UI
            // thread. So create a new thread.
             
            new Thread(){
                public void run(){
                    posts.addAll(postsHolder.fetchPosts());
                     
                    // UI elements should be accessed only in
                    // the primary thread, so we must use the
                    // handler here.
                     
                    handler.post(new Runnable(){
                        public void run(){
                            createAdapter();
                        }
                    });
                }
            }.start();
        }else{
            createAdapter();
        }
    }
     
    /**
     * This method creates the adapter from the list of posts
     * , and assigns it to the list.
     */
    private void createAdapter(){
         
        // Make sure this fragment is still a part of the activity.
        if(getActivity()==null) return;
         
        adapter=new ArrayAdapter<Post>(getActivity()
                                             ,R.layout.post_item
                                             , posts){
            @Override
            public View getView(int position,
                                View convertView,
                                ViewGroup parent) {
 
                if(convertView==null){
                    convertView=getActivity()
                                .getLayoutInflater()
                                .inflate(R.layout.post_item, parent, false);
                }
 
                TextView postTitle;
                postTitle=(TextView)convertView
                          .findViewById(R.id.post_title);
 
//                TextView postDetails;
//                postDetails=(TextView)convertView
//                            .findViewById(R.id.post_details);
// 
//                TextView postScore;
//                postScore=(TextView)convertView
//                          .findViewById(R.id.post_score);
//                ImageView thumbs;
//                thumbs = (ImageView)convertView
//                		 .findViewById(R.id.image_view);
 
                postTitle.setText(posts.get(position).title);
//                postDetails.setText(posts.get(position).getDetails());
//                postScore.setText(posts.get(position).getScore());
//                thumbs.setImageBitmap(getBitmapFromURL(posts.get(position).thumbnail));
                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }
    
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
	public void onRefresh() {
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
}
