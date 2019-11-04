import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TicTacToeSingleCommand extends Application { // Indicate which player has a turn, initially it is the X
															// player
	private static char whoseTurn = 'X';
	// Create and initialize cell
	private static CellSingleCommand[][] cell = new CellSingleCommand[3][3];
	// Create and initialize a status label
	protected static Label lblStatus = new Label("X's turn to play");
	private static int numberOfObjects;
	private static final int NUMBER_OF_OBJECTS = 3;

	public static void main(String[] args) {
		launch(args);
	}

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Pane to hold cell
		GridPane pane = new GridPane();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				pane.add(cell[i][j] = CellSingleCommand.getInstance(), i, j);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(lblStatus);
		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 450, 170);
		primaryStage.setTitle("TicTacToe"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		primaryStage.setAlwaysOnTop(true);

	}

	/** Determine if the cell are all occupied */
	public boolean isFull() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (cell[i][j].getToken() == ' ')
					return false;
		return true;
	}

	/** Determine if the player with the specified token wins */
	public static boolean isWon(char token) {
		for (int i = 0; i < 3; i++)
			if (cell[i][0].getToken() == token && cell[i][1].getToken() == token && cell[i][2].getToken() == token)
				return true;
		for (int j = 0; j < 3; j++)
			if (cell[0][j].getToken() == token && cell[1][j].getToken() == token && cell[2][j].getToken() == token)
				return true;
		if (cell[0][0].getToken() == token && cell[1][1].getToken() == token && cell[2][2].getToken() == token)
			return true;
		if (cell[0][2].getToken() == token && cell[1][1].getToken() == token && cell[2][0].getToken() == token)
			return true;
		return false;
	}

	public static char getWhoseTurn() {
		return whoseTurn;
	}

	public static void setWhoseTurn(char whoseTurn) {
		TicTacToeSingleCommand.whoseTurn = whoseTurn;
	}

	public static GridPane getInstance() {
		if (numberOfObjects >= NUMBER_OF_OBJECTS) {
			return null;
		}
		numberOfObjects++;
		return new GridPane();
	}
}