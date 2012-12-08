package teamCityCiGame;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.serverSide.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 07/12/12
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class serverListener extends BuildServerAdapter {

    private final SBuildServer buildServer;

    public serverListener(SBuildServer buildServer) {
        this.buildServer = buildServer;
    }

    public void register() {
        this.buildServer.addListener(this);
    }

    @Override
    public void buildFinished(SRunningBuild sRunningBuild) {

        int points = 0;
        Status buildStatus = sRunningBuild.getBuildStatus();

        if (buildStatus == Status.NORMAL) {
            points++;
        }

        if (buildStatus == Status.FAILURE) {
            points--;
        }

        BuildStatistics statistics = sRunningBuild.getFullStatistics();
        points -= statistics.getNewFailedCount();
    }
}
