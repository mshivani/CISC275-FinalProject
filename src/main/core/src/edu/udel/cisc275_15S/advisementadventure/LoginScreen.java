package edu.udel.cisc275_15S.advisementadventure;

import java.io.BufferedReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoginScreen extends ScreenAdapter {
	private MyGdxGame game;
	private SpriteBatch batch;
	private Label welcome;
	private Texture bg;
	private TextField tf;
	private Skin uiskin;
	private Stage s;
	private float height;
	private float width;
	private TextButton enter;

	public LoginScreen(MyGdxGame g) {
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		this.game = g;
		createTextField();
		createEnter();
		createWelcome();
		batch = new SpriteBatch();
		bg = new Texture("login-bg.png");
		s = new Stage();
		s.addActor(tf);
		s.addActor(enter);
		s.addActor(welcome);
	}

	@Override
	public void show() {
		parseQuestions();
		Gdx.input.setInputProcessor(s);
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(bg, 0, 0, width, height);
		batch.end();
		s.draw();
	}

	@Override
	public void hide() {
		batch.dispose();
		s.dispose();
	}

	public void parseQuestions() {
		FileHandle quest = Gdx.files.internal("Questions.txt");
		BufferedReader reader = new BufferedReader(quest.reader());
		String line;
		try {
			line = reader.readLine();
			int count = Integer.parseInt(line);
			for (int i = 0; i < count; i++) {
				String quest2 = reader.readLine();
				String quest3 = reader.readLine();
				Question x;
				if (quest3.equals("No responses")) {
					x = new Question(quest2, "empty", "empty", "empty",
							"empty", "empty");
					game.questionList.add(x);
				} else {
					x = new Question(quest2, quest3, line = reader.readLine(),
							line = reader.readLine(), line = reader.readLine(),
							line = reader.readLine());
					game.questionList.add(x);
				}

			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createTextField() {
		tf = new TextField("", uiskin);
		tf.setPosition(width / 2 - tf.getWidth() / 2, height / 2);
		tf.setMessageText("Enter Student ID");
	}

	public void createEnter() {
		enter = new TextButton("Enter", uiskin);
		enter.setPosition(tf.getX() + tf.getWidth(), tf.getY());
		enter.setHeight(tf.getHeight());
		enter.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				/*
				 * if and else statement below can be added when we want to
				 * force the user to enter an 9 digit number into the text field
				 */
				if (tf.getText().matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) {

					game.data.writeString(tf.getText() + "\n", false);
					game.setScreen(new HomeScreen(game));
				} else {
					Label enterAgain = new Label(
							"Please enter a valid 9 digit student ID number.",
							uiskin);
					enterAgain.setAlignment(Align.center);
					enterAgain.setX(width / 2 - enterAgain.getWidth() / 2);
					enterAgain.setY(height / 2 - enterAgain.getHeight() - 10);
					s.addActor(enterAgain);
				}

				return true;
			}
		});
	}

	public void createWelcome() {
		welcome = new Label("Welcome to \n Advisement Adventure", uiskin);
		welcome.setFontScale(3);
		welcome.setAlignment(Align.center);
		welcome.setX(width / 2 - welcome.getWidth() / 2);
		welcome.setY(height / 2 + height / 4);
	}
}
