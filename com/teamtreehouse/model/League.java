package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Set;

public class League {
//  private List<Player> mPlayers;
  private Players players;
  private Player[] playersArray;
  List<Player> playerList;
  private List<Team> mLeague;
  private Player mPlayer;
  
  public League() {
    mLeague = new ArrayList<Team>();
    playersArray = getPlayers();
    playerList = Arrays.asList(playersArray);
  }
 
  public void addTeam(Team team) {
    mLeague.add(team); 
  }
  
  public int getTeamCount() {
    return mLeague.size(); 
  }
  
  public Player[] getPlayers() {
    return players.load(); 
  }
  
   private Map<String, List<Player>> byTeam() {
    Map<String, List<Player>> teamMap = new TreeMap<>(); //now in alphabetical order
    for (Player player : playerList) {
      List<Player> teamPlayers = teamMap.get(player.getFirstName());
      if (teamPlayers == null) {
        teamPlayers = new ArrayList<>();
        teamMap.put(player.getFirstName(), teamPlayers);
      }
      teamPlayers.add(player);
    }
    return teamMap;
  }
  
  public Set<String> getTeams() {
    return byTeam().keySet(); 
  }
  
}