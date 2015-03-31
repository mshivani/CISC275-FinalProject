package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UDSIS_Screen extends ScreenAdapter {
	
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture udsisWelcome;
	Texture udsisAddDrop;
	Texture udsisSech;
	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	public UDSIS_Screen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		udsisWelcome = new Texture("udsisWelcome.png");
		udsisAddDrop = new Texture("udsisAddDrop.png");
		udsisSech = new Texture("udsisSech.png");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			udsisScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	batch.draw(btnBack, 25, 425);
    	batch.draw(udsisWelcome, width/4, height/1.2f, width/2, height/5);
    	batch.draw(udsisSech, width/3, height/2, width/3, height/6);
    	batch.draw(udsisAddDrop, width/3, height/3.2f, width/3, height/6);
    	batch.end();
    	
	}
	
	public void udsisScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		//System.out.println("Width: "+ width  + " height: " + height);
		//System.out.println("x: " + clickX + "y: " + clickY);
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100 && Gdx.input.justTouched()) {
			game.setScreen(game.web);
		}
		if (clickX >= width/2.88f && clickX <= width/1.54f && clickY >= height/2.72f && clickY <= height/2.14f && Gdx.input.justTouched()) {
			game.setScreen(game.schedule);
		}
		if (clickX >= width/2.88f && clickX <= width/1.54f && clickY >= height/1.79f && clickY <= height/1.51f && Gdx.input.justTouched()) {
			game.setScreen(game.addDrop);
		}
		
	}

}
