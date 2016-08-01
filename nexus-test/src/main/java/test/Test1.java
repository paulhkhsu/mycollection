package test;

import java.text.DecimalFormat;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		float f1 = (Float.parseFloat("18"));
		float f2 = f1/60;
		float f3 = (Float.parseFloat("51.656"));
		float f4 = f3/3600;
		float f5 = (Float.parseFloat("118"));
		float result = f2 + f4 ;
		DecimalFormat form = new DecimalFormat(".000000");
		System.out.println(form.format(result));
		String ss = Float.toString(result);
System.out.println("118" + ss.substring(1));
		
		
		double d1 = (Double.parseDouble("18"));
		double d2 = d1/60;
		double d3 = (Double.parseDouble("51.656"));
		double d4 = d3/3600;
		double d5 = (Double.parseDouble("118"));
		double dd = d5 + d2 + d4;
		System.out.println(dd);
		
	}

}
