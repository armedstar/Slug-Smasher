//serial read from Arduino
import processing.serial.*;
Serial port;
int buttonPress = 0;
boolean isPressing = false;

//
Hero boot = new Hero();
Snake snake = new Snake();
PImage bg;

void setup(){
  size(800, 600);
  port = new Serial(this, "/dev/tty.usbmodemfd121", 9600);
  bg = loadImage("background.png");
}

void draw(){
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

	void serialEvent (Serial port){

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

