package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScheduleScreen extends ScreenAdapter{
	
	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	Texture Schedule;
	Texture SchTitle;
	BitmapFont font;
	BitmapFont font2;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	public ScheduleScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font2 = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font2.setColor(1, 0, 0, 1);
		btnBack = new Texture("btn_back.png");
		Schedule = new Texture("schLogo.png");
		SchTitle = new Texture("schTitle.png");
	}
	@Override
	public void render(float delta){
		if(Gdx.input.isTouched()){
			SechScreenClick();
		}
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(Schedule, 0, height-height/5.5f, width, height/5.5f);
        batch.draw(SchTitle, 25, height-(2*(height/5.64f)), width, height/6.5f);
    	batch.draw(btnBack, 25, 425);
      	float tempWidth = width/2.5f;
        float tempHeight = height/1.3f - 50;
      	for(int i = 0; i < game.addDrop.currentList.size(); i++){
             font.draw(batch,  game.addDrop.currentList.get(i), tempWidth, tempHeight);
             tempHeight -= 30;
        } 
      	if(game.addDrop.currentList.isEmpty()){
      		font2.draw(batch, "You Have No Scheduled Classes", width/3.2f, height/2f);
      	}
    	batch.end();
	}
	
	public void SechScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		System.out.println("x: " + clickX + "\n");
		System.out.println("x: " + clickY + "\n");
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100 && Gdx.input.justTouched()) {
			game.setScreen(game.udsis);
		}
	}

}
