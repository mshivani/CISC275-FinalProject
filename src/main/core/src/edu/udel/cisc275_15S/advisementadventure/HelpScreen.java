package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
	private boolean[] sawComp;
	private ShapeRenderer shapeRend;
	
	
	public HelpScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_Back.png");
		trans = new Texture("blackTransparent.png");
		//s = new Stage();
		//createBackButton();
		this.tasklist = g.taskList;
		star = new Texture("star.png");
		vecArr = new ArrayList<Vector2>();
		shapeRend = new ShapeRenderer();
		//s.addActor(btnB);
		sawComp = new boolean[10];
		for(int i=0; i<sawComp.length; i++){
			sawComp[i] = false;
		}
		
		pos0 = new Vector2(width,height);pos1 = new Vector2(width,height);pos2 = new Vector2(width,height);
		pos3 = new Vector2(width,height);pos4 = new Vector2(width,height);pos5 = new Vector2(width,height);
		pos6 = new Vector2(width,height);pos7 = new Vector2(width,height);pos8 = new Vector2(width,height);
		pos9 = new Vector2(width,height);
		vecArr.add(pos0);vecArr.add(pos1);vecArr.add(pos2);vecArr.add(pos3);vecArr.add(pos4);
		vecArr.add(pos5);vecArr.add(pos6);vecArr.add(pos7);vecArr.add(pos8);vecArr.add(pos9);
	}
	
	
//	public void create() {
//	    camera = new PerspectiveCamera();
//	    viewport = new FitViewport(width, height, camera);
//	}
//	public void resize(int width, int height) {
//	    viewport.update(width, height);
//	}
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
		
		if(Gdx.input.isTouched()){
			helpScreenClick();
		}
		
		//get coordinates of mouse
		batch.begin();
		Gdx.gl.glClearColor(205/255f, 242/255f, 250/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        
        //width = Gdx.graphics.getWidth();
		//height = Gdx.graphics.getHeight();
		batch.draw(btnBack, (float)(width/25), (float) (height*.9f));
        
		for(int i = 0; i < tasklist.size()/2; i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1)*(width*.15f), height*.8f);
			}
		}
		for(int i = tasklist.size()/2; i < tasklist.size(); i++){
			if(tasklist.get(i).isCompleted()){
				font.setColor(Color.BLACK);
				batch.draw(tasklist.get(i).getCompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
			else{
				font.setColor(Color.LIGHT_GRAY);
				batch.draw(tasklist.get(i).getUncompletedPic(), (i+1-tasklist.size()/2)*(width*.15f), height*.6f);
			}
		}
		
		
    	
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
    	for(int i=0; i<sawComp.length; i++){
    		if(sawComp[i]){
    			batch.draw(star, vecArr.get(0).x, vecArr.get(0).y, vecArr.get(0).x*.5f, vecArr.get(0).x*.5f);
    		}
    	}
    	if(tasklist.get(0).isCompleted() && !sawComp[0]){
	    	speedx = width+75-vecArr.get(0).x;
	    	speedy = vecArr.get(0).y +35- (height*.57f - height*.05f*(1));
			if(vecArr.get(0).x >= width*.073f){
				vecArr.get(0).x -= speedx *Gdx.graphics.getDeltaTime();
			}
			if(vecArr.get(0).y >= height*.57f - height*.05f*(1)){
				vecArr.get(0).y -= speedy *Gdx.graphics.getDeltaTime();
			}
	    	batch.draw(star, vecArr.get(0).x, vecArr.get(0).y, vecArr.get(0).x*.5f, vecArr.get(0).x*.5f);
	    	if(vecArr.get(0).x <= width*.073f){
	    		batch.draw(trans, 0, 0, width, height);
	    		font.setColor(217, 141, 32, 1);
    			font.draw(batch, "Congrats on Task 1!", width*.4f, height*.6f);
    			font.draw(batch, "click anywhere to return", width*.35f, height*.5f);
	    		if(Gdx.input.isTouched()){
	    			sawComp[0] = true;
	    			game.setScreen(game.email2);
	    		}
	    		//starExplosion(vecArr.get(0).x, vecArr.get(0).y);
	    	}
    	}
    	if(tasklist.get(4).isCompleted()){
	    	speedx = width+75-vecArr.get(4).x;
	    	speedy = vecArr.get(0).y +35- (height*.57f - height*.05f*(5));
			if(vecArr.get(4).x >= width*.073f){
				vecArr.get(4).x -= speedx *Gdx.graphics.getDeltaTime();
			}
			if(vecArr.get(4).y >= height*.57f - height*.05f*(5)){
				vecArr.get(4).y -= speedy *Gdx.graphics.getDeltaTime();
			}
	    	batch.draw(star, vecArr.get(4).x, vecArr.get(4).y, vecArr.get(4).x*.5f, vecArr.get(4).x*.5f);
	    	if(vecArr.get(4).x <= width*.073f){
	    		sawComp[4] = true;
	    		//starExplosion(vecArr.get(0).x, vecArr.get(0).y);
	    	}
    	}
//    	font.setColor(Color.BLACK);
//        font.draw(batch, width+"", 400, 100);
//        font.draw(batch, height+"", 450, 100);
//        font.draw(batch, Gdx.input.getX()+"", 400, 50);
//        font.draw(batch, Gdx.input.getY()+"", 450, 50);
//    	s.draw();
//      s.act();
    	batch.end();
	}
	
//	public void starExplosion(float x, float y){
//		if(y >= 0){
//			batch.draw(star, x, y, 10, 10);
//			y -= 10 *Gdx.graphics.getDeltaTime();
//		}
//	}
	
//	
//	public void show(){
//		Gdx.input.setInputProcessor(s);
//	}
//	
//	private void createBackButton(){
//		btnBack = new Texture("btn_back.png");
//		btnB = new Image(btnBack);
//		//System.out.println(btnB.getHeight());
//		btnB.addListener(new ClickListener(){
//			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
//				game.setScreen(new HomeScreen(game));
//				System.out.println("back");
//				return true;
//			}
//		});
//		btnB.setX(0);
//		btnB.setY(height*.9f);
//	}
	
	
	
	public void helpScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= width/25 && clickX <= (width/25 + btnBack.getWidth()) 
				&& clickY <= height-(height*.9) && clickY >= height-(height*.9)-btnBack.getHeight()) {
			game.setScreen(game.welcome);
		}
	}
}
