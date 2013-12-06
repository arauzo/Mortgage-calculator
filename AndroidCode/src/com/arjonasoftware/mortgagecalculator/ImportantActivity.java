package com.arjonasoftware.mortgagecalculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImportantActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_important);

		Button button_sign = (Button) findViewById(R.id.button_sign);
		button_sign.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				
				String url = getResources().getString(R.string.petition);

				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
	}
}
