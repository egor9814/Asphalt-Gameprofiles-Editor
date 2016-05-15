package ru.egor9814.app.a8gpe2.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by egor9814 on 12.05.2016.
 */
class Lexer {

	private static final String OPERATOR_CHARS = ":,.{}[]";
	private static final Map<String, TokenType> OPERATORS;
	static {
		OPERATORS = new HashMap<>();
		OPERATORS.put(":", TokenType.COLON);
		OPERATORS.put(",", TokenType.COMMA);
		OPERATORS.put("{", TokenType.LBRACE);
		OPERATORS.put("}", TokenType.RBRACE);
		OPERATORS.put("[", TokenType.LBRACKET);
		OPERATORS.put("]", TokenType.RBRACKET);
	}

	private final String input;
	private final int length;

	private final List<Token> tokens;
	private final StringBuilder buffer;

	private int pos, row, col;

	public Lexer(String input) {
		this.input = input;
		length = input.length();

		tokens = new ArrayList<>();
		buffer = new StringBuilder();
		row = col = 1;
		pos = 0;
	}

	public List<Token> tokenize(){
		while(pos < length){
			final char current = peek(0);
			if(Character.isDigit(current)) tokenizeNumber();
			else if(Character.isJavaIdentifierStart(current)) tokenizeWord();
			else if(current == '"') tokenizeText();
			else if(OPERATOR_CHARS.indexOf(current) != -1) tokenizeOperator();
			else next();
		}
		return tokens;
	}

	private void tokenizeNumber(){
		clearBuffer();
		char current = peek(0);
		int start = pos;
		while(true){
			if(current == '.'){
				if(buffer.indexOf(".") != -1) throw error("Invalid float number");
			} else if(!Character.isDigit(current)){
				break;
			}
			buffer.append(current);
			current = next();
		}
		addToken(buffer.indexOf(".") == -1 ? TokenType.INTEGER : TokenType.DOUBLE,
				buffer.toString(), start, pos);
	}

	private void tokenizeOperator(){
		char current = peek(0);
		clearBuffer();
		int start = pos;
		while (true) {
			final String text = buffer.toString();
			if (!OPERATORS.containsKey(text + current) && !text.isEmpty()) {
				addToken(OPERATORS.get(text), start, pos);
				return;
			}
			buffer.append(current);
			current = next();
		}
	}

	private void tokenizeWord(){
		int start = pos;
		clearBuffer();
		char current = peek(0);
		while (true) {
			if (!Character.isLetterOrDigit(current) && (current != '_')  && (current != '$')) {
				break;
			}
			buffer.append(current);
			current = next();
		}

		final String word = buffer.toString();
		switch(word){
			case "true":
				addToken(TokenType.TRUE, start, pos);
				break;
			case "false":
				addToken(TokenType.FALSE, start, pos);
				break;
			default:
				throw error("Unknown type: " + word);
		}
	}

	private void tokenizeText(){
		int start = pos;
		next();// skip "
		clearBuffer();
		char current = peek(0);
		while (true) {
			if (current == '\0') throw error("Reached end of file while parsing text string.");
			if (current == '\\') {
				current = next();
				switch (current) {
					case '"': current = next(); buffer.append('"'); continue;
					case '0': current = next(); buffer.append('\0'); continue;
					case 'b': current = next(); buffer.append('\b'); continue;
					case 'f': current = next(); buffer.append('\f'); continue;
					case 'n': current = next(); buffer.append('\n'); continue;
					case 'r': current = next(); buffer.append('\r'); continue;
					case 't': current = next(); buffer.append('\t'); continue;
				}
				buffer.append('\\');
				continue;
			}
			if (current == '"') break;
			buffer.append(current);
			current = next();
		}
		next(); // skip closing "

		String text = buffer.toString();
		addToken(TokenType.TEXT, text, start, pos);
	}

	private void clearBuffer(){
		buffer.setLength(0);
	}

	private char peek(int relativePosition){
		final int position = pos + relativePosition;
		if(position >= length) return '\0';
		return input.charAt(position);
	}

	private char next(){
		pos++;
		final char result = peek(0);
		if(result == '\n'){
			row++;
			col = 1;
		} else col++;
		return result;
	}

	private void addToken(TokenType type, String text, int start, int end){
		tokens.add(new Token(type, text, start, end, row, col));
	}
	private void addToken(TokenType type, int start, int end){
		addToken(type, "", start, end);
	}

	private IllegalStateException error(String text){
		return new IllegalStateException("["+row+":"+col+"] " + text);
	}
}
