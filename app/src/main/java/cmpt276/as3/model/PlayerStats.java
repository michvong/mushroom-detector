package cmpt276.as3.model;

public class PlayerStats {
    private int numScans;
    private int numCellsFound;
    private int bestScore;

    public PlayerStats(int numScans, int numCellsFound, int bestScore) {
        this.numScans = numScans;
        this.numCellsFound = numCellsFound;
        this.bestScore = bestScore;
    }

    public void incrementNumScans() {
        numScans++;
    }

    public void incrementCellsFound() {
        numCellsFound++;
    }

    public int getNumScans() {
        return numScans;
    }

    public int getNumCellsFound() {
        return numCellsFound;
    }
}

