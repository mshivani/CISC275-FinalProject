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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class RsvpScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	Texture banner;
	TextButton add;
	SelectBox sb;
	float height;
	float width;
	Array rsvpChoices;
	Stage s;
	Texture btnBack;
	Image btnB;
//<<<<<<< Updated upstream

//=======
//<<<<<<< HEAD
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;
//=======
	Label rsvpL;
//>>>>>>> origin/master
//>>>>>>> Stashed changes
	
	
	public RsvpScreen(MyGdxGame g){
		this.game = g;
		this.taskList = g.taskList;
		rsvpChoices = new Array();
		rsvpChoices.add(" ");
		rsvpChoices.add("Advisory Networking Night: March 9, 2016");
		rsvpChoices.add("A slot in the Writing Center");
		rsvpChoices.add("Getting the Flu Shot");
		rsvpChoices.add("J.P.Morgan Dining Etiquette: April 21, 2015");
		rsvpChoices.add("Meet the Firms Night: Sept 9, 2015");
		rsvpChoices.add("Law School Fair: Oct 14, 2015");
	}
	
	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				game.setScreen(game.web);
				return true;
			}
		});
	}
	
	
	public void createDropDown(){
		sb = new SelectBox(uiskin);
		sb.setWidth(width/2);
		sb.setItems(rsvpChoices);
		sb.setPosition(width/4, height/2f);
		sb.setMaxListCount(6);
	}
	
	
	public void createAdd(){
		add = new TextButton("Register", uiskin);
		add.setPosition(sb.getX()+sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb.getSelected() != null && sb.getSelected() != " "){	
					
					rsvpL.setText("Congrats! You have registered for: " + sb.getSelected());
					s.addActor(rsvpL);
					rsvpL.setColor(1, 0, 0, 1);
					rsvpL.setX(sb.getX() - sb.getX()/2);
					rsvpL.setY(sb.getY() - sb.getHeight());
					
					
					rsvpChoices.removeValue(sb.getSelected(), true);
					sb.setItems(rsvpChoices);
				}
				return true;
			}
		});
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
	public void show() {
		
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		rsvpL = new Label(" ", uiskin);
		
		createBackButton();
		createDropDown();
		createAdd();
		
		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");
		
		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(btnB);
		s.addActor(sb);
		s.addActor(add);
		createAchieveStar();
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		
	}
	
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(banner, 0, height-height/5.5f, width, height/5.5f);
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


