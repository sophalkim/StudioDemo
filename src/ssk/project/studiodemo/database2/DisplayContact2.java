package ssk.project.studiodemo.database2;

import ssk.project.studiodemo.R;
import ssk.project.studiodemo.database.DBHelper;
import ssk.project.studiodemo.database.DataBaseActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayContact2 extends Activity {

	int from_Where_I_am_Coming = 0;
	int id_To_Update = 0;
	
	DBHelper mydb;
	TextView name;
	TextView phone;
	TextView email;
	TextView street;
	TextView place;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_contact);
		name = (TextView) findViewById(R.id.editTextName);
		phone = (TextView) findViewById(R.id.editTextPhone);
		email = (TextView) findViewById(R.id.editTextEmail);
		street = (TextView) findViewById(R.id.editTextStreet);
		place = (TextView) findViewById(R.id.editTextCity);
		
		mydb = new DBHelper(this);
		context = getApplicationContext();
		
		getUser();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			if (Value > 0) {
				getMenuInflater().inflate(R.menu.display_contact, menu);
			} else {
				getMenuInflater().inflate(R.menu.main, menu);
			}
		}
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case R.id.Edit_Contact:
				editUser();
			    return true;
			case R.id.Delete_Contact:
				deleteUser();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void getUser() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int value = extras.getInt("id");
			if (value > 0) {
				Cursor rs = mydb.getData(value);
				id_To_Update = value;
				rs.moveToFirst();
				String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
				String phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
				String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
				String stree = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
				String plac = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY));
				if (!rs.isClosed()) {
					rs.close();
				}
				Button b = (Button) findViewById(R.id.button1);
				b.setVisibility(View.INVISIBLE);
				
				name.setText((CharSequence)nam);
	            name.setFocusable(false);
	            name.setClickable(false);

	            phone.setText((CharSequence)phon);
	            phone.setFocusable(false); 
	            phone.setClickable(false);

	            email.setText((CharSequence)emai);
	            email.setFocusable(false);
	            email.setClickable(false);

	            street.setText((CharSequence)stree);
	            street.setFocusable(false); 
	            street.setClickable(false);

	            place.setText((CharSequence)plac);
	            place.setFocusable(false);
	            place.setClickable(false);
			}
		}
	}
	
	/*
	 * Shows a dialog for deleting user.
	 */
	public void deleteUser() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.deleteContact)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mydb.deleteContact(id_To_Update);
				Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, DataBaseActivity.class);
				startActivity(intent);
			}
		})
		.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		AlertDialog d = builder.create();
		d.setTitle("Are you sure");
		d.show();
	}
	
	/*
	 * Enables editing of text fields and shows a button for saving changes
	 */
	public void editUser() {
		Button b = (Button) findViewById(R.id.button1);
		b.setVisibility(View.VISIBLE);
		name.setEnabled(true);
		name.setFocusableInTouchMode(true);
	    name.setClickable(true);
	
	    phone.setEnabled(true);
	    phone.setFocusableInTouchMode(true);
	    phone.setClickable(true);
	
	    email.setEnabled(true);
	    email.setFocusableInTouchMode(true);
	    email.setClickable(true);
	
	    street.setEnabled(true);
	    street.setFocusableInTouchMode(true);
	    street.setClickable(true);
	
	    place.setEnabled(true);
	    place.setFocusableInTouchMode(true);
	    place.setClickable(true);
	}
	
	public void run(View view) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			if (Value > 0) {
				if (mydb.updateContact(id_To_Update, name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString())) {
					Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(context, DataBaseActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(context, "not Updated", Toast.LENGTH_SHORT).show();
				}
			} else {
				if (mydb.insertContact(name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString())){
					Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "not done", Toast.LENGTH_SHORT).show();
				}
				Intent intent = new Intent(this, DataBaseActivity.class);
				startActivity(intent);
			}
		}
	}
}
