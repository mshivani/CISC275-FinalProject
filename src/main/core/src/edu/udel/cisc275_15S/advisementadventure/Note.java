package edu.udel.cisc275_15S.advisementadventure;

public class Note {
	String name;
	String text;
	
	public Note(){
		text = "";
	}
	
	public void setName(String n){
		this.name = n;
	}
	public void setText(String t){
		this.text = t;
	}
	public String getName(){
		return this.name;
	}
	public String getText(){
		return this.text;
	}
}
