package ru.egor9814.app.a8gpe2.editor;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ru.egor9814.app.a8gpe2.json.JSONObject;
import ru.egor9814.app.a8gpe2.profiles.CPUProfile;
import ru.egor9814.app.a8gpe2.profiles.DeviceProfile;
import ru.egor9814.app.a8gpe2.profiles.GFXOptionProfile;
import ru.egor9814.app.a8gpe2.profiles.GPModel;
import ru.egor9814.app.a8gpe2.profiles.GPUProfile;
import ru.egor9814.app.a8gpe2.profiles.MemoryProfile;
import ru.egor9814.app.a8gpe2.profiles.ResolutionProfile;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class GPModelWrapper {

	private static GPModelWrapper wrapper;
	public static GPModelWrapper getWrapper() {
		return wrapper;
	}

	private GPModel model;
	public GPModelWrapper(GPModel model) {
		if(wrapper != null){
			wrapper.destroy();
		}
		this.model = model;
		wrapper = this;
	}
	public GPModelWrapper(JSONObject json) throws JSONException {
		this(new GPModel(json));
	}


	private List<String> sort(List<String> list){
		if(list.size() == 0) return list;
		String[] items = list.toArray(new String[list.size()]);
		Arrays.sort(items);
		return Arrays.asList(items);
	}

	public GPUProfile getGpuProfile(String key){
		return model.gpu.get(key);
	}
	public List<String> getGpuProfiles(){
		return sort(new ArrayList<>(model.gpu.keySet()));
	}
	public void putGpuProfile(String key){
		try{
			model.newGPU(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException(e);
		}
	}
	public boolean hasGpu(String key){
		return model.gpu.containsKey(key);
	}
	public void removeGpu(String key){
		model.gpu.remove(key);
	}


	public CPUProfile getCpuProfile(String key){
		return model.cpu.get(key);
	}
	public List<String> getCpuProfiles(){
		return sort(new ArrayList<>(model.cpu.keySet()));
	}
	public void putCpuProfile(String key){
		try {
			model.newCPU(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException(e);
		}
	}
	public boolean hasCpu(String key){
		return model.cpu.containsKey(key);
	}
	public void removeCpu(String key){
		model.cpu.remove(key);
	}


	public MemoryProfile getMemoryProfile(String key){
		return model.mem.get(key);
	}
	public List<String> getMemoryProfiles(){
		return sort(new ArrayList<>(model.mem.keySet()));
	}
	public void putMemoryProfile(String key){
		try {
			model.newMEM(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException();
		}
	}
	public boolean hasMem(String key){
		return model.mem.containsKey(key);
	}
	public void removeMem(String key){
		model.mem.remove(key);
	}

	public ResolutionProfile getResolutionProfile(String key){
		return model.res.get(key);
	}
	public List<String> getResolutionProfiles(){
		return sort(new ArrayList<>(model.res.keySet()));
	}
	public void putResolutionProfile(String key){
		try{
			model.newRes(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException();
		}
	}
	public boolean hasRes(String key){
		return model.res.containsKey(key);
	}
	public void removeRes(String key){
		model.res.remove(key);
	}

	public GFXOptionProfile getGfxOptionProfile(String key){
		return model.opt.get(key);
	}
	public List<String> getGfxOptionProfiles(){
		return new ArrayList<>(model.opt.keySet());
	}
	public void putGfxOptionProfile(String key){
		try{
			model.newOPT(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException();
		}
	}
	public boolean hasGfx(String key){
		return model.opt.containsKey(key);
	}
	public void removeGfx(String key){
		model.opt.remove(key);
	}

	public DeviceProfile getDeviceProfile(String key){
		return model.specifics.get(key);
	}
	public List<String> getDevicePofiles(){
		return sort(new ArrayList<>(model.specifics.keySet()));
	}
	public void putDeviceProfile(String key){
		try{
			model.newDevice(key);
		} catch(JSONException e) {
			throw new UnsupportedOperationException();
		}
	}
	public void putDeviceProfile(String key, DeviceProfile profile){
		model.specifics.put(key, profile);
	}
	public boolean hasDevice(String key){
		return model.specifics.containsKey(key);
	}
	public void removeDevice(String key){
		model.specifics.remove(key);
	}
	public void renameDevice(String name, String newName){
		DeviceProfile device = model.specifics.remove(name);
		model.specifics.put(newName, device);
	}

	public void destroy(){
		model.destroy();
		wrapper = null;
	}

	public void save(File file) throws IOException, JSONException {
		PrintWriter writer = new PrintWriter(file);
		Scanner in = new Scanner(model.compile().toString());
		while(in.hasNextLine()){
			writer.println(in.nextLine());
		}
		writer.flush();
		writer.close();
	}
}
