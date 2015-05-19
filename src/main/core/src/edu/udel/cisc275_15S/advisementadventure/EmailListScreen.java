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
	Texture starT;
	Image star;
	Label la;
	
	Texture indicArrow;
	Image arrow;
	Texture warning;
	Image warn;
	
	
	
	int num;

	public EmailListScreen(MyGdxGame g) {
		this.game = g;
		this.textFont = new BitmapFont();
		this.textFont.setColor(0, 0, 0, 1);
		this.titleFont = new BitmapFont();
		this.titleFont.setScale(2);
		this.titleFont.setColor(0, 0, 0, 1);
		this.taskList = g.taskList;
		Texture starT = new Texture("star.png");
		star = new Image(starT);
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
		game.previousScreen = this;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage = new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		emailList = game.getEmailList();
		createTitle();
		createBackButton();
		createEmail();
		
		createIndicatorArrow();
		createAchieveStar();
		Gdx.input.setInputProcessor(stage);
	}

public void createAchieveStar() {
		starT = new Texture("star.png");
		star = new Image(starT);
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
					Actions.moveTo(screenWidth-72, screenHeight-72, .7f), 
					Actions.moveTo(screenWidth-80, screenHeight-80, .7f))));
		
			stage.addActor(star);
			la = new Label(num + "", uiskin);
			la.setX(screenWidth - star.getWidth()+ star.getWidth() * .44f);
			la.setY(screenHeight - star.getHeight() + star.getHeight() * .36f);

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
	public void createIndicatorArrow() {
		if (game.currentTask == 0) {
			indicArrow = new Texture("arrow.png");
			arrow = new Image(indicArrow);
			warning = new Texture("warning-icon-sm.png");
			warn = new Image(warning);
			if (game.currentTask == 0) {
				arrow.setX(screenWidth - inbox.getWidth());
				arrow.setY(screenHeight / 2);
				warn.setX(arrow.getX() + 21);
				warn.setY(arrow.getY() - 5);
				stage.addActor(arrow);
				stage.addActor(warn);
			}
		}
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
				game.welcome.timeViewingScreen++;
				game.setScreen(game.welcome);
				return true;
			}
		});
		stage.addActor(btnB);
	}

	private void createEmail() {
		int emailLocationFactor = 0;
		int emailShown = 0;
		switch (game.currentTask) {
		case 0:
			emailShown = 0;
			break;
		case 1:
			emailShown = 0;
			break;
		case 2:
			emailShown = 0;
			break;
		case 3:
			emailShown = 0;
			break;
		case 4:
			emailShown = 1;
			break;
		case 5:
			emailShown = 2;
			break;
		case 6:
			emailShown = 3;
			break;
		case 7:
			emailShown = 3;
			break;
		case 8:
			emailShown = 4;
		default:
			emailShown = 0;
			break;
		}
		final Email em = emailList.get(emailShown);
		email = new Label(em.getSender() + "\n" + em.getDate() + "\n"
				+ em.getSubject() + "\n" + em.getSalutation() + "\n"
				+ wrapString(em.getContent(), 90) + "\n" + em.getClosing()
				+ "\n" + em.getSignature(), uiskin);
		email.setX(emailLabelMargin);
		email.setY(emailLocationFactor
				+ (screenHeight - btnB.getHeight() - email.getHeight()
						- MyGdxGame.btnBackMargin - emailLabelMargin));
		email.setColor(Color.BLACK);
		email.setWidth(screenWidth - 20);
		email.setWrap(true);
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		labelStyle.background = uiskin.newDrawable("white", 0.8f, 0.8f, 0.8f,
				0.2f);
		email.setStyle(labelStyle);

		email.addListener(new ClickListener() {
			public boolean touchDown(InputEvent ev, float x, float y,
					int pointer, int button) {
				if (!taskList.get(0).isCompleted()) {
					taskList.get(0).setCompleted();
				}
				game.setScreenHelp(game.email2, em);
				if (game.currentTask == 4) {
					game.currentTask2 = 100;
//					game.currentTask = 4;
				}
				if (game.currentTask == 5) {
					game.currentTask2 = 103;
				}
				if (game.currentTask == 6) {
					game.currentTask2 = 105;
				}
				if (game.currentTask == 8) {
					game.currentTask2 = 108;
				}
//				if (game.currentTask == 2) {
//					game.currentTask2 = 101;
//					game.currentTask = 3;
//				}
				if (game.currentTask == 1) {
					game.currentTask = 2;
				}

				// System.out.println(game.textCount);
				// game.currText++;
				// }
				//
				// if (!taskList.get(0).isCompleted()) {
				// taskList.get(0).setCompleted();
				// game.setScreen(game.help);
				// }
				return true;
			}
		});
		stage.addActor(email);
		emailLocationFactor -= email.getHeight() + emailLabelMargin;
		emailLabelWidth = email.getWidth();

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