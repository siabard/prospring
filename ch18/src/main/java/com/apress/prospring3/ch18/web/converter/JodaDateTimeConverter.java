package com.apress.prospring3.ch18.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@FacesConverter("jodaDateTimeConverter")
public class JodaDateTimeConverter implements Converter {

	private static final String PATTERN = "yyyy-MM-dd";
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return DateTimeFormat.forPattern(PATTERN).parseDateTime(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		DateTime dateTime = (DateTime) arg2;
		
		return DateTimeFormat.forPattern(PATTERN).print(dateTime);
	}

}
