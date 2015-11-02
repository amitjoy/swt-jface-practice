package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FormLayoutSimple {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final FormLayout layout = new FormLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		shell.setLayout(layout);
		new Button(shell, SWT.PUSH).setText("Button");
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
