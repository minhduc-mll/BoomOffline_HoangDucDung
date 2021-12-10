package hoang.duc.dung.boomoffline.entities;

import java.awt.*;
import java.util.LinkedList;

import hoang.duc.dung.boomoffline.entities.tile.destroyable.DestroyableTile;
import hoang.duc.dung.boomoffline.graphics.Screen;

public class LayeredEntity extends Entity {
	
	protected LinkedList<Entity> entities_ = new LinkedList<Entity>();
	
	public LayeredEntity(int x, int y, Entity ... entities) {
		x_ = x;
		y_ = y;
		
		for (int i = 0; i < entities.length; i++) {
			entities_.add(entities[i]);
			
			if(i >= 1) { //Add to destroyable tiles the bellow sprite for rendering in explosion
				if(entities[i] instanceof DestroyableTile) {
					((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
					((DestroyableTile)entities[i]).addBelowSpriteImage(entities[i-1].getSpriteImage());
				}
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getLastEntity().update();
	}
	
	@Override
	public void render(Screen screen) {
		getLastEntity().render(screen);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		getFirstEntity().draw(screen, graphics);
		getLastEntity().draw(screen, graphics);
	}

	@Override
	public boolean collide(Entity e) {
		return getLastEntity().collide(e);
	}

	public Entity getLastEntity() {
		return entities_.getLast();
	}

	public Entity getFirstEntity() {
		return entities_.get(0);
	}

	private void clearRemoved() {
		Entity top  = getLastEntity();

		if(top.isRemoved())  {
			entities_.removeLast();
		}
	}

	public void addBeforeTop(Entity e) {
		entities_.add(entities_.size() - 1, e);
	}

	public void addTop(Entity e) {
		entities_.add(e);
	}

}
