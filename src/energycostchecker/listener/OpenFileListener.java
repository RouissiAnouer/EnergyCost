package energycostchecker.listener;

/*import energycostchecker.view.SelectionView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PartInitException;
*/

public class OpenFileListener {
	/*
	private static OpenFileListener INSTANCE;
	
	public static OpenFileListener getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OpenFileListener();
		}
		return INSTANCE;
	}
	
	SelectionView View = SelectionView.getInstance();
	
	OpenFile openThis = new OpenFile();
	
	OpenLine openThisLine = new OpenLine();
	
	
	TableViewer tableviewer = View.getTableviewer();
	Action DoubleClickAction;
	
	
	public void makeAction() {
		DoubleClickAction = new Action() {
			public void run() {
				int index = tableviewer.getTable().getSelectionIndex();
				Table table = tableviewer.getTable();
				int a = table.getColumnCount();
				if(a == 5) {
					try {
						String openFile = table.getItem(index).getText(4);
						openThis.fileOpen(openFile);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}else if(a == 4) {
					String openLine = table.getItem(index).getText(0);
					String i = table.getItem(index).getText(3);
					Integer line = Integer.parseInt(i);
					openThisLine.openLine(openLine,line);
				}
			}
		};
	}
	
	public void hookDoubleClickAction() {
		tableviewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				DoubleClickAction.run();
			}
		});
	}
	*/

}
