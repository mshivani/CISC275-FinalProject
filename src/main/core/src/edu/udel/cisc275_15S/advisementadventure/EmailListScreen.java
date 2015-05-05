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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EmailListScreen extends ScreenAdapter {

	public static final int inboxTitleMargin = 10;
	public static final int emailLabelMargin = 10;
	public static float emailLabelWidth;

	MyGdxGame game;
	BitmapFont textFont;
	BitmapFont titleFont;
	Label inbox;
	Label email;
	Image btnB;
	Skin uiskin;
	float screenWidth;
	float screenHeight;
	ArrayList<Task> taskList;
	Stage stage;
	ArrayList<Email> emailList;

	public EmailListScreen(MyGdxGame g) {
		this.game = g;
		this.textFont = new BitmapFont();
		this.textFont.setColor(0, 0, 0, 1);
		this.titleFont = new BitmapFont();
		this.titleFont.setScale(2);
		this.titleFont.setColor(0, 0, 0, 1);
		this.taskList = g.taskList;
		emailList = new ArrayList<Email>();
		parseEmails();

	}

	@Override
	public void hide() {
		stage.dispose();
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		stage.act();
	}

	@Override
	public void show() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage = new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		createTitle();
		createBackButton();
		createEmail();
		Gdx.input.setInputProcessor(stage);
	}

	private void createTitle() {
		inbox = new Label("Inbox", uiskin);
		inbox.setX(screenWidth / 2 - inbox.getWidth());
		inbox.setY(screenHeight - inbox.getHeight() - inboxTitleMargin);
		inbox.setFontScale(2);
		inbox.setColor(Color.BLACK);
		stage.addActor(inbox);
	}

	private void createBackButton() {
		Texture btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(screenHeight - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.welcome);
				return true;
			}
		});
		stage.addActor(btnB);
	}

	private void createEmail() {
		int emailLocationFactor = 0;
		for (Email e : emailList) {
			final Email em = e;
			email = new Label(e.toString(), uiskin);
			email.setX(emailLabelMargin);
			email.setY(emailLocationFactor + (screenHeight - btnB.getHeight() - email.getHeight() - MyGdxGame.btnBackMargin - emailLabelMargin));
			email.setColor(Color.BLACK);
			email.setWidth(screenWidth - 20);
			email.setWrap(true);
			LabelStyle labelStyle = new LabelStyle();
			labelStyle.font = new BitmapFont();
			labelStyle.background = uiskin.newDrawable("white", 0.8f, 0.8f, 0.8f, 0.2f);
			email.setStyle(labelStyle);
			email.addListener(new ClickListener() {
				public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
					game.setScreenHelp(game.email2, em);
					if(game.currentTask==3 &&em.subject.equals("Degree Audit")){
						game.currentTask=4;
					}
					if(game.currentTask==0&&em.subject.equals("Add Courses"))
						game.currentTask=1;
					if(game.currentTask==-1&&em.subject.equals("Add your Major")){

						System.out.println("hit it");
						game.currentTask=0;
					}

					//System.out.println(game.textCount);
					//game.currText++;
					//					}
					//
					//					if (!taskList.get(4).isCompleted()) {
					//						taskList.get(4).setCompleted();
					//						game.setScreen(game.help);
					//					}
					return true;
				}
			});
			stage.addActor(email);
			emailLocationFactor -= email.getHeight() + emailLabelMargin;
			emailLabelWidth = email.getWidth();
		}
	}

	public void parseEmails() {
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
						lines.get(3), wrapString(lines.get(4), 100), lines.get(5), lines.get(6));
				emailList.add(e);
				lines.clear();
			}
			//fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String wrapString(String string, int charWrap) {
		int lastBreak = 0;
		int nextBreak = charWrap;
		if (string.length() > charWrap) {
			String setString = "";
			while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
				nextBreak--;
			}
			if (nextBreak == lastBreak) {
				nextBreak = lastBreak + charWrap;
			}
			setString += string.substring(lastBreak, nextBreak).trim() + "...";
			return setString;
		} else {
			return string;
		}
	}

}
