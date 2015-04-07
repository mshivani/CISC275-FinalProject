package edu.udel.cisc275_15S.advisementadventure.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.udel.cisc275_15S.advisementadventure.MyGdxGame;
//test by Jeremy
//edited from server
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
		config.title = "Advisement Adventure";
	}
}
