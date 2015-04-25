package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WebScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture btnUDSIS;
	Texture rsvp;
	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	
	public WebScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		rsvp = new Texture("rsvp.png");
		btnUDSIS = new Texture("UDSIS.jpg");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			webScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1.18f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	batch.draw(btnBack, 25, 425);
    	batch.draw(btnUDSIS, width/8, height/4,  width/2.6f, height/1.6f);
    	font.draw(batch, "UDSIS", width/4, height/4.8f);
    	batch.draw(rsvp, width/1.8f, height/4,  width/2.6f, height/1.6f);
    	font.draw(batch, "RSVP", width/1.45f, height/4.8f);
    	batch.end();
	}
	
	public void webScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		//System.out.println("Width: "+ width  + " height: " + height);
		//System.out.println("x: " + clickX + "y: " + clickY);
		if (clickX >= 0 && clickX <= 80 && clickY >= 0 && clickY <= 80 && Gdx.input.justTouched()) {
			game.setScreen(game.welcome);
		}
		if(clickX >= width/7.8f && clickX <= width/1.98f && clickY >= height/7.5f && clickY <= 354 && Gdx.input.justTouched()){
			game.setScreen(game.udsis);
		}
		if(clickX >= width/1.79f && clickX <= width/1.07f && clickY >= height/7.5f && clickY <= 354 && Gdx.input.justTouched()){
			game.setScreen(game.rsvp);
		}
	}
	
}

