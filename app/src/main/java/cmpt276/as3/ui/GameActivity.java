package cmpt276.as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import cmpt276.as3.R;
import cmpt276.as3.model.Cell;
import cmpt276.as3.model.GameBoard;
import cmpt276.as3.model.Options;
import cmpt276.as3.model.PlayerStats;

public class GameActivity extends AppCompatActivity {
    private static final int NUM_SCANS_AT_START = 0;
    private static final int NUM_CELLS_FOUND_AT_START = 0;
    private static final int NUM_BEST_SCORE_AT_START = 0;

    private int numRows;
    private int numColumns;
    private int numMushrooms;
    private Options options;
    private GameBoard gameBoard;
    private Cell[][] theGameBoard;
    private Button buttons[][];

    private PlayerStats playerStats;
    private TextView numScansDisplay;
    private String scansFormat;
    private TextView numMushroomsDisplay;
    private String foundMushroomsFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        options = Options.getInstance();
        numRows = options.getRowSize();
        numColumns = options.getColumnSize();
        numMushrooms = options.getNumMushrooms();
        buttons = new Button[numRows][numColumns];

        gameBoard = new GameBoard(numRows, numColumns, numMushrooms);
        theGameBoard = gameBoard.getGameBoard();

        playerStats = new PlayerStats(
                NUM_SCANS_AT_START, NUM_CELLS_FOUND_AT_START, NUM_BEST_SCORE_AT_START);

        scansFormat = getString(R.string.num_scans, playerStats.getNumScans());
        numScansDisplay = findViewById(R.id.numScans);
        numScansDisplay.setText(String.valueOf(scansFormat));

        foundMushroomsFormat = getString(
            R.string.num_mushrooms, playerStats.getNumCellsFound(), gameBoard.getNumMushrooms());
        numMushroomsDisplay = findViewById(R.id.numMushrooms);
        numMushroomsDisplay.setText(String.valueOf(foundMushroomsFormat));

        populateButtons();
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForMushrooms);

        for (int row = 0; row < numRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numColumns; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];

        if (theGameBoard[row][col].isScanned()) {
            return;
        }
        theGameBoard[row][col].setScanned(true);

        if (theGameBoard[row][col].isMushroom()) {
            updateFoundMushrooms();
            updateRemainingMushroomsDisplay();
        }
        updateScanDisplay();
        lockButtonSizes();

        if (theGameBoard[row][col].isMushroom()) {
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie_mushroom);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    gameBoard.setRemainingMushroomsOfCell(i, j);
                }
            }
        } else {
            gameBoard.setRemainingMushroomsOfCell(row, col);
            button.setText("" + theGameBoard[row][col].getNumRemainingMushrooms());
        }
    }

    private void updateRemainingMushroomsDisplay() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                gameBoard.setRemainingMushroomsOfCell(i, j);
                if (theGameBoard[i][j].isScanned() && !theGameBoard[i][j].isMushroom()) {
                    buttons[i][j].setText("" + theGameBoard[i][j].getNumRemainingMushrooms());
                }
            }
        }
    }

    private void updateScanDisplay() {
        playerStats.incrementNumScans();
        scansFormat = getString(R.string.num_scans, playerStats.getNumScans());
        numScansDisplay.setText(String.valueOf(scansFormat));
    }

    private void updateFoundMushrooms() {
        playerStats.incrementCellsFound();
        foundMushroomsFormat = getString(
            R.string.num_mushrooms, playerStats.getNumCellsFound(), gameBoard.getNumMushrooms());
        numMushroomsDisplay.setText(String.valueOf(foundMushroomsFormat));
    }

    private void lockButtonSizes() {
        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numColumns; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}