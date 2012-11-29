package com.mikehanson.teamcity.cigame;

import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildStatistics;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SRunningBuild;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 23/11/2012
 * Time: 09:19
 */
public class ServerListener extends BuildServerAdapter {

    private final SBuildServer buildServer;

    public ServerListener(SBuildServer buildServer) {
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
