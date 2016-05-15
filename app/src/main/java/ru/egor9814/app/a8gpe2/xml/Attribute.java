package ru.egor9814.app.a8gpe2.xml;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class Attribute {

	public String name, value;

	public Attribute(String name, String value){
		this.name = name;
		this.value = value;
	}
	public Attribute(String name){
		this(name, "");
	}

	@Override
	public String toString() {
		return name + "=\"" + value + "\"";
	}

}
