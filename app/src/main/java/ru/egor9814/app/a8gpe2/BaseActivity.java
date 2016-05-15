package ru.egor9814.app.a8gpe2;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class BaseActivity extends AppCompatActivity {

	public class Strings{
		private Resources res;
		private Strings(Resources res) {
			this.res = res;
		}

		protected String getString(int id){
			return res.getString(id);
		}

		protected String[] getStringArray(int id){
			return res.getStringArray(id);
		}
	}


	public class GlobalStrings extends Strings{
		private GlobalStrings(Resources res) {
			super(res);
		}

		public String enterValue(){
			return getString(R.string.editor_enter_value);
		}

		public String enterName(){
			return getString(R.string.editor_enter_name);
		}

		public String name(){
			return getString(R.string.editor_name);
		}
	}
	private GlobalStrings globalStrings;
	public GlobalStrings getGlobals() {
		if(globalStrings == null){
			globalStrings = new GlobalStrings(getResources());
		}
		return globalStrings;
	}

	public class CPUStrings extends GlobalStrings{
		private CPUStrings(Resources res) {
			super(res);
		}

		public String processor(){
			return getString(R.string.processor);
		}
	}
	private CPUStrings cpuStrings;
	public CPUStrings getCpuStrings() {
		if(cpuStrings == null){
			cpuStrings = new CPUStrings(getResources());
		}
		return cpuStrings;
	}


	public class GPUStrings extends GlobalStrings{
		private GPUStrings(Resources res) {
			super(res);
		}

		public String newRenderer(){
			return getString(R.string.renderer_new_renderer);
		}

		public String editParam(){
			return getString(R.string.renderer_edit_param);
		}

		public String rendererName(){
			return getString(R.string.renderer_name);
		}

		public String rendererValue(){
			return getString(R.string.renderer_value);
		}

		public String rendererParam(){
			return getString(R.string.renderer_param);
		}
	}
	private GPUStrings gpuStrings;
	public GPUStrings getGpuStrings() {
		if(gpuStrings == null){
			gpuStrings = new GPUStrings(getResources());
		}
		return gpuStrings;
	}

	public class MEMStrings extends GlobalStrings{
		private MEMStrings(Resources res) {
			super(res);
		}
	}
	private MEMStrings memStrings;
	public MEMStrings getMemStrings() {
		if(memStrings == null){
			memStrings = new MEMStrings(getResources());
		}
		return memStrings;
	}
}
