package ssk.project.studiodemo.gridview;

import ssk.project.studiodemo.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewFragment extends Fragment{

	public static GridViewFragment newInstance() {
		GridViewFragment gridViewFragment = new GridViewFragment();
		return gridViewFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);
		GridView gridview = (GridView) v.findViewById(R.id.gridview);
		gridview.setBackgroundColor(Color.BLACK);
		gridview.setAdapter(new GridViewImageAdapter(getActivity()));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
			}
			
		});
		return v;
	}
	
}
