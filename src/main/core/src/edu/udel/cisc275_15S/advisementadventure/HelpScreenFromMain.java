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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class HelpScreenFromMain extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;

	Texture btnBack;
	Texture trans;
	Texture glass;
	Image btnB;
	Image star;
	Skin uiskin;
	Stage s;

	BitmapFont font;
	float width;
	float height;
	String helpDisplay;
	ArrayList<Task> tasklist;
	ArrayList<Image> compImg;
	ArrayList<Image> unCompImg;
	ArrayList<Label> taskLabel;
	Texture starT;
	float speedx;
	float speedy;


	public HelpScreenFromMain(MyGdxGame g) {
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_Back.png");
		btnB = new Image(btnBack);
		
		
		compImg = new ArrayList<Image>();
		unCompImg = new ArrayList<Image>();
		taskLabel = new ArrayList<Label>();
		
		//add to every page
		this.tasklist = g.taskList;
		starT = new Texture("star.png");
		star = new Image(starT);
	}
	
	public void show(){
		game.previousScreen = this;
		s=new Stage();
		compImg.clear();
		unCompImg.clear();
		taskLabel.clear();
		for(int i=0; i < tasklist.size();i++){
			compImg.add(new Image(tasklist.get(i).getCompletedPic()));
			unCompImg.add(new Image(tasklist.get(i).getUncompletedPic()));
			taskLabel.add(new Label(tasklist.get(i).getDescription(), uiskin));
		}
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		font.setColor(0, 0, 0, 1);
		createBackButton();
		createTaskImages();
		createTaskList();
		
		Gdx.input.setInputProcessor(s);
	}
	
	
	
	private void createTaskList(){
		for(int i = 0; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				star.setX(width*.06f);
				star.setY(height*.57f - height*.05f*(i+1));
				star.setWidth(width*.03f);
				star.setHeight(width*.03f);
				s.addActor(star);
				taskLabel.get(i).setX(width*.1f);
				taskLabel.get(i).setY(height*.6f - height*.05f*(i+1));
				taskLabel.get(i).setColor(Color.BLACK);
				s.addActor(taskLabel.get(i));
			}
			else{
				taskLabel.get(i).setX(width*.1f);
				taskLabel.get(i).setY(height*.6f - height*.05f*(i+1));
				taskLabel.get(i).setColor(Color.GRAY);
				s.addActor(taskLabel.get(i));
			}

		}
	}
	private void createTaskImages(){
		for(int i = 0; i < tasklist.size()/2; i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				compImg.get(i).setX((i+1)*(width*.15f));
				compImg.get(i).setY(height*.8f);
				s.addActor(compImg.get(i));
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				unCompImg.get(i).setX((i+1)*(width*.15f));
				unCompImg.get(i).setY(height*.8f);
				s.addActor(unCompImg.get(i));
			}
		}
		for(int i = tasklist.size()/2; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				compImg.get(i).setX((i+1-tasklist.size()/2)*(width*.15f));
				compImg.get(i).setY(height*.6f);
				s.addActor(compImg.get(i));
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				unCompImg.get(i).setX((i+1-tasklist.size()/2)*(width*.15f));
				unCompImg.get(i).setY(height*.6f);
				s.addActor(unCompImg.get(i));
			}
		}
		
	}
	private void createBackButton(){
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(0);
		btnB.setY(height-btnB.getHeight());
		System.out.println(btnB.getHeight());
		btnB.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.welcome);
				System.out.println("back");
				return true;
			}
		});

		s.addActor(btnB);
	}

	public void render(float delta){

		Gdx.gl.glClearColor(205/255f, 242/255f, 250/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();

		
	}
	
	public void resize(int x, int y){
		this.show();
	}
	
	public void helpFromMainClick() {
//		int clickX = Gdx.input.getX();
//		int clickY = Gdx.input.getY();
//		if (clickX >= width/25 && clickX <= (width/25 + btnBack.getWidth()) 
//				&& clickY <= height-(height*.9) && clickY >= height-(height*.9)-btnBack.getHeight()) {
//			game.setScreen(game.welcome);
//		}
	}
}
