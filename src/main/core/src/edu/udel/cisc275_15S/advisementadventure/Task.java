package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.graphics.Texture;

public class Task {

	private Texture completedPic;
	private Texture uncompletedPic;
	private boolean completed;
	private boolean seen;
	private String description;
	
	String display;
	int tasknum;

	public Task(int num, String comPic, String uncomPic, String des) {
		tasknum = num;
		this.display = "";
		this.completed = false;
		this.seen = false;
		completedPic = new Texture(comPic);
		uncompletedPic = new Texture(uncomPic);
		description = des;
	}

	public Texture getCompletedPic() {
		return completedPic;
	}

	public Texture getUncompletedPic() {
		return uncompletedPic;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted() {
		completed = true;
	}

	public void setSeen() {
		seen = true;
	}

	public boolean isSeen() {
		return seen;
	}

	public String getDescription() {
		return description;
	}
}
