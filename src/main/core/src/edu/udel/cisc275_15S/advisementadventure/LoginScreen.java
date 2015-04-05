package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoginScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	BitmapFont font;

	public LoginScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();	
	}

	@Override
	public void show() {
		//TextInputListener listener = new TextInputListener();
		Gdx.input.getTextInput(new TextInputListener(){

			@Override
			public void input(String text) {
				if (text.isEmpty()){
					game.setScreen(game.login);
				}
				else {
					game.setScreen(game.welcome);
				}

			}

			@Override
			public void canceled() {
				game.setScreen(game.login);

			}

		}, "Enter Student ID Number", null, "ID");
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		font.draw(batch, "login", 200, 200);

		batch.end();
		if(Gdx.input.justTouched()){
			game.setScreen(new LoginScreen(game));

		}
	}


}
