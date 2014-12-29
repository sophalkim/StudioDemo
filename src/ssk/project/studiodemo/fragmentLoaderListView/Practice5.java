package ssk.project.studiodemo.fragmentLoaderListView;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;

public class Practice5 extends ListFragment 
	implements OnQueryTextListener, LoaderManager.LoaderCallbacks<Cursor> {

	SimpleCursorAdapter mAdapter;
	String mCurFilter;
	
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
		Contacts._ID,
		Contacts.DISPLAY_NAME,
		Contacts.CONTACT_STATUS,
		Contacts.CONTACT_PRESENCE,
		Contacts.PHOTO_ID,
		Contacts.LOOKUP_KEY
	};
	
	public Practice5() {}
	
	public static Practice5 newInstance() {
		Practice5 p = new Practice5();
		return p;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No phone number");
		setHasOptionsMenu(true);
		
		mAdapter = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2, null,
				new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS }, 
				new int[] { android.R.id.text1, android.R.id.text2 }, 0);
		setListAdapter(mAdapter);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem item = menu.add("Search");
		item.setIcon(android.R.drawable.ic_menu_search);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		SearchView sv = new SearchView(getActivity());
		sv.setOnQueryTextListener(this);
		item.setActionView(sv);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.i("Practice5", "Item clicked: " + id);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Uri baseUri;
		if (mCurFilter != null) {
			baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(mCurFilter));
		} else {
			baseUri = Contacts.CONTENT_URI;
		}
		
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ))";
		return new CursorLoader(getActivity(), baseUri, CONTACTS_SUMMARY_PROJECTION,
				select, null, Contacts.DISPLAY_NAME +  " Collate Localized src");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
		getLoaderManager().restartLoader(0, null, this);
		return true;
	}

	
}
