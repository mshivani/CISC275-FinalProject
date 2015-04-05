package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelpScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	String helpDisplay;
	
	public HelpScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		helpDisplay = taskSelector(g);
	}
	
	// Prototype assumes no current task. Final implementation will retrieve current task
	// from MyGdxGame and display as string. Help suggestions will be tied to "type" of task.
	// Game will have a list of tasks. Tasks will be marked as completed as they are finished.
	
	public String taskSelector(MyGdxGame g) {
		// if (g.getCurrentTask()) {
		//	return currentTask;
		//} else {
		return "No current task.";
		//}
	}
	
	
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			helpScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font.draw(batch, helpDisplay, width/3, height/2);
    	batch.draw(btnBack, 25, 425);
    	batch.end();
	}
	
	public void helpScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
			game.setScreen(game.welcome);
		}
	}
}
