package energycostchecker.listener;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;


import energycostchecker.view.SelectionView;

public class ItemSelectListener{
	
	SelectionView view = SelectionView.getInstance();
	
	ISelectionListener listener;

	public ItemSelectListener() {
		
		listener = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
				
				if (sourcepart != view) {
				    view.showSelection(sourcepart, selection);
				}
			}
		};

	}
	
	public ISelectionListener getListener() {
		return listener;
	}
	
	

}
