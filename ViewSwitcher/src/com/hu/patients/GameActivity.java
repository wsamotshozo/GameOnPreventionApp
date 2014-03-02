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
	TextView displayPoints;
	String gotBread;
	Button inst_Diagnose, MedLab, Manual, approve, decline;
	ViewSwitcher switcher;
	Button queue;
	ArrayList<Patient> line = new ArrayList<Patient>();
	ArrayList<String> patientNames = new ArrayList<String>();
	int currentPatient;
	ListView patientList;
	ArrayAdapter<String> padapter;
	
	/*
	 * 
	 * 
	 */
	int level = 1;
	int credPoints = 0;
	int numCorrect = 0;
	int numWrong = 0;
	int stage = 5; ///THe number used to deteremine whethere or not to go to the next stage
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game); 
		

		// Previous = (Button) findViewById(R.id.Previous);
		 // initialise view objects
		switcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher);
		displayData = (TextView) findViewById(R.id.patientDataFullView);
		displayData.setMovementMethod(new ScrollingMovementMethod());// Setting the text to be scrollable

		//queue = (Button) findViewById(R.id.queue);
		//queue.setOnClickListener(this);

		approve = (Button) findViewById(R.id.approve);
		decline = (Button) findViewById(R.id.decline);
		approve.setOnClickListener(this);
		decline.setOnClickListener(this);
		
		displayPoints = (TextView)findViewById(R.id.points);
		displayPoints.setText(String.valueOf(credPoints));
				
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
		//switcher.showPrevious();
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
				else
				{
					Toast.makeText(this, "Good job you got it correct", Toast.LENGTH_SHORT).show();
					numCorrect++;
					credPoints++;
					displayPoints.setText(String.valueOf(credPoints));
				}
				removePatient(currentPatient);
				
			}
			else if (arg0 == decline)
			{
				if(!line.get(currentPatient).fake)
				{
					Toast.makeText(this, "Everything on the card was true", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "Good job you got it correct", Toast.LENGTH_SHORT).show();
					numCorrect++;
					credPoints++;
					displayPoints.setText(String.valueOf(credPoints));
				}
				removePatient(currentPatient);
			}
			if(numCorrect % stage == 0 && level < 3  && credPoints >0)
			{
				level ++;
				Toast.makeText(this, "Get Ready for level"+level+" Presciptions", Toast.LENGTH_LONG).show();
			}
			switcher.showPrevious();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		displayData.setText(line.get(arg2).toString(level));
		currentPatient = arg2;
		switcher.showNext();

	}
	public void removePatient(int patient){
		line.remove(patient);
		patientNames.remove(patient);
		padapter.notifyDataSetChanged();
		addPatient();
	}
	//TODO generate a totally new queue when level increases.
	public void addPatient() {
		Random gen = new Random();
		boolean sex = gen.nextBoolean();
		String name;
		/*int partners;
		String practice; // sexual practice
		String symptoms;
		String disease;*/
		if (sex) {
			String[] names = getResources().getStringArray(R.array.MaleNames);
			name = names[gen.nextInt(names.length)];
		} else {
			String[] names = getResources().getStringArray(R.array.FemaleNames);
			name = names[gen.nextInt(names.length)];
		}
		//implementing level switch
		if(level == 1){
			if (gen.nextBoolean()) {
				Disease temp = Constants.realDisease[gen.nextInt(Constants.realDisease.length)];
				line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name, gotBread)));
				Log.i("disease", temp.name);
			} else {
				Disease temp = Constants.fakeDisease[gen.nextInt(Constants.fakeDisease.length)];
				line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name,gotBread, " ")));
				Log.i("disease", temp.name);
			}
		}
		else // implementing level switch
		{
			if (gen.nextBoolean()) {
				Disease temp = Constants.realDisease[gen.nextInt(Constants.realDisease.length)];
				line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name,gotBread)));
			} else {
				Disease temp = Constants.level2FakeDiseas[gen.nextInt(Constants.fakeDisease.length)];
				line.add((new Patient(name, sex, gotBread, temp.symptom, temp.name,gotBread, " ")));
			}
		}
		patientNames.add(name);
	}
}
