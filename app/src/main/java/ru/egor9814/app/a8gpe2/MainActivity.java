package ru.egor9814.app.a8gpe2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.widget.FrameLayout;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.egor9814.app.a8gpe2.editor.CPUProfileEditor;
import ru.egor9814.app.a8gpe2.editor.DeviceEditorActivity;
import ru.egor9814.app.a8gpe2.editor.EditorDialogs;
import ru.egor9814.app.a8gpe2.editor.EditorPanel;
import ru.egor9814.app.a8gpe2.editor.GPModelWrapper;
import ru.egor9814.app.a8gpe2.editor.GPUProfileEditor;
import ru.egor9814.app.a8gpe2.editor.MEMProfileEditor;
import ru.egor9814.app.a8gpe2.editor.OPTProfileEditor;
import ru.egor9814.app.a8gpe2.editor.RESProfileEditor;
import ru.egor9814.app.a8gpe2.json.JSONObject;
import ru.egor9814.app.a8gpe2.license.LicensesShowDialog;
import ru.egor9814.app.a8gpe2.profiles.DeviceProfile;
import ru.egor9814.app.a8gpe2.profiles.JSONAssetsLoader;
import ru.egor9814.app.a8gpe2.profiles.JSONLoader;
import ru.egor9814.app.a8gpe2.widget.ListViewWithActionButton;
import ru.egor9814.app.a8gpe2.widget.ProgressDialog;

/**
 * Created by egor9814 on 01.05.2016.
 */
public class MainActivity extends MainAbstractActivity {

	static {
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
	}

	private ArrayList<EditorPanel> panels;
	private EditorPanel currentPanel;
	private GPModelWrapper wrapper;

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setTitle("");


		panels = new ArrayList<>();

