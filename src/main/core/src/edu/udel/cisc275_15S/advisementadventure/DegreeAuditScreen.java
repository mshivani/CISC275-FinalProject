package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DegreeAuditScreen extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;
	float height;
	float width;
	Stage s;
	Texture btnBack;
	Image btnB;
	Texture bg;
	//Texture activeCourses;
	
	public DegreeAuditScreen(MyGdxGame g){
		this.game = g;
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
	
	@Override
	public void show() {
		if(game.currentTask==4){
			game.currText=1;
			game.currentTask=5;
			System.out.println("hit it audit");
		}
		bg = new Texture("univReq.png");
		//activeCourses = new Texture("activeCourses.png");

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		createBackButton();
		
		batch = new SpriteBatch();
		
		s = new Stage();
		Gdx.input.setInputProcessor(s);
		s.addActor(btnB);
		s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		
	}
	
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		batch.begin();
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(bg, btnB.getWidth()+10, 0, width, height);
		//batch.draw(activeCourses, 0, btnB.getWidth()+10+bg.getWidth());
		
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

