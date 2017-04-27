package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class League {
  private List<Player> mPlayers;
  private List<Team> mLeague;
  private Player mPlayer;
  
  public League() {
    mLeague = new ArrayList<Team>();
  }
 
  public void addTeam(Team team) {
    mLeague.add(team); 
  }
  
  public int getTeamCount() {
    return mLeague.size(); 
  }
  
  public void importPlayers() {
    List<Player> mPlayers = new ArrayList<>();
    
    for(Player player : mPlayers) {
    System.out.printf("Name: %s %s%n" + 
                      "Height: %d%n" + 
                      "Previous Experience: %s", 
                      player.getFirstName(),
                      player.getLastName(),
                      player.getHeightInInches(),
                      player.isPreviousExperience());
    }
  }
  
   private Map<String, List<Player>> byTeam() {
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