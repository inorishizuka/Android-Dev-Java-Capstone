package com.example.itcapstone.burglarkitten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		TextView textResult = (TextView) findViewById(R.id.textResult);

		Bundle b = getIntent().getExtras();

		int score = b.getInt("score");
		if(score < 20) {
			textResult.setText("Your score is" + " " + score + " out of 20. Thanks for playing.");
		}
		else{
			textResult.setText("Congratulations.  You got a perfect score.\nYou know so much about cats you should probably be one.");
		}


	}


	public void playagain(View o) {

			Intent intent = new Intent(this, QuestionActivity.class);

			startActivity(intent);
		    finish();


	}
}