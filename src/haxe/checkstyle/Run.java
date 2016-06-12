package haxe.checkstyle;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.*;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Run extends AnAction {

    String id;
    String projectPath;
    StatusBar statusBar;
    Balloon statusBaloon;

    public Run() {
	super("_Run");
	id = "Haxe Checkstyle";
    }

    private void createStatusBaloon(Project project) {
	statusBar = WindowManager.getInstance().getStatusBar(project);
	statusBaloon = JBPopupFactory.getInstance().createHtmlTextBalloonBuilder("Running " + id, MessageType.INFO, null).setFadeoutTime(1000).createBalloon();
    }

    private void showBaloon() {
	statusBaloon.show(RelativePoint.getCenterOf(statusBar.getComponent()), Balloon.Position.atRight);
    }

    private void hideBaloon() {
	statusBaloon.hide();
    }

    private void runCheckstyle(Project project, ToolWindow window) {
	/*Runnable r = new Runnable() {
	    public void run() {

	    }
	};
	new Thread(r).start();*/

	try {
	    Runtime rt = Runtime.getRuntime();
	    Process pr = rt.exec("haxelib run checkstyle -s " + projectPath + "/src -r xml -p " + projectPath + "/.idea/checkstyle-report.xml -c " + projectPath + "/checkstyle.json");

	    try {
		pr.waitFor();
	    } catch (InterruptedException e) {
	    }

	    Font font = new Font("Arial", Font.PLAIN, 12);
	    DefaultTableModel tm = new DefaultTableModel();
	    tm.addColumn("File");
	    tm.addColumn("Message");
	    tm.addColumn("Line");
	    tm.addColumn("Column");
	    tm.addColumn("Severity");
	    JBTable issues = new JBTable(tm);
	    issues.setFont(font);
	    issues.setForeground(Color.BLUE);
	    issues.getColumn("File").setPreferredWidth(100);
	    issues.getColumn("Message").setPreferredWidth(600);
	    issues.getColumn("Line").setPreferredWidth(20);
	    issues.getColumn("Column").setPreferredWidth(20);
	    issues.getColumn("Severity").setPreferredWidth(30);
	    issues.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
		    if (issues.getSelectedRow() > -1) {
			String fileName = projectPath + "/" + issues.getValueAt(issues.getSelectedRow(), 0).toString();
			int row = Integer.parseInt(issues.getValueAt(issues.getSelectedRow(), 2).toString()) - 1;
			int col = Integer.parseInt(issues.getValueAt(issues.getSelectedRow(), 3).toString());
			File f = new File(fileName);
			VirtualFile vf = LocalFileSystem.getInstance().findFileByIoFile(f);
			new OpenFileDescriptor(project, vf, row, col).navigateInEditor(project, false);
		    }
		}
	    });

	    JBScrollPane content = new JBScrollPane(issues);
	    int issueCount = 0;

	    try {
		File issuesXml = new File(project.getBasePath() + "/.idea/checkstyle-report.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(issuesXml);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("file");

		for (int temp = 0; temp < nList.getLength(); temp++) {
		    Node nNode = nList.item(temp);
		    Element fileElement = (Element) nNode;
		    NodeList messageList = nNode.getChildNodes();
		    for (int msg = 0; msg < messageList.getLength(); msg++) {
			Node m = messageList.item(msg);
			if (m.getNodeType() == Node.ELEMENT_NODE) {

			    Element eElement = (Element) m;
			    String path = fileElement.getAttribute("name");
			    path = path.substring(projectPath.length() + 1, path.length());

			    Object[] data = {
				    path,
				    eElement.getAttribute("message"),
				    eElement.getAttribute("line"),
				    eElement.getAttribute("column"),
				    eElement.getAttribute("severity")
			    };

			    tm.addRow(data);
			    issueCount++;
			}
		    }
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    if (issueCount == 0) {
		Messages.showMessageDialog(project, "No issues found.", id, Messages.getInformationIcon());
	    } else {
		window.getComponent().getParent().add(content);
		if (window != null) {
		    window.activate(null, false);
		}
	    }
	    hideBaloon();

	} catch (IOException e) {
	}
    }

    public void actionPerformed(AnActionEvent event) {
	Project project = event.getData(PlatformDataKeys.PROJECT);
	projectPath = project.getBasePath();
	createStatusBaloon(project);
	showBaloon();

	ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
	toolWindowManager.unregisterToolWindow(id);
	ToolWindow window = toolWindowManager.registerToolWindow(id, false, ToolWindowAnchor.BOTTOM, project, true);
	runCheckstyle(project, window);
    }
}