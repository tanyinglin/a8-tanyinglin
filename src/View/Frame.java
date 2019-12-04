package View;
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import java.awt.Toolkit;
	import controller.Process;

	//collaborated with Zining Han
	public class Frame{
		JFrame gameFrame;
		
		JPanel operatePanel;
		JPanel viewPanel;
		Process gameControl;

		boolean isRunning=false;
		int multipyCycle=1;
		int multipyCount=0;
		int row=10;
		int col=10;
		
		int[][] cells= new int[10][10];

		int sideLength=15;
		int adjustLength=30;
		
		JTextField multipyCountField;
		JButton changeButton;
		JButton reStartButton;
		JButton startButton;
		
		
		public static void main(String[] args)
		{
			new Frame();
		}
		
		public Frame(){
			gameFrame=new JFrame("Conway's game of life");
			gameFrame.setResizable(false);
			
		
			Dimension screnSize=Toolkit.getDefaultToolkit().getScreenSize();
			int width=1300;
			int height=800;
			gameFrame.setBounds((screnSize.width-width)/2, (screnSize.height-height)/2, width, height);
			gameFrame.setLayout(null);

			gameControl=new Process(row,col);
			
			cells=gameControl.getAllCellStatus();

			operatePanel=getOperatePanel();
			viewPanel=getViewPanel();
			
			gameFrame.add(viewPanel);
			gameFrame.add(operatePanel);

			gameFrame.setVisible(true);
			gameFrame.addMouseListener(new ClickMonitor());
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}


		private JPanel getOperatePanel()
		{
			JPanel operatePanel=new JPanel();

			operatePanel.setBounds(800, 0, 360, 800);

			operatePanel.setLayout(new GridLayout(10,10));
		
			JPanel mapWidthPanel=new JPanel();
			JLabel mapWidthLabel=new JLabel("Width");
			JTextField mapWidthFiled=new JTextField(5);
			mapWidthFiled.setText(String.valueOf(col));
			mapWidthPanel.add(mapWidthLabel);
			mapWidthPanel.add(mapWidthFiled);

			JPanel mapLengthPanel=new JPanel();
			JLabel mapLengthLabel=new JLabel("Length");
			JTextField mapLengthField=new JTextField(5);
			mapLengthField.setText(String.valueOf(row));
			mapLengthPanel.add(mapLengthLabel);
			mapLengthPanel.add(mapLengthField);
			
			JPanel multipyCountPanel=new JPanel();
			JLabel multipyCountLabel=new JLabel("generation");
			multipyCountField=new JTextField(5);
			multipyCountField.setText(String.valueOf(multipyCount));
		
			multipyCountField.setEditable(false);
			multipyCountPanel.add(multipyCountLabel);
			multipyCountPanel.add(multipyCountField);
			
			JPanel multipyCycPanel=new JPanel();
			JLabel multipyCycLabel=new JLabel("Time");
			JTextField multipyCycField=new JTextField(5);
			multipyCycField.setText(String.valueOf(multipyCycle));
			multipyCycPanel.add(multipyCycLabel);
			multipyCycPanel.add(multipyCycField);
			
			changeButton=new JButton("Restart");
			changeButton.addActionListener(new ActionListener()
					{
					@Override
						public void actionPerformed(ActionEvent e) {
						clearPaint(gameFrame.getGraphics());
						row=Integer.parseInt(mapWidthFiled.getText());
						col=Integer.parseInt(mapLengthField.getText());
						multipyCycle=Integer.parseInt(multipyCycField.getText());
						multipyCount=0;
						multipyCountField.setText(String.valueOf(multipyCount));
						gameControl=new Process(row,col);
						
		
						paintGirdLines(gameFrame.getGraphics());
						
						}
					});
			
			reStartButton=new JButton("Set Random");
			reStartButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
				
					gameControl.setDefaultWorld();
					cells=gameControl.getAllCellStatus();

					paintGirdLines(gameFrame.getGraphics());
					paintCells(gameFrame.getGraphics());
					
				}
			});
			
			startButton=new JButton("Start");
			startButton.addActionListener(new ActionListener() 
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					new MultipyThread().start();
				}
				
			});

			JButton stopButton=new JButton("Stop");
			stopButton.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							isRunning=false;	
							changeButton.setEnabled(true);
							reStartButton.setEnabled(true);
							startButton.setEnabled(true);
						}		
					});

			
			JLabel spaceLabel=new JLabel("");
			operatePanel.add(spaceLabel);
			operatePanel.add(spaceLabel);
			operatePanel.add(mapWidthPanel);
			operatePanel.add(mapLengthPanel);
			operatePanel.add(multipyCountPanel);
			operatePanel.add(multipyCycPanel);
			operatePanel.add(changeButton);
			operatePanel.add(reStartButton);
			operatePanel.add(startButton);
			operatePanel.add(stopButton);
			
			return operatePanel;
		}

		private JPanel getViewPanel()
		{
			JPanel viewPanel=new JPanel();
			
			viewPanel.setBounds(0, 0, 800, 800);
			
			return viewPanel;
		}

		public void clearPaint(Graphics g)
		{
			g.setColor(new Color(238,238,238));
			for(int i=0;i<=row;i++)
			{
				for(int j=0;j<=col;j++)
				{
				
						g.fillRect(j*sideLength+adjustLength, i*sideLength+adjustLength,sideLength,sideLength);
					
				}
			}
		}

		public void paintGirdLines(Graphics g)
		{
			g.setColor(new Color(0,0,0));
			for(int i=0;i<=row;i++)
			{
				g.drawLine(adjustLength, i*sideLength+adjustLength, col*sideLength+adjustLength,i*sideLength+adjustLength);
			}
		
			for(int i=0;i<=col;i++)
			{
				g.drawLine(i*sideLength+adjustLength,adjustLength,i*sideLength+adjustLength,row*sideLength+adjustLength);
			}
		}
		
		public void paintCells(Graphics g)
		{
			g.setColor(new Color(0,0,0));
		
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
					if(1==cells[i][j])
					{
						g.fillRect(j*sideLength+adjustLength, i*sideLength+adjustLength, sideLength, sideLength);
					}
				}
			}
		}

		public void createCell(Graphics g,int row,int col)
		{
			g.setColor(new Color(0,0,0));
			
			g.fillRect(col*sideLength+adjustLength, row*sideLength+adjustLength, sideLength, sideLength);
		}

		public void killCell(Graphics g,int row,int col)
		{
			g.setColor(new Color(238,238,238));
			g.fillRect(col*sideLength+adjustLength, row*sideLength+adjustLength, sideLength, sideLength);
			clearPaint(g);
		}
	class MultipyThread extends Thread
	{
		@Override
		public void run()
		{
			isRunning=true;	
			changeButton.setEnabled(false);
			reStartButton.setEnabled(false);
			startButton.setEnabled(false);
			

			while(isRunning)
			{
				gameControl.cellMultiply();//ϸ������ı�һ��
				cells=gameControl.getAllCellStatus();//��ȡ�ı��ϸ����������
				
				clearPaint(gameFrame.getGraphics());//���ԭͼ
				paintGirdLines(gameFrame.getGraphics());//������ͼ
				paintCells(gameFrame.getGraphics());//����ϸ��
				multipyCount++;//��ֳһ�Σ����ڼ�һ
				multipyCountField.setText(String.valueOf(multipyCount));//���õ�ǰ����
				
				try {
					Thread.sleep(multipyCycle*1000);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}
		}
	}
	class ClickMonitor implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int point_x=e.getX();
			int point_y=e.getY();
			
			if((point_x<adjustLength)||(point_x>col*sideLength+adjustLength)||(point_y<adjustLength)||(point_y>row*sideLength+adjustLength))
				return;
			int col=(point_x-adjustLength)/sideLength;
			int row=(point_y-adjustLength)/sideLength;
			
			boolean beforeStatus=gameControl.changeCellStatus(row, col);
			if(beforeStatus)
			{
				killCell(gameFrame.getGraphics(),row,col);
			}
			else
				createCell(gameFrame.getGraphics(),row,col);	
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
