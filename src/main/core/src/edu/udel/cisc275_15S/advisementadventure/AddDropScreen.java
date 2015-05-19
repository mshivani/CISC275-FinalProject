package edu.udel.cisc275_15S.advisementadventure;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	Label AddL;
	Label DropL;
	
	Texture home;
	Image btnHome;

	
	ArrayList<Task> taskList;
	Image star;
	Texture starT;
	Label la;
	int num;

	
	public AddDropScreen(MyGdxGame g) {
		
		// Create a list of items that stores the possible classes that the user can take
		currentList = new ArrayList();
		newItems  = new Array();
		newItems.add("");
		newItems.add("ANTH 101");
		newItems.add("CHEM 103");
		newItems.add("CISC 108");
		newItems.add("DANC101");
		newItems.add("ECON 101");
		newItems.add("EGGG 101");
		newItems.add("ENGL 110");
		newItems.add("GEOG 102");
		newItems.add("HIST 104");
		newItems.add("LING 101");
		newItems.add("PHYS 207");
		newItems.add("MATH 201");
		newItems.add("MUSC 100");
		newItems.add("UNIV 101");
		newItems.add("WOMS 205");
		
		//initializes a drop items list for the possible classes that the user can drop 
		dropItems = new Array();
		dropItems.add("");
		
		this.game = g;
		this.taskList = g.taskList;
		
	}

	// creates a drop down for the classes the user is able to add
	public void createDropDown(){
		sb = new SelectBox(uiskin);
		sb.setWidth(width/2);
		sb.setItems(newItems);
		sb.setPosition(width/4, height/1.8f);
		sb.setMaxListCount(3);
	}
	
	// creates a drop down for the classes the user is able to drop
	public void createDropDown2(){
		sb2 = new SelectBox(uiskin);
		sb2.setWidth(width/2);
		sb2.setItems(dropItems);
		sb2.setPosition(width/4, height/3.5f);
		sb2.setMaxListCount(3);
	}
	
	public void createHomeButton() {
		home = new Texture("home-icon.png");
		btnHome = new Image(home);
		btnHome.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(btnHome);
	}
	
	
	// creates an add button for the user to click on in order to add a class
	public void createAdd(){
		add = new TextButton("Add", uiskin);
		add.setPosition(sb.getX()+sb.getWidth(), sb.getY());
		add.setHeight(sb.getHeight());
		add.addListener(new ClickListener(){
			private int classCount;

			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb.getSelected() != null && sb.getSelected() != ""){
					
					// displays a message to the user confirming that they have added the class
					AddL.setText("Congrats! You have added: " + sb.getSelected());
					s.addActor(AddL);
					AddL.setColor(1, 0, 0, 1);
					AddL.setX(sb.getX());
					AddL.setY(sb.getY() - sb.getHeight());
					
					//updates the list of classes the user is able to add
					currentList.add(sb.getSelected());
					newItems.removeValue(sb.getSelected(), true);
					
					//updates the list of classes that the user is able to drop
					dropItems.add(sb.getSelected());
					
					sb.setItems(newItems);
					sb2.setItems(dropItems);
					
					classCount++;
					if(classCount>=5){
						taskList.get(3).setCompleted();
						game.currentTask2 = -1;
						game.currentTask=4;
						if(!taskList.get(2).isSeen()){
							la.remove();
							star.remove();
						}
						createAchieveStar();
					}
				}
				return true;
			}
		});
	}
	
	
	// creates an drop button for the user to click on in order to drop a class
	public void createDrop(){
		drop = new TextButton("Drop", uiskin);
		drop.setPosition(sb2.getX()+sb2.getWidth(), sb2.getY());
		drop.setHeight(sb2.getHeight());
		drop.addListener(new ClickListener(){
			private int classCount=0;

			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(sb2.getSelected() != null && sb2.getSelected() != ""){
					
					// displays a message to the user confirming that they have dropped the class
					DropL.setText("Congrats! You have dropped: " + sb2.getSelected());
					s.addActor(DropL);
					DropL.setColor(1, 0, 0, 1);
					DropL.setX(sb2.getX());
					DropL.setY(sb2.getY() - sb2.getHeight());
					
					//updates the list of classes that the user is able to drop
					currentList.remove(sb2.getSelected());
					dropItems.removeValue(sb2.getSelected(), true);
					
					//updates the list of classes the user is able to add
					newItems.add(sb2.getSelected());
					
					sb.setItems(newItems);
					sb2.setItems(dropItems);
					classCount--;
					if (game.currentTask == 6 && classCount <= 4) {
						game.currText=2;
						taskList.get(5).setCompleted();
						game.currentTask2 = -1;
						game.currentTask=7;
						if(!taskList.get(4).isSeen()){
							la.remove();
							star.remove();
						}
						createAchieveStar();
					}
					
				}
				return true;
			}
		});
	}

	// creates a back button that the user is able to click on in order to go back to the udsis screen
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
	// initializations of pictures, methods, and adding actors
	@Override
	public void show() {
		game.previousScreen = this;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		AddL = new Label(" ", uiskin);
		DropL = new Label(" ", uiskin);
		
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
		createAchieveStar();
		createHomeButton();
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


