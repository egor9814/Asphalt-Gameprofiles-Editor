package ru.egor9814.app.a8gpe2.profiles;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import ru.egor9814.app.a8gpe2.json.JSONArray;
import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class GPUProfile extends Profile {

	public GPUProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public GPUProfile(String name) {
		super(name);
	}

	public boolean useParaboloidReflection(){
		return optBoolean("useParaboloidReflection");
	}

	public boolean useStaticParaboloidReflection(){
		return optBoolean("useStaticParaboloidReflection");
	}

	public boolean useHighQualityCars(){
		return optBoolean("useHighQualityCars", false);
	}

	public boolean useRoadSpecular(){
		return optBoolean("useRoadSpecular", false);
	}

	public boolean useCarParticles(){
		return optBoolean("useCarParticles", false);
	}

	public boolean usePerfBoost(){
		return optBoolean("usePerfBoost", false);
	}

	public int roadTextureAnisotropy(){
		return optInt("roadTextureAnisotropy", 8);
	}

	public int carTextureAnisotropy(){
		return optInt("carTextureAnisotropy", 8);
	}

	public String GFXOption(){
		return optString("GFXOption", "VERYLOW");
	}

	public boolean OptimCarLods(){
		return optBoolean("OptimCarLods", true);
	}

	public boolean useDof(){
		return optBoolean("useDof", false);
	}

	public boolean useCarSpecular(){
		return optBoolean("useCarSpecular", false);
	}

	public boolean useCarQualityLighting(){
		return optBoolean("useCarQualityLighting", false);
	}

	public boolean useSkidMarks(){
		return optBoolean("useSkidMarks", false);
	}

	public boolean sortSolidsFrontToBack(){
		return optBoolean("sortSolidsFrontToBack", false);
	}

	public int defaultTextureFiltering(){
		return optInt("defaultTextureFiltering", 1);
	}

	public int startTextureLOD(){
		return optInt("startTextureLOD", 1);
	}

	public boolean allowReflectionInAP(){
		return optBoolean("allowReflectionInAP", false);
	}

	public int fullScreenBlurQuality(){
		return optInt("fullScreenBlurQuality", 1);
	}


	public void useParaboloidReflection(boolean value){
		put("useParaboloidReflection", value);
	}

	public void useStaticParaboloidReflection(boolean value){
		put("useStaticParaboloidReflection", value);
	}

	public void useHighQualityCars(boolean value) {
		put("useHighQualityCars", value);
	}

	public void useRoadSpecular(boolean value) {
		put("useRoadSpecular", value);
	}

	public void useCarParticles(boolean value) {
		put("useCarParticles", value);
	}

	public void usePerfBoost(boolean value) {
		put("usePerfBoost", value);
	}

	public void roadTextureAnisotropy(int value){
		put("roadTextureAnisotropy", value);
	}

	public void carTextureAnisotropy(int value){
		put("carTextureAnisotropy", value);
	}

	public void GFXOption(String value) {
		put("GFXOption", value);
	}

	public void OptimCarLods(boolean value) {
		put("OptimCarLods", value);
	}

	public void useDof(boolean value) {
		put("useDof", value);
	}

	public void useCarSpecular(boolean value){
		put("useCarSpecular", value);
	}

	public void useCarQualityLighting(boolean value){
		put("useCarQualityLighting", value);
	}

	public void useSkidMarks(boolean value) {
		put("useSkidMarks", value);
	}

	public void sortSolidsFrontToBack(boolean value){
		put("sortSolidsFrontToBack", value);
	}

	public void defaultTextureFiltering(int value){
		put("defaultTextureFiltering", value);
	}

	public void startTextureLOD(int value) {
		put("startTextureLOD", value);
	}

	public void allowReflectionInAP(boolean value) {
		put("allowReflectionInAP", value);
	}

	public void fullScreenBlurQuality(int value){
		put("fullScreenBlurQuality", value);
	}


	public void compile() {
		if(getRenderers().size() > 0){
			put("renderers", getRenderers().getArray());
		} else if(has("renderers")){
			remove("renderers");
		}
	}


	private Renderers renderers;
	public Renderers getRenderers() {
		if(renderers == null){
			JSONArray array = optArray("renderers");
			if(array == null){
				renderers = new Renderers(new JSONArray());
			} else {
				renderers = new Renderers(array);
			}
		}
		return renderers;
	}

	public class Renderers {
		private JSONArray array;
		Renderers(JSONArray array){
			this.array = array;
			if(array != null && array.length() > 0){
				for(int i = 0; i < array.length(); i++){
					renderers.add(newRenderer(array.optJSONObject(i)));
				}
			}
			update();
		}

		private ArrayList<Renderer> renderers = new ArrayList<>();
		public ArrayList<Renderer> getRenderers() {
			return renderers;
		}
		public void addRenderer(Renderer renderer){
			renderers.add(renderer);
			update();
		}
		public void removeRenderer(int index){
			renderers.remove(index);
			update();
		}
		public Renderer getRenderer(int index){
			return renderers.get(index);
		}

		public int size(){
			return getRenderers().size();
		}

		public Renderer newRenderer(String name){
			return new Renderer(name);
		}
		public Renderer newRenderer(JSONObject renderer){
			return new Renderer(renderer);
		}

		private void update(){
			array = new JSONArray();
			for(Renderer renderer : renderers){
				try {
					array.put(renderer.toObject());
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}
		}

		public JSONArray getArray() {
			return array;
		}
	}

	public class Renderer {
		private String name;
		private List<RendererParam> params = new ArrayList<>();
		Renderer(String name) {
			this.name = name;
		}
		Renderer(JSONObject renderer) {
			for(String key : renderer.keys()){
				String value = renderer.optString(key);
				if(key.equals("n")) setName(value);
				else addParam(key, value);
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<RendererParam> getParams() {
			return params;
		}
		public RendererParam getParam(int index){
			return params.get(index);
		}
		public RendererParam getParam(String name){
			if(params.size() == 0) return null;
			for(int i = 0; i < params.size(); i++){
				if(params.get(i).getName().equals(name)){
					return params.get(i);
				}
			}
			return null;
		}
		public void removeParam(int index){
			params.remove(index);
		}
		public void removeParam(String name){
			if(params.size() == 0) return;
			for(int i = 0; i < params.size(); i++){
				if(params.get(i).getName().equals(name)){
					params.remove(i);
					break;
				}
			}
		}
		public void addParam(RendererParam param){
			for(int i = 0; i < params.size(); i++){
				if(params.get(i).getName().equals(name)){
					params.remove(i);
					break;
				}
			}
			params.add(param);
		}
		public void addParam(String name, String value){
			addParam(newRendererParam(name, value));
		}
		public RendererParam newRendererParam(String name, String value){
			return new RendererParam(name, value);
		}

		public JSONObject toObject() throws JSONException {
			JSONObject object = new JSONObject();
			object.put("n", name);
			for(RendererParam param : getParams()){
				object.put(param.getName(), param.getValue());
			}
			return object;
		}
	}

	public class RendererParam {
		private String name, value;
		RendererParam(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
