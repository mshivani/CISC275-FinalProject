package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TextScreen extends ScreenAdapter{
	MyGdxGame game;
	SpriteBatch batch;
	Skin uiskin;
	Texture btnBack;
	Texture textComp;
	Image textC;
	Image btnB;
	Label roommate;
	Label wrong;
	Question currentQuestion;
	//Texture dropAddText;
	Texture textReply;
	//Texture textOptions;
	//Texture textTitle;
	//BitmapFont font;
	Stage s;
	TextButton r1,r2,r3,r4;
	//boolean correctAns = false;
	//boolean touched = false;
	float width;
	float height;
	Image textR;

	public TextScreen(MyGdxGame g, Question q) {
		width = Gdx.graphics.getWidth();
		height= Gdx.graphics.getHeight();
		currentQuestion = q;
		this.game = g;
		s= new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		//font = new BitmapFont();
		createLabel();
		createBackButton();
		createChatBubble();
		createResponses();
		//createReply();
		wrong = new Label("Please try again later", uiskin);
		wrong.setX(width/2 - wrong.getWidth()/2);
		wrong.setY(r1.getHeight() + r2.getHeight()+10);
		wrong.setColor(Color.BLACK);
		s.addActor(r1);
		s.addActor(r2);
		s.addActor(r3);
		s.addActor(r4);
		s.addActor(btnB);
		s.addActor(roommate);

	}
	
	public void createResponses(){
		final ArrayList<String> resp = currentQuestion.getResponses();	
		r1 = new TextButton(resp.get(0), uiskin);
		r2 = new TextButton(resp.get(1), uiskin);
		r3 = new TextButton(resp.get(2), uiskin);
		r4 = new TextButton("I don't know", uiskin);
		r1.setX(0);
		r1.setWidth(width/2);
		r1.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(currentQuestion.getCorrectResponse().response.equals(resp.get(0))){
					System.out.println("Correct");
					createReply(currentQuestion.getCorrectResponse().response);
				}
				else{
					s.addActor(wrong);
					//game.setScreen(new HomeScreen(game));
				}
				return true;
			}
		});
		r2.setX(width -r1.getWidth());
		r2.setWidth(width/2);
		r2.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(currentQuestion.getCorrectResponse().response.equals(resp.get(1))){
					System.out.println("Correct");
					createReply(currentQuestion.getCorrectResponse().response);
				}
				else{
					s.addActor(wrong);
					//game.setScreen(new HomeScreen(game));
				}
				return true;
			}
		});
		r3.setY(r1.getHeight());
		r3.setX(0);
		r3.setWidth(width/2);
		r3.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				if(currentQuestion.getCorrectResponse().response.equals(resp.get(2))){
					System.out.println("Correct");
					createReply(currentQuestion.getCorrectResponse().response);
				}
				else{
					s.addActor(wrong);
					//game.setScreen(new HomeScreen(game));
				}
				return true;
			}
		});
		r4.setX(width-r3.getWidth());
		r4.setY(r2.getHeight());
		r4.setWidth(width/2);
		r4.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				s.addActor(wrong);
				//game.setScreen(new HomeScreen(game));
				return true;
			}
		});

	}
	public void createChatBubble(){
		textComp = new Texture("chatBubble_computer.png");
		textC = new Image(textComp);

		Label message = new Label(currentQuestion.question, uiskin);

		textC.setX(0);
		System.out.println(textC.getHeight());
		textC.setY(height - btnB.getHeight() - textC.getHeight()/2);
		textC.setWidth((float) (width/2.5));
		textC.setHeight(height/6);
		message.setWrap(true);
		message.setBounds(50, textC.getY()+textC.getHeight(), textC.getWidth()-50, textC.getHeight());
		//message.setX(30);
		message.setColor(Color.BLACK);
		message.setY((textC.getY()+textC.getHeight())-message.getHeight());
		s.addActor(textC);
		s.addActor(message);
	}
	@Override
	public void show(){
		Gdx.input.setInputProcessor(s);
	}
	public void createReply(String x){
		textReply = new Texture("chatBubble_reply.png");
		textR = new Image(textReply);
		textR.setHeight(height/6);
		textR.setWidth((float) (width/2.5));
		textR.setX(width-textR.getWidth());
		textR.setY(textC.getY()-textR.getHeight()-10);
		Label correctAnswer = new Label(x, uiskin);
		correctAnswer.setColor(Color.BLACK);
		correctAnswer.setWrap(true);
		correctAnswer.setX(textR.getX()+10);
		correctAnswer.setY(textR.getY()+5);
		correctAnswer.setWidth(textR.getWidth()-50);
		correctAnswer.setHeight(textR.getHeight());
		
		s.addActor(textR);
		s.addActor(correctAnswer);
	}
	private void createBackButton(){
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		System.out.println(btnB.getHeight());
		btnB.addListener(new ClickListener(){
			public boolean touchDown(InputEvent e, float x, float y, int pointer, int button){
				game.setScreen(new HomeScreen(game));
				System.out.println("back");
				return true;
			}
		});
		btnB.setX(0);
		btnB.setY(height-btnB.getHeight());
	}
	public void createLabel(){
		roommate = new Label("Roommate", uiskin);
		roommate.setX(width/2-roommate.getWidth());
		roommate.setY(height- roommate.getHeight());
		roommate.setFontScale(2);
		roommate.setColor(Color.BLACK);
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//s.act();
		batch.begin();
		s.draw();
		s.act();
		batch.end();
	}
	

}
