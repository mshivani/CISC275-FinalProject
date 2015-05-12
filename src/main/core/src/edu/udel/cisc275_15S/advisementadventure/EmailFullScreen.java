package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EmailFullScreen extends ScreenAdapter {

	MyGdxGame game;
	BitmapFont textFont;
	BitmapFont titleFont;
	float screenWidth;
	float screenHeight;
	Skin uiskin;
	ArrayList<Task> taskList;
	Stage stage;
	ArrayList<Email> emailList;
	Email currentEmail;
	Image btnB;
	Texture starT;
	Image star;
	Label la;
	int num;
	

	public EmailFullScreen(MyGdxGame g, Email e) {
		this.game = g;
		textFont = new BitmapFont();
		textFont.setColor(0, 0, 0, 1);
		this.currentEmail = e;
		this.taskList = g.taskList;
		Texture starT = new Texture("star.png");
		star = new Image(starT);
	}
	
	@Override
	public void show() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage = new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		this.emailList = game.getEmailList();
		createBackButton();
		createAchieveStar();
		if (currentEmail != null) {
			createEmail();
		}
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		stage.act();

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
			star.setX(screenWidth - star.getWidth());
			star.setY(screenHeight - star.getHeight());
			stage.addActor(star);
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
			stage.addActor(la);
		}
		
	}
	
	private void createBackButton() {
		Texture btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(screenHeight - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				game.setScreen(game.email);
				return true;
			}
		});
		stage.addActor(btnB);
	}

	private void createEmail() {
		int numberInList = currentEmail.getNumberInList();
		Email fullEmail = emailList.get(numberInList);
		Label email = new Label(fullEmail.toString(), uiskin);
		email.setX(EmailListScreen.emailLabelMargin);
		email.setY(screenHeight - btnB.getHeight() - email.getHeight() - MyGdxGame.btnBackMargin - EmailListScreen.emailLabelMargin);
		email.setColor(Color.BLACK);
		email.setWidth(screenWidth - 20);
		email.setWrap(true);
		stage.addActor(email);
	}

	public void setCurrentEmail(Email currentEmail) {
		this.currentEmail = currentEmail;
	}
	
}