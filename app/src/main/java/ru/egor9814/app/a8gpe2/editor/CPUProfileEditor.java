package ru.egor9814.app.a8gpe2.editor;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;

import java.util.Arrays;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.CPUProfile;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class CPUProfileEditor extends EditorActivity {

	private CPUProfile getProfile(){
		return GPModelWrapper.getWrapper().getCpuProfile(getIntent().getStringExtra("cpu_name"));
	}

	@Override
	protected void actionEdit(final String name) {
		String key = findKey(getProfile(), name, keys);
		if(key == null){
			try {
				EditorDialogs.showUnknownItemDialog(this, getProfile(), name);
			} catch(JSONException e) {
				e.printStackTrace();
			}
		} else switch(Arrays.asList(keys).indexOf(key)){
			case 0:
				Intent i = new Intent(this, ProcessorsEditorActivity.class);
				i.putExtra("cpu_name", getIntent().getStringExtra("cpu_name"));
				startActivity(i);
				break;
			case 1:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useTrafficCars(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useTrafficCars(value);
					}
				});
				break;
			case 2:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useBreakables(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useBreakables(value);
					}
				});
				break;
			case 3:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useSimplifiedCarCollisions(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useSimplifiedCarCollisions(value);
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAICarSounds(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAICarSounds(value);
					}
				});
				break;
			case 5:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAICarParticles(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAICarParticles(value);
					}
				});
				break;
			case 6:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useQualityPhysics(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useQualityPhysics(value);
					}
				});
				break;
			case 7:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useNetworkWakeupThread(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useNetworkWakeupThread(value);
					}
				});
				break;
			case 8:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useAnamorphicGlows(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useAnamorphicGlows(value);
					}
				});
				break;
			case 9:
				EditorDialogs.showIntegerDialog(this, name, getCpuStrings().enterValue(), getProfile().maxPlayersWhenHosting(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().maxPlayersWhenHosting(value);
					}
				});
				break;
			case 10:
				EditorDialogs.showIntegerDialog(this, name, getCpuStrings().enterValue(), getProfile().maxTakedownPlayersWhenHosting(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().maxTakedownPlayersWhenHosting(value);
					}
				});
				break;
			case 11:
				EditorDialogs.showBooleanDialog(this, name, getProfile().disablePhysicsThread(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().disablePhysicsThread(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"processors", "useTrafficCars", "useBreakables", "useSimplifiedCarCollisions",
			"useAICarSounds", "useAICarParticles", "useQualityPhysics",
			"useNetworkWakeupThread", "useAnamorphicGlows", "maxPlayersWhenHosting",
			"maxTakedownPlayersWhenHosting", "disablePhysicsThread"
	};

	@Override
	protected int getOptionResource() {
		return R.array.cpu_options;
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
						getProfile().getProcessors();
						break;
					case 1:
						getProfile().useTrafficCars(getProfile().useTrafficCars());
						break;
					case 2:
						getProfile().useBreakables(getProfile().useBreakables());
						break;
					case 3:
						getProfile().useSimplifiedCarCollisions(getProfile().useSimplifiedCarCollisions());
						break;
					case 4:
						getProfile().useAICarSounds(getProfile().useAICarSounds());
						break;
					case 5:
						getProfile().useAICarParticles(getProfile().useAICarParticles());
						break;
					case 6:
						getProfile().useQualityPhysics(getProfile().useQualityPhysics());
						break;
					case 7:
						getProfile().useNetworkWakeupThread(getProfile().useNetworkWakeupThread());
						break;
					case 8:
						getProfile().useAnamorphicGlows(getProfile().useAnamorphicGlows());
						break;
					case 9:
						getProfile().maxPlayersWhenHosting(getProfile().maxPlayersWhenHosting());
						break;
					case 10:
						getProfile().maxTakedownPlayersWhenHosting(getProfile().maxTakedownPlayersWhenHosting());
						break;
					case 11:
						getProfile().disablePhysicsThread(getProfile().disablePhysicsThread());
						break;
				}
			}
		});
	}


	public static class ProcessorsEditorActivity extends EditorActivity {
		private CPUProfile getCpuProfile(){
			return GPModelWrapper.getWrapper().getCpuProfile(getIntent().getStringExtra("cpu_name"));
		}
		private CPUProfile.Processors getProcessors(){
			return getCpuProfile().getProcessors();
		}

		@Override
		protected int getOptionResource() {
			throw new UnsupportedOperationException();
		}

		@Override
		protected String[] getEnabledOptions() {
			return new String[0];
		}

		@Override
		protected void actionEdit(String name) {
			int proc = getPanel().indexOf(name);
			if(proc == -1) return;
			String[] titles = getResources().getStringArray(R.array.processor_options);
			final CPUProfile.Processor p = getProcessors().getProcessor(proc);
			Number[] values = {p.getCore(), p.getMax(), p.getMin()};
			EditorDialogs.OnResultListener[] listeners = {
					new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							p.setCore(value);
						}
					},
					new DoubleResultAdapter() {
						@Override
						public void onResult(double value) {
							p.setMax(value);
						}
					},
					new DoubleResultAdapter() {
						@Override
						public void onResult(double value) {
							p.setMin(value);
						}
					}
			};
			EditorDialogs.showProcessorDialog(this, name, titles, values, listeners);
		}

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			getPanel().setOnUpdateListener(new EditorPanel.OnUpdateListener() {
				@Override
				public void onPostRemove(int position, String name, int newSize) {
					getProcessors().removeProcessor(position);
					refresh();
				}
				@Override
				public void onNewElement(String name) {
				}
			});
			refresh();
			setTitle(getResources().getStringArray(R.array.cpu_options)[0]);
		}
		private void refresh(){
			getPanel().clearAdapter();
			String p = getCpuStrings().processor();
			for(int i = 0; i < getProcessors().size(); i++){
				getPanel().addToAdapter(p + " " + (i+1));
			}
		}

		@Override
		protected void actionAdd() {
			CPUProfile.Processors p = getProcessors();
			p.addProcessor(p.newProcessor());
			refresh();
		}

		@Override
		protected void onDestroy() {
			getCpuProfile().compile();
			super.onDestroy();
		}
	}

}
