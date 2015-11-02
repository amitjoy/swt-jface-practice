package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StackLayoutTest {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final StackLayout layout = new StackLayout();
		shell.setLayout(layout);
		final StackLayoutSelectionAdapter adapter = new StackLayoutSelectionAdapter(
				shell, layout);
		final Button one = new Button(shell, SWT.PUSH);
		one.setText("one");
		one.addSelectionListener(adapter);
		final Button two = new Button(shell, SWT.PUSH);
		two.setText("two");
		two.addSelectionListener(adapter);
		final Button three = new Button(shell, SWT.PUSH);
		three.setText("three");
		three.addSelectionListener(adapter);
		layout.topControl = one;
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}

class StackLayoutSelectionAdapter extends SelectionAdapter {
	Shell shell;
	StackLayout layout;

	public StackLayoutSelectionAdapter(Shell shell, StackLayout layout) {
		this.shell = shell;
		this.layout = layout;
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		final Control control = layout.topControl;
		final Control[] children = shell.getChildren();
		int i = 0;
		for (final int n = children.length; i < n; i++) {
			final Control child = children[i];
			if (child == control) {
				break;
			}
		}
		++i;
		if (i >= children.length) {
			i = 0;
		}
		layout.topControl = children[i];
		shell.layout();
	}
}
