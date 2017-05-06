package com.teamtreehouse;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Queue; 
import java.io.*; 


public class RosterGenerator {
  private Team team;
  private String teamName;
  private String coachName;
  private Team mTeam = new Team(teamName, coachName);
  private Team teamChoice;
  private BufferedReader mReader;
  private List<Team> teams;
  private Player player;
  private Players players;
  private Player[] playersArray;
  private Queue<Player> mRoster;
  private Map<String, String>mMenu;
  private List<Player> playerList;
  private TreeSet<Player> playerSet;
  
  //Constructor
  public RosterGenerator() {
    //Initialization
    teams = new ArrayList<Team>();
    playersArray = players.load();
    playerList = Arrays.asList(playersArray);
    playerSet = new TreeSet<Player>(playerList);
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mRoster = new ArrayDeque<Player>();
    mMenu = new HashMap<String, String>();
    mMenu.put("create team", "Create a new team to add to the Teams");
    mMenu.put("add players", "Add players to a Team");
    mMenu.put("quit app", "Exit this application");
  }
  
  private String promptAction() throws IOException {
    System.out.printf("There are %d teams available in the League.%nYour options are: %n",
                     teams.size()
                     );
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
      String teamChoice = "";
      do {
        try {
          choice = promptAction();
          switch(choice) {
           case "create team":
            // TODO: create a team, prompt for coach and name
            team = promptNewTeam();
            // add that team to a set of teams located here
            teams.add(team);
            System.out.printf("%s added! %n%n", team);
            break;
           case "add players":
            if(teams.size() == 0) {
              System.out.printf("There are no teams to add players to. Please create a team first.%n%n");
            } else {
              
              teamChoice = promptTeam();
              System.out.printf("The %s have %d players on the team.%n%n", 
                              teamChoice,
                              mRoster.size());
              player = promptAvailablePlayers(teamChoice);
              mRoster.add(player);
              System.out.printf("You added player %s to the %s.%n",
                                player.getFirstName(), teamChoice );
            }
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
  
  //returns a new team with team name and coach
  private Team promptNewTeam() throws IOException {
      System.out.print("Enter the team's name:  ");
      teamName = mReader.readLine();
      System.out.print("Enter the coach's name:  ");
      coachName = mReader.readLine();
      return new Team(teamName, coachName);
    }
  
  //shows a list of teams to pick and returns that pick
  private String promptTeam() throws IOException {
      System.out.println("Pick a team to add players to:");
      List<String> league = new ArrayList<>(getTeams());
      int index = promptForIndex(league);
      return league.get(index);
    }
  
  //returns available players in the league
  private Player promptAvailablePlayers(String teamChoice) throws IOException {
    List<String> roster = new ArrayList<>();
    List<Player> mPlayers = new ArrayList<>(playerSet);
    for (Player player : mPlayers) {
      roster.add(player.getFirstName() + " " + player.getLastName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
      
   }
    System.out.printf("Available players for the %s: %n", teamChoice);
    int index = promptForIndex(roster);
    return mPlayers.get(index);
  }
 
  //Lists out each item in list by number and takes user number input
  private int promptForIndex(List<String> options) throws IOException {
    int counter = 1;
    for (String option : options) {
      System.out.printf("%d.) %s%n", counter, option);
      counter++;
    }
    String optionAsString = mReader.readLine();
    int choice = Integer.parseInt(optionAsString.trim());
    return choice -1;
  }
  
  private Map<String, List<Team>> byTeam() {
    Map<String, List<Team>> teamMap = new TreeMap<>();
    for (Team team : teams) {
      List<Team> teamList = teamMap.get(team.getTeamName());
      if (teamList == null) {
        teamList = new ArrayList<>();
        teamMap.put(team.getTeamName(), teamList);
      }
      teamList.add(team);
    }
    return teamMap;
  }
  
  private Map<String, Set<Player>> byPlayer() {
    Map<String, Set<Player>> playerMap = new TreeMap<>();
    for (Player player : playerSet) {
      Set<Player> playerList = playerMap.get(player.getFirstName());
      if (playerList == null) {
        playerList = new TreeSet<>();
        playerMap.put(player.getFirstName(), playerList);
      }
      playerList.add(player);
    }
    return playerMap;
  }
  
  public Set<String> getTeams() {
    return byTeam().keySet(); 
  }
  
  public Set<String> getAvailablePlayers() {
    return byPlayer().keySet(); 
  }

}