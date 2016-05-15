package ru.egor9814.app.a8gpe2;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.ExFilePickerParcelObject;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class StartActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final File gp = new File("/sdcard/Android/data/com.gameloft.android.ANMP.GloftA8HM/files/gameprofiles.txt");
		if(gp.exists()){
			new AlertDialog.Builder(this)
					.setTitle(R.string.gp_file_exists)
					.setMessage(R.string.gp_load)
					.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							main();
						}
					})
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent i = new Intent(StartActivity.this, MainActivity.class);
							i.putExtra("file_path", gp.getAbsolutePath());
							i.putExtra("loadDefault", false);
							startActivity(i);
							finish();
						}
					})
					.setCancelable(false)
					.create().show();
		} else {
			main();
		}
	}

	private void main(){
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gp_file_operations)));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch(position){
			case 0:
				open(true);
				break;
			case 1:
				open(false);
				break;
		}
	}

	private void open(boolean newFile){
		Intent i = new Intent(getApplicationContext(), ru.bartwell.exfilepicker.ExFilePickerActivity.class);
		i.putExtra(ExFilePicker.SET_CHOICE_TYPE, newFile ? ExFilePicker.CHOICE_TYPE_DIRECTORIES : ExFilePicker.CHOICE_TYPE_FILES);
		i.putExtra(ExFilePicker.SET_ONLY_ONE_ITEM, true);
		i.putExtra(ExFilePicker.DISABLE_NEW_FOLDER_BUTTON, !newFile);
		startActivityForResult(i, newFile ? 0xa : 0xb);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0xa || requestCode == 0xb){
			if(data != null){
				ExFilePickerParcelObject object = data.getParcelableExtra(ExFilePickerParcelObject.class.getCanonicalName());
				if(object.count == 1){
					String path = object.path;
					if(!path.endsWith("/")){
						path += "/";
					}
					path += object.names.get(0);
					Intent i = new Intent(StartActivity.this, MainActivity.class);
					i.putExtra("file_path", requestCode == 0xa ? path + "/gameprofiles.txt" : path);
					i.putExtra("loadDefault", requestCode == 0xa);
					startActivity(i);
					finish();
				} else openFailed();
			} else openFailed();
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void openFailed(){
		Toast.makeText(this, R.string.gp_open_failed, Toast.LENGTH_LONG).show();
	}
}
