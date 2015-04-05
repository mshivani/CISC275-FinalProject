package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AddDropScreen extends ScreenAdapter {
	
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture addDropTitle;
	BitmapFont font;
	BitmapFont font2;
	boolean valid;
	ArrayList<String> currentList;
	ArrayList<String> dropAddList;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	
	public AddDropScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font2.setColor(1, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		addDropTitle = new Texture("AddDropTitle.png");
		currentList = new ArrayList<String>();
		dropAddList = new ArrayList<String>();
		dropAddList.add("MUSC 100");
		dropAddList.add("CHEM 103");
		dropAddList.add("ENGL 110");
		dropAddList.add("PHYS 207");
		dropAddList.add("CISC 108");
		dropAddList.add("HIST 104");
		dropAddList.add("ECON 101");
		dropAddList.add("UNIV 101");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			AddDropScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(btnBack, 25, 425);
        batch.draw(addDropTitle, width/4, height/1.2f, width/2, height/5.5f);
        float tempWidth = width/2.2f;
        float tempHeight = height/3f;
        for(int i = 0; i <  dropAddList.size(); i++){
        font.draw(batch,  dropAddList.get(i), tempWidth, tempHeight);
        tempHeight += 30;
        }
    	batch.end();
	}
	
	public void AddDropScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		//System.out.println("Width: "+ width  + " height: " + height);
		//System.out.println("x: " + clickX + "y: " + clickY);
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100 && Gdx.input.justTouched()) {
			game.setScreen(game.udsis);
		}
		if (clickX >= width/2.246f && clickX <= width/1.734f && clickY >= height/4.528f && clickY <= height/1.416f && Gdx.input.justTouched()) {
			textInput();
		}
	}
	
	public void textInput(){
		Gdx.input.getTextInput(new TextInputListener(){
			int clickX = Gdx.input.getX();
			int clickY = Gdx.input.getY();
		
			@Override
			public void input(String text) {
		
				if (clickY >= height/4.528f && clickY <= height/3.664f) {
					if (text.contentEquals("add") && currentList.contains("UNIV 101") == false){
						currentList.add("UNIV 101");
					}
					if(text.contentEquals("drop") && currentList.contains("UNIV 101")){
						currentList.remove("UNIV 101");
					}
				}
				if (clickY >= height/3.664f && clickY <= height/2.981f) {
					if (text.contentEquals("add") && currentList.contains("ECON 101") == false){
						currentList.add("ECON 101");
					}
					if(text.contentEquals("drop") && currentList.contains("ECON 101")){
						currentList.remove("ECON 101");
					}
				}
				if (clickY >= height/2.981f && clickY <= height/2.513f) {
					if (text.contentEquals("add") && currentList.contains("HIST 104") == false){
						currentList.add("HIST 104");
					}
					if(text.contentEquals("drop") && currentList.contains("HIST 104")){
						currentList.remove("HIST 104");
					}
				}
				
				if (clickY >= height/2.513f && clickY <= height/2.172f) {
					if (text.contentEquals("add") && currentList.contains("CISC 108") == false){
						currentList.add("CISC 108");
					}
					if(text.contentEquals("drop") && currentList.contains("CISC 108")){
						currentList.remove("CISC 108");
					}
				}
				
				if (clickY >= height/2.172f && clickY <= height/1.912f) {
					if (text.contentEquals("add") && currentList.contains("PHYS 207") == false){
						currentList.add("PHYS 207");
					}
					if(text.contentEquals("drop") && currentList.contains("PHYS 207")){
						currentList.remove("PHYS 207");
					}
				}
				if (clickY >= height/1.912f && clickY <= height/1.720f) {
					if (text.contentEquals("add") && currentList.contains("ENGL 110") == false){
						currentList.add("ENGL 110");
					}
					if(text.contentEquals("drop") && currentList.contains("ENGL 110")){
						currentList.remove("ENGL 110");
					}
				}
				
				if (clickY >= height/1.720f && clickY <= height/1.534f) {
					if (text.contentEquals("add") && currentList.contains("CHEM 103") == false){
						currentList.add("CHEM 103");
					}
					if(text.contentEquals("drop") && currentList.contains("CHEM 103")){
						currentList.remove("CHEM 103");
					}
				}
				if (clickY >= height/1.534f && clickY <= height/1.416f) {
					if (text.contentEquals("add") && currentList.contains("MUSC 100") == false){
						currentList.add("MUSC 100");
					}
					if(text.contentEquals("drop") && currentList.contains("MUSC 100")){
						currentList.remove("MUSC 100");
					}
				}
				
			}
			@Override
			public void canceled() {
				
			}
		}, "Would you like to add or drop a course?", null, "Enter 'add' or 'drop'");	
	}

}
