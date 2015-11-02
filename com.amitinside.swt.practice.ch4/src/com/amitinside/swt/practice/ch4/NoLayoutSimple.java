package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NoLayoutSimple {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final Button button = new Button(shell, SWT.PUSH);
		button.setText("No layout");
		button.setBounds(new Rectangle(5, 5, 100, 100));
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
