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