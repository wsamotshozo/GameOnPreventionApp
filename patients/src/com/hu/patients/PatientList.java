package com.hu.patients;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PatientList extends Activity implements OnClickListener {
	Button treat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list);
        Button treat = (Button)findViewById(R.id.button1);
        treat.setOnClickListener(this);
        final ListView listview = (ListView) findViewById(R.id.names);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.patient));
        listview.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_list, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		/*if(arg0 == treat)
		{*/
			//startActivity(new Intent(this,Assess.class));
			startActivity(new Intent(this,Assess.class));
		//}
	}
    
}
