package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Question {
	String question;
	private Response response1;
	private Response response2;
	private Response response3;
	private Response response4;
	private Response response5;

	public Question(String a, String b, String c, String d, String e, String f){
		this.question = a;
		this.response1 = new Response(b);
		this.response2 = new Response(c);
		this.response3 = new Response(d);
		this.response4 = new Response(e);
		this.response5 = new Response(f);
	}
	public Response getCorrectResponse(){
		return response1;
	}
	@Override 
	public String toString(){
		return "question is " + question + " response 1 is " +  response1.response + " response 2 is " + response2.response
				+ " response 3 is " + response3.response + " response 4 is " + response4.response + " response 5 is " + response5.response;
	}
	public ArrayList<String> getResponses(){
		ArrayList<String> x = new ArrayList<String>();
		Random rand = new Random();
		x.add(this.response1.response);
		for(int i =0;i<3;i++){
			int temp = rand.nextInt(4);
			if(temp==0)
				x.add(this.response2.response);
			else if(temp==1)
				x.add(this.response3.response);
			else if(temp==2)
				x.add(this.response4.response);
			else if(temp==3)
				x.add(this.response5.response);
		}
		Collections.shuffle(x);
		
		return x;

	}
	public Response getResponse5() {
		return response5;
	}

	public void setResponse5(Response response5) {
		this.response5 = response5;
	}

	public Response getResponse4() {
		return response4;
	}

	public void setResponse4(Response response4) {
		this.response4 = response4;
	}

	public Response getResponse3() {
		return response3;
	}

	public void setResponse3(Response response3) {
		this.response3 = response3;
	}

	public Response getResponse2() {
		return response2;
	}

	public void setResponse2(Response response2) {
		this.response2 = response2;
	}

	public Response getResponse1() {
		return response1;
	}

	public void setResponse1(Response response1) {
		this.response1 = response1;
	}


}
