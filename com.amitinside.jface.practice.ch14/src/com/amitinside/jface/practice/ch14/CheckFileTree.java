package com.amitinside.jface.practice.ch14;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates the CheckboxTreeViewer
 */
public class CheckFileTree extends FileTree {
	/**
	 * Configures the shell
	 * 
	 * @param shell
	 *            the shell
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Check File Tree");
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param parent
	 *            the main window
	 * @return Control
	 */
	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		// Add a checkbox to toggle whether the labels preserve case
		final Button preserveCase = new Button(composite, SWT.CHECK);
		preserveCase.setText("&Preserve case");

		// Create the tree viewer to display the file tree
		final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider());
		tv.setInput("root"); // pass a non-null that will be ignored

		// When user checks the checkbox, toggle the preserve case attribute
		// of the label provider
		preserveCase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				final boolean preserveCase = ((Button) event.widget)
						.getSelection();
				final FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv
						.getLabelProvider();
				ftlp.setPreserveCase(preserveCase);
			}
		});

		// When user checks a checkbox in the tree, check all its children
		tv.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				// If the item is checked . . .
				if (event.getChecked()) {
					// . . . check all its children
					tv.setSubtreeChecked(event.getElement(), true);
				}
			}
		});
		return composite;
	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new CheckFileTree().run();
	}
}