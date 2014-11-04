package ssk.project.studiodemo.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "MyDBName.db";
	public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
	
	public DBHelper2(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* This SQL statement will create a new table named "contacts".  The ID will be
		the primary key.*/
		db.execSQL(
				"create table contacts " + 
				"(id integer primary key, name text, phone text, email text, street text, place text");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This SQL statement will drop the table named "contacts" if it exist
		db.execSQL("DROP TABLE IF EXISTS contacts");
		onCreate(db);
	}
	
	/**
	 * Opens and calls lifecycle methods for database, then inserts information into database.
	 * @param name Customer Name
	 * @param phone Customer Phone Number
	 * @param email Customer Email
	 * @param street Customer Street Address
	 * @param place The location of transaction
	 * @return True, if information was successfully inserted into database
	 */
	public boolean insertContact(String name, String phone, String email, String street, String place) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("name", name);
		contentValues.put("phone", phone);
	    contentValues.put("email", email);	
	    contentValues.put("street", street);
	    contentValues.put("place", place);
	    
	    db.insert("contacts", null, contentValues);
	    return true;
	}
}
