package com.hu.patients;

import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Assess extends Activity implements OnClickListener {

	TextView displayData;
	Button inst_Diagnose, MedLab, Manual;
	String gotBread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assess);
		init();
		/*Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("key");
		displayData.setText(gotBread);
		*/
	}

	private void init() {
		 displayData = (TextView) findViewById(R.id.patientDataFullView);
		 inst_Diagnose = (Button) findViewById(R.id.buttonInstantDiagnose);
		 MedLab = (Button) findViewById(R.id.buttonMedicalLab);
		 Manual = (Button) findViewById(R.id.buttonDoctorsManual);
		 inst_Diagnose.setOnClickListener(this);
		 Manual.setOnClickListener(this);
		 MedLab.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.buttonDoctorsManual :
			break;
		case R.id.buttonInstantDiagnose :
			break;
		case R.id.buttonMedicalLab :
			break;
		}
		
	}


	
}
