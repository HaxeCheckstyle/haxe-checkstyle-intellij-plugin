package haxe.checkstyle;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class Install extends AnAction {

    public Install() {
	super("Install _Library");
    }

    public void actionPerformed(AnActionEvent event) {
	try {
	    Runtime rt = Runtime.getRuntime();
	    Process pr = rt.exec("haxelib install checkstyle");
	    try {
		pr.waitFor();
	    } catch (InterruptedException e) {
		System.out.print(e);
	    }

	    Project project = event.getData(PlatformDataKeys.PROJECT);

	    Integer exitValue = pr.exitValue();

	    if (exitValue == 0) {
		Messages.showMessageDialog(project, "Library successfully installed.", "Haxe Checkstyle", Messages.getInformationIcon());
	    } else {
		Messages.showMessageDialog(project, "Library installation failed.", "Haxe Checkstyle", Messages.getErrorIcon());
	    }
	} catch (IOException e) {
	    System.out.print(e);
	}
    }
}