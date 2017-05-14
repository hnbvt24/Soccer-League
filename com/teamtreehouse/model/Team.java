package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet; 

public class Team {
  private Set<Player> team;
  private String mCoach;
  private String mTeamName;
  public static final int MAX_PLAYERS = 11;
  
  public Team(String teamName, String coach) {
    mCoach = coach;
    mTeamName = teamName;
    team = new TreeSet<Player>();
  }
  
  public String getCoach() {
    return mCoach; 
  }
  
  public String getTeamName() {
    return mTeamName; 
  }
  
  @Override
  public String toString() {
    return String.format("The %s, coached by %s", mTeamName, mCoach);
  }
  
  public void addPlayer(Player player) {
    team.add(player); 
  }
  public void removePlayer(Player player) {
    team.remove(player); 
  }
  
  public int getMaxPlayers() {
    return MAX_PLAYERS; 
  }
  
  public int getTeamCount() {
    return team.size(); 
  }
  
  public Set<Player> getTeam() {
    return team;
  }
  
}