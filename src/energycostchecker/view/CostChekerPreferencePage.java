package energycostchecker.view;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import energycostchecker.Activator;
import energycostchecker.listener.*;
import org.eclipse.ui.IWorkbench;

public class CostChekerPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {
	

	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	PreferenceListener myListener = new PreferenceListener();
	
	public CostChekerPreferencePage() {
		super(GRID);
		setPreferenceStore(store);
		setDescription("");
	}
	
	@Override
	public void createFieldEditors() {
		
		RadioGroupFieldEditor check = new RadioGroupFieldEditor(
				PreferenceConstants.P_CHOICE,
			"Select Version",
			1,
			new String[][] { { "&0.0.1", "0.0.1" }, {
				"&0.0.2", "0.0.2" },
					{"&0.0.3","0.0.3"}
		}, getFieldEditorParent());
		addField(check);
	}
	
	@Override
	public void init(IWorkbench workbench) {	
		store.addPropertyChangeListener(myListener.getPrefListener()); 
	}
	
}