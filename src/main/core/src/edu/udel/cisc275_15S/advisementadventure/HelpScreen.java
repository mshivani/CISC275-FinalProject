package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class HelpScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	String helpDisplay;
	ArrayList<Task> tasklist;
	
	public HelpScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		this.tasklist = g.taskList;
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
		Gdx.gl.glClearColor(205/255f, 242/255f, 250/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(int i = 0; i < tasklist.size()/2; i++){
			if(tasklist.get(i).isCompleted()){
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
			else{
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
		}
		for(int i = tasklist.size()/2; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
			else{
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
		}
    	batch.draw(btnBack, (float)(width/25), (float) (height*.9));
    	for(int i = 0; i < tasklist.size(); i++){
    		font.draw(batch, "Task " + (i+1) + ": " + "blah blah blah", width*.1f, height*.6f - height*.05f*(i+1));
    	}
    	batch.end();
	}
	
	public void helpScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= width/25 && clickX <= (width/25 + btnBack.getWidth()) 
				&& clickY <= height-(height*.9) && clickY >= height-(height*.9)-btnBack.getHeight()) {
			game.setScreen(game.welcome);
		}
	}
}
