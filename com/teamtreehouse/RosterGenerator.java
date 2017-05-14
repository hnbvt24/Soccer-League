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
import java.io.*; 


public class RosterGenerator {
  private Team team;
  private String teamName;
  private String coachName;
  private Team teamChoice;
  private BufferedReader mReader;
  private List<Team> teams;
  private Player player;
  private Players players;
  private Player[] playersArray;
  private Map<String, String>mMenu;
  private Map<String, String>rMenu;
  private List<Player> playerList;
  private TreeSet<Player> playerSet;
  
  //Constructor
  public RosterGenerator() {
    //Initialization
    team = new Team(teamName, coachName);
    teams = new ArrayList<Team>();
    playersArray = players.load();
    playerList = Arrays.asList(playersArray);
    playerSet = new TreeSet<Player>(playerList);
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    rMenu = new HashMap<String, String>();
    mMenu.put("new", "Create a new team to add to the Teams");
    mMenu.put("add", "Add players to a Team");
    mMenu.put("remove", "Remove a player from the Team");
    mMenu.put("reports", "View reports of team rosters");
    mMenu.put("print", "Print your team roster");
    mMenu.put("quit", "Exit this application");
    rMenu.put("height", "Group players by height");
    rMenu.put("experience", "Group players by experience");
  }
  
