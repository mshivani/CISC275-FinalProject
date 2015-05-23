package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.graphics.Texture;

public class Task {
	TaskType type;
	String display;
	int tasknum;
	Texture completedPic;
	Texture uncompletedPic;
	boolean completed;
	boolean seen;
	String description;

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
