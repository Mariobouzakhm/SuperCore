package me.mariozgr8.supercore.api;

public class Methods {
	public static String convertTimeToString(long time) {
		//String returned at the end
		StringBuilder str = new StringBuilder();
		if(time > 0) {
			int days = 0;
			int hours = 0;
			int min = 0;
			int seconds = 0;
			
			if(time >= secondsInDay()) {
				days = (int) time/secondsInDay();
				time %= secondsInDay();
				
				str.append(days+"d ");
			}
			if(time >= secondsInHour()) {
				hours = (int) time/secondsInHour();
				time %= secondsInHour();
				
				str.append(hours+"h ");
			}
			if(time >= secondsInMinute()) {
				min = (int) time/secondsInMinute();
				time %= secondsInMinute();
				
				str.append(min+"m ");
			}
			if(time != 0) {
				seconds = (int) time;
				str.append(seconds+"s");
			}
		}
		else {
			str.append("0s");
		}
		
		return str.toString();
	}
	private static int secondsInDay() {
		return 60*60*24;
	}
	private static int secondsInHour() {
		return 60*60;
	}
	private static int secondsInMinute() {
		return 60;
	}

}