		EditorPanel panel = new EditorPanel(this);
		panel.setName("gpu");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, GPUProfileEditor.class);
				i.putExtra("title", name);
				i.putExtra("gpu_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeGpu(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasGpu(name))
					wrapper.putGpuProfile(name);
			}
		});
		panels.add(panel);

		panel = new EditorPanel(this);
		panel.setName("cpu");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, CPUProfileEditor.class);
				i.putExtra("title", name);
				i.putExtra("cpu_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeCpu(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasCpu(name))
					wrapper.putCpuProfile(name);
			}
		});
		panels.add(panel);

		panel = new EditorPanel(this);
		panel.setName("mem");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, MEMProfileEditor.class);
				i.putExtra("title", name);
				i.putExtra("mem_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeMem(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasMem(name))
					wrapper.putMemoryProfile(name);
			}
		});
		panels.add(panel);

		panel = new EditorPanel(this);
		panel.setName("res");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, RESProfileEditor.class);
				i.putExtra("title", name);
				i.putExtra("res_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeRes(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasRes(name))
					wrapper.putResolutionProfile(name);
			}
		});
		panels.add(panel);

		panel = new EditorPanel(this);
		panel.setName("opt");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, OPTProfileEditor.class);
				i.putExtra("title", name);
				i.putExtra("opt_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeGfx(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasGfx(name))
					wrapper.putGfxOptionProfile(name);
			}
		});
		panel.loadOptions(R.array.opt_options);
		panels.add(panel);

		panel = new EditorPanel(this);
		panel.setName("specifics");
		panel.setOnEditorClickListener(new EditorPanel.OnEditorClickListener() {
			@Override
			public void onEditClick(String name) {
				Intent i = new Intent(MainActivity.this, DeviceEditorActivity.class);
				i.putExtra("title", name);
				i.putExtra("device_name", name);
				startActivity(i);
			}
		});
		panel.setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				wrapper.removeDevice(name);
			}

			@Override
			public void onNewElement(String name) {
				if(!wrapper.hasDevice(name))
					wrapper.putDeviceProfile(name);
			}
		});
		panel.setOnLongPressListener(new EditorPanel.OnLongPressListener() {
			@Override
			public boolean onLongPress(final EditorPanel devices, int position, final String name) {
				EditorDialogs.showSelectorDialog(MainActivity.this, getResources().getString(R.string.editor_select), getResources().getStringArray(R.array.editor_device_options), new EditorActivity.IntegerResultAdapter() {
					private void update(){
						devices.clearAdapter();
						for(String device : wrapper.getDevicePofiles()) {
							devices.addToAdapter(device);
						}
					}
					@Override
					public void onResult(int value) {
						EditorDialogs.OnResultListener result = null;
						String title = "";
						switch(value){
							case 0:
								result = new EditorActivity.StringResultAdapter() {
									@Override
									public void onResult(String value) {
										wrapper.renameDevice(name, value);
										update();
									}
								};
								title = getResources().getString(R.string.editor_renaming);
								break;
							case 1:
								result = new EditorActivity.StringResultAdapter() {
									@Override
									public void onResult(String value) {
										if(devices.containsInAdapter(value)) toast(getResources().getString(R.string.editor_already_contains));
										else {
											DeviceProfile device = wrapper.getDeviceProfile(name);
											wrapper.putDeviceProfile(value, device);
											update();
										}
									}
								};
								title = getResources().getString(R.string.editor_cloning);
								break;
						}
						if(result != null) EditorDialogs.showNamedDeviceDialog(MainActivity.this, title, name, result);
					}
				});
				return true;
			}
		});
		panels.add(panel);
		for(EditorPanel p : panels){
			p.setActionButton(new ListViewWithActionButton.OnActionButtonStateChangeListener() {
				@Override
				public void showActionButton() {
					showAddButton();
				}
				@Override
				public void hideActionButton() {
					hideAddButton();
				}
			});
		}

		getDrawer().openDrawer(GravityCompat.START);
		hideAddButton();

		try {
			loadJSON();
		} catch(Exception e) {
			toast(e.getMessage());
		}
	}

	private void loadJSON() throws JSONException, IOException {
		boolean loadDefault = getIntent().getBooleanExtra("loadDefault", true);
		JSONObject json = loadDefault
				? (new JSONAssetsLoader(getAssets(), "def.json").read())
				: (new JSONLoader(getIntent().getStringExtra("file_path")).read());
		clearPanels();
		loadJSON(json);
	}
	private void loadJSON(JSONObject json) throws JSONException {
		wrapper = new GPModelWrapper(json);
		for(String gpu : wrapper.getGpuProfiles()){
			panels.get(0).addToAdapter(gpu);
		}
		for(String cpu : wrapper.getCpuProfiles()){
			panels.get(1).addToAdapter(cpu);
		}
		for(String mem : wrapper.getMemoryProfiles()){
			panels.get(2).addToAdapter(mem);
		}
		for(String res : wrapper.getResolutionProfiles()){
			panels.get(3).addToAdapter(res);
		}
		for(String opt : wrapper.getGfxOptionProfiles()){
			panels.get(4).pickOption(opt);
		}
		for(String device : wrapper.getDevicePofiles()){
			panels.get(5).addToAdapter(device);
		}
	}
	private void clearPanels(){
		for(int i = 0; i < panels.size(); i++){
			panels.get(i).clearAdapter();
		}
		panels.get(4).loadOptions(R.array.opt_options);
	}

	@Override
	protected void goGPU() {
		setTitle(R.string.profile_gpu);
		currentPanel = panels.get(0);
		updateView();
		showAddButton();
	}

	@Override
	protected void goCPU() {
		setTitle(R.string.profile_cpu);
		currentPanel = panels.get(1);
		updateView();
		showAddButton();
	}

	@Override
	protected void goMEM() {
		setTitle(R.string.profile_mem);
		currentPanel = panels.get(2);
		updateView();
		showAddButton();
	}

	@Override
	protected void goRES() {
		setTitle(R.string.profile_res);
		currentPanel = panels.get(3);
		updateView();
		showAddButton();
	}

	@Override
	protected void goOPT() {
		setTitle(R.string.profile_opt);
		currentPanel = panels.get(4);
		updateView();
		showAddButton();
	}

	@Override
	protected void goSPECIFICS() {
		setTitle(R.string.profile_specifics);
		currentPanel = panels.get(5);
		updateView();
		showAddButton();
	}

	private void updateView(){
		FrameLayout frame = (FrameLayout)findViewById(R.id.content_view);
		frame.removeAllViews();
		frame.addView(currentPanel);
	}

	@Override
	protected void applyTemplate() {
		String[] templates = {
				"Default", "MTK 6589", "Tegra 2", "Tegra 3", "LG-D802"
		};
		EditorDialogs.showSingleChoiceDialog(this, getResources().getString(R.string.gp_select_template), templates, 0, new EditorActivity.IntegerResultAdapter() {
			@Override
			public void onResult(int value) {
				String name;
				switch(value) {
					default:
					case 0:
						name = "def";
						break;
					case 1:
						name = "mtk6589";
						break;
					case 2:
						name = "tegra2";
						break;
					case 3:
						name = "tegra3";
						break;
					case 4:
						name = "lg_d802";
						break;
				}
				try {
					clearPanels();
					loadJSON(new JSONAssetsLoader(getAssets(), name + ".json").read());
				} catch(Exception e) {
					e.printStackTrace();
					new AlertDialog.Builder(MainActivity.this).setTitle(R.string.gp_open_failed).setMessage(e.getMessage()).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create().show();
				}
			}
		});
	}

	@Override
	protected void applyCustomTemplate() {
		final File dir = new File("/sdcard/egor9814/gameprofiles/");
		if(!dir.exists()) dir.mkdirs();
		final List<String> files = new ArrayList<>();
		for(File file : dir.listFiles()){
			if(file.isFile() && file.getName().endsWith(".json")){
				String name = file.getName();
				files.add(name.substring(0, name.length() - 5));
			}
		}
		if(files.size() == 0){
			new AlertDialog.Builder(this)
					.setMessage(R.string.gp_custom_templates)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.create().show();
		} else {
			EditorDialogs.showSingleChoiceDialog(this, getResources().getString(R.string.gp_select_template), files.toArray(new String[files.size()]), 0, new EditorActivity.IntegerResultAdapter() {
				@Override
				public void onResult(int value) {
					clearPanels();
					try {
						loadJSON(new JSONLoader(new File(dir, files.get(value) + ".json")).read());
					} catch(Exception e) {
						e.printStackTrace();
						new AlertDialog.Builder(MainActivity.this)
								.setMessage(e.getMessage())
								.create().show();
					}
				}
			});
		}
	}

	@Override
	protected void actionAdd() {
		if(currentPanel == null){
			getDrawer().openDrawer(GravityCompat.START);
			toast(R.string.nav_select_panel);
		} else {
			if(currentPanel.getName().equals("opt")){
				ArrayList<String> options = currentPanel.getAvailableOptions();
				String[] array = options.toArray(new String[options.size()]);
				new AlertDialog.Builder(this)
						.setItems(array, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								currentPanel.pickOption(which);
							}
						})
						.setCancelable(true)
						.create().show();
			} else {
				EditorDialogs.showStringDialog(this, getResources().getString(R.string.editor_new_profile),
						getResources().getString(R.string.editor_enter_name),
						currentPanel.getName().equals("specifics") ? DeviceInfo.device() : "",
						new EditorDialogs.OnResultListener() {
							@Override
							public void onResult(String value) {
								String name = null;
								if(currentPanel.getName().equals("specifics")){
									name = value;
								} else {
									name = currentPanel.getName().toUpperCase() + "_" + value;
								}
								if(currentPanel.containsInAdapter(name)){
									//snack(getResources().getString(R.string.editor_already_contains)).show();
									toast(getResources().getString(R.string.editor_already_contains));
								} else {
									currentPanel.addToAdapter(name);
								}
							}
							@Override
							public void onResult(double value) {}
							@Override
							public void onResult(int value) {}
							@Override
							public void onResult(boolean value) {}
						});
			}
		}
	}


	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.action_exit)
				.setMessage(R.string.are_you_sure)
				.setCancelable(true)
				.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						super_onBackPressed();
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.create().show();
	}
	private void super_onBackPressed(){
		super.onBackPressed();
	}


	@Override
	protected void actionSave() {
		File out = new File(getIntent().getStringExtra("file_path"));
		try {
			out.createNewFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
		new AsyncTask<File, Integer, Boolean>(){
			private ProgressDialog progress;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progress = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.saving), getResources().getString(R.string.please_wait), true, false);
			}

			@Override
			protected Boolean doInBackground(File... params) {
				File f = params[0];
				try {
					wrapper.save(f);
					return true;
				} catch(Exception e) {
					e.printStackTrace();
				}
				return false;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				progress.dismiss();
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this)
						.setCancelable(true)
						.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
				if(result){
					adb.setMessage(R.string.success_save);
				} else {
					adb.setMessage(R.string.fail_on_save);
				}
				adb.create().show();
			}
		}.execute(out);
	}

	@Override
	protected void actionAbout() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.action_about)
				.setMessage(String.format(getResources().getString(R.string.about), DeviceInfo.version(), DeviceInfo.device()))
				.setNeutralButton(R.string.licenses, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new LicensesShowDialog().show(getSupportFragmentManager(), "licenses");
					}
				})
				.create().show();
	}
}
