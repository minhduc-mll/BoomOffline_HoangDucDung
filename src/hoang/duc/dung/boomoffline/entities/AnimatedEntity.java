package hoang.duc.dung.boomoffline.entities;


public abstract class AnimatedEntity extends Entity {

	protected int animate_ = 0;
	protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
	
	protected void animate() {
		if(animate_ < MAX_ANIMATE)
			animate_++;
		else
			animate_ = 0; //reset animation
	}

}
