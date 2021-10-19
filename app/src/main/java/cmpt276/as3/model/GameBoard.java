package cmpt276.as3.model;

import java.util.Random;

public class GameBoard {
    private Cell[][] gameBoard;
    private int rowSize;
    private int columnSize;
    private int numMushrooms;

    public GameBoard(int rowSize, int columnSize, int numMushrooms) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.numMushrooms = numMushrooms;

        this.gameBoard = new Cell[rowSize][columnSize];
        setCellFields(gameBoard, rowSize, columnSize);
    }

    public void setCellFields(Cell[][] gameBoard, int rowSize, int columnSize) {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                gameBoard[row][col] = new Cell(false, false);

                int remainingMushrooms = gameBoard[row][col].getNumRemainingMushrooms();
                gameBoard[row][col].setNumRemainingMushrooms(remainingMushrooms);
            }
        }

        for (int i = 0; i < numMushrooms; i++) {
            setMushrooms();
        }
    }

    public void setMushrooms() {
        int rowNum;
        int columnNum;

        rowNum = generateRandomNum(rowSize);
        columnNum = generateRandomNum(columnSize);

        while (gameBoard[rowNum][columnNum].isMushroom()) {
            rowNum = generateRandomNum(rowSize);
            columnNum = generateRandomNum(columnSize);
        }
        gameBoard[rowNum][columnNum].setMushroom(true);
    }

    public int generateRandomNum(int upperbound) {
        Random random = new Random();
        // Upper bound is rowSize or columnSize
        int randomNum = random.nextInt(upperbound);
        return randomNum;
    }

    public void setRemainingMushroomsOfCell(int rowNum, int columnNum) {
        int remainingMushrooms = 0;
        for (int i = 0; i < columnSize; i++) {
            if (gameBoard[rowNum][i].isMushroom()
                    && gameBoard[rowNum][i] != gameBoard[rowNum][columnNum] && !gameBoard[rowNum][i].isScanned()) {
                remainingMushrooms++;
            }
        }

        for (int i = 0; i < rowSize; i++) {
            if (gameBoard[i][columnNum].isMushroom()
                    && gameBoard[i][columnNum] != gameBoard[rowNum][columnNum] && !gameBoard[i][columnNum].isScanned()) {
                remainingMushrooms++;
            }
        }

        gameBoard[rowNum][columnNum].setNumRemainingMushrooms(remainingMushrooms);
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public int getNumMushrooms() {
        return numMushrooms;
    }
}