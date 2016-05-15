package ru.egor9814.app.a8gpe2.profiles;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class GPModel {

	public ProfileMap<ResolutionProfile> res = new ProfileMap<>();
	public ProfileMap<GPUProfile> gpu = new ProfileMap<>();
	public ProfileMap<GFXOptionProfile> opt = new ProfileMap<>();
	public ProfileMap<CPUProfile> cpu = new ProfileMap<>();
	public ProfileMap<MemoryProfile> mem = new ProfileMap<>();
	public ProfileMap<DeviceProfile> specifics = new ProfileMap<>();

	public GPModel(JSONObject root) throws JSONException {
		for(String rootKey : root.keys()){
			JSONObject object = root.optJSONObject(rootKey);
			for(String key : object.keys()){
				JSONObject value = object.optJSONObject(key);
				switch(rootKey){
					case "RES_PROFILE":
						res.put(key, new ResolutionProfile(key, value));
						break;
					case "GPU_PROFILE":
						gpu.put(key, new GPUProfile(key, value));
						break;
					case "CPU_PROFILE":
						cpu.put(key, new CPUProfile(key, value));
						break;
					case "MEM_PROFILE":
						mem.put(key, new MemoryProfile(key, value));
						break;
					case "OPT_PROFILE":
						opt.put(key, new GFXOptionProfile(key, value));
						break;
					case "SPECIFICS":
						specifics.put(key, new DeviceProfile(key, value));
						break;
				}
			}
		}
	}

	public JSONObject compile() throws JSONException {
		for(GPUProfile gpu : this.gpu.values()){
			gpu.compile();
		}
		for(CPUProfile cpu : this.cpu.values()){
			cpu.compile();
		}
		for(MemoryProfile mem : this.mem.values()){
			mem.compile();
		}
		for(ResolutionProfile res : this.res.values()){
			res.compile();
		}

		JSONObject root = new JSONObject();
		String[] keys = {"RES_PROFILE", "GPU_PROFILE", "CPU_PROFILE", "MEM_PROFILE", "OPT_PROFILE", "SPECIFICS"};
		for(String objectKey : keys){
			JSONObject profile = new JSONObject();
			ProfileMap<? extends Profile> map = null;
			switch(objectKey){
				case "RES_PROFILE":
					map = res;
					break;
				case "GPU_PROFILE":
					map = gpu;
					break;
				case "CPU_PROFILE":
					map = cpu;
					break;
				case "MEM_PROFILE":
					map = mem;
					break;
				case "OPT_PROFILE":
					map = opt;
					break;
				case "SPECIFICS":
					map = specifics;
					break;
			}
			if(map != null) for(Map.Entry<String, ? extends Profile> entry : map.entrySet()){
				profile.put(entry.getKey(), entry.getValue().getProfile());
			}
			root.put(objectKey, profile);
		}
		return root;
	}

	public static class ProfileMap<T extends Profile> extends HashMap<String, T> {
		private static final long serialVersionUID = 6551062660148204671L;
	}

	public List<DeviceProfile> getDevices(){
		List<DeviceProfile> devices = new ArrayList<>();
		for(Map.Entry<String, DeviceProfile> entry : specifics.entrySet()){
			devices.add(entry.getValue());
		}
		return devices;
	}

	public void newRes(String key) throws JSONException {
		res.put(key, new ResolutionProfile(key));
	}
	public void newGPU(String key) throws JSONException {
		gpu.put(key, new GPUProfile(key));
	}
	public void newCPU(String key) throws JSONException {
		cpu.put(key, new CPUProfile(key));
	}
	public void newMEM(String key) throws JSONException {
		mem.put(key, new MemoryProfile(key));
	}
	public void newOPT(String key) throws JSONException {
		opt.put(key, new GFXOptionProfile(key));
	}
	public void newDevice(String name) throws JSONException {
		specifics.put(name, new DeviceProfile(name));
	}


	public void destroy() throws NullPointerException{
		res.clear();
		res = null;

		gpu.clear();
		gpu = null;

		cpu.clear();
		cpu = null;

		mem.clear();
		mem = null;

		opt.clear();
		opt = null;

		specifics.clear();
		specifics = null;
	}
}
