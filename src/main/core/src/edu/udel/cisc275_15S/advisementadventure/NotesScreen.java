package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sun.glass.events.KeyEvent;
import com.sun.management.GarbageCollectionNotificationInfo;

public class NotesScreen extends ScreenAdapter implements InputProcessor {
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont font;
	String notes;
	TextArea ta;
	Skin uiskin;
	Stage s;
	InputMultiplexer m;
	Image backButton;
	boolean backspace;
	int code;
	int untitledCount;
	//boolean alreadyCreated;
	Note newNote;

	public NotesScreen(MyGdxGame g, String x) {
		newNote = new Note();
		notes = x;
		this.game = g;
		s = new Stage();
		m = new InputMultiplexer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		backButton = new Image(btnBack);
		backButton.setX(0);
		backButton.setY(Gdx.graphics.getHeight()-backButton.getHeight());
		backButton.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new NoteMenu(game));
				return true;
			}
		});
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		ta = new TextArea(notes, uiskin);
		ta.setHeight(Gdx.graphics.getHeight()- backButton.getHeight()*2);
		ta.setWidth(Gdx.graphics.getWidth());
		
		ta.setTextFieldListener(new TextFieldListener(){


			@Override
			public void keyTyped(TextField textField, char c) {
				if(code == Keys.BACKSPACE && notes.length()!=0){

					notes = notes.substring(0, notes.length()-1);
					System.out.println(1);
					System.out.println(notes);
					System.out.println(ta.getLines());
				}
				else if(code == Keys.ENTER){
					ta.moveCursorLine(ta.getLines());
					System.out.println(2);
					System.out.println(notes);
					System.out.println(ta.getLines());
					notes = notes+ "\n";
				}

				else{
					notes = notes+c;

				}

			}


		});
		TextButton create = new TextButton("Create", uiskin);
		create.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				newNote.setText(ta.getText());
				game.notes.add(newNote);
				game.setScreen(new NoteMenu(game));
			}

		});
		ta.setText(notes);
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
		//batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//font.draw(batch, notes, 150, 150);

		//batch.draw(btnBack, 25, 425);
		//batch.end();
		//ta.setText(notes);

		s.draw();
	}
	@Override
	public void show(){
		if(notes.equals("")){
			Gdx.input.getTextInput(new TextInputListener(){

				@Override
				public void input(String text) {
					newNote.setName(text);

				}

				@Override
				public void canceled() {
					newNote.setName("Untitled " + untitledCount);
					untitledCount++;

				}

			}, "Enter Name for new note", null, "Name");
		}
		Gdx.input.setInputProcessor(m);
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
		//System.out.println(character);
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//		if (screenX >= 0 && screenX <= 100 && screenY >= 0 && screenY <= 100) {
		//			game.setScreen(game.welcome);
		//			return true;
		//		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
