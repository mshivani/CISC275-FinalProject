package edu.udel.cisc275_15S.advisementadventure;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HomeScreen extends ScreenAdapter{

	MyGdxGame game;
	Skin uiskin;
	Stage s;
	SpriteBatch batch;
	BitmapFont font;
	Texture btnBack;
	Image btnB;
	Texture btnNotes;
	Image btnN;
	Label notesL;
	Texture btnWeb;
	Image btnW;
	Label webL;
	Texture btnText;
	Image btnT;
	Label textL;
	Texture btnEmail;
	Image btnE;
	Label emailL;
	Texture btnHelp;
	Image btnH;
	Label helpL;
	float height;
	float width;
	Texture bg;
	Texture arr;
	Image arrow;

	public HomeScreen(MyGdxGame g){
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		
	}
	@Override
	public void show(){
		s=new Stage();
		
		bg = new Texture("homescreen-bg.png");
		Image bgI = new Image(bg);
		s.addActor(bgI);
		batch = new SpriteBatch();
		font = new BitmapFont();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		bgI.setBounds(0, 0, width, height);
		font.setColor(0, 0, 0, 1);
		//	createBackButton();
		createNotesButton();
		createWebButton();
		createTextButton();
		createEmailButton();
		createHelpButton();
		Gdx.input.setInputProcessor(s);
	}
	public void createHelpButton(){
		helpL = new Label("Trophies", uiskin);
		btnHelp = new Texture("trophy-widget.png");
		btnH = new Image(btnHelp);
		btnH.setX(btnE.getX()+btnE.getWidth()+30);
		btnH.setY(btnT.getY());
		btnH.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(game.help);
				return true;
			}
		});
		helpL.setX(btnH.getX()+(btnH.getWidth()/4)-10);
		helpL.setY(btnH.getY()- helpL.getHeight());
		helpL.setColor(Color.BLACK);
		s.addActor(helpL);
		s.addActor(btnH);
	}
	public void createEmailButton(){
		emailL = new Label("Email", uiskin);
		btnEmail = new Texture("email-widget.png");
		btnE = new Image(btnEmail);
		btnE.setX(btnT.getX()+btnT.getWidth()+30);
		btnE.setY(btnT.getY());
		btnE.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new EmailListScreen(game));
				return true;
			}
		});
		emailL.setX(btnE.getX()+(btnE.getWidth()/4));
		float temp1  = emailL.getHeight();
		emailL.setY(btnE.getY()-temp1);
		emailL.setColor(Color.BLACK);
		s.addActor(btnE);
		s.addActor(emailL);
	}
	public void createTextButton(){
		textL = new Label("Text", uiskin);
		btnText = new Texture("text-widget.png");
		btnT = new Image(btnText);
		btnT.setX(btnW.getX()+ btnW.getWidth()+30);
		btnT.setY(btnW.getY());
		btnT.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(game.currText >= 0)
					game.setScreen(new TextScreen(game, game.questionList.get(game.currText)));
				return true;
			}
		});
		float temp1  = textL.getHeight();
		textL.setX(btnT.getX() + btnT.getWidth()/4);
		textL.setY(btnT.getY() - temp1);
		textL.setColor(Color.BLACK);
		s.addActor(btnT);
		s.addActor(textL);
	}
	public void createWebButton(){
		webL = new Label("Web", uiskin);
		btnWeb = new Texture("web-widget.png");
		btnW = new Image(btnWeb);
		btnW.setX(btnN.getX() + btnN.getWidth()+30);
		btnW.setY((float) (height/1.5));
		btnW.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(game.currentTask>=0)
					game.setScreen(game.web);
				System.out.println("web");
				return true;
			}
		});
		float temp1  = webL.getHeight();
		webL.setX(btnW.getX() + btnW.getWidth()/4);
		webL.setY(btnW.getY() - temp1);
		webL.setColor(Color.BLACK);
		s.addActor(btnW);
		s.addActor(webL);
	}
	public void createNotesButton(){
		notesL = new Label("Notes", uiskin);
		btnNotes = new Texture("notes-widget.png");
		btnN = new Image(btnNotes);
		btnN.setX((float) (width/9));
		btnN.setY((float) (height/1.5));
		btnN.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new NoteMenu(game));
				return true;
			}
		});
		float temp1  = notesL.getHeight();
		notesL.setX(btnN.getX() + btnN.getWidth()/4);
		notesL.setY(btnN.getY() - temp1);
		notesL.setColor(Color.BLACK);
		s.addActor(btnN);
		s.addActor(notesL);
	}
	
	//TODO draw arrow on screen
	public void createIndicatorArrow(){
		arr = new Texture("arrow.png");
		arrow = new Image(arr);
		if (game.currText == -1 && game.currentTask == 1) {
			arrow.setX((float) btnE.getImageX());
			arrow.setY((float) btnE.getImageY());
		}
	}
	
	@Override
	public void render(float delta){
		//if(Gdx.input.isTouched()){
		//	homeScreenClick();
		//}
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		s.draw();
		s.act();
		
		batch.begin();
		
		
	}
	@Override 
	public void resize(int x, int y){
		this.show();
	}

	public void homeScreenClick() {
		//	stack.add(currentScreen);
		//System.out.println("X: " + Gdx.input.getX() + ", Y: "+ Gdx.input.getY());
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		if (clickX >= 70 && clickX <= 150 && clickY >= 50 && clickY <= 180) {
			game.setScreen(new NoteMenu(game));
		} else if (clickX >= 170 && clickX <= 250 && clickY >= 50
				&& clickY <= 180) {
			game.setScreen(game.web);

		} else if (clickX >= 270 && clickX <= 350 && clickY >= 50
				&& clickY <= 180) {
			//Random x = new Random();
			System.out.println(game.questionList.size());
			game.setScreen(new TextScreen(game, game.questionList.get(1)));

		} else if (clickX >= 370 && clickX <= 450 && clickY >= 50
				&& clickY <= 180) {
			game.setScreen(game.email);
			//	dispose();
		} else if (clickX >= 470 && clickX <= 550 && clickY >= 50
				&& clickY <= 180) {
			game.setScreen(game.helpFromMain);
		}

	}

}

