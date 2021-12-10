package hoang.duc.dung.boomoffline.entities.character.enemy.ai;

/**
 *
 */
public class AIRandom extends AI{

    @Override
    public int calculateDirection() {       
        return random.nextInt(4);
    }

}
