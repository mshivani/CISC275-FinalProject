package edu.udel.cisc275_15S.advisementadventure;

import java.util.Stack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game implements ApplicationListener{

	SpriteBatch batch;
	Screen currentScreen;
	Stack<Screen> stack;
	HomeScreen welcome;
	LoginScreen login;
	EmailScreen email;
	HelpScreen help;
	NotesScreen notes;
	TextScreen text;
	WebScreen web;


	@Override
	public void create() {
		batch = new SpriteBatch();
		//currentScreen = Screen.HOME;
		welcome = new HomeScreen(this);
		login = new LoginScreen(this);
		email = new EmailScreen(this);
		help = new HelpScreen(this);
		notes = new NotesScreen(this);
		text = new TextScreen(this);
		web = new WebScreen(this);
		this.setScreen(welcome);
	}


	@Override
	public void render() {
		super.render();
//		if(Gdx.input.isTouched()){
//		homeScreenClick();
//		}
	}


//	public void homeScreenClick() {
//	//	stack.add(currentScreen);
//		//System.out.println("X: " + Gdx.input.getX() + ", Y: "+ Gdx.input.getY());
//		int clickX = Gdx.input.getX();
//		int clickY = Gdx.input.getY();
//		if (clickX >= 70 && clickX <= 150 && clickY >= 50 && clickY <= 180) {
//			setScreen(notes);
//		} else if (clickX >= 170 && clickX <= 250 && clickY >= 50
//				&& clickY <= 180) {
//			setScreen(web);
//		} else if (clickX >= 270 && clickX <= 350 && clickY >= 50
//				&& clickY <= 180) {
//			setScreen(text);
//		} else if (clickX >= 370 && clickX <= 450 && clickY >= 50
//				&& clickY <= 180) {
//			setScreen(email);
//			welcome.hide();
//		} else if (clickX >= 470 && clickX <= 550 && clickY >= 50
//				&& clickY <= 180) {
//			setScreen(help);
//		}
//
//	}
}
//
//	public void otherScreenClick() {
//		System.out.println("X: " + Gdx.input.getX() + ", Y: "+ Gdx.input.getY());
//		int clickX = Gdx.input.getX();
//		int clickY = Gdx.input.getY();
//		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
//			currentScreen = stack.pop();
//		}
//
//	}

// original badlogic default gdx game implementation

// SpriteBatch batch;
// Texture img;
//
// @Override
// public void create() {
// batch = new SpriteBatch();
// img = new Texture("badlogic.jpg");
// }
//
// @Override
// public void render() {
// Gdx.gl.glClearColor(1, 0, 0, 1);
// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
// batch.begin();
// batch.draw(img, 0, 0);
// batch.end();
// }

