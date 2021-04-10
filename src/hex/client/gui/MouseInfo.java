package hex.client.gui;

import hex.client.gui.graphics.HexBoard;
import hex.resources.*;

public class MouseInfo {

	public static Pos getPos(int mouseX,int mouseY) {
		Pos positionOnBoard=null;
		for(int i=0;i<Chessboard.getSize();i++) {
			for(int j=0;j<Chessboard.getSize();j++) {
				int crossing=0;
				for(int u=0;u<6;u++) {
					float k=(HexBoard.pawns[i][j].getYi((u+1)%6)-HexBoard.pawns[i][j].getYi(u))/
							(HexBoard.pawns[i][j].getXi((u+1)%6)-HexBoard.pawns[i][j].getXi(u));
					boolean cond1=(mouseX>HexBoard.pawns[i][j].getXi(u)&&mouseX<HexBoard.pawns[i][j].getXi((u+1)%6));
					boolean cond2=(mouseX>HexBoard.pawns[i][j].getXi((u+1)%6)&&mouseX<HexBoard.pawns[i][j].getXi(u));
					boolean under=mouseY<k*(mouseX-HexBoard.pawns[i][j].getXi(u))+HexBoard.pawns[i][j].getYi(u);
					if((cond1||cond2)&&under)crossing++;
				}
				if(crossing%2==1) {
					positionOnBoard=new Pos(i,j);
					return positionOnBoard;
				}
			}
		}
		return null;
	}
}
//not finishied