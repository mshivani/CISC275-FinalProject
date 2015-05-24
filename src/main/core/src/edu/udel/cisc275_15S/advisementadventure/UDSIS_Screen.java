package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UDSIS_Screen extends ScreenAdapter {

	private MyGdxGame game;
	private SpriteBatch batch;
	private Skin uiskin;

	private Texture btnBack;
	private Image btnB;

	private Texture udBanner;
	private Texture academicsHeader;
	private Texture academicsBar;
	private Texture advisorBox;

	private Texture ClassSch;
	private Image btnClassSch;

	private Texture addDrop;
	private Image btnAddDrop;

	private Texture major;
	private Image btnMajor;

	private Texture audit;
	private Image btnAudit;

	private Texture home;
	private Image btnHome;

	private Image star;
	private Texture starT;
	private ArrayList<Task> taskList;

	private Stage s;
	private int num;
	private Label la;

	// width and height in pixels
	private float width;
	private float height;

	public UDSIS_Screen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
	}

	//cretaes a achieve star on the page when a task has been accomplished
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

	public void show() {
		Gdx.input.setInputProcessor(s);
		game.previousScreen = this;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		s = new Stage();

		udBanner = new Texture("udSisLogo.png");
		academicsHeader = new Texture("academics-header.png");
		academicsBar = new Texture("academics-bar.png");
		advisorBox = new Texture("advisor-box.png");

		home = new Texture("home-icon.png");
		btnHome = new Image(home);
		btnHome.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(btnHome);

		ClassSch = new Texture("schedule-btn.png");
		btnClassSch = new Image(ClassSch);
		btnClassSch.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.schedule);
				return true;
			}
		});
		btnClassSch
				.setBounds(
						10,
						height
								- (udBanner.getHeight()
										+ academicsBar.getHeight() + 50),
						btnClassSch.getWidth(), btnClassSch.getHeight());
		s.addActor(btnClassSch);

		addDrop = new Texture("reg-btn.png");
		btnAddDrop = new Image(addDrop);
		btnAddDrop.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.addDrop);
				return true;
			}
		});
		btnAddDrop.setBounds(10,
				height - (udBanner.getHeight() + academicsBar.getHeight() + 50)
						- btnClassSch.getHeight(), btnAddDrop.getWidth(),
				btnAddDrop.getHeight());
		s.addActor(btnAddDrop);

		major = new Texture("decmajor-btn.png");
		btnMajor = new Image(major);
		btnMajor.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {

				game.setScreen(game.major);
				return true;
			}
		});
		btnMajor.setBounds(10,
				height - (udBanner.getHeight() + academicsBar.getHeight() + 50)
						- btnClassSch.getHeight() - btnAddDrop.getHeight(),
				btnMajor.getWidth(), btnMajor.getHeight());
		s.addActor(btnMajor);

		audit = new Texture("audit-btn.png");
		btnAudit = new Image(audit);
		btnAudit.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				if (game.currentTask >= 4) {
					taskList.get(4).setCompleted();
					game.setScreen(game.degreeAudit);
				}
				return true;
			}
		});
		btnAudit.setBounds(10,
				height - (udBanner.getHeight() + academicsBar.getHeight() + 50)
						- btnClassSch.getHeight() - btnAddDrop.getHeight()
						- btnMajor.getHeight(), btnAudit.getWidth(),
				btnAudit.getHeight());
		s.addActor(btnAudit);

		createAchieveStar();
		createBackButton();
	}

	//creates a back button 
	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.welcome.timeViewingScreen++;
				game.setScreen(game.web);
				return true;
			}
		});
		s.addActor(btnB);
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(udBanner, btnBack.getWidth() + 15, height - height / 9.5f);
		batch.draw(academicsHeader, 0, height - height / 5.5f);
		batch.draw(academicsBar, academicsHeader.getWidth(), height - height
				/ 5.5f, width, academicsBar.getHeight());
		batch.draw(advisorBox, width / 1.90f, height - height / 1.75f);
		batch.end();

		s.act();
		s.draw();
	}

	@Override
	public void resize(int x, int y) {
		this.show();
	}

}
