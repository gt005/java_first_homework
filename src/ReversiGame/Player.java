package ReversiGame;

public class Player {
    // Лучший счет, полученый после окончания игр
    private int bestScore;

    public int getBestScore() {
        return bestScore;
    }

    public Player() {
        this.bestScore = 0;
    }

    public void setBestScore(int bestScore) {
        if (bestScore > 0) {
            this.bestScore = bestScore;
        }
    }
}
