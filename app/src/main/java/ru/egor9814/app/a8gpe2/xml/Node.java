package ru.egor9814.app.a8gpe2.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class Node {

	private List<Node> childs;
	private Node parent;

	private String name, text;
	private Map<String, Attribute> attributes;

	Node(Node parent, String name, Attribute... attributes){
		childs = new ArrayList<>();
		this.parent = parent;
		this.name = name;
		this.text = "";
		this.attributes = new HashMap<>();
		if(attributes != null) for(Attribute attr : attributes){
			this.attributes.put(attr.name, attr);
		}
	}

	public List<Node> getChilds() {
		return childs;
	}

	public Node getChild(String name){
		for(Node node : childs){
			if(node.getName().equals(name)) return node;
		}
		return null;
	}

	private Node addNode(Node node){
		childs.add(node);
		return this;
	}

	public Node removeNode(Node node){
		childs.remove(node);
		return node;
	}
	public Node removeNode(String name){
		return removeNode(getChild(name));
	}
	public Node removeNode(int position){
		return removeNode(childs.get(position));
	}

	public int getChildCount(){
		return childs.size();
	}


	public Node getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}


	public Map<String, Attribute> getAttributes() {
		return attributes;
	}

	public List<Attribute> getAttributesList(){
		List<Attribute> list = new ArrayList<>();
		if(attributes.size() == 0) return list;
		for(Map.Entry<String, Attribute> attr : attributes.entrySet()){
			list.add(attr.getValue());
		}
		return list;
	}

	public Attribute getAttribute(String name){
		if(attributes.containsKey(name)){
			return attributes.get(name);
		} else {
			return null;
		}
	}

	public Node addAttribute(Attribute attr){
		if(attributes.containsKey(attr.name)){
			removeAttribute(attr);
		}
		attributes.put(attr.name, attr);
		return this;
	}

	public Node removeAttribute(Attribute attr){
		return removeAttribute(attr.name);
	}
	public Node removeAttribute(String name){
		attributes.remove(name);
		return this;
	}

	public int getAttributesSize(){
		return getAttributes().size();
	}

	public Node clearAttributes(){
		attributes.clear();
		return this;
	}


	public Node setText(String text) {
		this.text = text;
		return this;
	}

	public String getText() {
		return text;
	}


	public Node newNode(String name, Attribute... attributes){
		Node child = new Node(this, name, attributes);
		addNode(child);
		return child;
	}

	private void clearNode(Node node){
		for(Node child : node.getChilds()){
			clearNode(child);
		}
		node.setText("");
		node.clearAttributes();
		node.childs.clear();
		node.parent = null;
	}
	public Node clearAll(){
		clearNode(this);
		return this;
	}


	public static Node createRootNode(String name, Attribute... attributes){
		return new Node(null, name, attributes);
	}

}
