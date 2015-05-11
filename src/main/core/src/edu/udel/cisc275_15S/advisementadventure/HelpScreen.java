
package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class HelpScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	
	Texture btnBack;
	Texture trans;
	Texture glass;
	Image btnB;
	
	BitmapFont font;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	String helpDisplay;
	ArrayList<Task> tasklist;
	Texture star;
	ArrayList<Vector2> vecArr;
	float speedx;
	float speedy;
	Stage s;
	private Viewport viewport;
	private Camera camera;
	private Vector2 pos0;
	private Vector2 pos1;
	private Vector2 pos2;
	private Vector2 pos3;
	private Vector2 pos4;
	private Vector2 pos5;
	private Vector2 pos6;
	private Vector2 pos7;
	private Vector2 pos8;
	private Vector2 pos9;
	private Vector2 exp1;
	private Vector2 exp2;
	private Vector2 exp3;
	private Vector2 exp4;
	private boolean[] sawComp;
	private boolean[] changePic;
	private boolean[] returnScreen;
	private ShapeRenderer shapeRend;
	private boolean explosion;
	Sound chime;
	Sound awesome;
	private boolean canBack;
	
	
	public HelpScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_Back.png");
		trans = new Texture("blackTransparent.png");
		glass = new Texture("glass.jpg");
		s = new Stage();
		createBackButton();
		this.tasklist = g.taskList;
		star = new Texture("star.png");
		vecArr = new ArrayList<Vector2>();
		shapeRend = new ShapeRenderer();
		
		explosion = false;
		sawComp = new boolean[10];
		changePic = new boolean[10];
		returnScreen = new boolean[10];
		chime = Gdx.audio.newSound(Gdx.files.internal("chime.mp3"));
		
		for(int i=0; i<sawComp.length; i++){
			sawComp[i] = false;
			changePic[i] = false;
			returnScreen[i] = false;
		}
		exp1 = new Vector2(width, height);exp2 = new Vector2(width, height);
		exp3 = new Vector2(width, height);exp4 = new Vector2(width, height);
		pos0 = new Vector2(width,height);pos1 = new Vector2(width,height);pos2 = new Vector2(width,height);
		pos3 = new Vector2(width,height);pos4 = new Vector2(width,height);pos5 = new Vector2(width,height);
		pos6 = new Vector2(width,height);pos7 = new Vector2(width,height);pos8 = new Vector2(width,height);
		pos9 = new Vector2(width,height);
		vecArr.add(pos0);vecArr.add(pos1);vecArr.add(pos2);vecArr.add(pos3);vecArr.add(pos4);
		vecArr.add(pos5);vecArr.add(pos6);vecArr.add(pos7);vecArr.add(pos8);vecArr.add(pos9);
		
		
	}
	
	

	// Prototype assumes no current task. Final implementation will retrieve current task
	// from MyGdxGame and display as string. Help suggestions will be tied to "type" of task.
	// Game will have a list of tasks. Tasks will be marked as completed as they are finished.
	
	public String taskSelector(MyGdxGame g) {
		// if (g.getCurrentTask()) {
		//	return currentTask;
		//} else {
		return "No current task.";
		//}
	}
	
	
	@Override
	public void render(float delta){
		
		
		
		//get coordinates of mouse
		batch.begin();
		Gdx.gl.glClearColor(205/255f, 242/255f, 250/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
		for(int i = 0; i < tasklist.size()/2; i++){
			if(tasklist.get(i).isCompleted() && changePic[i]){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1)*(width*.15f), height*.8f, tasklist.get(i).getUncompletedPic().getWidth(),tasklist.get(i).getUncompletedPic().getHeight());
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
		}
		for(int i = tasklist.size()/2; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted() && changePic[i]){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
		}
		
		
    	//write tasks
    	for(int i = 0; i < tasklist.size(); i++){
    		if(tasklist.get(i).isCompleted()){
    			if(vecArr.get(i).x <= width*.073)
    				font.setColor(Color.BLACK);
				font.draw(batch, "Task " + (i+1) + ": " + tasklist.get(i).getDescription(), width*.1f, height*.6f - height*.05f*(i+1));
				
    		}
			else{
				font.setColor(Color.LIGHT_GRAY);
				font.draw(batch, "Task " + (i+1) + ": " + tasklist.get(i).getDescription(), width*.1f, height*.6f - height*.05f*(i+1));
			}
    		
    	}
    	//draw star if completed
    	for(int i=0; i<tasklist.size(); i++){
    		if(sawComp[i]){
    			batch.draw(star, vecArr.get(i).x, vecArr.get(i).y, vecArr.get(i).x*.5f, vecArr.get(i).x*.5f);
    		}
    		if(tasklist.get(i).isCompleted() && !sawComp[i]){
    			
    		
    			if(vecArr.get(i).x >= width*.75f && vecArr.get(i).x <= width*.77f){
    				chime.play();
    				tasklist.get(i).setSeen();
    				
    			}
    	    	speedx = width+75-vecArr.get(i).x;
    	    	speedy = vecArr.get(i).y +35- (height*.57f - height*.05f*(i+1));
    			if(vecArr.get(i).x >= width*.073f){
    				vecArr.get(i).x -= speedx *Gdx.graphics.getDeltaTime();
    			}
    			if(vecArr.get(i).y >= height*.57f - height*.05f*(i+1)){
    				vecArr.get(i).y -= (speedy+5*i) *Gdx.graphics.getDeltaTime();
    			}
    	    	batch.draw(star, vecArr.get(i).x, vecArr.get(i).y, vecArr.get(i).x*.5f, vecArr.get(i).x*.5f);
    	    	if(vecArr.get(i).x <= width*.073f){
    	    		changePic[i] = true;
    	    		starExplosion(vecArr.get(i).x, vecArr.get(i).y, i);
    	    	}
        	}
    	}

    	
		
		
    	batch.end();
    	s.draw();
    	s.act();
	}
	
	public void starExplosion(float x, float y, int index){
		
		int speed = 550;
		if(!explosion){
			exp1.x = x+vecArr.get(index).x*.25f;
			exp1.y = y+vecArr.get(index).x*.25f;
			exp2.x = x+vecArr.get(index).x*.25f;
			exp2.y = y+vecArr.get(index).x*.25f;
			exp3.x = x+vecArr.get(index).x*.25f;
			exp3.y = y+vecArr.get(index).x*.25f;
			exp4.x = x+vecArr.get(index).x*.25f;
			exp4.y = y+vecArr.get(index).x*.25f;
			explosion = true;
		}
		exp1.x -= speed*Gdx.graphics.getDeltaTime();
		exp2.x += speed*Gdx.graphics.getDeltaTime();
		exp3.x -= speed*Gdx.graphics.getDeltaTime();
		exp4.x += speed*Gdx.graphics.getDeltaTime();
		
		if(exp1.y < height+1000 && exp2.y < height+1000 && exp3.y > -1000 && exp4.y>-1000){
			exp1.y += speed*Gdx.graphics.getDeltaTime();
			exp2.y += speed*Gdx.graphics.getDeltaTime();
			exp3.y -= speed*Gdx.graphics.getDeltaTime();
			exp4.y -= speed*Gdx.graphics.getDeltaTime();
		}else{
			explosion = false;
			
			//returnScreen[index] = true;
			sawComp[index] = true;
			
			
		}
		batch.draw(star, exp1.x, exp1.y, 10, 10);
		batch.draw(star, exp2.x, exp2.y, 10, 10);
		batch.draw(star, exp3.x, exp3.y, 10, 10);
		batch.draw(star, exp4.x, exp4.y, 10, 10);

	}
	
	
	public void show(){
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		
		batch = new SpriteBatch();
		s = new Stage();
	
		createBackButton();
		s.addActor(btnB);
		
		Gdx.input.setInputProcessor(s);
	}
	
	private void createBackButton(){
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		//System.out.println(btnB.getHeight());
		btnB.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new HomeScreen(game));
				System.out.println("back");
				return true;
			}
		});
		btnB.setX(0);
		btnB.setY(height*.9f);
	}
	
	
	
}

