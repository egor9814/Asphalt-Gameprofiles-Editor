package ru.egor9814.app.a8gpe2.json;

import java.util.List;

/**
 * Created by egor9814 on 12.05.2016.
 */
class ObjectExpression implements Expression {

	private final List<String> keys;
	private final List<Expression> values;

	public ObjectExpression(List<String> keys, List<Expression> values) {
		this.keys = keys;
		this.values = values;
	}

	@Override
	public Object eval() {
		JSONObject object = new JSONObject();
		for(int i = 0; i < keys.size(); i++){
			object.put(keys.get(i), values.get(i).eval());
		}
		return object;
	}

	@Override
	public String toString() {
		return eval().toString();
	}
}
