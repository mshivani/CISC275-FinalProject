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

public class RsvpScreen extends ScreenAdapter {
	private MyGdxGame game;
	private SpriteBatch batch;
	private Skin uiskin;
	private Texture banner;
	private TextButton add;
	private SelectBox<String> sb;
	private float height;
	private float width;
	private Array<String> rsvpChoices;
	private Stage s;
	private Texture btnBack;
	private Image btnB;

	private Texture home;
	private Image btnHome;
	private ArrayList<Task> taskList;
	private Image star;
	private Texture starT;
	private Label la;
	private int num;
	private Label rsvpL;

	public RsvpScreen(MyGdxGame g) {
		this.game = g;
		this.taskList = g.taskList;
		rsvpChoices = new Array<String>();
		rsvpChoices.add(" ");
		rsvpChoices.add("Writing Center appointment");
		rsvpChoices.add("Flu shot appointment");
		rsvpChoices.add("Advisory Networking Night");
		rsvpChoices.add("J.P.Morgan Dining Etiquette");
		rsvpChoices.add("Meet the Firms Night");
		rsvpChoices.add("Law School Fair");
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

	//creates a drop down list with RSVP events 
	public void createDropDown() {
		sb = new SelectBox<String>(uiskin);
		sb.setWidth(width / 2);
		sb.setItems(rsvpChoices);
		sb.setPosition(width / 4, height / 2f);
		sb.setMaxListCount(6);
	}

	//creates an add button 
	public void createAdd() {
		add = new TextButton("Register", uiskin);
		add.setPosition(sb.getX() + sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				if (sb.getSelected() != null && sb.getSelected() != " ") {

					rsvpL.setText("Congrats! You have registered for: "
							+ sb.getSelected());
					s.addActor(rsvpL);
					rsvpL.setColor(1, 0, 0, 1);
					rsvpL.setX(sb.getX() - sb.getX() / 2);
					rsvpL.setY(sb.getY() - sb.getHeight());

					if (sb.getSelected().equals("Writing Center appointment")
							&& game.currentTask == 7) {
						boolean remove = false;
						for (int i = 0; i < taskList.size(); i++) {
							if (taskList.get(i).isCompleted()
									&& !taskList.get(i).isSeen()) {
								remove = true;
							}
						}
						if (remove) {
							la.remove();
							star.remove();
						}
						taskList.get(6).setCompleted();
						game.currentTask = 8;
						createAchieveStar();

					}
					if (sb.getSelected().equals("Flu shot appointment")
							&& game.currentTask == 8) {
						game.currentTask = 9;
						boolean remove = false;
						for (int i = 0; i < taskList.size(); i++) {
							if (taskList.get(i).isCompleted()
									&& !taskList.get(i).isSeen()) {
								remove = true;
							}
						}
						if (remove) {
							la.remove();
							star.remove();
						}
						taskList.get(7).setCompleted();
						createAchieveStar();
					}

					rsvpChoices.removeValue(sb.getSelected(), true);
					sb.setItems(rsvpChoices);

				}
				return true;
			}
		});
	}

	//displays an achieve star when a task is completed 
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

	//creates a home button 
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

	@Override
	public void show() {
		game.previousScreen = this;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		rsvpL = new Label(" ", uiskin);

		createDropDown();
		createAdd();

		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");

		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(sb);
		s.addActor(add);
		createAchieveStar();
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
