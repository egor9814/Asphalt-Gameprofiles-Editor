package ru.egor9814.app.a8gpe2;

import android.os.Build;

/**
 * Created by egor9814 on 12.05.2016.
 */
public class DeviceInfo {

	public static String version(){
		return Build.VERSION.SDK + ", API " + Build.VERSION.SDK_INT;
	}

	public static String device(){
		return Build.DEVICE;
	}
}
