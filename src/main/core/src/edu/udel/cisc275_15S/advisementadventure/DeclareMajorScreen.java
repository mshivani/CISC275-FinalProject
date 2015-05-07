package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

public class DeclareMajorScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	Texture banner;
	TextButton add;
	SelectBox sb;
	float height;
	float width;
	Array majorChoices;
	String yourMajor;
	Stage s;
	Texture btnBack;
	Image btnB;
	
	
	public DeclareMajorScreen(MyGdxGame g){
		this.game = g;
		majorChoices = new Array();
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
	
	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
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
	
	
	public void createDropDown(){
		sb = new SelectBox(uiskin);
		sb.setWidth(width/2);
		sb.setItems(majorChoices);
		sb.setPosition(width/4, height/2f);
		sb.setMaxListCount(6);
	}
	
	
	public void createAdd(){
		add = new TextButton("Add", uiskin);
		add.setPosition(sb.getX()+sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb.getSelected() != null){
					yourMajor = (String) sb.getSelected();
					Label majorDeclaredL = new Label("Congrats! Your major is: " + sb.getSelected(), uiskin);
					s.addActor(majorDeclaredL);
					majorDeclaredL.setColor(1, 0, 0, 1);
					majorDeclaredL.setX(sb.getX());
					majorDeclaredL.setY(sb.getY() - sb.getHeight());
					sb.clearItems();
					System.out.println(game.currentTask);
					if(game.currentTask==0){
						game.currText=0;
						game.currentTask=1;
					}
				}
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
		createAdd();
		
		batch = new SpriteBatch();
		banner = new Texture("schLogo.png");
		
		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(btnB);
		s.addActor(sb);
		s.addActor(add);
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		
	}
	
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(banner, 0, height-height/5.5f, width, height/5.5f);
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
