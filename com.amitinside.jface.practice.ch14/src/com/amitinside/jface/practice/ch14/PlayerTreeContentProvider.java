package com.amitinside.jface.practice.ch14;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class provides the content for the TableTreeViewer in PlayerTableTree
 */
public class PlayerTreeContentProvider implements ITreeContentProvider {
	private static final Object[] EMPTY = new Object[] {};

	/**
	 * Gets the children for a team or player
	 * 
	 * @param arg0
	 *            the team or player
	 * @return Object[]
	 */
	@Override
	public Object[] getChildren(Object arg0) {
		if (arg0 instanceof Team)
			return ((Team) arg0).getPlayers().toArray();
		// Players have no children . . . except Shawn Kemp
		return EMPTY;
	}

	/**
	 * Gets the parent team for a player
	 * 
	 * @param arg0
	 *            the player
	 * @return Object
	 */
	@Override
	public Object getParent(Object arg0) {
		return ((Player) arg0).getTeam();
	}

	/**
	 * Gets whether this team or player has children
	 * 
	 * @param arg0
	 *            the team or player
	 * @return boolean
	 */
	@Override
	public boolean hasChildren(Object arg0) {
		return getChildren(arg0).length > 0;
	}

	/**
	 * Gets the elements for the table
	 * 
	 * @param arg0
	 *            the model
	 * @return Object[]
	 */
	@Override
	public Object[] getElements(Object arg0) {
		// Returns all the teams in the model
		return ((PlayerTableModel) arg0).teams;
	}

	/**
	 * Disposes any resources
	 */
	@Override
	public void dispose() {
		// We don't create any resources, so we don't dispose any
	}

	/**
	 * Called when the input changes
	 * 
	 * @param arg0
	 *            the parent viewer
	 * @param arg1
	 *            the old input
	 * @param arg2
	 *            the new input
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// Nothing to do
	}
}