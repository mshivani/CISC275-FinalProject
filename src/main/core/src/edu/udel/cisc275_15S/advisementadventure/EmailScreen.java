package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EmailScreen extends ScreenAdapter{

	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont font;
	
	public EmailScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
	}

	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			emailScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font.draw(batch, "email", 150, 150);
        
    	batch.draw(btnBack, 25, 425);
    	batch.end();
	}
	
	public void emailScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
			game.setScreen(game.welcome);
		}
	}

}
