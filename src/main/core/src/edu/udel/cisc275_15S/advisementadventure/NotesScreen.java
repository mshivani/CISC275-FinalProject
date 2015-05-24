package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NotesScreen extends ScreenAdapter implements InputProcessor {
	private MyGdxGame game;
	private SpriteBatch batch;
	private Texture btnBack;
	private BitmapFont font;
	private Note notes;
	private TextArea ta;
	private Skin uiskin;
	private Stage s;
	private InputMultiplexer m;
	private Image backButton;
	private int code;
	private int untitledCount;
	private float height;
	private float width;
	private Note temp;
	private ArrayList<Task> taskList;
	private Texture starT;
	private Image star;
	private Label la;
	private int num;

	public NotesScreen(MyGdxGame g, Note note) {
		temp = new Note();
		temp.setText(note.getText());
		temp.setName(note.getName());
		notes = note;
		this.game = g;
		this.taskList = g.taskList;
		s = new Stage();
		m = new InputMultiplexer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		backButton = new Image(btnBack);
		backButton.setX(MyGdxGame.btnBackMargin);
		backButton.setY(height - backButton.getHeight() - MyGdxGame.btnBackMargin);
		backButton.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new NoteMenu(game));
				return true;
			}
		});
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		ta = new TextArea(notes.getText(), uiskin);
		ta.setHeight(Gdx.graphics.getHeight()- backButton.getHeight()*2);
		ta.setWidth(Gdx.graphics.getWidth());

		ta.setTextFieldListener(new TextFieldListener(){


			@Override
			public void keyTyped(TextField textField, char c) {
				if(code == Keys.BACKSPACE && temp.getText().length()!=0){

					temp.setText(temp.getText().substring(0, (temp.getText()).length()-1));
				}
				else if(code == Keys.ENTER){
					ta.moveCursorLine(ta.getLines());
					temp.setText(temp.getText() + "\n");
				}

				else{
					temp.setText(temp.getText() + c);

				}

			}


		});
		TextButton create = new TextButton("Save", uiskin);
		create.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(notes.getText().equals("")){
					notes = temp;
					game.notesList.add(temp);
				}
				notes.setText(ta.getText());

				game.setScreen(new NoteMenu(game));
			}

		});
		ta.setText(notes.getText());
		ta.setY(create.getHeight()+1);
		create.setY(0);
		create.setX(Gdx.graphics.getWidth()-create.getWidth());

		s.addActor(create);
		s.addActor(backButton);
		s.addActor(ta);
		m.addProcessor(this);
		m.addProcessor(s);
	}
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();
	}
	@Override
	public void hide(){
		batch.dispose();
		font.dispose();
		s.dispose();
		
	}
	@Override
	public void show(){
		game.previousScreen = this;
		if(notes.getText().equals("")){
			Gdx.input.getTextInput(new TextInputListener(){

				@Override
				public void input(String text) {
					temp.setName(text);

				}

				@Override
				public void canceled() {
					temp.setName("Untitled " + untitledCount);
					untitledCount++;

				}

			}, "Enter Name for new note", null, "Name");
		}
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		createAchieveStar();
		Gdx.input.setInputProcessor(m);
		Gdx.input.setOnscreenKeyboardVisible(true);
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
			
			star.addAction(Actions.forever(Actions.sequence(Actions.sizeTo(65, 65, .7f), Actions.sizeTo(80, 80, .7f))));
			star.addAction(Actions.forever(Actions.sequence(
					Actions.moveTo(width-72, height-72, .7f), 
					Actions.moveTo(width-80, height-80, .7f))));
		
			s.addActor(star);
			la = new Label(num + "", uiskin);
			la.setX(width - star.getWidth()+ star.getWidth() * .44f);
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
	public boolean keyDown(int keycode) {
		code = keycode;

		return false;
	}
	@Override
	public boolean keyUp(int keycode) {

		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
