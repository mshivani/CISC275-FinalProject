package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture dropAddText;
	Texture textReply;
	Texture textOptions;
	Texture textTitle;
	BitmapFont font;
	boolean correctAns = false;
	boolean touched = false;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	public TextScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		dropAddText = new Texture("drop-add-text.png");
		textReply = new Texture("textReply.png");
		textOptions = new Texture("textOptions.png");
		textTitle = new Texture("textTitle.png");
	}
	
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			textScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //font.draw(batch, "text", 150, 150);
        batch.draw(dropAddText, 100, 300);
        batch.draw(btnBack, 25, 425);
        batch.draw(textTitle, width/4, height/1.2f, width/2, height/5.5f);
        if(correctAns == true){
            batch.draw(textReply, 380, 250); 
        }
    	if(touched == true && correctAns == false){
    	font.draw(batch, "Try Again", 150, 150);
    	}
    	if(correctAns == false){
    		batch.draw(textOptions, 25, 50, 570, 50);
    	}
    	if(correctAns == false && touched == false){
    		font.draw(batch, "Choose an answer to reply back", 150, 150);
    	}
    	
    	batch.end();
	}
	
	public void textScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
	//	System.out.println("Width: "+ width  + " height: " + height);
	//	System.out.println("x: " + clickX + "y: " + clickY);
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
			game.setScreen(game.welcome);
		}
		if (clickX >= 28 && clickX <= 208 && clickY >= 382 && clickY <= 421) {
			touched = true;
			correctAns = false;
		}
		if (clickX >= 404 && clickX <= 583 && clickY >= 382 && clickY <= 421) {
			touched = true;
			correctAns = false;
		}
		if (clickX >= 215 && clickX <= 393 && clickY >= 382 && clickY <= 421) {
			correctAns = true;
		}
	}
}
