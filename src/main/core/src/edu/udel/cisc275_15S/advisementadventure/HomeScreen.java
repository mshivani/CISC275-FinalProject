package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HomeScreen extends ScreenAdapter{

	MyGdxGame game;
	SpriteBatch batch;
	BitmapFont font;
	Texture btnBack;
	Texture btnNotes;
	Texture btnWeb;
	Texture btnText;
	Texture btnEmail;
	Texture btnHelp;
	
	public HomeScreen(MyGdxGame g){
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		btnNotes = new Texture("btn_notes.png");
		btnWeb = new Texture("btn_web.png");
		btnText = new Texture("btn_text.png");
		btnEmail = new Texture("btn_email.png");
		btnHelp = new Texture("btn_help.png");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			homeScreenClick();
		}
		batch.begin();
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(btnBack, 25, 425);
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
		

		batch.end();
	}
	
	public void homeScreenClick() {
		//	stack.add(currentScreen);
			//System.out.println("X: " + Gdx.input.getX() + ", Y: "+ Gdx.input.getY());
			int clickX = Gdx.input.getX();
			int clickY = Gdx.input.getY();
			if (clickX >= 70 && clickX <= 150 && clickY >= 50 && clickY <= 180) {
				game.setScreen(game.notes);
			} else if (clickX >= 170 && clickX <= 250 && clickY >= 50
					&& clickY <= 180) {
				game.setScreen(game.web);
				
			} else if (clickX >= 270 && clickX <= 350 && clickY >= 50
					&& clickY <= 180) {
				game.setScreen(game.text);
				
			} else if (clickX >= 370 && clickX <= 450 && clickY >= 50
					&& clickY <= 180) {
				game.setScreen(new EmailScreen(game));
			//	dispose();
			} else if (clickX >= 470 && clickX <= 550 && clickY >= 50
					&& clickY <= 180) {
				game.setScreen(game.help);
			}

		}

}
