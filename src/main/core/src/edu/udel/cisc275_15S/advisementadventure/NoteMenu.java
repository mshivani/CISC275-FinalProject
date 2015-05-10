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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NoteMenu extends ScreenAdapter {
	private String note;
	Texture btnBack;
	Texture createNew;
	Image createNote;
	Image bb;
	Table t;
	MyGdxGame game;
	float width;
	float height;
	Stage s;
	Label l;
	Skin uiskin;
	TextButton newNote;
	private SpriteBatch batch;
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;

	public NoteMenu(MyGdxGame g){
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
		createNote.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new NotesScreen(game, new Note()));
				return true;
			}
		});
		createNote.setX(width - createNote.getWidth());
		createNote.setY(0);
		
		t.setFillParent(true);
		
		l.setX(width/2 - l.getWidth());
		l.setY(height-l.getHeight()-5);
		l.setColor(Color.BLACK);
		l.setFontScale(2);
		btnBack = new Texture("btn_back.png");
		bb = new Image(btnBack);
		bb.setX(0);
		bb.setY(Gdx.graphics.getHeight()-bb.getHeight());
		bb.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new HomeScreen(game));
				return true;
			}
		});
		//t.setBounds(0, 0, width, height-bb.getHeight());
		s.addActor(createNote);
		//t.row();
		s.addActor(l);		
		s.addActor(bb);
		//s.addActor(t);
		drawScreen();
	}
	public void drawScreen(){
		//System.out.println(
		float heightcounter=height- createNote.getHeight();
		//int widthcounter;
		for (int i =0; i <game.notesList.size(); i++){
			final int index = i;
			TextButton x = new TextButton(game.notesList.get(i).getName(), uiskin);
			x.setY(heightcounter - x.getHeight());
			x.setWidth(width);
			x.addListener(new ClickListener(){
				public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
					game.setScreen(new NotesScreen(game, game.notesList.get(index)));
					System.out.println("pressed extras");
					return true;
				}
			});
			s.addActor(x);			
			heightcounter -= x.getHeight();
		}
	}
	
	public void createAchieveStar(){
		starT = new Texture("star.png");
		star = new Image(starT);
		num = 0;
		boolean create = false;
		for(int i = 0; i < taskList.size(); i++){
			if(taskList.get(i).isCompleted() && !taskList.get(i).isSeen()){
				create = true;
				num++;
			}
		}
	
		if(create){
			star.addListener(new ClickListener(){
				public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
					game.setScreen(game.help);			
					return true;
				}
			});
			star.setWidth(80);
			star.setHeight(80);
			star.setX(width - star.getWidth());
			star.setY(height - star.getHeight());
			s.addActor(star);
			la = new Label(num+"", uiskin);
			la.setX(star.getX()+star.getWidth()*.44f);
			la.setY(star.getY()+star.getHeight()*.36f);
			la.setColor(Color.BLACK);
			s.addActor(la);
		}
		
	}
	

	@Override
	public void show(){
		//drawScreen();
		//s.addActor(t);
		createAchieveStar();
		Gdx.input.setInputProcessor(s);
	}

	@Override 
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//s.act();
		batch.begin();
		
		batch.end();
		s.draw();
		s.act();
	}
	@Override
	public void hide(){
		s.dispose();
	}
}
