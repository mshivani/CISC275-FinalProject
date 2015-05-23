//<<<<<<< Updated upstream
package edu.udel.cisc275_15S.advisementadventure;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game implements ApplicationListener{
	public static final int btnBackMargin = 10;
	public ScreenAdapter previousScreen;
	FileHandle data;
	ArrayList<Question> questionList;
	SpriteBatch batch;
	ArrayList<Note> notesList;
	Screen currentScreen;
	Stack<Screen> stack;
	HomeScreen welcome;
	LoginScreen login;
	EmailListScreen email;
	EmailFullScreen email2;
	HelpScreen help;
	HelpScreenFromMain helpFromMain;
	NotesScreen notescrn;
	TextScreen text;
	WebScreen web;
	UDSIS_Screen udsis;
	ScheduleScreen schedule;
	AddDropScreen addDrop;
	RsvpScreen rsvp;
	DeclareMajorScreen major;
	DegreeAuditScreen degreeAudit;
	NoteMenu notemenu;
	ArrayList<Task> taskList;
	FileHandle questions;
	Email emailChosen;

	ArrayList<Email> emailList;
	
	int currentTask;
	int currentTask2;
	int currText;	

	@Override
	public void create() {
		currentTask = 1;
		currentTask2 = -1;
		currText=-1;
		data= Gdx.files.local("list of students.txt");
		questionList = new ArrayList<Question>();
		notesList= new ArrayList<Note>();
		batch = new SpriteBatch();
		taskList = new ArrayList<Task>();
		welcome = new HomeScreen(this);
		login = new LoginScreen(this);
		email = new EmailListScreen(this);
		email2 = new EmailFullScreen(this, null);
		help = new HelpScreen(this);
		helpFromMain = new HelpScreenFromMain(this);
		major = new DeclareMajorScreen(this);
		degreeAudit = new DegreeAuditScreen(this);
		web = new WebScreen(this);
		udsis = new UDSIS_Screen(this);
		schedule = new ScheduleScreen(this);
		addDrop = new AddDropScreen(this);
		rsvp = new RsvpScreen(this);
		notemenu = new NoteMenu(this);
		createTasks();
		this.setScreen(login);
		parseEmails();
	}

	public void setScreenHelp(com.badlogic.gdx.Screen screen, Email email) {
		email2.setCurrentEmail(email);
		setScreen(screen);
	}

	public ArrayList<Email> getEmailList() {
		return emailList;
	}
	
	public void parseEmails() {
		emailList = new ArrayList<Email>();
		try {
			FileHandle fileReader = Gdx.files.internal("Emails.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader.reader());
			String line = bufferedReader.readLine();
			ArrayList<String> lines = new ArrayList<String>();
			int amountOfEmails = Integer.parseInt(line);
			for (int i = 0; i < amountOfEmails; i++) {
				for (int j = 1; j < 8; j++) {
					line = bufferedReader.readLine();
					lines.add(line);
				}
				Email e = new Email(i, lines.get(0), lines.get(1), lines.get(2),
									lines.get(3), lines.get(4), lines.get(5), lines.get(6));
				emailList.add(e);
				lines.clear();
			}
			//fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTasks() {
		makeTask(1, "mailbox-color.png", "mailbox-gray.png", "Open your first email in the email app.");
		makeTask(2, "major-color.png", "major-gray.png", "Declare your major.");
		makeTask(1, "roommate-color.png", "roommate-gray.png", "Introduce yourself to your roommate.");
		makeTask(2, "register-color.png", "register-gray.png", "Register for five classes.");
		makeTask(1, "audit-color.png", "audit-gray.png", "Look at your degree audit.");
		makeTask(2, "drop-color.png", "drop-gray.png", "Drop a class.");
		makeTask(1, "writing-color.png", "writing-gray.png", "Sign up for a slot at the writing center.");
		makeTask(2, "shot-color.png", "shot-gray.png", "Sign up to get a flu shot.");
	}
	
	private void makeTask(int type, String icon1, String icon2, String title) {
		taskList.add(new Task(type, icon1, icon2, title));
	}
}
