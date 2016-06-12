package haxe.checkstyle;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Install extends AnAction {

    public Install() {
	super("Install _Library");
    }

    public void actionPerformed(AnActionEvent event) {
	Project project = event.getData(PlatformDataKeys.PROJECT);

	try {
	    Process pr = Runtime.getRuntime().exec("haxelib install checkstyle");
	    pr.waitFor();

	    StringBuffer output = new StringBuffer();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));

	    String line = "";
	    String l = "";
	    int i = 0;
	    while ((line = reader.readLine()) != null) {
		if (i == 0) output.append(line + "\n");
		i++;
		l = line;
	    }
	    if (i > 1 && l != null) output.append(l);

	    Messages.showMessageDialog(project, output.toString(), "Haxe Checkstyle (haxelib)", Messages.getInformationIcon());

	} catch (IOException e) {
	    Messages.showMessageDialog(project, e.getMessage(), "Haxe Checkstyle (haxelib)", Messages.getErrorIcon());
	} catch (InterruptedException e) {
	    Messages.showMessageDialog(project, e.getMessage(), "Haxe Checkstyle (haxelib)", Messages.getErrorIcon());
	}
    }
}