package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DegreeAuditScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	float height;
	float width;
	Stage s;
	Texture btnBack;
	Image btnB;
	Texture bg;
	//Texture activeCourses;
	Skin uiskin;
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;
	
	Texture home;
	Image btnHome;
	
	public DegreeAuditScreen(MyGdxGame g){
		this.game = g;
		this.taskList = g.taskList;
	}
	
	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				game.setScreen(game.udsis);
				return true;
			}
		});
	}
	
	private void createHomeButton() {
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
	
	@Override
	public void show() {
		game.previousScreen = this;
		if(game.currentTask==4){
			game.currentTask2 = -1;
			game.currText=1;
			game.currentTask=5;
			System.out.println("hit it audit");
		}
		bg = new Texture("univReq.png");
		//activeCourses = new Texture("activeCourses.png");
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		createBackButton();
		
		batch = new SpriteBatch();
		
		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(btnB);
		createAchieveStar();
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		createHomeButton();
		
	}
	
	public void createAchieveStar(){
		starT = new Texture("star.png");
		star = new Image(starT);
		num = 0;
		boolean create = false;
		for(int i = 0; i < taskList.size(); i++){
			if(taskList.get(i).isCompleted() && !taskList.get(i).isSeen()){
				create = true;
				num++;
			}
		}
	
		if(create){
			star.addListener(new ClickListener(){
				public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
					game.setScreen(game.help);			
					return true;
				}
			});
			star.setWidth(80);
			star.setHeight(80);
			star.setX(width - star.getWidth());
			star.setY(height - star.getHeight());
			s.addActor(star);
			la = new Label(num+"", uiskin);
			la.setX(star.getX()+star.getWidth()*.44f);
			la.setY(star.getY()+star.getHeight()*.36f);
			la.setColor(Color.BLACK);
			la.addListener(new ClickListener(){
				public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
					game.setScreen(game.help);			
					return true;
				}
			});
			s.addActor(la);
		}
		
	}
	
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(bg, btnB.getWidth()+10, 0, width, height);
		//batch.draw(activeCourses, 0, btnB.getWidth()+10+bg.getWidth());
		
		batch.end();
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		s.draw();
	}
	
	@Override
	public void hide(){
		batch.dispose();
		s.dispose();
	}
	
}

