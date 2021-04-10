package hex.client.gui.graphics;

import java.awt.Color;
//色彩映射 int->Color (RGB)
public class ColorMapping {
	public static Color mapColor(int mutex) {
		if(mutex==0) {
			return new Color(255, 68, 68);//红色
			//return new Color(240, 70, 70);//红色
		}
		else if(mutex==1) {
			return new Color(45, 183, 255);//蓝色
		}
		else return new Color(219, 219, 219);//底色
	}
	//悬浮色彩映射
	public static Color mapHoverColor(int mutex) {
		if(mutex==0) {
			return new Color(234, 167, 167);//红色
		}
		else if(mutex==1) {
			return new Color(151, 200, 224);//蓝色
		}
		else return new Color(219, 219, 219);//底色
	}
	public static String mapToString(int mutex) {
		if(mutex==0) {
			return "RED";//红色
			//return new Color(240, 70, 70);//红色
		}
		else if(mutex==1) {
			return "BLUE";//蓝色
		}
		return "ERROR";
	}
}
