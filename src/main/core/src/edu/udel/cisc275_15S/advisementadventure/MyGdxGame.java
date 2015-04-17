package edu.udel.cisc275_15S.advisementadventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game implements ApplicationListener{
	ArrayList<Question> questionList;
	SpriteBatch batch;
	ArrayList<Note> notesList;
	Screen currentScreen;
	Stack<Screen> stack;
	HomeScreen welcome;
	LoginScreen login;
	EmailScreen email;
	Email2Screen email2;
	HelpScreen help;
	NotesScreen notescrn;
	TextScreen text;
	WebScreen web;
	UDSIS_Screen udsis;
	ScheduleScreen schedule;
	AddDropScreen addDrop;
	RsvpScreen rsvp;
	NoteMenu notemenu;
	ArrayList<Task> taskList;
	FileHandle questions;


	

	@Override
	public void create() {
		//parseQuestions();
		questionList = new ArrayList<Question>();
		notesList= new ArrayList<Note>();
		batch = new SpriteBatch();
		taskList = new ArrayList<Task>();
		//currentScreen = Screen.HOME;
		welcome = new HomeScreen(this);
		login = new LoginScreen(this);
		email = new EmailScreen(this);
		email2 = new Email2Screen(this);
		help = new HelpScreen(this);
		//notes = new NotesScreen(this, null);
		//text = new TextScreen(this);
		web = new WebScreen(this);
		udsis = new UDSIS_Screen(this);
		schedule = new ScheduleScreen(this);
		addDrop = new AddDropScreen(this);
		rsvp = new RsvpScreen(this);
		//notemenu = new NoteMenu(this);
		Task t1 = new Task(1, "btn_notes.png", "btn_help.png", "Open your first email in the email app.");
		Task t2 = new Task(2, "btn_notes.png", "btn_help.png", "Do something else.");
		Task t3 = new Task(1, "btn_notes.png", "btn_help.png", "Finish the game!");
		Task t4 = new Task(2, "btn_notes.png", "btn_help.png", "Something else goes here.");
		Task t5 = new Task(1, "btn_notes.png", "btn_help.png", "Find the hidden secret.");
		Task t6 = new Task(2, "btn_notes.png", "btn_help.png", "Type a program.");
		Task t7 = new Task(1, "btn_notes.png", "btn_help.png", "Come up with something better to say.");
		Task t8 = new Task(2, "btn_notes.png", "btn_help.png", "Beat the game again.");
		Task t9 = new Task(1, "btn_notes.png", "btn_help.png", "Close out of the game.");
		Task t10 = new Task(2, "btn_notes.png", "btn_help.png", "Nothing for the last one.");
		taskList.add(t1);
		taskList.add(t2);
		taskList.add(t3);
		taskList.add(t4);
		taskList.add(t5);
		taskList.add(t6);
		taskList.add(t7);
		taskList.add(t8);
		taskList.add(t9);
		taskList.add(t10);
		this.setScreen(login);
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

