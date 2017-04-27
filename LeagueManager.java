import com.teamtreehouse.RosterGenerator;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.League;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    Team team = new Team("Tigers", "Bud Foster");
    System.out.printf("%s%n",team.toString());
    League league = new League();
    RosterGenerator roster = new RosterGenerator(league);
    roster.run();
  }

}
