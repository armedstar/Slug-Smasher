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
		
		x += vilDir * 1.0;

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