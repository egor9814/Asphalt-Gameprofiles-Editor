package ru.egor9814.app.a8gpe2.json;

/**
 * Created by egor9814 on 12.05.2016.
 */
enum TokenType {

	INTEGER, DOUBLE, TRUE, FALSE, TEXT,

	LBRACE, // {
	RBRACE, // }
	LBRACKET, // [
	RBRACKET, // ]
	COLON, // :
	COMMA, // ,

	EOF
}
