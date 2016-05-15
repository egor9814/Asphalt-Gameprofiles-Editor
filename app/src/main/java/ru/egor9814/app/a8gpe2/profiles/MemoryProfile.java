package ru.egor9814.app.a8gpe2.profiles;


import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class MemoryProfile extends Profile {

	public MemoryProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public MemoryProfile(String name) {
		super(name);
	}

	public boolean freeMoreMemory(){
		return optBoolean("freeMoreMemory", true);
	}

	public boolean dropAICarLodWhenManyCarsOnScreen(){
		return optBoolean("dropAICarLodWhenManyCarsOnScreen", true);
	}

	public boolean useTextureStreaming(){
		return optBoolean("useTextureStreaming", false);
	}

	public int startTextureLOD(){
		return optInt("startTextureLOD", 1);
	}

	public int textureBudgetMB(){
		return optInt("textureBudgetMB", 80);
	}


	public void freeMoreMemory(boolean value) {
		put("freeMoreMemory", value);
	}

	public void dropAICarLodWhenManyCarsOnScreen(boolean value) {
		put("dropAICarLodWhenManyCarsOnScreen", value);
	}

	public void useTextureStreaming(boolean value) {
		put("useTextureStreaming", value);
	}

	public void startTextureLOD(int value) {
		put("startTextureLOD", value);
	}

	public void textureBudgetMB(int value) {
		put("textureBudgetMB", value);
	}

	public void compile(){
		if(getMemory().initialized()){
			put("memory", getMemory().getProfile());
		} else if(has("memory")){
			remove("memory");
		}
	}

	private Memory memory;
	public Memory getMemory() {
		if(memory == null){
			JSONObject mem = optObject("memory");
			if(mem == null){
				memory = new Memory();
			} else {
				memory = new Memory(mem);
			}
		}
		return memory;
	}

	public class Memory extends Profile{

		public Memory(JSONObject profile) {
			super(null, profile);
		}
		public Memory(){
			super(null, new JSONObject());
		}

		public int getMax() {
			return optInt("max", 768);
		}

		public void setMax(int max) {
			put("max", max);
		}

		public int getMin() {
			return optInt("min", 512);
		}

		public void setMin(int min) {
			put("min", min);
		}

		public boolean initialized(){
			return has("max") || has("min");
		}
	}
}
