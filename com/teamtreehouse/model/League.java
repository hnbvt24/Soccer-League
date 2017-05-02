package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class League {
//  private List<Player> mPlayers;
  private Players players;
  private Player[] playersArray;
  private List<Team> mLeague;
  private Player mPlayer;
  
  public League() {
    mLeague = new ArrayList<Team>();
    playersArray = getPlayers();
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
  
//  public void readPlayers() {
//    List<Player> playerList = Arrays.asList(playersArray);
//    System.out.println("Printing players...");
//    int i = 0;
//    for(Player player : playerList) {
//      i++;
//      System.out.printf(i + ")  %s %s  |  %d in.  |  Previous Experience: %s%n",
//                        player.getFirstName(), 
//                        player.getLastName(),
//                        player.getHeightInInches(),
//                        Boolean.toString(player.isPreviousExperience()));
//    }
//  }
  
   private Map<String, List<Player>> byTeam() {
    List<Player> mPlayers = Arrays.asList(playersArray);
    Map<String, List<Player>> teamMap = new TreeMap<>(); //now in alphabetical order
    for (Player player : mPlayers) {
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