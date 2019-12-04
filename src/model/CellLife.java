package model;

public class CellLife {
	private int worldRow;
	private int worldCol;
	private Cell[][] cells;
	
	public CellLife(int worldRow,int worldCol)
	{
		this.worldRow=worldRow;
		this.worldCol=worldCol;
		cells=new Cell[worldRow][worldCol];
		for(int i=0;i<worldRow;i++)
			for(int j=0;j<worldCol;j++)
			{
				cells[i][j]=new Cell();
			}//
		randomCellsStatus();
	}

	public int getWorldRow()
	{
		return worldRow;
	}
	public int getWorldCol()
	{
		return worldCol;
	}
	
	public void randomCellsStatus()
	{
		for(int i=0;i<worldRow;i++)
		{
			for(int j=0;j<worldCol;j++)
			{
				
				int randomNum=(int) (Math.random()*10);
				if(randomNum>7)
					cells[i][j].setCellStatus(true);
				else
					cells[i][j].setCellStatus(false);	
			
				cells[i][j].setChangeStatus(false);
			}
		}
	}

	public boolean getCellStatus(int row,int col)
	{
		if((row<0)||(row>worldRow-1)||(col<0)||(col>worldCol-1))
			return false;
		else
		return cells[row][col].isCellStatus();
	}

	public boolean changeCellStatus(int row,int col)
	{
		boolean beforeStatus=cells[row][col].isCellStatus();
		cells[row][col].setCellStatus(!beforeStatus);
		
		return beforeStatus;
	}

	public boolean getChangeStatus(int row,int col)
	{
		return cells[row][col].isChangeStatus();
	}

	public void setChangeFlag(int row,int col)
	{
		cells[row][col].setChangeStatus(true);
	}

	public void canelChangeFlag(int row,int col)
	{
		cells[row][col].setChangeStatus(false);
	}
	
	public int getPericyteCellNum(int row,int col)
	{
		int curNum=0;
		for(int i=row-1;i<=row+1;i++)
		{
			for(int j=col-1;j<=col+1;j++)
			{
				if((i==row)&&(j==col))
					continue;
				else if(getCellStatus(i,j))
				{
					curNum++;
				}
			}
		}
		return curNum;
	}
}
