package hex.resources;

public class Chessboard {
	//棋盘默认大小11
	private static int size=11;
	public static boolean isAI=false;
	//玩家轮换互斥量 
	//0->Player1
	//1->Player2
	static public int mutex=-1;
	//二维数组实现棋盘
	static public int[][] board =new int[size][size];
	//是否存在棋子
	static public boolean[][] isSeatTaken =new boolean[size][size];
	//其他棋盘大小
	public void setSize(int size){
		Chessboard.size=size;
	}
	//获取棋盘大小
	static public int getSize() {
		return size;
	}
	public static void Reset() {
		isSeatTaken=new boolean[size][size];		
		board=new int[size][size];
	}
}
//棋盘