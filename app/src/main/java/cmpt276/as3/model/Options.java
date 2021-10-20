package cmpt276.as3.model;

/*
    This class presents the options components.
    The grid size and mushrooms are adjusted accordingly in here.
 */
public class Options {
    private static final int DEFAULT_ROW_SIZE = 4;
    private static final int DEFAULT_COLUMN_SIZE = 6;
    private static final int DEFAULT_NUM_MUSHROOMS = 6;

    private int rowSize;
    private int columnSize;
    private int numMushrooms;

    /*
        Singleton
     */
    private static Options instance;
    private Options() {
        this.rowSize = DEFAULT_ROW_SIZE;
        this.columnSize = DEFAULT_COLUMN_SIZE;
        this.numMushrooms = DEFAULT_NUM_MUSHROOMS;
    }
    public static Options getInstance() {
        if (instance == null) {
            instance = new Options();
        }
        return instance;
    }

    /*
        Normal Object Code
     */
    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public int getNumMushrooms() {
        return numMushrooms;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setNumMushrooms(int numMushrooms) {
        this.numMushrooms = numMushrooms;
    }
}
