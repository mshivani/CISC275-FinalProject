package edu.udel.cisc275_15S.advisementadventure;

import java.io.BufferedReader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
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

	public EmailFullScreen(MyGdxGame g, Email e) {
		this.game = g;
		textFont = new BitmapFont();
		textFont.setColor(0, 0, 0, 1);
		this.currentEmail = e;
	}
	
	@Override
	public void show() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage = new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		createBackButton();
		parseEmails();
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
	
	public void parseEmails() {
		emailList = new ArrayList<Email>();
		try {
			FileHandle fileReader = Gdx.files.internal("Emails.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader.reader());
			String line = bufferedReader.readLine();
			ArrayList<String> lines = new ArrayList<String>();
			int amountOfEmails = Integer.parseInt(line);
			for (int i = 0; i < amountOfEmails; i++) {
				for (int j = 1; j < 8; j++) {
					line = bufferedReader.readLine();
					lines.add(line);
				}
				Email e = new Email(i, lines.get(0), lines.get(1), lines.get(2),
									lines.get(3), lines.get(4), lines.get(5), lines.get(6));
				emailList.add(e);
				lines.clear();
			}
		//	fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
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