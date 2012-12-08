package teamCityCiGame;

import jetbrains.buildServer.serverSide.CustomDataStorage;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.openapi.buildType.BuildTypeTab;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class configurationTab extends BuildTypeTab {

    public configurationTab(@NotNull WebControllerManager manager,
                            @NotNull ProjectManager projectManager) {
        super("teamCityCiGame", "CI Game", manager, projectManager, "configurationTab.jsp");
    }

    @Override
    public boolean isAvailable(@NotNull HttpServletRequest request) {
        return true;
    }

    @Override
    protected void fillModel(@NotNull Map<String, Object> model,
                             @NotNull HttpServletRequest request,
                             @NotNull SBuildType buildType,
                             @Nullable SUser user) {
        CustomDataStorage customDataStorage = buildType.getCustomDataStorage("teamCityCiGame");
        String isEnabledValue = customDataStorage.getValue("isEnabled");
        boolean isEnabled = isEnabledValue != null && Boolean.parseBoolean(isEnabledValue);
        model.put("isEnabled", isEnabled);

    }
}
