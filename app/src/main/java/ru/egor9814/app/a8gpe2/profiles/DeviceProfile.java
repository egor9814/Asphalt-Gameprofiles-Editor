package ru.egor9814.app.a8gpe2.profiles;


import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class DeviceProfile extends Profile {

	public DeviceProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public DeviceProfile(String name) {
		super(name);
	}

	public String cpu(){
		return optString("CPU", "0");
	}

	public String gpu(){
		return optString("GPU", "0");
	}

	public String res(){
		return optString("RES", "0");
	}

	public String mem(){
		return optString("MEM", "0");
	}

	public boolean reduceDepthFighting(){
		return optBoolean("reduceDepthFighting", false);
	}


	public void cpu(String value){
		put("CPU", value);
	}

	public void gpu(String value){
		put("GPU", value);
	}

	public void res(String value){
		put("RES", value);
	}

	public void mem(String value){
		put("MEM", value);
	}

	public void reduceDepthFighting(boolean value){
		put("reduceDepthFighting", value);
	}

}
