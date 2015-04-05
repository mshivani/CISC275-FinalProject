package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NoteMenu extends ScreenAdapter {
	private String note;
	Texture btnBack;
	Table t;
	MyGdxGame game;
	//ArrayList<String> game.notes;
	Stage s;
	Skin uiskin;
	TextButton newNote;

	public NoteMenu(MyGdxGame g){
		//this.note =x;
		this.game = g;
		btnBack = new Texture("btn_back.png");
		Image bb = new Image(btnBack);
		//bb.setAlign(Align.bottomLeft);
		bb.setX(0);
		bb.setY(Gdx.graphics.getHeight()-bb.getHeight());
		bb.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new HomeScreen(game));
				return true;
			}
		});
		
		//game.notes;
		//game.notes.add("number one");
		//game.notes.add("number two");
		//notes.add(x);
		
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		newNote = new TextButton("Create New Note", uiskin);
		newNote.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				System.out.println("bug");
				System.out.println(game.getScreen());
				game.setScreen(new NotesScreen(game, new Note()));
				return true;
			}
		});
		newNote.setY(Gdx.graphics.getHeight()-bb.getHeight() - newNote.getHeight()-20);
		newNote.setX(0);
		s = new Stage();
		
		t = new Table();
		t.addActor(bb);
		drawScreen();
	}
	public void drawScreen(){
		t.add(newNote);
		t.row();
		//int heightcounter;
		//int widthcounter;
		for (int i =0; i <game.notesList.size(); i++){
			final int index = i;
			TextButton x = new TextButton(game.notesList.get(i).getName(), uiskin);
			
			x.addListener(new ClickListener(){
				public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
					game.setScreen(new NotesScreen(game, game.notesList.get(index)));
					System.out.println("pressed extras");
					return true;
				}
			});
			t.add(x).align(Align.left);
			t.row();
		}
		t.setFillParent(true);
		s.addActor(t);
	}

	@Override
	public void show(){
		//drawScreen();
		//s.addActor(t);
		Gdx.input.setInputProcessor(s);
	}

	@Override 
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//s.act();
		s.draw();
	}
	@Override
	public void hide(){
		s.dispose();
	}
}