  private String promptAction() throws IOException {
    System.out.printf("There are %d teams in the League.%nYour options are: %n",
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
  
  private String promptReports() throws IOException {
    System.out.println("Pick from the reports to see Team Stats.\nYour options are:\n");
    for (Map.Entry<String, String> option : rMenu.entrySet()) {
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.print("Which report would you like to see:  ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();
  }
  
  public void run() {
      String choice = "";
      String teamChoice = "";
      Set<Player> roster = new TreeSet<Player>();
      do {
        try {
          choice = promptAction();
          switch(choice) {
           case "new":
            // TODO: create a team, prompt for coach and name
            team = promptNewTeam();
            // add that team to a set of teams located here
            teams.add(team);
            System.out.printf("%s added! %n%n", team);
            break;
           case "add":
            if(teams.size() == 0) {
              System.out.printf("There are no teams to add players to. Please create a team first.%n%n");
            } else {
              
              teamChoice = promptTeam();
              for(Team team : teams) {
                if(team.getTeamName().equals(teamChoice)) {
                  roster = team.getTeam();
                  System.out.printf("The %s have %d players on the team.%n%n", 
                              teamChoice,
                              roster.size());
                  if(roster.size() == team.getMaxPlayers()) {
                    System.out.println("You have reached the maximum amount of players you can add to a team.");
                    break;
                  } else {
                  player = promptAvailablePlayers(teamChoice);
                  team.addPlayer(player);
                  System.out.printf("You added player %s %s to the %s.%n" +
                                "%s Roster: %d players%n%n",
                                player.getFirstName(),
                                player.getLastName(),
                                teamChoice,
                                teamChoice,
                                roster.size());
                  }
                }
              }
            }
            break;
           case "remove":
            if(teams.size() == 0) {
              System.out.printf("There are no teams to add players to. Please create a team first.%n%n");
            } else {
              
              teamChoice = promptTeam();
              for(Team team : teams) {
                if(team.getTeamName().equals(teamChoice)) {
                  roster = team.getTeam();
                  System.out.printf("The %s have %d players on the team.%n%n", 
                              teamChoice,
                              roster.size());
                  if(roster.size() == 0) {
                    System.out.println("You have no players on this team to remove.");
                    break;
                  } else {
                    player = promptRoster(teamChoice, roster);
                    team.removePlayer(player);
                    System.out.printf("You removed player %s %s from the %s.%n" +
                                  "%s Roster: %d players%n%n",
                                  player.getFirstName(),
                                  player.getLastName(),
                                  teamChoice,
                                  teamChoice,
                                  roster.size());
                    }
                }
              }
            }
            break;
           case "reports":
            if(teams.size() == 0) {
              System.out.printf("There are no teams to add players to. Please create a team first.%n%n");
            } else {
              String report = promptReports();
              if (report.equals("height")) {
                teamChoice = promptTeam();
                for(Team team : teams) {
                  if(team.getTeamName().equals(teamChoice)) {
                    roster = team.getTeam();
                  }
                }
              }
              switch(report) {
               case "height":
                Set<String> result = groupByHeight(roster);
                break;
               case "experience":
                result = groupByExperience();
                break;
               default:
                System.out.printf("Unknown choice:  '%s'. Try again. %n%n%n",
                                choice);
              }
            }
            break;
           case "print":
            //print players on a team roster
            if(teams.size() == 0) {
              System.out.printf("There are no teams to add players to. Please create a team first.%n%n");
            } else {
              teamChoice = promptTeam();
                for(Team team : teams) {
                  if(team.getTeamName().equals(teamChoice)) {
                    roster = team.getTeam();
                    List<String> result = printRoster(teamChoice, roster);
                    }
                }
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
    do {
      System.out.print("Enter the team's name:  ");
      teamName = mReader.readLine();
    } while(teamName.isEmpty());
    do {
      System.out.print("Enter the coach's name:  ");
      coachName = mReader.readLine();
    } while(coachName.isEmpty());
      return new Team(teamName, coachName);
    }
  
  //shows a list of teams to pick and returns that pick
  private String promptTeam() throws IOException {
      System.out.println("Pick a team:");
      List<String> league = new ArrayList<>(getTeams());
      int index = promptForIndex(league);
      return league.get(index);
    }
  
  //returns available players in the league
  private Player promptAvailablePlayers(String teamChoice) throws IOException {
    List<String> roster = new ArrayList<>();
    List<Player> mPlayers = new ArrayList<>(playerSet);
    for (Player player : mPlayers) {
      roster.add(player.getLastName() + ", " + player.getFirstName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
      
   }
    System.out.printf("%s's Roster: %n", teamChoice);
    int index = promptForIndex(roster);
    return mPlayers.get(index);
  }
    
  //returns roster of players on a team
  private Player promptRoster(String teamChoice, Set<Player> roster) throws IOException {
    List<String> mRoster = new ArrayList<>();
    List<Player> players = new ArrayList<>(roster);
    for (Player player : players) {
      mRoster.add(player.getLastName() + ", " + player.getFirstName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
      
   }
    System.out.printf("Players for the %s: %n", teamChoice);
    int index = promptForIndex(mRoster);
    return players.get(index);
  }   
  
  //prints roster of players on a team
  private List<String> printRoster(String teamChoice, Set<Player> roster) {
    int counter = 1;
    List<String> mRoster = new ArrayList<>();
    List<Player> players = new ArrayList<>(roster);
    System.out.printf("%s Players: %n%n", teamChoice);
    for (Player player : players) {
      System.out.printf("%d.) %s, %s || Height: %d || Experienced: %s%n", 
                        counter, 
                        player.getLastName(),
                        player.getFirstName(),
                        player.getHeightInInches(),
                        player.isPreviousExperience());
      counter++;
    }
    return mRoster;
  }
  
  //Lists out each item in list by number and takes user number input
  private int promptForIndex(List<String> options) throws IOException {
    int choice = 0;
    try {
      int counter = 1;
      for (String option : options) {
        System.out.printf("%d.) %s%n", counter, option);
        counter++;
      }
      String optionAsString = mReader.readLine();
      choice = Integer.parseInt(optionAsString.trim());
      
    } catch (IllegalArgumentException iae) {
        System.err.println("Invalid answer entered: " + iae.getMessage());
    }
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
  
  //prints teams by groups of height
  private Map<String, Set<String>> byHeight(Set<Player> roster) {
   Map<String, Set<String>> heightMap = new TreeMap<>();
     Set<String> groupA = new TreeSet<>();
     Set<String> groupB = new TreeSet<>();
     Set<String> groupC = new TreeSet<>();
     String params1 = "Height range between 35 and 40 in.";
     String params2 = "Height range between 41 and 46 in.";
     String params3 = "Height range between 47 and 50 in.";
    
     for (Player player : roster) {
       if(player.getHeightInInches() >= 35 && player.getHeightInInches() <= 40) {
         groupA.add(player.getLastName() + ", " + player.getFirstName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
       } else if (player.getHeightInInches() >= 41 && player.getHeightInInches() <= 46) {
         groupB.add(player.getLastName() + ", " + player.getFirstName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
       } else if (player.getHeightInInches() >= 47 && player.getHeightInInches() <= 50) {
         groupC.add(player.getLastName() + ", " + player.getFirstName() + " || Height:  " + player.getHeightInInches() + " in.  Experienced:  " + player.isPreviousExperience());
       } else {
         System.out.println("An error occurred printing the roster by height."); 
       }
     }
    
     heightMap.put(params1, groupA);
     heightMap.put(params2, groupB);
     heightMap.put(params3, groupC);

    //prints each player within each group
    for (Map.Entry<String, Set<String>> entry : heightMap.entrySet()) {
      System.out.println("\n" + entry.getKey() + "\n");
      List<String> group = new ArrayList<>(entry.getValue());
         for (String player : group) {
          
          System.out.println(player);
         }
    }
     
    return heightMap;
  }
  
  //prints each team and the number of experienced vs. non-experienced players
  private Map<String, String> byExperience() {
    Map<String, String> experienceMap = new TreeMap<>();
    for(Team team : teams) {
      String teamName = team.getTeamName();
      int groupA = 0;
      int groupB = 0;
      String params1 = "Inexperienced Players:";
      String params2 = "Experienced Players:";
        Set<Player> nPlayers = team.getTeam();
        for(Player player : nPlayers) {
          boolean experience = player.isPreviousExperience();
          if (!experience) {
            groupA++;
          } else {
            groupB++;
          }
        }
        System.out.println(teamName);
        System.out.println("Experienced Players: " + Integer.toString(groupB));
        System.out.println("Inxperienced Players: " + Integer.toString(groupA) + "\n");
      }
    return experienceMap;
  }
  
  public Set<String> getTeams() {
    return byTeam().keySet(); 
  }
  
  public Set<String> groupByHeight(Set<Player> roster) {
    return byHeight(roster).keySet();
  }
  
  public Set<String> groupByExperience() {
    return byExperience().keySet();
  }

}