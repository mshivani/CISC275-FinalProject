package edu.udel.cisc275_15S.advisementadventure;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TextScreen extends ScreenAdapter {
	private MyGdxGame game;
	private SpriteBatch batch;
	private Skin uiskin;
	private Texture btnBack;
	private Texture textComp;
	private Image textC;
	private Image btnB;
	private Label roommate;
	private Label wrong;
	private Question currentQuestion;
	private ArrayList<String> resp;
	private Texture textReply;
	private Stage s;
	private TextButton r1, r2, r3, r4;
	private float width;
	private float height;
	private Image textR;
	private ArrayList<Task> taskList;
	private Image star;
	private Texture starT;
	private Label la;
	private int num;
    int r1W;
    int r2W;
    int r3W;
	int r4W;
	int r5W;
	int idk;

	public TextScreen(MyGdxGame g, Question q) {
		this.currentQuestion = q;
		this.game = g;
		this.taskList = g.taskList;
		resp = q.getResponses();

	}

	@Override
	public void show() {
		game.previousScreen = this;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		s = new Stage();
		uiskin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		createLabel();
		createBackButton();
		createChatBubble();
		createResponses();
		createAchieveStar();
		Gdx.input.setInputProcessor(s);
		determineTasks();

	}

	//displays an acieve star on the page when a task has been completed 
	public void createAchieveStar() {
		starT = new Texture("star.png");
		star = new Image(starT);
		num = 0;
		boolean create = false;
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).isCompleted() && !taskList.get(i).isSeen()) {
				create = true;
				num++;
			}
		}

		if (create) {
			star.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					game.setScreen(game.help);
					return true;
				}
			});
			star.setWidth(80);
			star.setHeight(80);
			star.setX(width - star.getWidth());
			star.setY(height - star.getHeight());

			star.addAction(Actions.forever(Actions.sequence(
					Actions.sizeTo(65, 65, .7f), Actions.sizeTo(80, 80, .7f))));
			star.addAction(Actions.forever(Actions.sequence(
					Actions.moveTo(width - 72, height - 72, .7f),
					Actions.moveTo(width - 80, height - 80, .7f))));

			s.addActor(star);
			la = new Label(num + "", uiskin);
			la.setX(width - star.getWidth() + star.getWidth() * .44f);
			la.setY(height - star.getHeight() + star.getHeight() * .36f);

			la.setColor(Color.BLACK);
			la.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					game.setScreen(game.help);
					return true;
				}
			});
			s.addActor(la);
			if (game.currentTask == 3) {
				game.currentTask = 4;
			}
		}

	}

	public void determineTasks() {
		if (game.currentTask == 1)
			game.currentTask = 2;
	}

	public void createWrong() {
		wrong = new Label("Please try again later", uiskin);
		wrong.setX(width / 2 - wrong.getWidth() / 2);
		wrong.setY(r1.getHeight() + r2.getHeight() + 10);
		wrong.setColor(Color.BLACK);
	}

	//creates and randomizes responses for the text messages 
	public void createResponses() {
		if (!(resp.get(0).equals("empty"))) {
			r1 = new TextButton(resp.get(0), uiskin);
			r1.setX(0);
			r1.setWidth(width / 2);
			r1.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					if (currentQuestion.getCorrectResponse().response
							.equals(resp.get(0))) {
						determineTasks();
						createReply(currentQuestion.getCorrectResponse().response);
						game.data.writeString(currentQuestion.updateFile(),
								true);
					} else {
						createWrong();
						currentQuestion.updateWrong(resp.get(0));
						s.addActor(wrong);
					}
					return true;
				}
			});
			s.addActor(r1);
		}
		if (!(resp.get(1).equals("empty"))) {
			r2 = new TextButton(resp.get(1), uiskin);
			r2.setX(width - r1.getWidth());
			r2.setWidth(width / 2);
			r2.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					if (currentQuestion.getCorrectResponse().response
							.equals(resp.get(1))) {
						createReply(currentQuestion.getCorrectResponse().response);
						determineTasks();
						game.data.writeString(currentQuestion.updateFile(),
								true);
					} else {
						createWrong();
						currentQuestion.updateWrong(resp.get(1));
						s.addActor(wrong);
					}
					return true;
				}
			});
			s.addActor(r2);
		}
		if (!(resp.get(2).equals("empty"))) {
			r3 = new TextButton(resp.get(2), uiskin);
			r3.setY(r1.getHeight());
			r3.setX(0);
			r3.setWidth(width / 2);
			r3.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					if (currentQuestion.getCorrectResponse().response
							.equals(resp.get(2))) {
						createReply(currentQuestion.getCorrectResponse().response);
						determineTasks();
						game.data.writeString(currentQuestion.updateFile(),
								true);
					} else {
						createWrong();
						currentQuestion.updateWrong(resp.get(2));
						s.addActor(wrong);
					}
					return true;
				}
			});
			s.addActor(r3);
		}

		if (!(resp.get(0).equals("empty") && resp.get(1).equals("empty") && resp
				.get(2).equals("empty"))) {
			r4 = new TextButton("I don't know", uiskin);

			r4.setX(width - r3.getWidth());
			r4.setY(r2.getHeight());
			r4.setWidth(width / 2);
			r4.addListener(new ClickListener() {
				public boolean touchDown(InputEvent e, float x, float y,
						int pointer, int button) {
					createWrong();
					currentQuestion.updateWrong("I don't know");
					s.addActor(wrong);
					return true;
				}
			});
			s.addActor(r4);
		}

	}

	//creates a chat bubble
	public void createChatBubble() {
		textComp = new Texture("chatBubble_computer.png");
		textC = new Image(textComp);

		Label message = new Label(currentQuestion.question, uiskin);

		textC.setX(0);
		textC.setWidth((float) (width / 2.5));
		textC.setHeight(height / 3);
		textC.setY(height - btnB.getHeight() - textC.getHeight());
		message.setWrap(true);
		message.setBounds(50, textC.getY() + textC.getHeight(),
				textC.getWidth() - 50, textC.getHeight());
		message.setColor(Color.BLACK);
		message.setY((textC.getY() + textC.getHeight()) - message.getHeight());
		s.addActor(textC);
		s.addActor(message);
	}
	
	//creates a reply to the text depending on the users response 
	public void createReply(String x) {
		textReply = new Texture("chatBubble_reply.png");
		textR = new Image(textReply);
		textR.setHeight(height / 6);
		textR.setWidth((float) (width / 2.5));
		textR.setX(width - textR.getWidth());
		textR.setY(textC.getY() - textR.getHeight() - 10);
		Label correctAnswer = new Label(x, uiskin);
		correctAnswer.setColor(Color.BLACK);
		correctAnswer.setWrap(true);
		correctAnswer.setX(textR.getX() + 10);
		correctAnswer.setY(textR.getY() + 5);
		correctAnswer.setWidth(textR.getWidth() - 50);
		correctAnswer.setHeight(textR.getHeight());

		s.addActor(textR);
		s.addActor(correctAnswer);
		if (game.currentTask == 5) {
			game.currentTask2 = 102;
		}
		if (game.currentTask == 6) {
			game.currentTask2 = 104;
		}
		if (game.currentTask == 7) {
			game.currentTask2 = 106;
		}
		if (game.currentTask2 == 108) {
			game.currentTask2 = 109;
		} else if (game.currentTask == 8) {
			game.currentTask2 = 107;
		}
	}

	//creates a back button 
	private void createBackButton() {
		btnBack = new Texture("btn_back.png");
		btnB = new Image(btnBack);
		btnB.setX(MyGdxGame.btnBackMargin);
		btnB.setY(height - btnB.getHeight() - MyGdxGame.btnBackMargin);
		btnB.addListener(new ClickListener() {
			public boolean touchDown(InputEvent e, float x, float y,
					int pointer, int button) {
				if (game.currentTask == 7) {
					game.currentTask2 = 110;
				}
				game.setScreen(game.welcome);
				return true;
			}
		});
		s.addActor(btnB);
	}

	public void createLabel() {
		roommate = new Label("Roommate", uiskin);
		roommate.setX(width / 2 - roommate.getWidth());
		roommate.setY(height - roommate.getHeight());
		roommate.setFontScale(2);
		roommate.setColor(Color.BLACK);
		s.addActor(roommate);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		s.draw();
		s.act();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		this.show();
	}

	@Override
	public void dispose() {
		s.dispose();
		batch.dispose();
		this.dispose();
	}

	public void setQuestion(Question q) {
		this.currentQuestion = q;
		this.resp = q.getResponses();
	}

}
