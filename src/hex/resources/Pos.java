package hex.resources;
public class Pos {
	private int row;
	private int col;
	//构造函数
	public Pos(int x,int y){
		row=x;
		col=y;
	}
	//设置行列号
	public void setRow(int x) {
		row=x;
	}
	public void setCol(int y) {
		col=y;
	}
	//获取行列号
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}											
//位置