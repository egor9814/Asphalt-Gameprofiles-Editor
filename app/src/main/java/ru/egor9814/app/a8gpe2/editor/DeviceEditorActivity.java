package ru.egor9814.app.a8gpe2.editor;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.DeviceProfile;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class DeviceEditorActivity extends EditorActivity {

	private DeviceProfile getProfile(){
		return GPModelWrapper.getWrapper().getDeviceProfile(getIntent().getStringExtra("device_name"));
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
				singleChoice(name, GPModelWrapper.getWrapper().getCpuProfiles(), "CPU_" + getProfile().cpu(), R.string.editor_create_new_cpu, new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().cpu(GPModelWrapper.getWrapper().getCpuProfiles().get(value).substring(4));
					}
				});
				break;
			case 1:
				singleChoice(name, GPModelWrapper.getWrapper().getGpuProfiles(), "GPU_" + getProfile().gpu(), R.string.editor_create_new_gpu, new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().gpu(GPModelWrapper.getWrapper().getGpuProfiles().get(value).substring(4));
					}
				});
				break;
			case 2:
				singleChoice(name, GPModelWrapper.getWrapper().getResolutionProfiles(), "RES_" + getProfile().res(), R.string.editor_create_new_res, new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().res(GPModelWrapper.getWrapper().getResolutionProfiles().get(value).substring(4));
					}
				});
				break;
			case 3:
				singleChoice(name, GPModelWrapper.getWrapper().getMemoryProfiles(), "MEM_" + getProfile().mem(), R.string.editor_create_new_mem, new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().mem(GPModelWrapper.getWrapper().getMemoryProfiles().get(value).substring(4));
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().reduceDepthFighting(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().reduceDepthFighting(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"CPU", "GPU", "RES", "MEM", "reduceDepthFighting"
	};

	private void error(int id){
		Toast.makeText(this, id, Toast.LENGTH_LONG).show();
	}
	private void singleChoice(String title, List<String> list, String key, int errorId, EditorDialogs.OnResultListener listener){
		if(list.size() == 0){
			error(errorId);
		} else {
			int index = list.indexOf(key);
			if(index == -1) index = 0;
			EditorDialogs.showSingleChoiceDialog(this, title, list.toArray(new String[list.size()]), index, listener);
		}
	}

	@Override
	protected int getOptionResource() {
		return R.array.specifics_options;
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
						getProfile().cpu(getProfile().cpu());
						break;
					case 1:
						getProfile().gpu(getProfile().gpu());
						break;
					case 2:
						getProfile().res(getProfile().res());
						break;
					case 3:
						getProfile().mem(getProfile().mem());
						break;
					case 4:
						getProfile().reduceDepthFighting(getProfile().reduceDepthFighting());
						break;
				}
			}
		});
	}
}
