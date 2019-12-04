
public class GameModel {
	private int max_r;
    private int max_c;
 
    private int[][] board;
    
    public GameModel(int r, int c) {
    	max_r = r;
    	max_c = c;
    	
    	board = new int[max_r+2][max_c+2];
    	
    	for(int rr = 0; rr <= max_r+1; rr++) {
    		for(int cc = 0; cc<= max_c+1; cc++) {
    			board[rr][cc] = 0;
    		}
    	}
    }
    public int[][] getBoard(){
		return this.board;
	}
    
    public void setBoard(int[][]b) {
    	this.board = b;
    }
    
    public void update() {
    	int[][] new_board = new int[max_r+2][max_c+2];
    	for(int row =1; row<=max_r; row++) {
    		for(int col = 1; col <= max_c; col++) {
    			//stay the same
    			if(getNeighborCount(row, col)==2) {
    				new_board[row][col] = board[row][col];
    				break;
    			}
    			//alive cell
    			else if(getNeighborCount(row, col)==3) {
    				new_board[row][col] = 1;
    				break;
    			}
    			//dead cell
    			else
    				new_board[row][col] = 0;
    		}
    		for(int roww = 1; roww<=max_r; roww++) {
    			for(int coll = 1; coll <=max_c; coll++) {
    				board[roww][coll] = new_board[roww][coll];
    			}
    		}
    	}
    }
    
    private int getNeighborCount(int rrr, int ccc) {
    	int count = 0; 
    	for(int i = rrr-1; i <=rrr+1; i++) {
    		for(int j = ccc-1; j<=ccc+1; j++) {
    			count+=board[i][j];
    			count -=board[i][j];
    		}
    	}
    	return count;
    }
}
