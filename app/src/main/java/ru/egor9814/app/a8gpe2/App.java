package ru.egor9814.app.a8gpe2;

import android.app.Application;

import ru.egor9814.app.a8gpe2.editor.GPModelWrapper;

/**
 * Created by egor9814 on 03.05.2016.
 */
public class App extends Application {

	@Override
	public void onTerminate() {
		try{
			GPModelWrapper.getWrapper().destroy();
		} finally {}
		super.onTerminate();
	}
}
