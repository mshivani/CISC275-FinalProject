package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UDSIS_Screen extends ScreenAdapter {
	
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	
	Texture back;
	Image btnBack;
	
	Texture udBanner;
	Texture academicsHeader;
	Texture academicsBar;
	Texture advisorBox;
	
	Texture ClassSch;
	Image btnClassSch;
	
	Texture addDrop;
	Image btnAddDrop;
	
	Texture major;
	Image btnMajor;
	
	Texture audit;
	Image btnAudit;
	
	BitmapFont font;
	Stage s;
	float width;
	float height;
	
	public UDSIS_Screen(MyGdxGame g){
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	
	public void show(){
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		batch  = new SpriteBatch();
		
		s = new Stage();
		
		udBanner = new Texture("udSisLogo.png");
		academicsHeader = new Texture("academics-header.png");
		academicsBar = new Texture("academics-bar.png");
		advisorBox = new Texture("advisor-box.png");
		
		back = new Texture("btn_back.png");
		btnBack = new Image(back);
		btnBack.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.web);
				return true;
			}
		});
		btnBack.setX(0);
		btnBack.setY(height - btnBack.getHeight());
		s.addActor(btnBack);
		
		
		ClassSch = new Texture("schedule-btn.png");
		btnClassSch = new Image(ClassSch);
		btnClassSch.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.schedule);
				return true;
			}
		});
		btnClassSch.setBounds(10, height - (udBanner.getHeight()+academicsBar.getHeight()+50),  btnClassSch.getWidth(), btnClassSch.getHeight());
		s.addActor(btnClassSch);
		     
		
		addDrop = new Texture("reg-btn.png");
		btnAddDrop = new Image(addDrop);
		btnAddDrop.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.addDrop);
				return true;
			}
		});
		btnAddDrop.setBounds(10, height - (udBanner.getHeight()+academicsBar.getHeight()+50) - btnClassSch.getHeight(),  btnAddDrop.getWidth(), btnAddDrop.getHeight());
		s.addActor(btnAddDrop);
		
		
		major = new Texture("decmajor-btn.png");
		btnMajor = new Image(major);
		btnMajor.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.major);
				return true;
			}
		});
		btnMajor.setBounds(10, height - (udBanner.getHeight()+academicsBar.getHeight()+50) - btnClassSch.getHeight() - btnAddDrop.getHeight(),  btnMajor.getWidth(), btnMajor.getHeight());
		s.addActor(btnMajor);
		
		audit = new Texture("audit-btn.png");
		btnAudit = new Image(audit);
		btnAudit.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.degreeAudit);
				return true;
			}
		});
		btnAudit.setBounds(10, height - (udBanner.getHeight()+academicsBar.getHeight()+50) - btnClassSch.getHeight() - btnAddDrop.getHeight() - btnMajor.getHeight(),  btnAudit.getWidth(), btnAudit.getHeight());
		s.addActor(btnAudit);
		
		Gdx.input.setInputProcessor(s);
	}
	
	@Override
	public void render(float delta){
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(udBanner, btnBack.getWidth() + 15, height-height/9.5f);
		batch.draw(academicsHeader, 0, height-height/5.5f);
		batch.draw(academicsBar, academicsHeader.getWidth(), height-height/5.5f, width, academicsBar.getHeight());
		batch.draw(advisorBox, width/1.90f, height-height/1.75f);
		batch.end();
		s.act();
		s.draw();
	}
	
	@Override
	public void resize(int x, int y){
		this.show();
	}
	
	

}
