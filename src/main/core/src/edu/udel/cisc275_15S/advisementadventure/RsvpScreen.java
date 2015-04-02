package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class RsvpScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture RSVPtitle;
	BitmapFont font;
	BitmapFont font2;
	boolean rsvpEvent1 =  false;
	boolean rsvpEvent2 =  false;
	boolean rsvpEvent3 =  false;
	boolean rsvpEvent4 =  false;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	
	public RsvpScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font2.setColor(1, 0, 0, 1);
		//font.setScale(width/500, height/450);
		RSVPtitle = new Texture("RSVPtitle.png");
		btnBack = new Texture("btn_back.png");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			RsvpScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(RSVPtitle, width/4, height/1.2f, width/2, height/5.5f);
        font.draw(batch, "1. Meet the Firms Night: Sept 9, 2015", width/4.2f, height/1.3f);
    	font.draw(batch, "2. Law School Fair: Oct 14, 2015", width/4.2f, height/1.5f);
    	font.draw(batch, "3. Consulting/Advisory Networking Night: March 9, 2016", width/4.2f, height/1.75f);
      	font.draw(batch, "4. J.P.Morgan Dining Etiquette: April 21, 2015", width/4.2f, height/2.1f);
    	batch.draw(btnBack, 25, 425);
    	if(rsvpEvent1 == true){
    		font2.draw(batch, "Congrats! You have registered for Event 1", width/4.2f, height/3f);
    	}
    	if(rsvpEvent2 == true){
    		font2.draw(batch, "Congrats! You have registered for Event 2", width/4.2f, height/4f);
    	}
    	if(rsvpEvent3 == true){
    		font2.draw(batch, "Congrats! You have registered for Event 3", width/4.2f, height/5.5f);
    	}
    	if(rsvpEvent4 == true){
    		font2.draw(batch, "Congrats! You have registered for Event 4", width/4.2f, height/9f);
    	}
    	batch.end();
	}
	
	public void RsvpScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		//System.out.println("Width: "+ width  + " height: " + height);
		//System.out.println("x: " + clickX + " y: " + clickY);
		
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100 && Gdx.input.justTouched()) {
			game.setScreen(game.web);
		}
		
		if (clickX >= width/4.238f && clickX <= width/1.616f && clickY >= height/4.403f && clickY <= height/3.84f && Gdx.input.justTouched()) {
			Gdx.input.getTextInput(new TextInputListener(){
				@Override
				public void input(String text) {
					if (text.isEmpty()){
						rsvpEvent1 = false;
					}
					else {
						rsvpEvent1 = true;
					}

				}
				@Override
				public void canceled() {
					rsvpEvent1 = false;
				}
			}, "Enter Student ID Number", null, "ID");
    	}
		
		if (clickX >= width/4.238f && clickX <= width/1.768f && clickY >= height/3.097f && clickY <= height/2.712f && Gdx.input.justTouched()) {
			Gdx.input.getTextInput(new TextInputListener(){
				@Override
				public void input(String text) {
					if (text.isEmpty()){
						rsvpEvent2 = false;
					}
					else {
						rsvpEvent2 = true;
					}

				}
				@Override
				public void canceled() {
					rsvpEvent2 = false;
				}
			}, "Enter Student ID Number", null, "ID");
    	}
		
		if (clickX >= width/4.238f && clickX <= width/1.257f && clickY >= height/2.376f && clickY <= height/2.172f && Gdx.input.justTouched()) {
			Gdx.input.getTextInput(new TextInputListener(){
				@Override
				public void input(String text) {
					if (text.isEmpty()){
						rsvpEvent3 = false;
					}
					else {
						rsvpEvent3 = true;
					}

				}
				@Override
				public void canceled() {
					rsvpEvent3 = false;
				}
			}, "Enter Student ID Number", null, "ID");
    	}
		
		if (clickX >= width/4.238f && clickX <= width/1.425f && clickY >= height/1.983f && clickY <= height/1.784f && Gdx.input.justTouched()) {
			Gdx.input.getTextInput(new TextInputListener(){
				@Override
				public void input(String text) {
					if (text.isEmpty()){
						rsvpEvent4 = false;
					}
					else {
						rsvpEvent4 = true;
					}

				}
				@Override
				public void canceled() {
					rsvpEvent4 = false;
				}
			}, "Enter Student ID Number", null, "ID");
		}
		
	}
	
	

}
