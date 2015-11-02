package com.amitinside.jface.practice.ch14;

import java.io.File;
import java.io.FileFilter;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class returns the files in the specified directory. If the specified
 * directory doesn't exist, it returns an empty array.
 */
public class BackupFilesContentProvider implements IStructuredContentProvider {
	private static final Object[] EMPTY = new Object[] {};

	/**
	 * Gets the files in the specified directory
	 * 
	 * @param arg0
	 *            a String containing the directory
	 */
	@Override
	public Object[] getElements(Object arg0) {
		final File file = new File((String) arg0);
		if (file.isDirectory()) {
			return file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathName) {
					// Ignore directories; return only files
					return pathName.isFile();
				}
			});
		}
		return EMPTY;
	}

	/**
	 * Disposes any created resources
	 */
	@Override
	public void dispose() {
		// Nothing to dispose
	}

	/**
	 * Called when the input changes
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// Nothing to do
	}
}