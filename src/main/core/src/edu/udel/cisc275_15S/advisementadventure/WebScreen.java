package edu.udel.cisc275_15S.advisementadventure;

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


	public WebScreen(MyGdxGame g) {
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	
	
	@Override
	public void show(){
		s = new Stage();
		Label rsvpL = new Label("RSVP", uiskin);
		Label udL = new Label("UDSIS", uiskin);
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
		rsvp = new Texture("rsvp.png");
		btnRSVP = new Image(rsvp);
		btnRSVP.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.rsvp);
				return true;
			}
		});
		btnRSVP.setBounds(width/8, height/4,  width/2.6f, height/1.6f);
		btnUDSIS = new Texture("UDSIS.jpg");
		btnUD = new Image(btnUDSIS);
		btnUD.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.udsis);
				return true;

			}
		});
		btnUD.setBounds(width/1.8f, height/4,  width/2.6f, height/1.6f);
		rsvpL.setX(btnRSVP.getX()+ btnRSVP.getWidth()/2 - rsvpL.getWidth());
		rsvpL.setY(btnRSVP.getY() - rsvpL.getHeight());
		udL.setX(btnUD.getX()+ btnUD.getWidth()/2 - udL.getWidth());
		udL.setY(btnUD.getY() - udL.getHeight());
		rsvpL.setColor(Color.BLACK);
		udL.setColor(Color.BLACK);
		s.addActor(udL);
		s.addActor(rsvpL);
		s.addActor(btnUD);
		s.addActor(btnRSVP);
		s.addActor(btnB);
		Gdx.input.setInputProcessor(s);
	}
	@Override
	public void render(float delta){
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1.18f, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();
	}

	@Override
	public void resize(int x, int y){
		this.show();
	}
}
