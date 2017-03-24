package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.R;

import java.util.ArrayList;




public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
         
       TextView tripno = (TextView) convertView.findViewById(R.id.ztripno);
        TextView timedate = (TextView) convertView.findViewById(R.id.ztimetrip);
		TextView source = (TextView) convertView.findViewById(R.id.zsource);
		TextView  destination  = (TextView) convertView.findViewById(R.id.zdestination);
       // TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
         
        tripno.setText(navDrawerItems.get(position).get_Tripno());
        timedate.setText(navDrawerItems.get(position).get_tIME());
		source.setText(navDrawerItems.get(position).get_source());
		destination.setText(navDrawerItems.get(position).get_destination());
        
        // displaying count
        // check whether it set visible or not
      
        return convertView;
	}

}
