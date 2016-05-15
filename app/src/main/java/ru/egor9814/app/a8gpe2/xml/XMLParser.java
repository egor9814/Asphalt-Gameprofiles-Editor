package ru.egor9814.app.a8gpe2.xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class XMLParser {

	private Node doc, result, current;

	public interface OnXmlParseListener{
		void onStart();
		void onResult(Node result);
	}

	public void loadXml(XmlPullParser xml, OnXmlParseListener listener) throws XmlPullParserException, IOException {
		loop: while(true){
			switch(xml.getEventType()){
				case XmlPullParser.START_DOCUMENT:
					doc = Node.createRootNode("DOC");
					current = doc;
					listener.onStart();
					break;
				case XmlPullParser.END_DOCUMENT:
					result = doc.getChilds().get(0);
					doc.removeNode(result);
					doc = null;
					listener.onResult(result);
					break loop;

				case XmlPullParser.START_TAG:
					current = current.newNode(xml.getName());
					if(xml.getAttributeCount() > 0) for(int i = 0; i < xml.getAttributeCount(); i++){
						current.addAttribute(new Attribute(xml.getAttributeName(i), xml.getAttributeValue(i)));
					}
					break;
				case XmlPullParser.TEXT:
					current.setText(xml.getText());
					break;
				case XmlPullParser.END_TAG:
					current = current.getParent();
					break;
			}
			xml.next();
		}
	}
}
