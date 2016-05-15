package ru.egor9814.app.a8gpe2.profiles;


import java.util.ArrayList;

import ru.egor9814.app.a8gpe2.json.JSONArray;
import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class CPUProfile extends Profile {

	public CPUProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public CPUProfile(String name) {
		super(name);
	}

	public boolean useTrafficCars(){
		return optBoolean("useTrafficCars", false);
	}

	public boolean useBreakables(){
		return optBoolean("useBreakables", false);
	}

	public boolean useSimplifiedCarCollisions(){
		return optBoolean("useSimplifiedCarCollisions", true);
	}

	public boolean useAICarSounds(){
		return optBoolean("useAICarSounds", false);
	}

	public boolean useAICarParticles(){
		return optBoolean("useAICarParticles", false);
	}

	public boolean useQualityPhysics(){
		return optBoolean("useQualityPhysics", false);
	}

	public boolean useNetworkWakeupThread(){
		return optBoolean("useNetworkWakeupThread", false);
	}

	public boolean useAnamorphicGlows(){
		return optBoolean("useAnamorphicGlows", false);
	}

	public int maxPlayersWhenHosting(){
		return optInt("maxPlayersWhenHosting", 2);
	}

	public int maxTakedownPlayersWhenHosting(){
		return optInt("maxTakedownPlayersWhenHosting", 4);
	}

	public boolean disablePhysicsThread(){
		return optBoolean("disablePhysicsThread", true);
	}


	public void useTrafficCars(boolean value){
		put("useTrafficCars", value);
	}

	public void useBreakables(boolean value){
		put("useBreakables", value);
	}

	public void useSimplifiedCarCollisions(boolean value){
		put("useSimplifiedCarCollisions", value);
	}

	public void useAICarSounds(boolean value){
		put("useAICarSounds", value);
	}

	public void useAICarParticles(boolean value) {
		put("useAICarParticles", value);
	}

	public void useQualityPhysics(boolean value) {
		put("useQualityPhysics", value);
	}

	public void useNetworkWakeupThread(boolean value){
		put("useNetworkWakeupThread", value);
	}

	public void useAnamorphicGlows(boolean value){
		put("useAnamorphicGlows", value);
	}

	public void maxPlayersWhenHosting(int value) {
		put("maxPlayersWhenHosting", value);
	}

	public void maxTakedownPlayersWhenHosting(int value){
		put("maxTakedownPlayersWhenHosting", value);
	}

	public void disablePhysicsThread(boolean value) {
		put("disablePhysicsThread", value);
	}


	public void compile(){
		if(getProcessors().size() > 0){
			put("processors", getProcessors().getArray());
		} else if(has("processors")){
			remove("processors");
		}
	}


	private Processors processors;
	public Processors getProcessors() {
		if(processors == null){
			JSONArray array = optArray("processors");
			if(array == null){
				processors = new Processors(new JSONArray());
			} else {
				processors = new Processors(array);
			}
		}
		return processors;
	}

	public class Processors{
		private JSONArray array;
		Processors(JSONArray array){
			this.array = array;
			if(array != null && array.length() > 0){
				for(int i = 0; i < array.length(); i++){
					processors.add(newProcessor(array.optJSONObject(i)));
				}
			}
			update();
		}

		private ArrayList<Processor> processors = new ArrayList<>();
		public ArrayList<Processor> getProcessors() {
			return processors;
		}
		public void addProcessor(Processor processor){
			processors.add(processor);
			update();
		}
		public void removeProcessor(int index){
			processors.remove(index);
			update();
		}
		public Processor getProcessor(int index){
			return processors.get(index);
		}

		public int size(){
			return processors.size();
		}

		private void update(){
			array = new JSONArray();
			for(Processor processor : processors){
				array.put(processor.getProfile());
			}
		}

		public JSONArray getArray() {
			return array;
		}

		public Processor newProcessor(){
			return newProcessor(new JSONObject());
		}
		public Processor newProcessor(JSONObject processor){
			return new Processor(processor);
		}
	}

	public class Processor extends Profile {
		Processor(JSONObject profile) {
			super(null, profile);
		}

		public double getMax() {
			return optDouble("max", 1.2);
		}

		public void setMax(double max) {
			put("max", max);
		}

		public double getMin() {
			return optDouble("min", 1);
		}

		public void setMin(double min) {
			put("min", min);
		}

		public int getCore() {
			return optInt("core", 2);
		}

		public void setCore(int core) {
			put("core", core);
		}
	}

}
