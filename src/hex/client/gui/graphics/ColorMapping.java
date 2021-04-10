package hex.client.gui.graphics;

import java.awt.Color;
//ɫ��ӳ�� int->Color (RGB)
public class ColorMapping {
	public static Color mapColor(int mutex) {
		if(mutex==0) {
			return new Color(255, 68, 68);//��ɫ
			//return new Color(240, 70, 70);//��ɫ
		}
		else if(mutex==1) {
			return new Color(45, 183, 255);//��ɫ
		}
		else return new Color(219, 219, 219);//��ɫ
	}
	//����ɫ��ӳ��
	public static Color mapHoverColor(int mutex) {
		if(mutex==0) {
			return new Color(234, 167, 167);//��ɫ
		}
		else if(mutex==1) {
			return new Color(151, 200, 224);//��ɫ
		}
		else return new Color(219, 219, 219);//��ɫ
	}
	public static String mapToString(int mutex) {
		if(mutex==0) {
			return "RED";//��ɫ
			//return new Color(240, 70, 70);//��ɫ
		}
		else if(mutex==1) {
			return "BLUE";//��ɫ
		}
		return "ERROR";
	}
}
