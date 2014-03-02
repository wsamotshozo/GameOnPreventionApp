package com.hu.patients.utils;

import java.util.ArrayList;

import com.hu.patients.R;
import com.hu.patients.models.Patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PatientAdapter extends ArrayAdapter<Patient> {



	private final Context context;
	  private final ArrayList<Patient> values;
		public PatientAdapter(Context context, ArrayList<Patient> objects) {
			super(context, R.layout.imagetext, objects);
		    this.context = context;
		    this.values = objects;
		}
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.imagetext, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.name);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.face);
	    textView.setText(values.get(position).toString());
	    // Change the icon for Windows and iPhone
	    
	    if (values.get(position).sex) {
	    	imageView.setImageResource(R.drawable.alien_borg);
	    } else {
	    	imageView.setImageResource(R.drawable.alien_atlantah);
	    }

	    return rowView;
	  }
}
