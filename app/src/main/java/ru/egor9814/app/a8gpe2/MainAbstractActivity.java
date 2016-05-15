package ru.egor9814.app.a8gpe2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public abstract class MainAbstractActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout drawer;
	protected DrawerLayout getDrawer() {
		return drawer;
	}

	private FloatingActionButton addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		addButton = (FloatingActionButton) findViewById(R.id.action_add);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
				actionAdd();
			}
		});

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		if(drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings:
				break;
			case R.id.action_exit:
				onBackPressed();
				break;
			case R.id.action_save:
				actionSave();
				break;
			case R.id.action_about:
				actionAbout();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.nav_action_go_cpu:
				goCPU();
				break;
			case R.id.nav_action_go_gpu:
				goGPU();
				break;
			case R.id.nav_action_go_mem:
				goMEM();
				break;
			case R.id.nav_action_go_res:
				goRES();
				break;
			case R.id.nav_action_go_opt:
				goOPT();
				break;
			case R.id.nav_action_go_specifics:
				goSPECIFICS();
				break;
			case R.id.nav_action_apply_template:
				applyTemplate();
				break;
			case R.id.nav_action_apply_custom_template:
				applyCustomTemplate();
				break;
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	protected void hideAddButton(){
		//if(addButton.getVisibility() != View.GONE) addButton.setVisibility(View.GONE);
		if(addButton.isShown()) addButton.hide();
	}
	protected void showAddButton(){
		//if(addButton.getVisibility() != View.VISIBLE) addButton.setVisibility(View.VISIBLE);
		if(!addButton.isShown()) addButton.show();
	}

	protected Snackbar snack(String message){
		return Snackbar.make(addButton, message, Snackbar.LENGTH_LONG);
	}
	protected void toast(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	protected void toast(int message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}


	protected abstract void goCPU();
	protected abstract void goGPU();
	protected abstract void goMEM();
	protected abstract void goRES();
	protected abstract void goOPT();
	protected abstract void goSPECIFICS();
	protected abstract void applyTemplate();
	protected abstract void applyCustomTemplate();
	protected abstract void actionAdd();
	protected abstract void actionSave();
	protected abstract void actionAbout();

}
