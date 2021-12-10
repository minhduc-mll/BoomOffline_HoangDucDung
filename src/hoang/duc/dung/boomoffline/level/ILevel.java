package hoang.duc.dung.boomoffline.level;


import hoang.duc.dung.boomoffline.exceptions.LoadLevelException;

public interface ILevel {

	public void loadLevel(int level) throws LoadLevelException;
}
