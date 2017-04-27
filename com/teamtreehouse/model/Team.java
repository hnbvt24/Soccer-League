package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private String mTeamName;
  private String mCoach;
  private List<Player> mPlayers;
  
  public Team(String teamName, String coach) {
    mTeamName = teamName;
    mCoach = coach;
    mPlayers = new ArrayList<Player>();
  }
  
  public String getTeamName() {
    return mTeamName; 
  }
  
  public String getCoach() {
    return mCoach; 
  }
  
  @Override
  public String toString() {
    return String.format("Team:  %s, coached by %s", mTeamName, mCoach);
  }
  
}