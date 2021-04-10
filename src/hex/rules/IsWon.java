package hex.rules;
import hex.client.gui.graphics.ColorMapping;
import hex.client.gui.graphics.Winning;
import hex.resources.Chessboard;
import hex.resources.Pos;
public class IsWon {
	//������ʼ���ݹ����
	public static boolean isWin() {
		if(Chessboard.mutex==0) {
			for(int n=0;n<11;n++) {
				boolean[][] searchVisited =new boolean[Chessboard.getSize()][Chessboard.getSize()];//���������¼�ѷ��ʵ�
				Pos index=new Pos(0,n);//Player1 �������
				if(endSearch(searchVisited,index))return true;//���õݹ麯��
				else continue;
			}
		}
		else {
			for(int n=0;n<11;n++) {
				boolean[][] searchVisited =new boolean[Chessboard.getSize()][Chessboard.getSize()];
				Pos index=new Pos(n,0);//Player2 �������
				if(endSearch(searchVisited,index))return true;
				else continue;
			}
		}
		return false;
	}
	//�ݹ������ͨ����
	private static boolean endSearch(boolean[][] searchVisited, Pos index) {
		if(!Chessboard.isSeatTaken[index.getRow()][index.getCol()])return false;//�Ƿ������Ч���ӣ��Ƿ������ʼ�㣩
		Pos[] positions =BesidePawns.besidePos(searchVisited,index);//������Χ��Ч���Ӵ��븨������
		searchVisited[index.getRow()][index.getCol()]=true;//����Ѳ���
		for(int n=0;n<6;n++){
			if(positions[n]!=null) {
				if(Chessboard.mutex==0&&positions[n].getRow()==Chessboard.getSize()-1) {//�Ƿ�Ϊ�յ�
					System.out.println(Chessboard.mutex+" is won");
					Winning.setText(ColorMapping.mapToString(Chessboard.mutex)+" Won!");
					Winning.setColor(ColorMapping.mapColor(Chessboard.mutex));
					Chessboard.mutex=-1;
					return true;
				}
				else if(Chessboard.mutex==1&&positions[n].getCol()==Chessboard.getSize()-1) {//�Ƿ�Ϊ�յ�
					System.out.println(Chessboard.mutex+" is won");
					Winning.setText(ColorMapping.mapToString(Chessboard.mutex)+" Won!");
					Winning.setColor(ColorMapping.mapColor(Chessboard.mutex));
					Chessboard.mutex=-1;
					return true;
				}
				else if(endSearch(searchVisited,positions[n]))return true;//�ݹ�
				else continue;
			}
		}
		return false;
	}
}
