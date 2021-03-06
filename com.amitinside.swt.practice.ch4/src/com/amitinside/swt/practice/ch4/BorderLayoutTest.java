package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BorderLayoutTest {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new BorderLayout());
		final Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("North");
		b1.setLayoutData(BorderData.NORTH);
		final Button b2 = new Button(shell, SWT.PUSH);
		b2.setText("South");
		b2.setLayoutData(BorderData.SOUTH);
		final Button b3 = new Button(shell, SWT.PUSH);
		b3.setText("East");
		b3.setLayoutData(BorderData.EAST);
		final Button b4 = new Button(shell, SWT.PUSH);
		b4.setText("West");
		b4.setLayoutData(BorderData.WEST);
		final Button b5 = new Button(shell, SWT.PUSH);
		b5.setText("Center");
		b5.setLayoutData(BorderData.CENTER);
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
