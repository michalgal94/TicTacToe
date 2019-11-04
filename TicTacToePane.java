package newSplitTicTacToe;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class TicTacToePane extends BorderPane implements TicTacToeInterface {
	private static int number_of_objects = 0;
	private char whoseTurn = 'X';

	// Create and initialize a status label
	private Label lblStatus = new Label("X's turn to play");
	private Cell[][] cell = new Cell[3][3];

	
	private TicTacToePane() {
		GridPane pane = new GridPane();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				pane.add(cell[i][j] = new Cell(), i, j);

		this.setCenter(pane);
		this.setBottom(lblStatus);


	}

	public static TicTacToePane getInstance() {
		if (number_of_objects > NUMBER_OF_OBJECTS)
			return null;
		else {
			number_of_objects++;
			return new TicTacToePane();
		}
	}

	public static void reduceNumberOfObjects() {
		number_of_objects--;
	}

	public static int getNumberOfObjects() {
		return number_of_objects;
	}

	public static void resetNumberOfObjects() {
		number_of_objects = 0;
	}
	



		public boolean isFull() {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (cell[i][j].getToken() == ' ')
						return false;
			return true;
		}

		/** Determine if the player with the specified token wins */
		public boolean isWon(char token) {
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


		// An inner class for a cell
		class Cell extends Pane implements TicTacToeInterface{ // Token used for this cell
			private char token = ' ';
			
			public Cell() {
				setStyle(STYLE_COMMAND);
				this.setPrefSize(2000, 2000);
				this.setOnMouseClicked(e -> handleMouseClick());
			}

			/** Return token */
			public char getToken() {
				return token;
			}

			/** Set a new token */
			public void setToken(char c) {
				token = c;
				if (token == 'X') {
					Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
					line1.endXProperty().bind(this.widthProperty().subtract(10));
					line1.endYProperty().bind(this.heightProperty().subtract(10));
					Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
					line2.startYProperty().bind(this.heightProperty().subtract(10));
					line2.endXProperty().bind(this.widthProperty().subtract(10));
					// Add the lines to the pane
					this.getChildren().addAll(line1, line2);
				} else if (token == 'O') {
					Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10,
							this.getHeight() / 2 - 10);
					ellipse.centerXProperty().bind(this.widthProperty().divide(2));
					ellipse.centerYProperty().bind(this.heightProperty().divide(2));
					ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
					ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
					ellipse.setStroke(Color.BLACK);
					ellipse.setFill(Color.WHITE);
					getChildren().add(ellipse); // Add the ellipse to the pane
				}
			}

			/* Handle a mouse click event */
			private void handleMouseClick() { // If cell is empty and game is not over
				if (token == ' ' && whoseTurn != ' ') {
					setToken(whoseTurn); // Set token in the cell
					// Check game status
					if (isWon(whoseTurn)) {
						lblStatus.setText(whoseTurn + " won! The game is over");
						whoseTurn = ' '; // Game is over
					} else if (isFull()) {
						lblStatus.setText("Draw! The game is over");
						whoseTurn = ' '; // Game is over
					} else { // Change the turn
						whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
						// Display whose turn
						lblStatus.setText(whoseTurn + "'s turn");
					}
				}
			}
			
		
		}
}
