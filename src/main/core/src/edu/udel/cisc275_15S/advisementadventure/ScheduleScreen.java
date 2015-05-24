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

public class ScheduleScreen extends ScreenAdapter {

	private MyGdxGame game;
	private SpriteBatch batch;
	private Texture btnBack;
	private Image btnB;
	private Texture Schedule;
	private Texture SchTitle;
	private BitmapFont font;
	private BitmapFont font2;
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	private ArrayList<Task> taskList;
	private Image star;
	private Texture starT;
	private Label la;
	private int num;
	private Skin uiskin;
	private Stage s;

	private Texture home;
	private Image btnHome;

	public ScheduleScreen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font2.setColor(1, 0, 0, 1);
		Schedule = new Texture("schLogo.png");
		SchTitle = new Texture("schTitle.png");
	}

	//displays the users currently selected classes
	@Override
	public void render(float delta) {
		if (Gdx.input.isTouched()) {
			SechScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(Schedule, 0, height - height / 5.5f, width, height / 5.5f);
		batch.draw(SchTitle, 25, height - (2 * (height / 5.64f)), width,
				height / 6.5f);
		float tempWidth = width / 2.5f;
		float tempHeight = height / 1.3f - 50;
		for (int i = 0; i < game.addDrop.currentList.size(); i++) {
			font.draw(batch, (CharSequence) game.addDrop.currentList.get(i),
					tempWidth, tempHeight);
			tempHeight -= 30;
		}
		if (game.addDrop.currentList.isEmpty()) {
			font2.draw(batch, "You Have No Scheduled Classes", width / 3.2f,
					height / 2f);
		}
		batch.end();
		s.draw();
		s.act();
	}

	@Override
	public void show() {
		game.previousScreen = this;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		batch = new SpriteBatch();

		s = new Stage();

		createAchieveStar();
		createHomeButton();
		createBackButton();

		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		Gdx.input.setInputProcessor(s);
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
				game.setScreen(game.udsis);
				return true;
			}
		});
		s.addActor(btnB);
	}

	//displays an achieve star on the page if a task is accomplished 
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

	public void createHomeButton() {
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
	}

	public void SechScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100
				&& Gdx.input.justTouched()) {
			game.setScreen(game.udsis);
		}
	}

}
