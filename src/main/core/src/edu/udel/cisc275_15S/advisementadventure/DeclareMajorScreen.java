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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class DeclareMajorScreen extends ScreenAdapter {
	private MyGdxGame game;
	private SpriteBatch batch;
	private Skin uiskin;
	private Texture banner;
	private TextButton add;
	private SelectBox<String> sb;
	private float height;
	private float width;
	private Array<String> majorChoices;
	private Stage s;
	private Texture btnBack;
	private Image btnB;
	private ArrayList<Task> taskList;
	private Image star;
	private Texture starT;
	private Label la;
	private int num;

	private Texture home;
	private Image btnHome;
	
    String yourMajor;

	public DeclareMajorScreen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		// creates an array with a list of possible majors the students can choose from 
		majorChoices = new Array<String>();
		majorChoices.add("Undeclared");
		majorChoices.add("Agricultural and Natural Resources");
		majorChoices.add("Art");
		majorChoices.add("Biological Sciences");
		majorChoices.add("Communications");
		majorChoices.add("Computer Science");
		majorChoices.add("Criminal Justice");
		majorChoices.add("Economics");
		majorChoices.add("English");
		majorChoices.add("Finance");
		majorChoices.add("Foreign Language");
		majorChoices.add("Health Sciences");
		majorChoices.add("International Relations");
		majorChoices.add("History");
		majorChoices.add("Mathematics");
		majorChoices.add("Music");
		majorChoices.add("Nursing");
		majorChoices.add("Womens Studies");
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

	//creates a drop down menu with major choices
	public void createDropDown() {
		sb = new SelectBox<String>(uiskin);
		sb.setWidth(width / 2);
		sb.setItems(majorChoices);
		sb.setPosition(width / 4, height / 2f);
		sb.setMaxListCount(6);
	}

	//creates an add button
	public void createAdd() {
		add = new TextButton("Add", uiskin);
		add.setPosition(sb.getX() + sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				if (sb.getSelected() != null) {
					yourMajor = (String) sb.getSelected();
					Label majorDeclaredL = new Label(
							"Congrats! Your major is: " + sb.getSelected(),
							uiskin);
					s.addActor(majorDeclaredL);
					majorDeclaredL.setColor(1, 0, 0, 1);
					majorDeclaredL.setX(sb.getX());
					majorDeclaredL.setY(sb.getY() - sb.getHeight());
					sb.clearItems();
					if (game.currentTask == 2) {
						game.currText = 0;
						// questionList initialized in Login, currText
						// initialized in DeclareMajor
						game.text = new TextScreen(game, game.questionList
								.get(game.currText));
						game.currentTask = 3;
					}
					if (!taskList.get(1).isCompleted()) {
						taskList.get(1).setCompleted();
						if (!taskList.get(0).isSeen()) {
							star.remove();
							la.remove();
						}
						createAchieveStar();

					}

				}
				return true;
			}
		});

	}

	//determines the task that causes the achieve star to show up on the screen
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

	@Override
	public void show() {
		game.previousScreen = this;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		createDropDown();
		createAdd();
		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");
		s = new Stage();
		s.addActor(sb);
		s.addActor(add);
		Gdx.input.setInputProcessor(s);
		createAchieveStar();
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
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		createBackButton();
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(banner, 0, height - height / 5.5f, width, height / 5.5f);
		batch.end();
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		s.draw();
	}

	@Override
	public void hide() {
		batch.dispose();
		s.dispose();
	}

}
