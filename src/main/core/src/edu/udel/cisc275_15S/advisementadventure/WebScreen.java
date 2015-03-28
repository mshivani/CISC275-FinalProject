package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class WebScreen extends ScreenAdapter{
	MyGdxGame game;
	public WebScreen(MyGdxGame g) {
		this.game = g;
	}
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
