package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public enum Screen {
	HOME, EMAIL1, EMAIL2, WEB, UDSIS, RSVP, TEXT1, TEXT2, NOTES, HELP;

	private SpriteBatch batch;

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
}
