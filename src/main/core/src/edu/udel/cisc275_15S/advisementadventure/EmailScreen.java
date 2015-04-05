package edu.udel.cisc275_15S.advisementadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class EmailScreen extends ScreenAdapter {

	MyGdxGame game;
	SpriteBatch batch;
	Texture btnBack;
	BitmapFont textFont;
	BitmapFont titleFont;

	private ShapeRenderer shapeRenderer;
	private boolean projectionMatrixSet;

	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();

	public EmailScreen(MyGdxGame g) {
		this.game = g;
		batch = new SpriteBatch();
		textFont = new BitmapFont();
		textFont.setColor(0, 0, 0, 1);
		titleFont = new BitmapFont();
		titleFont.setScale(2);
		titleFont.setColor(0, 0, 0, 1);
		btnBack = new Texture("btn_back.png");

		shapeRenderer = new ShapeRenderer();
		projectionMatrixSet = false;
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			emailScreenClick();
		}
		
		batch.begin();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		titleFont.draw(batch, "Inbox", screenWidth / 2 - 30, screenHeight - 20);

		batch.draw(btnBack, 25, 425);
		
		textFont.draw(batch, "Sandra Carberry", 5, 390);
		textFont.draw(batch, "3/21/15", screenWidth - 55, 390);
		textFont.draw(batch, "Add EGGG101 to Your Schedule", 5, 350);
		textFont.draw(
				batch,
				"Hi John, I am just reminding you that you need to add EGGG101 to your schedule. You can do this...",
				5, 325);
		batch.end();
		
		if (!projectionMatrixSet) {
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		}
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(1, 300, screenWidth - 1, 100);
		shapeRenderer.end();
	}

	public void emailScreenClick() {
		int clickX = Gdx.input.getX();
		int clickY = Gdx.input.getY();
		System.out.println("X: " + clickX + "    Y: " + clickY);
		if (clickX >= 0 && clickX <= 100 && clickY >= 0 && clickY <= 100) {
			game.setScreen(game.welcome);
		} else if (clickX >= 1 && clickX <= screenWidth - 1 && clickY >= screenHeight - 400 && clickY <= screenHeight - 300) {
			game.setScreen(game.email2);
		}
	}

}
