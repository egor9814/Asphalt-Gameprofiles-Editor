package ru.egor9814.app.a8gpe2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.egor9814.app.a8gpe2.editor.EditorDialogs;
import ru.egor9814.app.a8gpe2.editor.EditorPanel;
import ru.egor9814.app.a8gpe2.profiles.Profile;
import ru.egor9814.app.a8gpe2.widget.ListViewWithActionButton;

/**
 * Created by egor9814 on 02.05.2016.
 */
public abstract class EditorActivity extends BaseActivity {

	private FloatingActionButton addButton;
	private EditorPanel panel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		addButton = (FloatingActionButton)findViewById(R.id.action_add);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				actionAdd();
			}
		});
		panel = (EditorPanel)findViewById(R.id.editor_panel_view);
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				actionEdit(name);
			}
		});
		panel.setActionButton(new ListViewWithActionButton.OnActionButtonStateChangeListener() {
			@Override
			public void showActionButton() {
				if(!addButton.isShown()) addButton.show();
			}
			@Override
			public void hideActionButton() {
				if(addButton.isShown()) addButton.hide();
			}
		});

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setTitle(getIntent().getStringExtra("title"));
		try{
			panel.loadOptions(getOptionResource());
		} catch(UnsupportedOperationException e){}
		for(String key : getEnabledOptions()){
			panel.pickOption(key);
		}
	}

	protected EditorPanel getPanel() {
		return panel;
	}

	protected void actionAdd(){
		ArrayList<String> options = panel.getAvailableOptions();
		String[] array = options.toArray(new String[options.size()]);
		new AlertDialog.Builder(this)
				.setItems(array, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						panel.pickOption(which);
					}
				})
				.setCancelable(true)
				.create().show();
	}

	protected abstract void actionEdit(String name);
	protected abstract int getOptionResource();
	protected abstract String[] getEnabledOptions();

	protected String findKey(Profile profile, String selected, String[] keys){
		return profile.findKey(selected, keys, getResources().getStringArray(getOptionResource()));
	}
	protected String[] obtainKeys(Profile profile, String[] originalKeys){
		List<String> keys = profile.getEnabledKeys();
		String[] k = new String[keys.size()];
		if(keys.size() == 0) return k;
		List<String> showed = Arrays.asList(getResources().getStringArray(getOptionResource()));
		List<String> original = Arrays.asList(originalKeys);
		for(int i = 0; i < k.length; i++){
			int index = original.indexOf(keys.get(i));
			if(index == -1){
				k[i] = keys.get(i);
			} else {
				k[i] = showed.get(index);
			}
		}
		return k;
	}


	public static abstract class BooleanResultAdapter implements EditorDialogs.OnResultListener{
		@Override
		public void onResult(String value) {}
		@Override
		public void onResult(double value) {}
		@Override
		public void onResult(int value) {}
	}
	public static abstract class IntegerResultAdapter implements EditorDialogs.OnResultListener{
		@Override
		public void onResult(String value) {}
		@Override
		public void onResult(double value) {}
		@Override
		public void onResult(boolean value) {}
	}
	public static abstract class DoubleResultAdapter implements EditorDialogs.OnResultListener{
		@Override
		public void onResult(String value) {}
		@Override
		public void onResult(int value) {}
		@Override
		public void onResult(boolean value) {}
	}
	public static abstract class StringResultAdapter implements EditorDialogs.OnResultListener{
		@Override
		public void onResult(double value) {}
		@Override
		public void onResult(int value) {}
		@Override
		public void onResult(boolean value) {}
	}
}
