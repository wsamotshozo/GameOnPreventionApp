package com.hu.patients;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;
//Also note we can also add side swipe to change views
public class GameActivity extends Activity implements OnClickListener{
	TextView displayData;
	Button inst_Diagnose, MedLab, Manual, treat;
	String gotBread;
	ViewSwitcher switcher;
	Button queue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		switcher = (ViewSwitcher)findViewById(R.id.ViewSwitcher);
		
		treat = (Button) findViewById(R.id.button1);
		treat.setOnClickListener(this);
		queue = (Button) findViewById(R.id.queue);
		queue.setOnClickListener(this);
       // Previous = (Button) findViewById(R.id.Previous);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == queue)
		{
			switcher.showNext();
		}
		else
		{
			switcher.showPrevious();
		}
		
	}

}
