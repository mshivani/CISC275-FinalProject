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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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


	boolean shrink;

	
	Texture home;
	Image btnHome;


	public EmailFullScreen(MyGdxGame g, Email e) {
		this.game = g;
		textFont = new BitmapFont();
		textFont.setColor(0, 0, 0, 1);
		this.currentEmail = e;
		this.taskList = g.taskList;
	
		shrink = false;
		starT = new Texture("star.png");
		star = new Image(starT);
		
	}

	@Override
	public void show() {
		game.previousScreen = this;
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

	public void createAchieveStar() {
		
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
			star.setX(screenWidth - star.getWidth());
			star.setY(screenHeight - star.getHeight());
			
			star.addAction(Actions.forever(Actions.sequence(Actions.sizeTo(65, 65, .7f), Actions.sizeTo(80, 80, .7f))));
			star.addAction(Actions.forever(Actions.sequence(
					Actions.moveTo(screenWidth-65, screenHeight-65, .7f), 
					Actions.moveTo(screenWidth-80, screenHeight-80, .7f))));
		
			stage.addActor(star);
			la = new Label(num + "", uiskin);
			la.setX(screenWidth - star.getWidth()+ star.getWidth() * .44f);
			la.setY(screenHeight - star.getHeight() + star.getHeight() * .36f);
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
			stage.addActor(la);
		}

	}

	private void createBackButton() {
		Texture btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(screenHeight - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				star.clearActions();
				la.clearActions();
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
		email.setWrap(true);
		email.setWidth(screenWidth - 20);
		email.setX(EmailListScreen.emailLabelMargin);
		email.setY(screenHeight - btnB.getHeight() - email.getHeight() - 2*MyGdxGame.btnBackMargin - 2*EmailListScreen.emailLabelMargin);
		email.setColor(Color.BLACK);
		stage.addActor(email);
	}

	public void setCurrentEmail(Email currentEmail) {
		this.currentEmail = currentEmail;
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
		stage.addActor(btnHome);
	}

}