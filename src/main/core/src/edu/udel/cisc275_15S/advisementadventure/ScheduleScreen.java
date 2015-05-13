package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScheduleScreen extends ScreenAdapter{
	
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture Schedule;
	Texture SchTitle;
	BitmapFont font;
	BitmapFont font2;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;
	Skin uiskin;
	Stage s;
	
	Texture home;
	Image btnHome;
	
	public ScheduleScreen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font2.setColor(1, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		Schedule = new Texture("schLogo.png");
		SchTitle = new Texture("schTitle.png");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			SechScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(Schedule, 0, height-height/5.5f, width, height/5.5f);
        batch.draw(SchTitle, 25, height-(2*(height/5.64f)), width, height/6.5f);
    	batch.draw(btnBack, 25, 425);
      	float tempWidth = width/2.5f;
        float tempHeight = height/1.3f - 50;
      	for(int i = 0; i < game.addDrop.currentList.size(); i++){
             font.draw(batch,  (CharSequence) game.addDrop.currentList.get(i), tempWidth, tempHeight);
             tempHeight -= 30;
        } 
      	if(game.addDrop.currentList.isEmpty()){
      		font2.draw(batch, "You Have No Scheduled Classes", width/3.2f, height/2f);
      	}
    	batch.end();
    	s.draw();
    	s.act();
	}
	
public void show() {
		game.previousScreen = this;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		batch = new SpriteBatch();
		
		s = new Stage();
	
		createAchieveStar();
		createHomeButton();
		
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		Gdx.input.setInputProcessor(s);
	}
	
public void createAchieveStar() {
	starT = new Texture("star.png");
	star = new Image(starT);
	num = 0;
	boolean create = false;
	for (int i = 0; i < taskList.size(); i++) {
		if (taskList.get(i).isCompleted() && !taskList.get(i).isSeen()) {
			create = true;
			num++;
		}
	}
	
	if (create) {
		star.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.help);
				return true;
			}
		});
		star.setWidth(80);
		star.setHeight(80);
		star.setX(width - star.getWidth());
		star.setY(height - star.getHeight());
		
		star.addAction(Actions.forever(Actions.sequence(Actions.sizeTo(65, 65, .7f), Actions.sizeTo(80, 80, .7f))));
		star.addAction(Actions.forever(Actions.sequence(
				Actions.moveTo(width-65, height-65, .7f), 
				Actions.moveTo(width-80, height-80, .7f))));
	
		s.addActor(star);
		la = new Label(num + "", uiskin);
		la.setX(width - star.getWidth()+ star.getWidth() * .44f);
		la.setY(height - star.getHeight() + star.getHeight() * .36f);
		la.addAction(Actions.forever(Actions.sequence(
				Actions.moveTo(la.getX()+7, la.getY()+7, .7f), 
				Actions.moveTo(la.getX(), la.getY(), .7f))));
		la.setColor(Color.BLACK);
		la.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.help);
				return true;
			}
		});
		s.addActor(la);
	}

}
	
	public void createHomeButton() {
		home = new Texture("home-icon.png");
		btnHome = new Image(home);
		btnHome.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(btnHome);
	}
	
	public void SechScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
//		System.out.println("x: " + clickX + "\n");
//		System.out.println("x: " + clickY + "\n");
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100 && Gdx.input.justTouched()) {
			game.setScreen(game.udsis);
		}
	}

}
