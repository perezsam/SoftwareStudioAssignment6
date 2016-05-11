package main.java;

import de.looksgood.ani.*;
import processing.core.PApplet;

public class AniTest extends PApplet {

	float x = 256;
	float y = 256;
	int diameter = 50;
	
	

	public void setup() {
	  size(512,512);
	  smooth();
	  noStroke();

	  // you have to call always Ani.init() first!
	  Ani.init(this);
	}

	public void draw() {
	  background(255);
	  fill(0);
	  ellipse(x,y,diameter,diameter);
	}

	public void mouseReleased() {
	  Ani.to(this, 1.0f, "x", mouseX, Ani.ELASTIC_IN_OUT);
	  Ani.to(this, 1.0f, "y", mouseY, Ani.ELASTIC_IN_OUT);
	}

	
}
