package ssk.project.studiodemo.database2;

import java.util.ArrayList;

import ssk.project.studiodemo.R;
import ssk.project.studiodemo.database.DisplayContact;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataBaseActivity2 extends Activity {

	ListView lv;
	DataBaseHelper database;
	ArrayList<String> contactsList;
	ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_contacts);
		
		database = new DataBaseHelper(this);
		contactsList = database.getAllContacts();
		arrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, contactsList);
		lv = (ListView) findViewById(R.id.list_view_1);
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int value = position + 1;
				Bundle bundle = new Bundle();
				bundle.putInt("id", value);
				Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_listview_all_contacts, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.item1:
			Bundle dataBundle = new Bundle();
			dataBundle.putInt("id", 0);
			Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
			intent.putExtras(dataBundle);
			startActivity(intent);
			return true;
		default: 
			return super.onOptionsItemSelected(item);
		}
	}
		
}