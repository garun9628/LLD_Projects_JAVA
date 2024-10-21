package SnakeLadderGame.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int noOfDice;
    int minVal = 1;
    int maxVal = 6;

    public Dice(int noOfDice) {
        this.noOfDice = noOfDice;
    }

    public int rollDice() {
        int diceUsed = 1;
        int totalSum = 0;
        while(diceUsed <= noOfDice) {
            totalSum += ThreadLocalRandom.current().nextInt(minVal, maxVal+1);
            diceUsed++;
        }

        return totalSum;
    }
}
