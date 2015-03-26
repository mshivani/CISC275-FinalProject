package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public enum Screen {
	HOME,
	EMAIL1,
	EMAIL2,
	WEB,
	UDSIS,
	RSVP,
	TEXT1,
	TEXT2,
	NOTES,
	HELP;
	
	private SpriteBatch batch;
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public void changeScreen(SpriteBatch batch) {
		this.batch = batch;
		
		BitmapFont font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		
		Texture btnBack = new Texture("btn_back.png");
		batch.draw(btnBack, 25, 425);
		
		Texture btnNotes = new Texture("btn_notes.png");
		Texture btnWeb = new Texture("btn_web.png");
		Texture btnText = new Texture("btn_text.png");
		Texture btnEmail = new Texture("btn_email.png");
		Texture btnHelp = new Texture("btn_help.png");
		
		switch (this) {
		case EMAIL1:
			font.draw(batch, "Email - Screen 1", 300, 250);
			break;
		case EMAIL2:
			font.draw(batch, "Email - Screen 2", 300, 250);
			break;
		case HELP:
			font.draw(batch, "Help", 300, 250);
			break;
		case HOME:
			batch.draw(btnNotes, 75, 350);
			batch.draw(btnWeb, 175, 350);
			batch.draw(btnText, 275, 350);
			batch.draw(btnEmail, 375, 350);
			batch.draw(btnHelp, 475, 350);
			
			font.draw(batch, "Notes", 95, 325);
			font.draw(batch, "Web", 195, 325);
			font.draw(batch, "Texts", 295, 325);
			font.draw(batch, "Email", 395, 325);
			font.draw(batch, "Help", 495, 325);
			break;
		case NOTES:
			font.draw(batch, "Notes", 300, 250);
			break;
		case RSVP:
			font.draw(batch, "Web - RSVP", 300, 250);
			break;
		case TEXT1:
			font.draw(batch, "Text - Screen 1", 300, 250);
			break;
		case TEXT2:
			font.draw(batch, "Text - Screen 2", 300, 250);
			break;
		case UDSIS:
			font.draw(batch, "Web - UDSIS", 300, 250);
			break;
		case WEB:
			font.draw(batch, "Web", 300, 250);
			break;
		default:
			break;
		
		}
	}
}
