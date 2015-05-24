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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NoteMenu extends ScreenAdapter {
	private Texture btnBack;
	private Texture createNew;
	private Image createNote;
	private Image bb;
	private Table t;
	private MyGdxGame game;
	private float width;
	private float height;
	private Stage s;
	private Label l;
	private Skin uiskin;
	private SpriteBatch batch;
	private ArrayList<Task> taskList;
	private Image star;
	private Texture starT;
	private Label la;
	private int num;

	public NoteMenu(MyGdxGame g) {
		batch = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.game = g;
		this.taskList = g.taskList;
		s = new Stage();
		t = new Table();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		l = new Label("Notes", uiskin);
		createNew = new Texture("create_new.PNG");
		createNote = new Image(createNew);
		createNote.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(new NotesScreen(game, new Note()));
				return true;
			}
		});
		createNote.setX(width - createNote.getWidth());
		createNote.setY(0);

		t.setFillParent(true);

		l.setX(width / 2 - l.getWidth());
		l.setY(height - l.getHeight() - 5);
		l.setColor(Color.BLACK);
		l.setFontScale(2);
		btnBack = new Texture("btn_back.png");
		bb = new Image(btnBack);
		bb.setX(MyGdxGame.btnBackMargin);
		bb.setY(height - bb.getHeight() - MyGdxGame.btnBackMargin);
		bb.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(createNote);
		s.addActor(l);
		s.addActor(bb);
		drawScreen();
	}

	public void drawScreen() {
		float heightcounter = height - createNote.getHeight();
		for (int i = 0; i < game.notesList.size(); i++) {
			final int index = i;
			TextButton x = new TextButton(game.notesList.get(i).getName(),
					uiskin);
			x.setY(heightcounter - x.getHeight());
			x.setWidth(width);
			x.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					game.setScreen(new NotesScreen(game, game.notesList
							.get(index)));
					return true;
				}
			});
			s.addActor(x);
			heightcounter -= x.getHeight();
		}
	}

	//creates a star on the screen when a task has been accomplished 
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
		createAchieveStar();
		Gdx.input.setInputProcessor(s);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.end();
		s.draw();
		s.act();
	}

	@Override
	public void hide() {
		s.dispose();
	}
}
