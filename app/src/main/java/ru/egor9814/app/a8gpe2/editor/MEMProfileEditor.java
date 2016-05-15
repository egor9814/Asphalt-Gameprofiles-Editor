package ru.egor9814.app.a8gpe2.editor;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;

import java.util.Arrays;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.MemoryProfile;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class MEMProfileEditor extends EditorActivity {

	private MemoryProfile getProfile(){
		return GPModelWrapper.getWrapper().getMemoryProfile(getIntent().getStringExtra("mem_name"));
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
				Intent i = new Intent(this, MemoryEditorActivity.class);
				i.putExtra("mem_name", getIntent().getStringExtra("mem_name"));
				startActivity(i);
				break;
			case 1:
				EditorDialogs.showIntegerDialog(this, name, getMemStrings().enterValue(), getProfile().startTextureLOD(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().startTextureLOD(value);
					}
				});
				break;
			case 2:
				EditorDialogs.showIntegerDialog(this, name, getMemStrings().enterValue(), getProfile().textureBudgetMB(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().textureBudgetMB(value);
					}
				});
				break;
			case 3:
				EditorDialogs.showBooleanDialog(this, name, getProfile().freeMoreMemory(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().freeMoreMemory(value);
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().dropAICarLodWhenManyCarsOnScreen(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().dropAICarLodWhenManyCarsOnScreen(value);
					}
				});
				break;
			case 5:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useTextureStreaming(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useTextureStreaming(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"memory", "startTextureLOD", "textureBudgetMB", "freeMoreMemory",
			"dropAICarLodWhenManyCarsOnScreen", "useTextureStreaming"
	};

	@Override
	protected int getOptionResource() {
		return R.array.mem_options;
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
				switch(Arrays.asList(keys).indexOf(key)){
					case 0:
						getProfile().getMemory();
						break;
					case 1:
						getProfile().startTextureLOD(getProfile().startTextureLOD());
						break;
					case 2:
						getProfile().textureBudgetMB(getProfile().textureBudgetMB());
						break;
					case 3:
						getProfile().freeMoreMemory(getProfile().freeMoreMemory());
						break;
					case 4:
						getProfile().dropAICarLodWhenManyCarsOnScreen(getProfile().dropAICarLodWhenManyCarsOnScreen());
						break;
					case 5:
						getProfile().useTextureStreaming(getProfile().useTextureStreaming());
						break;
				}
			}
		});
	}


	public static class MemoryEditorActivity extends EditorActivity{
		private MemoryProfile getProfile(){
			return GPModelWrapper.getWrapper().getMemoryProfile(getIntent().getStringExtra("mem_name"));
		}
		private MemoryProfile.Memory getMemory(){
			return getProfile().getMemory();
		}

		private final String[] keys = {
				"max", "min"
		};

		@Override
		protected int getOptionResource() {
			return R.array.memory_options;
		}

		@Override
		protected String[] getEnabledOptions() {
			return obtainKeys(getMemory(), keys);
		}

		@Override
		protected void actionEdit(String name) {
			String key = findKey(getProfile(), name, keys);
			switch(Arrays.asList(keys).indexOf(key)){
				case 0:
					EditorDialogs.showIntegerDialog(this, name, getMemStrings().enterValue(), getMemory().getMax(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getMemory().setMax(value);
						}
					});
					break;
				case 1:
					EditorDialogs.showIntegerDialog(this, name, getMemStrings().enterValue(), getMemory().getMin(), new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getMemory().setMin(value);
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
					getMemory().remove(findKey(getProfile(), name, keys));
				}
				@Override
				public void onNewElement(String name) {
					String key = findKey(getProfile(), name, keys);
					switch(Arrays.asList(keys).indexOf(key)) {
						case 0:
							getMemory().setMax(getMemory().getMax());
							break;
						case 1:
							getMemory().setMin(getMemory().getMin());
							break;
					}
				}
			});
			setTitle(getResources().getStringArray(getOptionResource())[0]);
		}

		@Override
		protected void onDestroy() {
			getProfile().compile();
			super.onDestroy();
		}
	}
}
