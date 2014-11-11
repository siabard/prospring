package net.eduwill.prospring.ch16.xml.handler;

import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bea.xml.stream.samples.Parse;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {

	private static String dateFormatPattern;
	
	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}
	
	@Override
	public Object convertUponGet(Object arg0) {
		DateTime dateTime = (DateTime) arg0;
		
		return format(dateTime);
	}

	private static String format(DateTime dateTime) {
		String dateTimeString = "";
		
		if (dateTime != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTimeString = dateTimeFormatter.print(dateTime);
		}
		
		return dateTimeString;
	}

	@Override
	public Object convertUponSet(Object arg0) {
		String dateTimeString = (String) arg0;
		
		return parse(dateTimeString);
	}

	private static DateTime parse(String dateTimeString) {
		DateTime dateTime = new DateTime();
		
		if (dateTimeString != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTime = dateTimeFormatter.parseDateTime(dateTimeString);
		}
		return dateTime;
	}

	@Override
	public Class<DateTime> getFieldType() {
		return DateTime.class;
	}

}
