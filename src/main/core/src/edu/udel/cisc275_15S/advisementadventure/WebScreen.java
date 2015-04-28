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
		//		batch = new SpriteBatch();
		//		font = new BitmapFont();
		//		font.setColor(0, 0, 0, 1);
		//		temp1 = new TextureRegion();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		//		btnBack = new Texture("btn_back.png");
		//		rsvp = new Texture("rsvp.png");
		//		btnUDSIS = new Texture("UDSIS.jpg");
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
		//rsvpL.setX(0);
		//rsvpL.setY(0);
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
		if(Gdx.input.isTouched()){
			//webScreenClick();
		}
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1.18f, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//		batch.begin();
		//		Gdx.gl.glClearColor(1, 1.18f, 0, 1);
		//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//    	batch.draw(btnBack, 25, 425);
		//    	batch.draw(btnUDSIS, width/8, height/4,  width/2.6f, height/1.6f);
		//    	font.draw(batch, "UDSIS", width/4, height/4.8f);
		//    	batch.draw(rsvp, width/1.8f, height/4,  width/2.6f, height/1.6f);
		//    	font.draw(batch, "RSVP", width/1.45f, height/4.8f);
		//    	batch.end();
		s.draw();
		s.act();

		//		batch.begin();		
		//		batch.draw(temp1, 0, 0, height, width);
		//		batch.end();
	}

//	public void webScreenClick() {
//		int clickX = Gdx.input.getX();
//		int clickY = Gdx.input.getY();
//		//System.out.println("Width: "+ width  + " height: " + height);
//		//System.out.println("x: " + clickX + "y: " + clickY);
//		if (clickX >= 0 && clickX <= 80 && clickY >= 0 && clickY <= 80 && Gdx.input.justTouched()) {
//			game.setScreen(game.welcome);
//		}
//		if(clickX >= width/7.8f && clickX <= width/1.98f && clickY >= height/7.5f && clickY <= 354 && Gdx.input.justTouched()){
//			game.setScreen(game.udsis);
//		}
//		if(clickX >= width/1.79f && clickX <= width/1.07f && clickY >= height/7.5f && clickY <= 354 && Gdx.input.justTouched()){
//			game.setScreen(game.rsvp);
//		}
//	}
	@Override
	public void resize(int x, int y){
		this.show();
	}
}


