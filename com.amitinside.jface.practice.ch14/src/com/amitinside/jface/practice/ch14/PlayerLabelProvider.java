package com.amitinside.jface.practice.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the labels for PlayerTable
 */
public class PlayerLabelProvider implements ITableLabelProvider {
	// Image to display if the player led his team
	private Image ball;

	// Constructs a PlayerLabelProvider
	public PlayerLabelProvider() {
		// Create the image
		try {
			ball = new Image(null, new FileInputStream("images/ball.png"));
		} catch (final FileNotFoundException e) {
			// Swallow it
		}
	}

	/**
	 * Gets the image for the specified column
	 * 
	 * @param arg0
	 *            the player
	 * @param arg1
	 *            the column
	 * @return Image
	 */
	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		final Player player = (Player) arg0;
		Image image = null;
		switch (arg1) {
		// A player can't lead team in first name or last name
		case PlayerConst.COLUMN_POINTS:
		case PlayerConst.COLUMN_REBOUNDS:
		case PlayerConst.COLUMN_ASSISTS:
			if (player.ledTeam(arg1))
				// Set the image
				image = ball;
			break;
		}
		return image;
	}

	/**
	 * Gets the text for the specified column
	 * 
	 * @param arg0
	 *            the player
	 * @param arg1
	 *            the column
	 * @return String
	 */
	@Override
	public String getColumnText(Object arg0, int arg1) {
		final Player player = (Player) arg0;
		String text = "";
		switch (arg1) {
		case PlayerConst.COLUMN_FIRST_NAME:
			text = player.getFirstName();
			break;
		case PlayerConst.COLUMN_LAST_NAME:
			text = player.getLastName();
			break;
		case PlayerConst.COLUMN_POINTS:
			text = String.valueOf(player.getPoints());
			break;
		case PlayerConst.COLUMN_REBOUNDS:
			text = String.valueOf(player.getRebounds());
			break;
		case PlayerConst.COLUMN_ASSISTS:
			text = String.valueOf(player.getAssists());
			break;
		}
		return text;
	}

	/**
	 * Adds a listener
	 * 
	 * @param arg0
	 *            the listener
	 */
	@Override
	public void addListener(ILabelProviderListener arg0) {
		// Throw it away
	}

	/**
	 * Dispose any created resources
	 */
	@Override
	public void dispose() {
		// Dispose the image
		if (ball != null)
			ball.dispose();
	}

	/**
	 * Returns whether the specified property, if changed, would affect the
	 * label
	 * 
	 * @param arg0
	 *            the player
	 * @param arg1
	 *            the property
	 * @return boolean
	 */
	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	/**
	 * Removes the specified listener
	 * 
	 * @param arg0
	 *            the listener
	 */
	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// Do nothing
	}
}