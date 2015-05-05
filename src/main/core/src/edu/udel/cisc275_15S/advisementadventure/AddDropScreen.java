package edu.udel.cisc275_15S.advisementadventure;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class AddDropScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	Label welcome;
	BitmapFont font;
	Texture banner;
	Texture registration;
	SelectBox sb;
	SelectBox sb2;
	Skin uiskin;  
	Stage s;
	float height;
	float width;
	TextButton add;
	TextButton drop;
	Image btnB;
	ArrayList currentList;
	Array newItems;
	Array dropItems;
	
	public AddDropScreen(MyGdxGame g) {
		currentList = new ArrayList();
		newItems  = new Array();
		newItems.add("");
		newItems.add("EGGG 101");
		newItems.add("MUSC 100");
		newItems.add("CHEM 103");
		newItems.add("ENGL 110");
		newItems.add("PHYS 207");
		newItems.add("CISC 108");
		newItems.add("HIST 104");
		newItems.add("ECON 101");
		newItems.add("UNIV 101");
		
		dropItems = new Array();
		dropItems.add("");
		this.game = g;
		
	}

	public void createDropDown(){
		sb = new SelectBox(uiskin);
		sb.setWidth(width/2);
		sb.setItems(newItems);
		sb.setPosition(width/4, height/1.8f);
		sb.setMaxListCount(3);
	}
	
	public void createDropDown2(){
		sb2 = new SelectBox(uiskin);
		sb2.setWidth(width/2);
		sb2.setItems(dropItems);
		sb2.setPosition(width/4, height/3.5f);
		sb2.setMaxListCount(3);
	}
	
	
	public void createAdd(){
		add = new TextButton("Add", uiskin);
		add.setPosition(sb.getX()+sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener(){
			private int classCount;

			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb.getSelected() != null && sb.getSelected() != ""){
					currentList.add(sb.getSelected());
					newItems.removeValue(sb.getSelected(), true);
					dropItems.add(sb.getSelected());
					sb.setItems(newItems);
					sb2.setItems(dropItems);
					classCount++;
					if(classCount>=5 && game.currentTask==3)
						game.currentTask=4;
				}
				return true;
			}
		});
	}
	
	public void createDrop(){
		drop = new TextButton("Drop", uiskin);
		drop.setPosition(sb2.getX()+sb2.getWidth(), sb2.getY());
		drop.setHeight(sb2.getHeight());
		drop.addListener(new ClickListener(){
			private int classCount;

			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb2.getSelected() != null && sb2.getSelected() != ""){
					currentList.remove(sb2.getSelected());
					dropItems.removeValue(sb2.getSelected(), true);
					newItems.add(sb2.getSelected());
					sb.setItems(newItems);
					sb2.setItems(dropItems);
					classCount--;
				}
				return true;
			}
		});
	}

	private void createBackButton() {
		Texture btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
				game.setScreen(game.udsis);
				return true;
			}
		});
	}
	

	
	@Override
	public void show() {
		
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		createBackButton();
		createDropDown();
		createDropDown2();
		
		createAdd();
		createDrop();
		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");
		registration = new Texture("Registration.png");
		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(sb);
		s.addActor(sb2);
		s.addActor(add);
		s.addActor(drop);
		s.addActor(btnB);
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		
	}
	
 
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(banner, 0, height-height/5.5f, width, height/5.5f);
	    batch.draw(registration, 25, height-(2*(height/5.64f)), width, height/6.5f);
		batch.end();
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		s.draw();
	}
	
	@Override
	public void hide(){
		batch.dispose();
		s.dispose();
	}
}


