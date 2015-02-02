
//implement "destroy" interface with method "splat"


public class Hero extends Character{
	
	private float speed = 0; //changed these to private
	private float gravity = 0.2;
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