package com.hu.patients;

import java.util.ArrayList;
import java.util.Random;

import com.hu.patients.models.Constants;
import com.hu.patients.models.Disease;
import com.hu.patients.models.Constants;
import com.hu.patients.models.Patient;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
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
import android.widget.Toast;
import android.widget.ViewSwitcher;

//Also note we can also add side swipe to change views
public class GameActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	TextView displayData;
	Button inst_Diagnose, MedLab, Manual, approve, decline;
	String gotBread;
	ViewSwitcher switcher;
	Button queue;
	ArrayList<Patient> line = new ArrayList<Patient>();
	ArrayList<String> patientNames = new ArrayList<String>();
	int currentPatient;
	ListView patientList;
	ArrayAdapter<String> padapter;
	Patient patientObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game); 
		patientObj = new Patient();

		switcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher);
		displayData = (TextView) findViewById(R.id.patientDataFullView);

		queue = (Button) findViewById(R.id.queue);
		queue.setOnClickListener(this);

		approve = (Button) findViewById(R.id.approve);
		decline = (Button) findViewById(R.id.decline);
		approve.setOnClickListener(this);
		decline.setOnClickListener(this);
		// Previous = (Button) findViewById(R.id.Previous);

		// generate ten patients
		for (int i = 0; i < 10; i++) {
			addPatient();
		}
		patientList = (ListView) findViewById(R.id.names);
		padapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, patientNames);
		patientList.setAdapter(padapter);
		patientList.setOnItemClickListener(this);

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
		/*
		 * Intent setIntent = new Intent(Intent.ACTION_MAIN);
		 * setIntent.addCategory(Intent.CATEGORY_HOME);
		 * setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * startActivity(setIntent);
		 */
		// TODO make abetter flow of control
		switcher.showPrevious();
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == queue) {
			switcher.showNext();
		} else{
			if(arg0 == approve)
			{
				if(line.get(currentPatient).fake)
				{
					
					Toast.makeText(this, "The disease does not match with the patient", Toast.LENGTH_SHORT).show();
				}
				removePatient(currentPatient);
				
			}
			else if (arg0 == decline)
			{
				if(!line.get(currentPatient).fake)
				{
					Toast.makeText(this, "Everything on the card was true", Toast.LENGTH_SHORT).show();
				}
				removePatient(currentPatient);
			}
			switcher.showPrevious();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		displayData.setText(line.get(arg2).toString());
		displayData.setMovementMethod(new ScrollingMovementMethod());
		currentPatient = arg2;
		switcher.showNext();

	}
	public void removePatient(int patient){
		line.remove(patient);
		patientNames.remove(patient);
		padapter.notifyDataSetChanged();
		addPatient();
	}
	public void addPatient() {
		Random gen = new Random();
		boolean sex = gen.nextBoolean();
		String name;
		int partners;
		String practice; // sexual practice
		String symptoms;
		String disease;
		if (sex) {
			String[] names = getResources().getStringArray(R.array.MaleNames);
			name = names[gen.nextInt(names.length)];
		} else {
			String[] names = getResources().getStringArray(R.array.FemaleNames);
			name = names[gen.nextInt(names.length)];
		}
		if (gen.nextBoolean()) {
			Disease temp = Constants.realDisease[gen.nextInt(Constants.realDisease.length)];
			//patientObj.setFake(true, 1);
			line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name)).setFake(false, 0));
		} else {
			Disease temp = Constants.fakeDisease[gen.nextInt(Constants.fakeDisease.length)];
			//patientObj.setFake(false, 2);
			line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name)).setFake(true, 0));
		}
		patientNames.add(name);
	}

}
