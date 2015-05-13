package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WebScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	Texture btnBack;
	Image btnB;
	Texture btnUDSIS;
	Image btnUD;
	Texture rsvp;
	Image btnRSVP;
	BitmapFont font;
	Stage s;   
	float width;
	float height;
	TextureRegion temp1 = new TextureRegion();
	
	Image star;
	Texture starT;
	ArrayList<Task> taskList;
	Label la;
	int num;


	public WebScreen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
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
	public void show(){
		game.previousScreen = this;
		s = new Stage();
		//Label rsvpL = new Label("RSVP", uiskin);
		//Label udL = new Label("UDSIS", uiskin);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.welcome);
				return true;
			}
		});
		btnB.setX(0);
		btnB.setY(height - btnB.getHeight());
		rsvp = new Texture("web-rsvp.jpg");
		btnRSVP = new Image(rsvp);
		btnRSVP.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.rsvp);
				return true;
			}
		});
		btnRSVP.setX(width/12);
		btnRSVP.setY((height/4) - (btnRSVP.getHeight()/10));
		// btnRSVP.setBounds(width/8, height/4,  width/2.6f, height/1.6f);
		btnUDSIS = new Texture("udsis-page.png");
		btnUD = new Image(btnUDSIS);
		btnUD.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.udsis);
				return true;

			}
		});
		btnUD.setX(width/9 + btnUD.getWidth());
		btnUD.setY((height/4) - (btnUD.getHeight()/10));
		// btnUD.setBounds(width/1.8f, height/4,  width/2.6f, height/1.6f);
//		rsvpL.setX(btnRSVP.getX()+ btnRSVP.getWidth()/2 - rsvpL.getWidth());
//		rsvpL.setY(btnRSVP.getY() - rsvpL.getHeight());
//		udL.setX(btnUD.getX()+ btnUD.getWidth()/2 - udL.getWidth());
//		udL.setY(btnUD.getY() - udL.getHeight());
//		rsvpL.setColor(Color.BLACK);
//		udL.setColor(Color.BLACK);
//		s.addActor(udL);
//		s.addActor(rsvpL);
		s.addActor(btnUD);
		s.addActor(btnRSVP);
		s.addActor(btnB);
		createAchieveStar();
		Gdx.input.setInputProcessor(s);
	}
	@Override
	public void render(float delta){
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();
	}

	@Override
	public void resize(int x, int y){
		this.show();
	}
}
