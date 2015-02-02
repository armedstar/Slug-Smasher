import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {

//serial read from Arduino

Serial port;
int buttonPress = 0;
boolean isPressing = false;

//
Hero boot = new Hero();
Snake snake = new Snake();
PImage bg;

public void setup(){
  size(800, 600);
  port = new Serial(this, "/dev/tty.usbmodemfd121", 9600);
  bg = loadImage("background.png");
}

public void draw(){
	background(bg);

	
	
	//snake
	snake.render();
	snake.switchImage();

	//hero
	boot.render();

	//check if snake when in range of boot
	if(snake.x > 70 && snake.x < 350 && boot.y >= -50){
		snake.splat();  //destroy the snake
	}
	
}

	public void serialEvent (Serial port){

    if(port.available() > 0){
  		buttonPress = port.read();

  		println(buttonPress);

  		if(buttonPress == 48){
  			isPressing = true;
  			boot.setStomp();
  		} else if (buttonPress == 50){
  			isPressing = false;
  			boot.setLand();
  		}
  	}
	}


//implement "destroy" interface with method "splat"


public class Hero extends Character{
	
	private float speed = 0; //changed these to private
	private float gravity = 0.2f;
	private boolean isStomping;
	private boolean buttonPress;

	Hero(){
		super("boot.png");
		y = -300;
		x = 200;
	}

	protected Hero(String graphicImage)
	{
		super(graphicImage);
	} 

	public void render()
	{
		super.render();
		stomp();
	}


	public void stomp(){
		//if ((key == ' ') && (isStomping == true)){
		  if(isStomping == true){
			if((y <= -200 || y < -50)){
				y = y + (speed * 20);
				y += 20;

			} else if(y >= -50){
				y = -50;
				speed = 0;
			}
			

		} else if(isStomping == false){
				y = y + (speed * 20);
				speed = speed - gravity;

				if(y <= -300){
					y=-300;
					speed=0;

				} else if(y >= 450){
					y -=20;
				} 

		}
			
	}

	public void setStomp(){
		isStomping = true;
	}

	public void setLand(){
		isStomping = false;
	}
	

}
public class Snake extends Villian {

	public Snake()
	{
		super("slug.png");
		y = 450;
		x = 0;
	}

	protected Snake(String graphicImage)
	{
		super(graphicImage);
	}

	public void switchImage(){

		if(!super.goingLeft){
			graphicImage = "slug.png";
		} else {
			graphicImage = "slugLeft.png";
		}
	}

	public void splat(){
		graphicImage = "splat.png";
	}

}
public class Villian extends Character{

	private float vilDir = 3; 
	private boolean goingLeft;

	Villian(){
		super();
		goingLeft = false;
	}

	protected Villian(String graphicImage)
	{
		super(graphicImage);
	} 

	public void render()
	{
		super.render();
		move();
	}

	//moves
	public void move(){
		
		x += vilDir * 1.0f;

		if((x >= width - 80) || (x <= 0 - 80)){ 
			x += vilDir *= -1;
		} 

		if(vilDir > 0){
			goingLeft = false;
		} else if(vilDir < 0){
			goingLeft = true;
		}
		
	}


	//collision


}
//all characters have a body and locations
//how and where and why to add an interface?

public abstract class Character { //changed to abstract

	private static final String DEFAULT_GRAPHIC_IMAGE = "graphic.png";

	public PImage body; //need to be public because Hero is accessing them??
	public String image;
	public float x, y;

	public String graphicImage = DEFAULT_GRAPHIC_IMAGE;

	public Character()
	{
		this(DEFAULT_GRAPHIC_IMAGE);
	} 

	protected Character(String graphicImage)
	{
		this.graphicImage = graphicImage;
	} 

	public void setBody(String i, float x, float y){
		this.image = i;
		body = loadImage(image);
		this.body = body;
		
		this.x = x;
		this.y = y;
		image(this.body, this.x, this.y);
	}

	public void render()
	{
		setBody(graphicImage, x, y);
	}

	public void destroy(){

		println("destroy!");
	}

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
