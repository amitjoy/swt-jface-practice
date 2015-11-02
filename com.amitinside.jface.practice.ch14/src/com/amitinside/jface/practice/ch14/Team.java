package com.amitinside.jface.practice.ch14;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a team
 */
public class Team {
	private final String name;
	private final String year;
	private final List<Player> players;

	/**
	 * Constructs a Team
	 * 
	 * @param name
	 *            the name
	 * @param year
	 *            the year
	 */
	public Team(String name, String year) {
		this.name = name;
		this.year = year;
		players = new LinkedList<Player>();
	}

	/**
	 * Gets the name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the year
	 * 
	 * @return String
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Adds a player
	 * 
	 * @param player
	 *            the player to add
	 * @return boolean
	 */
	public boolean add(Player player) {
		final boolean added = players.add(player);
		if (added)
			player.setTeam(this);
		return added;
	}

	/**
	 * Gets the players
	 * 
	 * @return List
	 */
	public List getPlayers() {
		return Collections.unmodifiableList(players);
	}

	/**
	 * Returns whether the specified player led his team in the specified
	 * category
	 * 
	 * @param player
	 *            the player
	 * @param column
	 *            the category
	 * @return boolean
	 */
	public boolean led(Player player, int column) {
		boolean led = true;

		// Go through all the players on the team, comparing the specified
		// player's
		// stats with each other player.
		for (int i = 0, n = players.size(); i < n && led; i++) {
			final Player test = players.get(i);
			if (player == test)
				continue;
			switch (column) {
			case PlayerConst.COLUMN_POINTS:
				if (player.getPoints() < test.getPoints())
					led = false;
				break;
			case PlayerConst.COLUMN_REBOUNDS:
				if (player.getRebounds() < test.getRebounds())
					led = false;
				break;
			case PlayerConst.COLUMN_ASSISTS:
				if (player.getAssists() < test.getAssists())
					led = false;
				break;
			}
		}
		return led;
	}
}