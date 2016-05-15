package ru.egor9814.app.a8gpe2.editor;

import android.os.Bundle;

import org.json.JSONException;

import java.util.Arrays;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.GFXOptionProfile;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class OPTProfileEditor extends EditorActivity {

	private GFXOptionProfile getProfile(){
		return GPModelWrapper.getWrapper().getGfxOptionProfile(getIntent().getStringExtra("opt_name"));
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
				EditorDialogs.showBooleanDialog(this, name, getProfile().postFX(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().postFX(value);
					}
				});
				break;
			case 1:
				EditorDialogs.showBooleanDialog(this, name, getProfile().postFXLUTOnly(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().postFXLUTOnly(value);
					}
				});
				break;
			case 2:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useShadows(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useShadows(value);
					}
				});
				break;
			case 3:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCheapCustomPFX(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCheapCustomPFX(value);
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCheapColorCorrection(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCheapColorCorrection(value);
					}
				});
				break;
			case 5:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useVertexFresnel(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useVertexFresnel(value);
					}
				});
				break;
			case 6:
				EditorDialogs.showIntegerDialog(this, name, getGlobals().enterValue(), getProfile().cutoffDistanceOverride(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().cutoffDistanceOverride(value);
					}
				});
				break;
			case 7:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCarDirt(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCarDirt(value);
					}
				});
				break;
			case 8:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useFog(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useFog(value);
					}
				});
				break;
			case 9:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useRoadReflection(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useRoadReflection(value);
					}
				});
				break;
			case 10:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useMotionBlur(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useMotionBlur(value);
					}
				});
				break;
			case 11:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useLensflare(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useLensflare(value);
					}
				});
				break;
			case 12:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useQualityRoadReflection(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useQualityRoadReflection(value);
					}
				});
				break;
			case 13:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useGlassCrackPFX(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useGlassCrackPFX(value);
					}
				});
				break;
			case 14:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCustomPFX(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCustomPFX(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"postFX", "postFXLUTOnly", "useShadows", "useCheapCustomPFX", "useCheapColorCorrection",
			"useVertexFresnel", "cutoffDistanceOverride", "useCarDirt", "useFog",
			"useRoadReflection", "useMotionBlur", "useLensflare", "useQualityRoadReflection",
			"useGlassCrackPFX", "useCustomPFX"
	};

	@Override
	protected int getOptionResource() {
		return R.array.fx_options;
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
						getProfile().postFX(getProfile().postFX());
						break;
					case 1:
						getProfile().postFXLUTOnly(getProfile().postFXLUTOnly());
						break;
					case 2:
						getProfile().useShadows(getProfile().useShadows());
						break;
					case 3:
						getProfile().useCheapCustomPFX(getProfile().useCheapCustomPFX());
						break;
					case 4:
						getProfile().useCheapColorCorrection(getProfile().useCheapColorCorrection());
						break;
					case 5:
						getProfile().useVertexFresnel(getProfile().useVertexFresnel());
						break;
					case 6:
						getProfile().cutoffDistanceOverride(getProfile().cutoffDistanceOverride());
						break;
					case 7:
						getProfile().useCarDirt(getProfile().useCarDirt());
						break;
					case 8:
						getProfile().useFog(getProfile().useFog());
						break;
					case 9:
						getProfile().useRoadReflection(getProfile().useRoadReflection());
						break;
					case 10:
						getProfile().useMotionBlur(getProfile().useMotionBlur());
						break;
					case 11:
						getProfile().useLensflare(getProfile().useLensflare());
						break;
					case 12:
						getProfile().useQualityRoadReflection(getProfile().useQualityRoadReflection());
						break;
					case 13:
						getProfile().useGlassCrackPFX(getProfile().useGlassCrackPFX());
						break;
					case 14:
						getProfile().useCustomPFX(getProfile().useCustomPFX());
						break;
				}
			}
		});
	}
}
