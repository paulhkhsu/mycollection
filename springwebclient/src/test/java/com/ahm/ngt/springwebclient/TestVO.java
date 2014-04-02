package com.ahm.ngt.springwebclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ahm.ngt.support.enrollment.util.JaxbUtil;
import com.ahm.ngt.support.enrollment.vo.CountryDetailsVO;
import com.ahm.ngt.support.enrollment.vo.LanguageDetailsVO;

public class TestVO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CountryDetailsVO h = new CountryDetailsVO();

		h.setCountryCode("HK");
		h.setCountryName("HONG KONG");
		List<LanguageDetailsVO> languageDetailsVOs = new ArrayList<LanguageDetailsVO>();
		LanguageDetailsVO v = new LanguageDetailsVO();
		v.setLanguageCode("en");
		v.setLanguageName("English");
		languageDetailsVOs.add(v);

		v = new LanguageDetailsVO();
		v.setIsDefault("YES");
		v.setLanguageCode("ja");
		v.setLanguageName("Japanese");
		languageDetailsVOs.add(v);

		h.setLanguageDetails(languageDetailsVOs);
		System.out.println(JaxbUtil.marshallObject(CountryDetailsVO.class, h));

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(h));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
