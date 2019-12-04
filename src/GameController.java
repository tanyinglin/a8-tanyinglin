import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GameController implements ActionListener{

	private boolean isRunning;
	private Thread thread;
    private boolean isDead;
	@Override
	public void actionPerformed(ActionEvent e) {
		//GameView _vieww = GameView._view;
		
		if (e.getSource() == GameView._view.bt_OK) {
			GameView._view.setMaxRow(GameView._view.row_list.getSelectedIndex() + 3);
			GameView._view.setMaxCol(GameView._view.col_list.getSelectedIndex() + 3);
			GameView._view.setGame();
			GameModel _model = new GameModel(GameView._view.getMaxRow(), GameView._view.getMaxCol());
        } else if (e.getSource() == GameView._view.bt_next) {
            makeNextGeneration();
        } else if (e.getSource() == GameView._view.bt_start) {
            isRunning = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        makeNextGeneration();
                        boolean isSame = true;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        isDead = true;
                        for(int row = 1; row <= GameView._view.max_row; row++) {
                            for (int col = 1; col <= GameView._view.max_col; col++) {
                                if (GameView._view._model.getBoard()[row][col] != 0) {
                                    isDead = false;
                                    break;
                                }
                            }
                            if (!isDead) {
                                break;
                            }
                        }
                        if (isDead) {
                            JOptionPane.showMessageDialog(null, "Life gone.");
                            isRunning = false;
                            thread = null;
                        }
                    }
                }
            });
            thread.start();
        } else if (e.getSource() == GameView._view.bt_stop) {
            isRunning = false;
            thread = null;
        } else if (e.getSource() == GameView._view.bt_exit) {
            System.exit(0);
        } else {
            int[][] grid = GameView._view._model.getBoard();
            for (int i = 0; i <  GameView._view.max_row; i++) {
                for (int j = 0; j <  GameView._view.max_col; j++) {
                    if (e.getSource() ==  GameView._view.bt_board[i][j]) {
                    	 GameView._view.is_selected[i][j] = !GameView._view.is_selected[i][j];
                        if (GameView._view.is_selected[i][j]) {
                        	GameView._view.bt_board[i][j].setBackground(Color.BLACK);
                            grid[i + 1][j + 1] = 1;
                        } else {
                        	GameView._view.bt_board[i][j].setBackground(Color.WHITE);
                            grid[i + 1][j + 1] = 0;
                        }
                        break;
                    }
                }
            }
            GameView._view._model.setBoard(grid);


        }
		
	}
	
	private void makeNextGeneration() {
		 GameView._view._model.update();
		 int [][] grid =  GameView._view._model.getBoard();
		 for(int i= 0; i < GameView._view.max_row; i++) {
			 for(int j=0; j < GameView._view.max_col; j++) {
				 if(grid[i+1][j+1]==1) {
					 GameView._view.bt_board[i][j].setBackground(Color.BLACK);
				 }
				 else {
					 GameView._view.bt_board[i][j].setBackground(Color.WHITE);
				 }
			 }
		 }
	}

}
