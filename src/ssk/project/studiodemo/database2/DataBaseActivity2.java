package ssk.project.studiodemo.database2;

import java.util.ArrayList;

import ssk.project.studiodemo.R;
import ssk.project.studiodemo.database.DisplayContact;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataBaseActivity2 extends Fragment {

	Context context;
	View rootView;
	ListView lv;
	DataBaseHelper database;
	ArrayList<String> contactsList;
	ArrayAdapter<String> arrayAdapter;
	
	public static DataBaseActivity2 newInstance(Context context) {
		DataBaseActivity2 fragment = new DataBaseActivity2(context);
		return fragment;
	}

	public DataBaseActivity2(Context context) {
		this.context = context;
		Log.i("Database", "Database activity started");
		getDataFromDatabase();
		Log.i("Database", "Get DataFromDatabase");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_contacts, container,
				false);
		lv = (ListView) rootView.findViewById(R.id.list_view_1);
		Log.i("Database", "ListView initialized");
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int value = position + 1;
				Bundle bundle = new Bundle();
				bundle.putInt("id", value);
				Intent intent = new Intent(context, DisplayContact.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			
		});
		return rootView;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {    
        super.onActivityCreated(savedInstanceState);
        getDataFromDatabase();
    }
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menu_listview_all_contacts, menu);
//		return true;
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		super.onOptionsItemSelected(item);
//		switch (item.getItemId()) {
//		case R.id.item1:
//			Bundle dataBundle = new Bundle();
//			dataBundle.putInt("id", 0);
//			Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
//			intent.putExtras(dataBundle);
//			startActivity(intent);
//			return true;
//		default: 
//			return super.onOptionsItemSelected(item);
//		}
//	}
	
	/*
	 * Setup the ListView for this activity.
	 */
	public void setListView() {
		
	}
	
	/**
	 * Gets all contact from the database and binds to adapter
	 */
	public void getDataFromDatabase() {
		database = new DataBaseHelper(context);
		contactsList = database.getAllContacts();
		arrayAdapter = new ArrayAdapter<String>(
				context, android.R.layout.simple_list_item_1, contactsList);
		Log.i("Database", "Array Adapter successfully populated");
	}
		
}