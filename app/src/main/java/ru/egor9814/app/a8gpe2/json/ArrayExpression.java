package ru.egor9814.app.a8gpe2.json;

import java.util.List;

/**
 * Created by egor9814 on 12.05.2016.
 */
class ArrayExpression implements Expression{

	private final List<Expression> values;

	public ArrayExpression(List<Expression> values) {
		this.values = values;
	}

	@Override
	public Object eval() {
		JSONArray array = new JSONArray();
		for(Expression e : values){
			array.put(e.eval());
		}
		return array;
	}

	@Override
	public String toString() {
		return eval().toString();
	}
}
