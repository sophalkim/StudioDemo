package ssk.project.studiodemo.practice;

import java.util.ArrayList;

import ssk.project.studiodemo.R;
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

public class DataBaseActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.example.AddressBook.MESSAGE";
	private ListView lv;
	DBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_contacts);
		
		db = new DBHelper(this);
		ArrayList list = db.getAllContacts();
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		lv = (ListView) findViewById(R.id.listview1);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int personId = position + 1;
				Bundle bundle = new Bundle();
				bundle.putInt("id", personId);
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
			Bundle bundle = new Bundle();
			bundle.putInt("id", 0);
			Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
			intent.putExtras(bundle);
			startActivity(intent);
			return true;
		default: 
			return super.onOptionsItemSelected(item);
		}
	}
}
