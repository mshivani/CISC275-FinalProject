package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

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
				if (text.isEmpty() || text.equals("ID")){
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

		}, "Enter Student ID Number", "ID", null);
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		font.draw(batch, "login", 200, 200);
		if(Gdx.input.justTouched()){
			game.setScreen(new HomeScreen(game));
		}
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}


}
