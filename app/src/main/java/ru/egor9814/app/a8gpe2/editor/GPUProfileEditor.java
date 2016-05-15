package ru.egor9814.app.a8gpe2.editor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.GPUProfile;

/**
 * Created by egor9814 on 08.05.2016.
 */
public class GPUProfileEditor extends EditorActivity {

	private GPUProfile getProfile(){
		return GPModelWrapper.getWrapper().getGpuProfile(getIntent().getStringExtra("gpu_name"));
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
				Intent i = new Intent(this, RenderersEditorActivity.class);
				i.putExtra("gpu_name", getIntent().getStringExtra("gpu_name"));
				startActivity(i);
				break;
			case 1:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useParaboloidReflection(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useParaboloidReflection(value);
					}
				});
				break;
			case 2:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useStaticParaboloidReflection(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useStaticParaboloidReflection(value);
					}
				});
				break;
			case 3:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useHighQualityCars(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useHighQualityCars(value);
					}
				});
				break;
			case 4:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCarQualityLighting(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCarQualityLighting(value);
					}
				});
				break;
			case 5:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useRoadSpecular(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useRoadSpecular(value);
					}
				});
				break;
			case 6:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCarSpecular(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCarSpecular(value);
					}
				});
				break;
			case 7:
				EditorDialogs.showIntegerDialog(this, name, getGpuStrings().enterValue(), getProfile().roadTextureAnisotropy(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().roadTextureAnisotropy(value);
					}
				});
				break;
			case 8:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useCarParticles(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useCarParticles(value);
					}
				});
				break;
			case 9:
				EditorDialogs.showBooleanDialog(this, name, getProfile().usePerfBoost(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().usePerfBoost(value);
					}
				});
				break;
			case 10:
				/*EditorDialogs.showStringDialog(this, name, getGpuStrings().enterValue(), getProfile().GFXOption(), new StringResultAdapter() {
					@Override
					public void onResult(String value) {
						getProfile().GFXOption(value);
					}
				});*/
				final List<String> fx = GPModelWrapper.getWrapper().getGfxOptionProfiles();
				if(fx.size() == 0){
					Toast.makeText(this, R.string.editor_create_new_fx, Toast.LENGTH_LONG).show();
				} else {
					int index = fx.indexOf(getProfile().GFXOption());
					if(index == -1) index = 0;
					EditorDialogs.showSingleChoiceDialog(this, name, fx.toArray(new String[fx.size()]), index, new IntegerResultAdapter() {
						@Override
						public void onResult(int value) {
							getProfile().GFXOption(fx.get(value));
						}
					});
				}
				break;
			case 11:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useDof(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useDof(value);
					}
				});
				break;
			case 12:
				EditorDialogs.showIntegerDialog(this, name, getGpuStrings().enterValue(), getProfile().defaultTextureFiltering(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().defaultTextureFiltering(value);
					}
				});
				break;
			case 13:
				EditorDialogs.showBooleanDialog(this, name, getProfile().OptimCarLods(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().OptimCarLods(value);
					}
				});
				break;
			case 14:
				EditorDialogs.showIntegerDialog(this, name, getGpuStrings().enterValue(), getProfile().startTextureLOD(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().startTextureLOD(value);
					}
				});
				break;
			case 15:
				EditorDialogs.showBooleanDialog(this, name, getProfile().useSkidMarks(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().useSkidMarks(value);
					}
				});
				break;
			case 16:
				EditorDialogs.showBooleanDialog(this, name, getProfile().sortSolidsFrontToBack(), new BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						getProfile().sortSolidsFrontToBack(value);
					}
				});
				break;
			case 17:
				EditorDialogs.showIntegerDialog(this, name, getGpuStrings().enterValue(), getProfile().fullScreenBlurQuality(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().fullScreenBlurQuality(value);
					}
				});
				break;
			case 18:
				EditorDialogs.showIntegerDialog(this, name, getGpuStrings().enterValue(), getProfile().carTextureAnisotropy(), new IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						getProfile().carTextureAnisotropy(value);
					}
				});
				break;
		}
	}
	private final String[] keys = {
			"renderers", "useParaboloidReflection", "useStaticParaboloidReflection",
			"useHighQualityCars", "useCarQualityLighting", "useRoadSpecular", "useCarSpecular",
			"roadTextureAnisotropy", "useCarParticles", "usePerfBoost", "GFXOption", "useDof",
			"defaultTextureFiltering", "OptimCarLods", "startTextureLOD", "useSkidMarks",
			"sortSolidsFrontToBack", "fullScreenBlurQuality", "carTextureAnisotropy"
	};

	@Override
	protected int getOptionResource() {
		return R.array.gpu_options;
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
						getProfile().getRenderers();
						break;
					case 1:
						getProfile().useParaboloidReflection(getProfile().useParaboloidReflection());
						break;
					case 2:
						getProfile().useStaticParaboloidReflection(getProfile().useStaticParaboloidReflection());
						break;
					case 3:
						getProfile().useHighQualityCars(getProfile().useHighQualityCars());
						break;
					case 4:
						getProfile().useCarQualityLighting(getProfile().useCarQualityLighting());
						break;
					case 5:
						getProfile().useRoadSpecular(getProfile().useRoadSpecular());
						break;
					case 6:
						getProfile().useCarSpecular(getProfile().useCarParticles());
						break;
					case 7:
						getProfile().roadTextureAnisotropy(getProfile().roadTextureAnisotropy());
						break;
					case 8:
						getProfile().useCarParticles(getProfile().useCarParticles());
						break;
					case 9:
						getProfile().usePerfBoost(getProfile().usePerfBoost());
						break;
					case 10:
						getProfile().GFXOption(getProfile().GFXOption());
						break;
					case 11:
						getProfile().useDof(getProfile().useDof());
						break;
					case 12:
						getProfile().defaultTextureFiltering(getProfile().defaultTextureFiltering());
						break;
					case 13:
						getProfile().OptimCarLods(getProfile().OptimCarLods());
						break;
					case 14:
						getProfile().startTextureLOD(getProfile().startTextureLOD());
						break;
					case 15:
						getProfile().useSkidMarks(getProfile().useSkidMarks());
						break;
					case 16:
						getProfile().sortSolidsFrontToBack(getProfile().sortSolidsFrontToBack());
						break;
					case 17:
						getProfile().fullScreenBlurQuality(getProfile().fullScreenBlurQuality());
						break;
					case 18:
						getProfile().carTextureAnisotropy(getProfile().carTextureAnisotropy());
						break;
				}
			}
		});
	}


	public static class RenderersEditorActivity extends EditorActivity{
		private GPUProfile getProfile(){
			return GPModelWrapper.getWrapper().getGpuProfile(getIntent().getStringExtra("gpu_name"));
		}
		private GPUProfile.Renderers getRenderers(){
			return getProfile().getRenderers();
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
			final int rend = getPanel().indexOf(name);
			if(rend == -1) return;
			final GPUProfile.Renderer r = getRenderers().getRenderer(rend);
			String[] paramsNames = new String[r.getParams().size()];
			String[] params = new String[paramsNames.length];
			if(params.length > 0) for(int i = 0; i < params.length; i++){
				GPUProfile.RendererParam p = r.getParam(i);
				paramsNames[i] = p.getName();
				params[i] = p.getValue();
			}
			EditorDialogs.showRendererDialog(this, getGpuStrings().name(), r.getName(), paramsNames, params, new EditorDialogs.OnRendererResultListener() {
				@Override
				public void onNameChanged(String newName) {
					r.setName(newName);
					refresh();
				}
				@Override
				public void onParamsClick(String[] paramsNames, String[] params) {
					Intent i = new Intent(RenderersEditorActivity.this, ParamsEditorActivity.class);
					i.putExtra("gpu_name", getIntent().getStringExtra("gpu_name"));
					i.putExtra("renderer", rend);
					//i.putExtra("params", params);
					//i.putExtra("paramsNames", paramsNames);
					startActivity(i);
				}
			});
		}

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			getPanel().setOnUpdateListener(new EditorPanel.OnUpdateListener() {
				@Override
				public void onPostRemove(int position, String name, int newSize) {
					getRenderers().removeRenderer(position);
					refresh();
				}
				@Override
				public void onNewElement(String name) {
				}
			});
			refresh();
			setTitle(getResources().getStringArray(R.array.gpu_options)[0]);
		}

		private void refresh(){
			getPanel().clearAdapter();
			GPUProfile.Renderers r = getRenderers();
			for(int i = 0; i < r.size(); i++){
				getPanel().addToAdapter(r.getRenderer(i).getName());
			}
		}

		@Override
		protected void actionAdd() {
			EditorDialogs.showNewRendererDialog(this, getGpuStrings().newRenderer(), new StringResultAdapter() {
				@Override
				public void onResult(String value) {
					if(value.length() > 0 && !getPanel().containsInAdapter(value)) {
						GPUProfile.Renderers r = getRenderers();
						r.addRenderer(r.newRenderer(value));
						refresh();
					}
				}
			});
		}

		@Override
		protected void onDestroy() {
			getProfile().compile();
			super.onDestroy();
		}
	}


	public static class ParamsEditorActivity extends EditorActivity{
		private GPUProfile getProfile(){
			return GPModelWrapper.getWrapper().getGpuProfile(getIntent().getStringExtra("gpu_name"));
		}
		private GPUProfile.Renderer getRenderer(){
			return getProfile().getRenderers().getRenderer(getIntent().getIntExtra("renderer", -1));
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
			int param = getPanel().indexOf(name);
			if(param == -1) return;
			final GPUProfile.RendererParam p = getRenderer().getParam(param);
			EditorDialogs.showParamEditDialog(this, getGpuStrings().editParam(),
					getGpuStrings().rendererName(), getGpuStrings().rendererValue(),
					p.getName(), p.getValue(), new EditorDialogs.OnParamEditorListener() {
				@Override
				public void onParamChanged(String name, String value) {
					p.setName(name);
					p.setValue(value);
					refresh();
				}
			});
		}

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			getPanel().setOnUpdateListener(new EditorPanel.OnUpdateListener() {
				@Override
				public void onPostRemove(int position, String name, int newSize) {
					getRenderer().removeParam(position);
					refresh();
				}
				@Override
				public void onNewElement(String name) {
				}
			});
			refresh();
			setTitle(getRenderer().getName());
		}

		private void refresh(){
			getPanel().clearAdapter();
			GPUProfile.Renderer r = getRenderer();
			String param = getGpuStrings().rendererParam();
			for(int i = 0; i < r.getParams().size(); i++){
				getPanel().addToAdapter(param + " " + (i+1));
			}
		}

		@Override
		protected void actionAdd() {
			getRenderer().addParam("", "");
			refresh();
		}
	}
}
