package model;

public class Cell {
		private boolean cellStatus;
		private boolean isChangeStatus;
		
		public Cell(){
			cellStatus = false;
			isChangeStatus = false; 
		}
		
		public Cell(boolean cellStatus,boolean isChangeStatus){
			this.cellStatus = cellStatus;
			this.isChangeStatus=isChangeStatus;
		}
		
		//getter and setter
		public boolean isCellStatus() {
			return cellStatus;
		}

		public void setCellStatus(boolean cellStatus) {
			this.cellStatus = cellStatus;
		}

		public boolean isChangeStatus() {
			return isChangeStatus;
		}

		public void setChangeStatus(boolean isChangeStatus) {
			this.isChangeStatus = isChangeStatus;
		}
}
