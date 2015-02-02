//all characters have a body and locations

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
