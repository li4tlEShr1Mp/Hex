package hex.resources;
public class Pos {
	private int row;
	private int col;
	//���캯��
	public Pos(int x,int y){
		row=x;
		col=y;
	}
	//�������к�
	public void setRow(int x) {
		row=x;
	}
	public void setCol(int y) {
		col=y;
	}
	//��ȡ���к�
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}											
//λ��