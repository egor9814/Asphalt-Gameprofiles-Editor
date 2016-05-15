package ru.egor9814.app.a8gpe2.license;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ru.egor9814.app.a8gpe2.R;

public class LicenseViewActivity extends AppCompatActivity {

	private TextView licenseText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_license_view);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.license_view_browse);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
				browse();
			}
		});
		if(getIntent().getStringExtra("license_url") == null){
			fab.setVisibility(View.GONE);
		}

		licenseText = (TextView)findViewById(R.id.license_view_text);
		licenseText.setText("");

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		licenseText.setText(getIntent().getStringExtra("license_text"));
		setTitle(getIntent().getStringExtra("license_name"));
	}

	private void browse(){
		Uri uri = Uri.parse(getIntent().getStringExtra("license_url"));
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(uri);
		startActivity(Intent.createChooser(i, getResources().getString(R.string.open_with)));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			default:
				return super.onOptionsItemSelected(item);
			case android.support.v7.appcompat.R.id.home:
			case android.support.v7.appcompat.R.id.homeAsUp:
			case android.R.id.home:
				onBackPressed();
				return true;
		}
	}
}
