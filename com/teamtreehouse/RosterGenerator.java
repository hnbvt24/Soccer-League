package com.teamtreehouse;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.League;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;


public class RosterGenerator {
  private Team team;
  private Team pTeam;
  private League mLeague = new League();
  private Players players;
  private Player[] playersArray;
  private BufferedReader mReader;
  private Map<String, String>mMenu;
  
  //Constructor
  public RosterGenerator(League league) {
    //Initialization
    mLeague = league;
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mMenu.put("create team", "Create a new team to add to the Teams");
    mMenu.put("add players", "Add players to a Team");
  }
  
  private String promptAction() throws IOException {
    System.out.printf("There are %d teams available. Your options are: %n",
                     mLeague.getTeamCount());
    for (Map.Entry<String, String> option : mMenu.entrySet()) {
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.print("What do you want to do:  ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();
  }
  
  public void run() {
      String choice = "";
      do {
        try {
          choice = promptAction();
          switch(choice) {
           case "create team":
            Team team = promptNewTeam();
            mLeague.addTeam(team);
            System.out.printf("%s added! %n%n", team);
            break;
           case "add players":
            String mTeam = promptTeam();
            Player teamPlayer = promptPlayerForTeam(mTeam);
            pTeam.addPlayers(teamPlayer);
            System.out.printf("You chose: %s %n", teamPlayer);
            break;
           case "quit":
            System.out.println("Play Ball!");
            break;
           default:
            System.out.printf("Unknown choice:  '%s'. Try again. %n%n%n",
                              choice);
          }
        } catch(IOException ioe) {
          System.out.println("Problem with input");
          ioe.printStackTrace();
        }
      } while(!choice.equals("quit"));
    }
  
  private Team promptNewTeam() throws IOException {
      System.out.print("Enter the team's name:  ");
      String teamName = mReader.readLine();
      System.out.print("Enter the coach's name:  ");
      String coach = mReader.readLine();
      return new Team(teamName, coach);
    }
  
  private String promptTeam() throws IOException {
      System.out.println("Available teams:");
      List<String> league = new ArrayList<>(mLeague.getTeams());
      int index = promptForIndex(league);
      return league.get(index);
    }
  
  private Player promptPlayerForTeam(String mTeam) throws IOException {
    List<Player> playerList = Arrays.asList(mLeague.getPlayers());
    List<String> playerNames = new ArrayList<>();
    for (Player player : playerList) {
      playerNames.add(player.getFirstName());
    }
    System.out.printf("Available songs for %s: %n", mTeam);
    int index = promptForIndex(playerNames);
    return playerList.get(index);
  }
  
  private int promptForIndex(List<String> options) throws IOException {
    int counter = 1;
    for (String option : options) {
      System.out.printf("d.) % %s", counter, option);
      counter++;
    }
    String optionAsString = mReader.readLine();
    int choice = Integer.parseInt(optionAsString.trim());
    System.out.print("Your choice:  ");
    return choice -1;
  }
  
  
}