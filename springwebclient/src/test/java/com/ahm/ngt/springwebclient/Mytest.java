package com.ahm.ngt.springwebclient;

import com.ahm.ngt.support.enrollment.util.JaxbUtil;
import com.ahm.ngt.support.enrollment.xmlbo.HondaHeaderType;

public class Mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HondaHeaderType h = new HondaHeaderType();
		System.out.println(JaxbUtil.marshallObject(HondaHeaderType.class, h));
	}

}
