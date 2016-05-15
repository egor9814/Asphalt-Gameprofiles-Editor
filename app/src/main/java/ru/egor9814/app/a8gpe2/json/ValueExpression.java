package ru.egor9814.app.a8gpe2.json;

/**
 * Created by egor9814 on 12.05.2016.
 */
class ValueExpression implements Expression {

	private final Object value;

	public ValueExpression(Object value) {
		this.value = value;
	}
	public ValueExpression(String value) {
		this.value = value;
	}
	public ValueExpression(int value) {
		this.value = value;
	}
	public ValueExpression(double value) {
		this.value = value;
	}
	public ValueExpression(boolean value) {
		this.value = value;
	}

	@Override
	public Object eval() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
