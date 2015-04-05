package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Email2Screen extends ScreenAdapter {

	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont textFont;
	BitmapFont titleFont;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();

	public Email2Screen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		textFont = new BitmapFont();
		textFont.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			email2ScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(btnBack, 25, 425);
		textFont.draw(batch, "From: Sandra Carberry", 5, 390);
		textFont.draw(batch, "To: John Smith", 5, 375);
		textFont.draw(batch, "Add EGGG101 to Your Schedule", 5, 350);
		textFont.draw(batch, "March 21, 2015 at 12:15 PM", 5, 330);
		textFont.draw(batch, "Hi John,", 5, 300);
		textFont.draw(batch, "I am just reminding you that you need to add EGGG101 to your schedule. You can do this by ", 5, 285);
		textFont.draw(batch, "logging into UDSIS. Remeber that the deadline for free drop/add is Tuesday, September 15.", 5, 270);
		textFont.draw(batch, "Best,", 5, 255);
		textFont.draw(batch, "Prof. Carberry", 5, 240);
		batch.end();
	}

	public void email2ScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
			game.setScreen(game.email);
		}
	}

}