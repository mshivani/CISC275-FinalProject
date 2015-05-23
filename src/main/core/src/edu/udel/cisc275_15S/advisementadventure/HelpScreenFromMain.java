package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HelpScreenFromMain extends ScreenAdapter {
	MyGdxGame game;
	SpriteBatch batch;

	Texture btnBack;
	Texture trans;
	Texture glass;
	Image btnB;
	Image star;
	Skin uiskin;
	Stage s;

	BitmapFont font;
	float width;
	float height;
	String helpDisplay;
	ArrayList<Task> tasklist;
	ArrayList<Image> compImg;
	ArrayList<Image> unCompImg;
	ArrayList<Label> taskLabel;
	Texture starT;
	float speedx;
	float speedy;

	private Label c;
	private Label o;
	private Label n;
	private Label g;
	private Label r;
	private Label a;
	private Label t;
	private Label u;
	private Label l;
	private Label a2;
	private Label t2;
	private Label i;
	private Label o2;
	private Label n2;
	private Label s2;
	private Label e1;
	private Label e2;
	private Label e3;

	public HelpScreenFromMain(MyGdxGame g) {
		this.game = g;
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);

		// add to every page
		this.tasklist = g.taskList;
		starT = new Texture("star.png");
		star = new Image(starT);
	}

	public void show() {
		game.previousScreen = this;
		s = new Stage();

		batch = new SpriteBatch();
		font = new BitmapFont();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		font.setColor(0, 0, 0, 1);

		createStar();
		createText();
		createBackButton();
		Gdx.input.setInputProcessor(s);
	}

	public void createStar() {
		star.setX(-700);
		star.setY(-500);
		star.setWidth(2000);
		star.setHeight(2000);
		star.addAction(Actions.moveTo(width / 2 - 75, height / 2 - 75, 1));
		star.addAction(Actions.sizeTo(150, 150, 2));
		s.addActor(star);
	}

	public void createText() {
		int wi = 50;
		int he = 50;
		int speed = 2;
		int y = (int) height - 100;

		c = new Label("C", uiskin);
		c.setX(100);
		c.setY(height + 100);
		c.setSize(wi, he);
		c.setColor(Color.BLACK);
		c.addAction(Actions.moveTo(100, y, speed));
		s.addActor(c);

		o = new Label("O", uiskin);
		o.setX(125);
		o.setY(-100);
		o.setSize(wi, he);
		o.setColor(Color.BLACK);
		o.addAction(Actions.moveTo(125, y, speed));
		s.addActor(o);

		n = new Label("N", uiskin);
		n.setX(150);
		n.setY(height + 100);
		n.setSize(wi, he);
		n.setColor(Color.BLACK);
		n.addAction(Actions.moveTo(150, y, speed));
		s.addActor(n);

		g = new Label("G", uiskin);
		g.setX(175);
		g.setY(-100);
		g.setSize(wi, he);
		g.setColor(Color.BLACK);
		g.addAction(Actions.moveTo(175, y, speed));
		s.addActor(g);

		r = new Label("R", uiskin);
		r.setX(200);
		r.setY(height + 100);
		r.setSize(wi, he);
		r.setColor(Color.BLACK);
		r.addAction(Actions.moveTo(200, y, speed));
		s.addActor(r);

		a = new Label("A", uiskin);
		a.setX(225);
		a.setY(-100);
		a.setSize(wi, he);
		a.setColor(Color.BLACK);
		a.addAction(Actions.moveTo(225, y, speed));
		s.addActor(a);

		t = new Label("T", uiskin);
		t.setX(250);
		t.setY(height + 100);
		t.setSize(wi, he);
		t.setColor(Color.BLACK);
		t.addAction(Actions.moveTo(250, y, speed));
		s.addActor(t);

		u = new Label("U", uiskin);
		u.setX(275);
		u.setY(-100);
		u.setSize(wi, he);
		u.setColor(Color.BLACK);
		u.addAction(Actions.moveTo(275, y, speed));
		s.addActor(u);

		l = new Label("L", uiskin);
		l.setX(300);
		l.setY(height + 100);
		l.setSize(wi, he);
		l.setColor(Color.BLACK);
		l.addAction(Actions.moveTo(300, y, speed));
		s.addActor(l);

		a2 = new Label("A", uiskin);
		a2.setX(325);
		a2.setY(-100);
		a2.setSize(wi, he);
		a2.setColor(Color.BLACK);
		a2.addAction(Actions.moveTo(325, y, speed));
		s.addActor(a2);

		t2 = new Label("T", uiskin);
		t2.setX(350);
		t2.setY(height + 100);
		t2.setSize(wi, he);
		t2.setColor(Color.BLACK);
		t2.addAction(Actions.moveTo(350, y, speed));
		s.addActor(t2);

		i = new Label("I", uiskin);
		i.setX(375);
		i.setY(-100);
		i.setSize(wi, he);
		i.setColor(Color.BLACK);
		i.addAction(Actions.moveTo(375, y, speed));
		s.addActor(i);

		o2 = new Label("O", uiskin);
		o2.setX(400);
		o2.setY(height + 100);
		o2.setSize(wi, he);
		o2.setColor(Color.BLACK);
		o2.addAction(Actions.moveTo(400, y, speed));
		s.addActor(o2);

		n2 = new Label("N", uiskin);
		n2.setX(425);
		n2.setY(-100);
		n2.setSize(wi, he);
		n2.setColor(Color.BLACK);
		n2.addAction(Actions.moveTo(425, y, speed));
		s.addActor(n2);

		s2 = new Label("S", uiskin);
		s2.setX(450);
		s2.setY(-100);
		s2.setSize(wi, he);
		s2.setColor(Color.BLACK);
		s2.addAction(Actions.moveTo(450, y, speed));
		s.addActor(s2);

		e1 = new Label("!", uiskin);
		e1.setX(475);
		e1.setY(-100);
		e1.setSize(wi, he);
		e1.setColor(Color.BLACK);
		e1.addAction(Actions.moveTo(475, y, speed));
		s.addActor(e1);

		e2 = new Label("!", uiskin);
		e2.setX(500);
		e2.setY(-100);
		e2.setSize(wi, he);
		e2.setColor(Color.BLACK);
		e2.addAction(Actions.moveTo(500, y, speed));
		s.addActor(e2);

		e3 = new Label("!", uiskin);
		e3.setX(525);
		e3.setY(-100);
		e3.setSize(wi, he);
		e3.setColor(Color.BLACK);
		e3.addAction(Actions.moveTo(525, y, speed));
		s.addActor(e3);
	}

	public void render(float delta) {

		Gdx.gl.glClearColor(205 / 255f, 242 / 255f, 250 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		s.draw();
		s.act();

	}

	public void resize(int x, int y) {
		this.show();
	}

	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				game.welcome.timeViewingScreen++;
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(btnB);
	}
}
