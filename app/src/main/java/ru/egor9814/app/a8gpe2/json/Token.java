package ru.egor9814.app.a8gpe2.json;

/**
 * Created by egor9814 on 12.05.2016.
 */
final class Token {

	private final TokenType type;
	private final String text;
	private final int startPos, endPos, row, col;

	public Token(TokenType type, String text, int startPos, int endPos, int row, int col) {
		this.type = type;
		this.text = text;
		this.startPos = startPos;
		this.endPos = endPos;
		this.row = row;
		this.col = col;
	}

	public TokenType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getStartPos() {
		return startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public String getPosition(){
		return "[" + row + ":" + col + "]";
	}

	@Override
	public String toString() {
		return type.name() + " " + getPosition() + " " + text;
	}
}
