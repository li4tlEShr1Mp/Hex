package hex.resources;

public class Chessboard {
	//����Ĭ�ϴ�С11
	private static int size=11;
	public static boolean isAI=false;
	//����ֻ������� 
	//0->Player1
	//1->Player2
	static public int mutex=-1;
	//��ά����ʵ������
	static public int[][] board =new int[size][size];
	//�Ƿ��������
	static public boolean[][] isSeatTaken =new boolean[size][size];
	//�������̴�С
	public void setSize(int size){
		Chessboard.size=size;
	}
	//��ȡ���̴�С
	static public int getSize() {
		return size;
	}
	public static void Reset() {
		isSeatTaken=new boolean[size][size];		
		board=new int[size][size];
	}
}
//����