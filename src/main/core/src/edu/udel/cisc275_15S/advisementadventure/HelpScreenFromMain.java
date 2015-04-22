package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HelpScreenFromMain extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;

	Texture btnBack;
	Texture trans;
	Texture glass;
	Image btnB;

	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	String helpDisplay;
	ArrayList<Task> tasklist;
	Texture star;
	float speedx;
	float speedy;


	public HelpScreenFromMain(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_Back.png");
		this.tasklist = g.taskList;
		star = new Texture("star.png");

	}

	public void render(float delta){



		//get coordinates of mouse
		batch.begin();
		Gdx.gl.glClearColor(205/255f, 242/255f, 250/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//width = Gdx.graphics.getWidth();
		//height = Gdx.graphics.getHeight();
		batch.draw(btnBack, (float)(width/25), (float) (height*.9f));

		for(int i = 0; i < tasklist.size()/2; i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
		}
		for(int i = tasklist.size()/2; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
		}


		//write tasks
		for(int i = 0; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				batch.draw(star, width*.06f, height*.57f - height*.05f*(i+1), 
						width*.03f, width*.03f);
				font.setColor(Color.BLACK);
				font.draw(batch, "Task " + (i+1) + ": " + tasklist.get(i).getDescription(), width*.1f, height*.6f - height*.05f*(i+1));

			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				font.draw(batch, "Task " + (i+1) + ": " + tasklist.get(i).getDescription(), width*.1f, height*.6f - height*.05f*(i+1));
			}

		}





		batch.end();
		if(Gdx.input.isTouched()){
			helpFromMainClick();
		}
	}
	
	public void helpFromMainClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= width/25 && clickX <= (width/25 + btnBack.getWidth()) 
				&& clickY <= height-(height*.9) && clickY >= height-(height*.9)-btnBack.getHeight()) {
			game.setScreen(game.welcome);
		}
	}
}
