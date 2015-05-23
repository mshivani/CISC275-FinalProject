package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HomeScreen extends ScreenAdapter {

	MyGdxGame game;
	Skin uiskin;
	Stage s;
	SpriteBatch batch;
	BitmapFont font;
	Texture btnBack;
	Image btnB;
	Texture btnNotes;
	Image btnN;
	Label notesL;
	Texture btnWeb;
	Image btnW;
	Label webL;
	Texture btnText;
	Image btnT;
	Label textL;
	Texture btnEmail;
	Image btnE;
	Label emailL;
	Texture btnHelp;
	Image btnH;
	Texture indicArrow;
	Image arrow;
	Label helpL;
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;
	boolean input;
	float height;
	float width;
	Texture bg;

	int timeViewingScreen;
	Texture n;
	Image notification;

	public HomeScreen(MyGdxGame g) {
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		this.taskList = g.taskList;
		timeViewingScreen = 0;
	}

	@Override
	public void show() {
		game.previousScreen = this;
		s = new Stage();
		bg = new Texture("homescreen-bg.png");
		Image bgI = new Image(bg);
		s.addActor(bgI);
		batch = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		bgI.setBounds(0, 0, width, height);
		createNotesButton();
		createWebButton();
		createTextButton();
		createEmailButton();
		createHelpButton();
		createAchieveStar();
		createIndicatorArrow();
		Gdx.input.setInputProcessor(s);
		n = new Texture("warning-icon.png");
		notification = new Image(n);

		System.out.println("currentTask: " + game.currentTask
				+ "     currentTask2: " + game.currentTask2);

		if (game.currentTask2 == 100 && game.currentTask == 4) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 101) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 102) {
			setNotificationImage(btnW, false);
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, true);
		} else if (game.currentTask2 == 103 && game.currentTask == 5) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 104) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnW, false);
			setNotificationImage(btnE, true);
		} else if (game.currentTask2 == 105 && game.currentTask != 7) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 106) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 107) {
			setNotificationImage(btnT, false);
			setNotificationImage(btnW, false);
			setNotificationImage(btnE, true);
		} else if (game.currentTask2 == 108) {
			setNotificationImage(btnW, false);
			setNotificationImage(btnE, false);
			setNotificationImage(btnT, true);
		} else if (game.currentTask2 == 109) {
			setNotificationImage(btnE, false);
			setNotificationImage(btnT, false);
			setNotificationImage(btnW, true);
		} else if (game.currentTask2 == 110) {
			setNotificationImage(btnE, false);
			setNotificationImage(btnT, false);
			setNotificationImage(btnW, true);
		} else {
			switch (game.currentTask) {
			case 0:
				if (timeViewingScreen != 0) {
					setNotificationImage(btnT, false);
					setNotificationImage(btnE, false);
					setNotificationImage(btnW, true);
				} else {
					setNotificationImage(btnT, false);
					setNotificationImage(btnW, false);
					setNotificationImage(btnE, true);
				}
				break;
			case 1:
				setNotificationImage(btnW, false);
				setNotificationImage(btnT, false);
				setNotificationImage(btnE, true);
				break;
			case 2:
				setNotificationImage(btnT, false);
				setNotificationImage(btnE, false);
				setNotificationImage(btnW, true);
				break;
			case 3:
				setNotificationImage(btnW, false);
				setNotificationImage(btnE, false);
				setNotificationImage(btnT, true);
				break;
			case 4:
				setNotificationImage(btnW, false);
				setNotificationImage(btnT, false);
				setNotificationImage(btnE, true);
				break;
			case 5:
				setNotificationImage(btnE, false);
				setNotificationImage(btnW, false);
				setNotificationImage(btnT, true);
				break;
			case 6:
				setNotificationImage(btnE, false);
				setNotificationImage(btnW, false);
				setNotificationImage(btnT, true);
				break;
			case 7:
				setNotificationImage(btnE, false);
				setNotificationImage(btnW, false);
				setNotificationImage(btnT, true);
				break;
			case 8:
				setNotificationImage(btnW, false);
				setNotificationImage(btnE, false);
				setNotificationImage(btnT, true);
				break;
			default:
				break;
			}
		}
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
			star.setX(width - star.getWidth());
			star.setY(height - star.getHeight());

			star.addAction(Actions.forever(Actions.sequence(
					Actions.sizeTo(65, 65, .7f), Actions.sizeTo(80, 80, .7f))));
			star.addAction(Actions.forever(Actions.sequence(
					Actions.moveTo(width - 72, height - 72, .7f),
					Actions.moveTo(width - 80, height - 80, .7f))));

			s.addActor(star);
			la = new Label(num + "", uiskin);
			la.setX(width - star.getWidth() + star.getWidth() * .44f);
			la.setY(height - star.getHeight() + star.getHeight() * .36f);

			la.setColor(Color.BLACK);
			la.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					game.setScreen(game.help);
					return true;
				}
			});
			s.addActor(la);
		}

	}

	public void createHelpButton() {
		helpL = new Label("Trophies", uiskin);
		btnHelp = new Texture("trophy-widget.png");
		btnH = new Image(btnHelp);
		btnH.setX(btnE.getX() + btnE.getWidth() + 30);
		btnH.setY(btnT.getY());
		btnH.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.help);
				return true;
			}
		});
		helpL.setX(btnH.getX() + (btnH.getWidth() / 4) - 10);
		helpL.setY(btnH.getY() - helpL.getHeight());
		helpL.setColor(Color.BLACK);
		s.addActor(helpL);
		s.addActor(btnH);
	}

	public void createEmailButton() {
		emailL = new Label("Email", uiskin);
		btnEmail = new Texture("email-widget.png");
		btnE = new Image(btnEmail);
		btnE.setX(btnT.getX() + btnT.getWidth() + 30);
		btnE.setY(btnT.getY());
		btnE.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.email);
				return true;
			}
		});
		emailL.setX(btnE.getX() + (btnE.getWidth() / 4));
		float temp1 = emailL.getHeight();
		emailL.setY(btnE.getY() - temp1);
		emailL.setColor(Color.BLACK);
		s.addActor(btnE);
		s.addActor(emailL);
	}

	public void createTextButton() {
		textL = new Label("Text", uiskin);
		btnText = new Texture("text-widget.png");
		btnT = new Image(btnText);
		btnT.setX(btnW.getX() + btnW.getWidth() + 30);
		btnT.setY(btnW.getY());
		btnT.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				if (game.currentTask >= 3) {
					if (!taskList.get(2).isCompleted()) {
						taskList.get(2).setCompleted();
					}
					game.text.setQuestion(game.questionList.get(game.currText));
					game.setScreen(game.text);
				}
				return true;
			}
		});
		float temp1 = textL.getHeight();
		textL.setX(btnT.getX() + btnT.getWidth() / 4);
		textL.setY(btnT.getY() - temp1);
		textL.setColor(Color.BLACK);
		s.addActor(btnT);
		s.addActor(textL);
	}

	public void createWebButton() {
		webL = new Label("Web", uiskin);
		btnWeb = new Texture("web-widget.png");
		btnW = new Image(btnWeb);
		btnW.setX(btnN.getX() + btnN.getWidth() + 30);
		btnW.setY((float) (height / 1.5));
		btnW.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.web);
				System.out.println("web");
				return true;
			}
		});
		float temp1 = webL.getHeight();
		webL.setX(btnW.getX() + btnW.getWidth() / 4);
		webL.setY(btnW.getY() - temp1);
		webL.setColor(Color.BLACK);
		s.addActor(btnW);
		s.addActor(webL);
	}

	public void createNotesButton() {
		notesL = new Label("Notes", uiskin);
		btnNotes = new Texture("notes-widget.png");
		btnN = new Image(btnNotes);
		btnN.setX((float) (width / 9));
		btnN.setY((float) (height / 1.5));
		btnN.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.notemenu);
				return true;
			}
		});
		float temp1 = notesL.getHeight();
		notesL.setX(btnN.getX() + btnN.getWidth() / 4);
		notesL.setY(btnN.getY() - temp1);
		notesL.setColor(Color.BLACK);
		s.addActor(btnN);
		s.addActor(notesL);
	}

	public void createIndicatorArrow() {
		if (timeViewingScreen == 0) {
			indicArrow = new Texture("arrow.png");
			arrow = new Image(indicArrow);
			if (game.currText == -1 && game.currentTask == 0) {
				arrow.setX(emailL.getX() + emailL.getWidth());
				arrow.setY(emailL.getY());
				s.addActor(arrow);
			}
		}
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();
	}

	@Override
	public void resize(int x, int y) {
		this.show();
	}

	public void setNotificationImage(Image appIcon, boolean setOn) {
		if (setOn) {
			notification.setX(appIcon.getX() + appIcon.getWidth() * 3 / 4);
			notification.setY(appIcon.getY() + appIcon.getHeight() * 3 / 4);
			s.addActor(notification);
		} else {
			notification.remove();
		}
	}

}
