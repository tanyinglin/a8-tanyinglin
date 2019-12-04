package controller;

import model.CellLife;

public class Process 
{
	protected CellLife world;
	public Process(){
		world=new CellLife(10,10);
	}
	public Process(int row,int col)
	{
		world=new CellLife(row,col);
	}
	

	public void cellMultiply(){
	
		for(int i=0;i<world.getWorldRow();i++){
			for(int j=0;j<world.getWorldCol();j++){
		
				int cellNum=world.getPericyteCellNum(i, j);
				if(world.getCellStatus(i, j)!=cellSavePrinciple(world.getCellStatus(i, j),cellNum))
				{
					world.setChangeFlag(i, j);
				}				
			}
		}

		for(int i=0;i<world.getWorldRow();i++){
			for(int j=0;j<world.getWorldCol();j++)
			{
				if(world.getChangeStatus(i, j))
				{
					world.changeCellStatus(i, j);
					world.canelChangeFlag(i, j);
				}
			}
		}
	}
	
	private boolean cellSavePrinciple(boolean curStatus,int surCellNumber){
		if(surCellNumber==3)
		{
			return true;
		}
		else if(surCellNumber==2)
		{
			return curStatus;
		}
		else
		{
			return false;
		}
	}

	public boolean changeCellStatus(int row,int col)
	{
		return world.changeCellStatus(row, col);
	}
	
	public void setDefaultWorld(){
		world.randomCellsStatus();
	}

	public int[][] getAllCellStatus(){
		int width=world.getWorldRow();
		int length=world.getWorldCol();
		
		int[][] cellSaveRect=new int[width][length];
		
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(world.getCellStatus(i, j))
				{
					cellSaveRect[i][j]=1;
				}
				else
				{
					cellSaveRect[i][j]=0;
				}
			}
		}
		return cellSaveRect;
	}

}

