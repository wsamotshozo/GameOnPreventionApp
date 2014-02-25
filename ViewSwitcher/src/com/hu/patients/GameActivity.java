package com.hu.patients;

import java.util.ArrayList;
import java.util.Random;

import com.hu.patients.models.patient;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
//Also note we can also add side swipe to change views
public class GameActivity extends Activity implements OnClickListener, OnItemClickListener{
	TextView displayData;
	Button inst_Diagnose, MedLab, Manual, treat;
	String gotBread;
	ViewSwitcher switcher;
	Button queue;
	ArrayList<patient> line = new ArrayList<patient>();
	ArrayList<String>patientNames = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		switcher = (ViewSwitcher)findViewById(R.id.ViewSwitcher);
		displayData = (TextView)findViewById(R.id.patientDataFullView);
		
		treat = (Button) findViewById(R.id.button1);
		treat.setOnClickListener(this);
		queue = (Button) findViewById(R.id.queue);
		queue.setOnClickListener(this);
       // Previous = (Button) findViewById(R.id.Previous);
		
		//generate ten patients
		for(int i =0; i < 10; i ++)
		{
			addPatient();
		}
        final ListView listview = (ListView) findViewById(R.id.names);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, patientNames);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   /*Intent setIntent = new Intent(Intent.ACTION_MAIN);
	   setIntent.addCategory(Intent.CATEGORY_HOME);
	   setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   startActivity(setIntent);*/
	   //TODO make  abetter flow of control
	   switcher.showPrevious();
	}
	@Override
	public void onClick(View arg0) {
		if(arg0 == queue)
		{
			switcher.showNext();
		}
		else
		{
			switcher.showPrevious();
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		displayData.setText(line.get(arg2).toString());
		switcher.showNext();
		
	}
	public void addPatient()
	{
		Random gen = new Random();
		boolean sex = gen.nextBoolean();
		String name;
		int partners;
		String practice; //sexual practice
		String symptoms;
		String disease;
		if(sex)
		{
			String [] names = getResources().getStringArray(R.array.MaleNames);
			name = names[gen.nextInt(names.length)];
		}
		else
		{
			String [] names = getResources().getStringArray(R.array.FemaleNames);
			name = names[gen.nextInt(names.length)];
		}
		line.add(new patient(name, sex, gotBread, gotBread, gotBread));
		patientNames.add(name);
	}


}
