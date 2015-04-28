package edu.udel.cisc275_15S.advisementadventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AddDropScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	Label welcome;
	BitmapFont font;
	Texture banner;
	Texture registration;
	TextField tf;
	Skin uiskin;
	Stage s;
	float height;
	float width;
	TextButton enter;
	Image btnB;
	ArrayList currentList;
	
	public AddDropScreen(MyGdxGame g) {
//		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
//		height = Gdx.graphics.getHeight();
//		width = Gdx.graphics.getWidth();
		this.game = g;
//		createTextField();
//		createEnter();
//		batch = new SpriteBatch();
//		banner = new Texture("schLogo.png");
//		registration = new Texture("Registration.png");
//		currentList = new ArrayList();
//		s = new Stage();
//		s.addActor(tf);
//		s.addActor(enter);
	}

	public void createTextField(){
		tf = new TextField("", uiskin);
		tf.setPosition(width/4, height/2);
		tf.setMessageText("Enter a course ID");
		tf.setWidth(width/2);
	}
	public void createEnter(){
		enter = new TextButton("Enter", uiskin);
		enter.setPosition(tf.getX()+tf.getWidth(), tf.getY());
		enter.setHeight(tf.getHeight());
		enter.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if((tf.getText().equals(""))){
				}
				else{
					currentList.add(tf.getText());
					tf.setText("");
					tf.setMessageText("Enter a course ID");
				}


				return true;
			}
		});
	}

	@Override
	public void show() {
		
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		createTextField();
		createEnter();
		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");
		registration = new Texture("Registration.png");
		currentList = new ArrayList();
		s = new Stage();
		s.addActor(tf);
		s.addActor(enter);
		createBackButton();
		Gdx.input.setInputProcessor(s);
	}
	
	private void createBackButton() {
		Texture btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				game.setScreen(game.udsis);
				return true;
			}
		});
		s.addActor(btnB);
	}
	

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(banner, 0, height-height/5.5f, width, height/5.5f);
	    batch.draw(registration, 25, height-(2*(height/5.64f)), width, height/6.5f);
		batch.end();
		s.draw();
	}
	
	@Override
	public void hide(){
		batch.dispose();
		s.dispose();
	}

}

