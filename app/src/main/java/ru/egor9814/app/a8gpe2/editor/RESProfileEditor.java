package ru.egor9814.app.a8gpe2.editor;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;

import java.util.Arrays;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.ResolutionProfile;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class RESProfileEditor extends EditorActivity {

	private ResolutionProfile getProfile(){
		return GPModelWrapper.getWrapper().getResolutionProfile(getIntent().getStringExtra("res_name"));
	}

	@Override
	protected void actionEdit(String name) {
		String key = findKey(getProfile(), name, keys);
		if(key == null){
			try {
				EditorDialogs.showUnknownItemDialog(this, getProfile(), name);
			} catch(JSONException e) {
				e.printStackTrace();
			}
		} else switch(Arrays.asList(keys).indexOf(key)){
			case 0:
				Intent i = new Intent(this, ResolutionEditorActivity.class);
				i.putExtra("res_name", getIntent().getStringExtra("res_name"));
				startActivity(i);
				break;
			case 1:
				EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getProfile().scaleDisplay(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().scaleDisplay(value);
					}
				});
				break;
			case 2:
				EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getProfile().scaleAABuffer(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().scaleAABuffer(value);
					}
				});
				break;
			case 3:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAA(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAA(value);
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAAInGameplay(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAAInGameplay(value);
					}
				});
				break;
			case 5:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAAInMenu(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAAInMenu(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"resolutions", "scaleDisplay", "scaleAABuffer", "useAA",
			"useAAInGameplay", "useAAInMenu"
	};

	@Override
	protected int getOptionResource() {
		return R.array.res_options;
	}

	@Override
	protected String[] getEnabledOptions() {
		return obtainKeys(getProfile(), keys);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		getPanel().setOnUpdateListener(new EditorPanel.OnUpdateListener() {
			@Override
			public void onPostRemove(int position, String name, int newSize) {
				getProfile().remove(findKey(getProfile(), name, keys));
			}
			@Override
			public void onNewElement(String name) {
				String key = findKey(getProfile(), name, keys);
				switch(Arrays.asList(keys).indexOf(key)) {
					case 0:
						getProfile().getResolutions();
						break;
					case 1:
						getProfile().scaleDisplay(getProfile().scaleDisplay());
						break;
					case 2:
						getProfile().scaleAABuffer(getProfile().scaleAABuffer());
						break;
					case 3:
						getProfile().useAA(getProfile().useAA());
						break;
					case 4:
						getProfile().useAAInGameplay(getProfile().useAAInGameplay());
						break;
					case 5:
						getProfile().useAAInMenu(getProfile().useAAInMenu());
						break;
				}
			}
		});
	}

	public static class ResolutionEditorActivity extends EditorActivity{
		private ResolutionProfile getProfile(){
			return GPModelWrapper.getWrapper().getResolutionProfile(getIntent().getStringExtra("res_name"));
		}
		private ResolutionProfile.Resolutions getResolution(){
			return getProfile().getResolutions();
		}

		private final String[] keys = {
				"maxDII", "minDII", "maxW", "minW", "maxH", "minH"
		};

		@Override
		protected int getOptionResource() {
			return R.array.resolution_options;
		}

		@Override
		protected String[] getEnabledOptions() {
			return obtainKeys(getResolution(), keys);
		}

		@Override
		protected void actionEdit(String name) {
			String key = findKey(getProfile(), name, keys);
			switch(Arrays.asList(keys).indexOf(key)){
				case 0:
					EditorDialogs.showDoubleDialog(this, name, getGlobals().enterValue(), getResolution().getMaxDII(), new DoubleResultAdapter() {
						@Override
						public void onResult(double value) {
							getResolution().setMaxDII(value);
						}
					});
					break;
				case 1:
					EditorDialogs.showDoubleDialog(this, name, getGlobals().enterValue(), getResolution().getMinDII(), new DoubleResultAdapter() {
						@Override
						public void onResult(double value) {
							getResolution().setMinDII(value);
						}
					});
					break;
				case 2:
					EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getResolution().getMaxW(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getResolution().setMaxW(value);
						}
					});
					break;
				case 3:
					EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getResolution().getMinW(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getResolution().setMinW(value);
						}
					});
					break;
				case 4:
					EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getResolution().getMaxH(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getResolution().setMaxH(value);
						}
					});
					break;
				case 5:
					EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getResolution().getMinH(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getResolution().setMinH(value);
						}
					});
					break;
			}
		}

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			getPanel().setOnUpdateListener(new EditorPanel.OnUpdateListener() {
				@Override
				public void onPostRemove(int position, String name, int newSize) {
					getResolution().remove(findKey(getProfile(), name, keys));
				}
				@Override
				public void onNewElement(String name) {
					String key = findKey(getProfile(), name, keys);
					switch(Arrays.asList(keys).indexOf(key)) {
						case 0:
							getResolution().setMaxDII(getResolution().getMaxDII());
							break;
						case 1:
							getResolution().setMinDII(getResolution().getMinDII());
							break;
						case 2:
							getResolution().setMaxW(getResolution().getMaxW());
							break;
						case 3:
							getResolution().setMinW(getResolution().getMinW());
							break;
						case 4:
							getResolution().setMaxH(getResolution().getMaxH());
							break;
						case 5:
							getResolution().setMinH(getResolution().getMinH());
							break;
					}
				}
			});
			setTitle(getResources().getStringArray(R.array.res_options)[0]);
		}

		@Override
		protected void onDestroy() {
			getProfile().compile();
			super.onDestroy();
		}
	}
}
