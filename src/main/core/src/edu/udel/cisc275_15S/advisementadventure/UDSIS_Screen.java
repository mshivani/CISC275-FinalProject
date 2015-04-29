package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UDSIS_Screen extends ScreenAdapter {
	
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	
	Texture back;
	Image btnBack;
	
	Texture udBanner;
	
	Texture ClassSch;
	Image btnClassSch;
	
	Texture addDrop;
	Image btnAddDrop;
	
	Texture major;
	Image btnMajor;
	
	BitmapFont font;
	Stage s;
	float width;
	float height;
	
	public UDSIS_Screen(MyGdxGame g){
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	
	public void show(){
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		batch  = new SpriteBatch();
		
		s = new Stage();
		
		udBanner = new Texture("UDsisBanner.png");
		
		back = new Texture("btn_back.png");
		btnBack = new Image(back);
		btnBack.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.web);
				return true;
			}
		});
		btnBack.setX(0);
		btnBack.setY(height - btnBack.getHeight());
		s.addActor(btnBack);
		
		
		ClassSch = new Texture("classSchButton.png");
		btnClassSch = new Image(ClassSch);
		btnClassSch.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.schedule);
				return true;
			}
		});
		btnClassSch.setBounds(width/2.7f, height/1.8f,  width/3.6f, height/5.5f);
		s.addActor(btnClassSch);
		     
		
		addDrop = new Texture("RegistrationDropAddButton.png");
		btnAddDrop = new Image(addDrop);
		btnAddDrop.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.addDrop);
				return true;
			}
		});
		btnAddDrop.setBounds(width/2.7f, height/1.8f - btnClassSch.getHeight(),  width/3f, height/5.5f);
		s.addActor(btnAddDrop);
		
		Gdx.input.setInputProcessor(s);
	}
	
	@Override
	public void render(float delta){
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(udBanner, 0, height-height/5.5f, width, height/5.5f);
		batch.end();
		s.act();
		s.draw();
	}
	
	@Override
	public void resize(int x, int y){
		this.show();
	}
	
	

}
