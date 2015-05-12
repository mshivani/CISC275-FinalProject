package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Question {
	String question;
	private Response response1;
	
	private Response response2;
	int wrongCount2;
	private Response response3;
	int wrongCount3;
	private Response response4;
	int wrongCount4;
	private Response response5;
	int wrongCount5;
	private int idkCount;

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
		return question;
	}
	public String updateFile(){
		String temp; 
		temp = question + "\n" + "Response 1: 1 \n" + "Response 2: " + wrongCount2 + "\n" + "Response 3: " + wrongCount3 +"\n" + "Response 4: " + wrongCount4 + "\n"
				+ "Response 5: " + wrongCount5 + "\n" + "I don't know: " + idkCount + "\n";
		System.out.println(temp);
		return temp;
	}
	public void updateWrong(String x){
		if(x.equals(response2.response))
			wrongCount2++;
		else if(x.equals(response3.response))
			wrongCount3++;
		else if(x.equals(response4.response))
			wrongCount4++;
		else if(x.equals(response5.response))
			wrongCount5++;
		else if(x.equals("I don't know"))
			idkCount++;
		
	}
	public ArrayList<String> getResponses(){
		boolean alreadyDone = true;
		ArrayList<String> x = new ArrayList<String>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		Random rand = new Random();
		System.out.println(response1.response);
		if(!this.response1.response.equals("empty")){
			
			x.add(this.response1.response);
			while(x.size() < 3){
				int temp = rand.nextInt(4);
				for(int b = 0; b<y.size();b++){
					if(temp==y.get(b)){
						alreadyDone = false;
					}					
				}
				if(alreadyDone == true){
					if(temp==0){
						x.add(this.response2.response);
						y.add(0);
					}
					else if(temp==1){
						x.add(this.response3.response);
						y.add(1);
					}
					else if(temp==2){
						x.add(this.response4.response);
						y.add(2);
					}
					else if(temp==3){
						x.add(this.response5.response);
						y.add(3);
					}
				}

			}

		}
		else{
		//	System.out.println("else statement hit");
			for(int i=0;i<5;i++){
				x.add("empty");
			}
		}
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
