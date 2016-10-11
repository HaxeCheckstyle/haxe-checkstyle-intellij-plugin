package haxe.checkstyle;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;

import java.io.*;

public class Config extends AnAction {

    public Config() {
	super("Generate _Default Config");
    }

    public void actionPerformed(AnActionEvent event) {
	Project project = event.getData(PlatformDataKeys.PROJECT);
	try {

	    PluginId runtimePluginId = PluginManager.getPluginByClassName("haxe.checkstyle.Config");
	    IdeaPluginDescriptor runtimePlugin = PluginManager.getPlugin(runtimePluginId);
	    File json = new File(runtimePlugin.getPath().getAbsolutePath(), "/classes/checkstyle.json");
	    File toJson = new File(event.getProject().getBasePath(), "checkstyle.json");

	    if (json.isFile() && !toJson.exists()) {
		FileUtil.copy(json, toJson);
		Messages.showMessageDialog(project, "Config generated successfully.", "Haxe Checkstyle", Messages.getInformationIcon());
	    }
	    else {
		Messages.showMessageDialog(project, "Config already exists.", "Haxe Checkstyle", Messages.getInformationIcon());
	    }
	} catch (FileNotFoundException ex) {
	} catch (IOException ex) {
	}
    }
}
