package pacman.entries.pacman.behaviortree.tasks.actions;

import pacman.entries.pacman.behaviortree.MyPacMan;
import pacman.entries.pacman.behaviortree.helpers.Leaf;
import pacman.game.Constants;
import pacman.game.Game;

public class AvoidPowerpillTask extends Leaf {
    public AvoidPowerpillTask(MyPacMan parent)
    {
        super(parent);
    }

    @Override
    public boolean DoAction(Game game)
    {
        parent.setMove(game.getNextMoveAwayFromTarget(
                        state.getCurrent(),
                        state.getNearestPowerPill(),
                        Constants.DM.PATH)
        );
        return true;
    }
}