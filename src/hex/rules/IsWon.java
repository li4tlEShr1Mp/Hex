package hex.rules;
import hex.client.gui.graphics.ColorMapping;
import hex.client.gui.graphics.Winning;
import hex.resources.Chessboard;
import hex.resources.Pos;
public class IsWon {
	//单边起始逐点递归查找
	public static boolean isWin() {
		if(Chessboard.mutex==0) {
			for(int n=0;n<11;n++) {
				boolean[][] searchVisited =new boolean[Chessboard.getSize()][Chessboard.getSize()];//辅助数组记录已访问点
				Pos index=new Pos(0,n);//Player1 方向查找
				if(endSearch(searchVisited,index))return true;//调用递归函数
				else continue;
			}
		}
		else {
			for(int n=0;n<11;n++) {
				boolean[][] searchVisited =new boolean[Chessboard.getSize()][Chessboard.getSize()];
				Pos index=new Pos(n,0);//Player2 方向查找
				if(endSearch(searchVisited,index))return true;
				else continue;
			}
		}
		return false;
	}
	//递归查找连通棋子
	private static boolean endSearch(boolean[][] searchVisited, Pos index) {
		if(!Chessboard.isSeatTaken[index.getRow()][index.getCol()])return false;//是否存在有效棋子（是否存在起始点）
		Pos[] positions =BesidePawns.besidePos(searchVisited,index);//查找周围有效棋子存入辅助数组
		searchVisited[index.getRow()][index.getCol()]=true;//标记已查找
		for(int n=0;n<6;n++){
			if(positions[n]!=null) {
				if(Chessboard.mutex==0&&positions[n].getRow()==Chessboard.getSize()-1) {//是否为终点
					System.out.println(Chessboard.mutex+" is won");
					Winning.setText(ColorMapping.mapToString(Chessboard.mutex)+" Won!");
					Winning.setColor(ColorMapping.mapColor(Chessboard.mutex));
					Chessboard.mutex=-1;
					return true;
				}
				else if(Chessboard.mutex==1&&positions[n].getCol()==Chessboard.getSize()-1) {//是否为终点
					System.out.println(Chessboard.mutex+" is won");
					Winning.setText(ColorMapping.mapToString(Chessboard.mutex)+" Won!");
					Winning.setColor(ColorMapping.mapColor(Chessboard.mutex));
					Chessboard.mutex=-1;
					return true;
				}
				else if(endSearch(searchVisited,positions[n]))return true;//递归
				else continue;
			}
		}
		return false;
	}
}
