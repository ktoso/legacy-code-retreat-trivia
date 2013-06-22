package com.sckrk.legacycoderetreat.golden;

import com.adaptionsoft.games.uglytrivia.Game;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class GoldenGenerator {

  private static boolean notAWinner;

  public static void main(String[] args) throws IOException {
    int from  = Integer.parseInt(args[0]);
    int upto = Integer.parseInt(args[1]);
    String dir = args[2];

    for (int seed = from; seed < upto; seed++) {
      generate(seed, dir);
      System.out.print(".");
    }
  }

  private static void generate(int seed, String dir) throws IOException {
    Random random = new Random(seed);

    File golden = new File(String.format("./%s", dir));
    File target = new File(golden, String.format("%d.out", seed));
    Files.createParentDirs(target);
    target.createNewFile();

    PrintStream stream = new PrintStream(target);
    System.setOut(stream);

    run(random);

    // finally ;-)
    stream.close();
  }

  public static void run(Random rand) {
    Game aGame = new Game();

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    do {

      aGame.roll(rand.nextInt(5) + 1);

      if (rand.nextInt(9) == 7) {
        notAWinner = aGame.wrongAnswer();
      } else {
        notAWinner = aGame.wasCorrectlyAnswered();
      }

    } while (notAWinner);
  }

}
