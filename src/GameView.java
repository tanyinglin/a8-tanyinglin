import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JFrame {
	protected static GameView _view;
	protected GameModel _model;
	private JPanel backPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	protected JButton bt_OK;
	protected JButton bt_next;
	protected JButton bt_start;
	protected JButton bt_stop;
	protected JButton bt_exit;
	protected JButton[][] bt_board;
	private JLabel la_row;
	private JLabel la_col;
	protected JComboBox row_list;
	protected JComboBox col_list;
	protected boolean[][] is_selected;
	protected int max_row;
	protected int max_col;
	
	public int getMaxRow() {
		return max_row;
	}
	public void setMaxRow(int max) {
		max_row = max;
	}
	public int getMaxCol() {
		return max_col;
	}
	public void setMaxCol(int max) {
		max_col = max;
	}
	public void setGame() {
		if(max_row == 0) {
			max_row = 20;
		}
		if(max_col == 0) {
			max_col = 60;
		}
		_model = new GameModel(max_row, max_col);
		
		backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(max_row, max_col));
        bottomPanel = new JPanel();
        
        row_list = new JComboBox();
        for(int i = 3; i <= 20; i++) {
        	row_list.addItem(String.valueOf(i));
        }
        
        col_list = new JComboBox();
        for(int i = 3; i <= 60; i++) {
        	col_list.addItem(String.valueOf(i));
        }
        
        row_list.setSelectedIndex(max_row - 3);
        col_list.setSelectedIndex(max_col - 3);
        
        bt_OK = new JButton("Confirm");
        bt_next = new JButton("Next");
        bt_board = new JButton[max_row][max_col];
        bt_start = new JButton("Start");
        bt_stop = new JButton("Stop");
        bt_exit = new JButton("Exit");
        
        is_selected = new boolean[max_row][max_col];
        
        la_row = new JLabel ("Set Row:");
        la_col = new JLabel ("Set Column:");
        this.setContentPane(backPanel);
        
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "South");
        
        for(int i = 0; i < max_row; i++) {
        	for(int j = 0; j < max_col; j++) {
        		bt_board[i][j] = new JButton("");
        		bt_board[i][j].setBackground(Color.WHITE);
        		centerPanel.add(bt_board[i][j]);
        	}
        }
        bottomPanel.add(la_row);
        bottomPanel.add(row_list);
        bottomPanel.add(la_col);
        bottomPanel.add(col_list);
        bottomPanel.add(bt_OK);
        bottomPanel.add(bt_next);
        bottomPanel.add(bt_start);
        bottomPanel.add(bt_stop);
        bottomPanel.add(bt_exit);
        
        this.setSize(900, 620);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
        
        this.addWindowFocusListener(new WindowAdapter(){
        	public void windowClosed(WindowEvent e) {
        		System.exit(0);
        	}
        });
        
        //bt_OK.addActionListener(this);
       
	}
	 public GameView(String name) {
     	super(name);
     	setGame();
     }
	 public static void main(String arg[]) {
	        _view = new GameView("Conway's Game of Life");
	    }

}
