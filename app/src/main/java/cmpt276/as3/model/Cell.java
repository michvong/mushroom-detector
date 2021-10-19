package cmpt276.as3.model;

public class Cell {
    private boolean isMushroom;
    private boolean isScanned;
    private int remainingMushroomsInRowCol;

    public Cell(boolean isMushroom, boolean isScanned) {
        this.isMushroom = isMushroom;
        this.isScanned = isScanned;
    }

    public boolean isMushroom() {
        return isMushroom;
    }

    public void setMushroom(boolean mushroom) {
        isMushroom = mushroom;
    }

    public boolean isScanned() {
        return isScanned;
    }

    public void setScanned(boolean scanned) {
        isScanned = scanned;
    }

    public int getNumRemainingMushrooms() {
        return remainingMushroomsInRowCol;
    }

    public void setNumRemainingMushrooms(int remainingMushroomsInRowCol) {
        this.remainingMushroomsInRowCol = remainingMushroomsInRowCol;
    }
}
