package edu.udel.cisc275_15S.advisementadventure;

public class Task {
	TaskType type;
	String display;
	boolean completed;
	
	public Task(TaskType type) {
		this.type = type;
		this.display = "";
		this.completed = false;
	}
	
	public void helpDisplayer(TaskType type) {
		switch(type) {
			case RSVP:
				display = "Go the web app to RSVP for your event.";
				break;
				
			case DROPADD:
				display = "Head to the web app and drop or add a class on UDSIS.";
				break;
		}
	}

}
