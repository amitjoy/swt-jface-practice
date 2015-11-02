package com.amitinside.jface.practice.ch14;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * This class allows users to browse for a directory
 */
public class DirectoryBrowser extends SelectionAdapter {
	// The Text this browser is tied to
	private final Text text;

	/**
	 * DirectoryBrowser constructor
	 * 
	 * @param text
	 */
	public DirectoryBrowser(Text text) {
		this.text = text;
	}

	/**
	 * Called when the browse button is pushed
	 * 
	 * @param event
	 *            the generated event
	 */
	@Override
	public void widgetSelected(SelectionEvent event) {
		final DirectoryDialog dlg = new DirectoryDialog(Display.getCurrent()
				.getActiveShell());
		dlg.setFilterPath(text.getText());
		final String dir = dlg.open();
		if (dir != null) {
			text.setText(dir);
		}
	}
}