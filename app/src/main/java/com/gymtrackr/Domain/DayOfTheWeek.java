package com.gymtrackr.Domain;

import com.gymtrackr.GymTrackr;
import com.gymtrackr.R;

public enum DayOfTheWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, NONE;

    @Override
    public String toString() {
        switch (this){
            case MONDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_monday);
            case TUESDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_tuesday);
            case WEDNESDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_wednesday);
            case THURSDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_thursday);
            case FRIDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_friday);
            case SATURDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_saturday);
            case SUNDAY:
                return GymTrackr.getContext().getResources().getString(R.string.days_sunday);
            default:
                return GymTrackr.getContext().getResources().getString(R.string.days_none);
        }
    }

    public static String toString(DayOfTheWeek dayOfTheWeek) {
        switch (dayOfTheWeek){
            case MONDAY:
                return "MONDAY";
            case TUESDAY:
                return "TUESDAY";
            case WEDNESDAY:
                return "WEDNESDAY";
            case THURSDAY:
                return "THURSDAY";
            case FRIDAY:
                return "FRIDAY";
            case SATURDAY:
                return "SATURDAY";
            case SUNDAY:
                return "SUNDAY";
            default:
                return "NONE";
        }
    }

    public static DayOfTheWeek toDayOfTheWeek(String string) {
        switch (string){
            case "MONDAY":
                return DayOfTheWeek.MONDAY;
            case "TUESDAY":
                return DayOfTheWeek.TUESDAY;
            case "WEDNESDAY":
                return DayOfTheWeek.WEDNESDAY;
            case "THURSDAY":
                return DayOfTheWeek.THURSDAY;
            case "FRIDAY":
                return DayOfTheWeek.FRIDAY;
            case "SATURDAY":
                return DayOfTheWeek.SATURDAY;
            case "SUNDAY":
                return DayOfTheWeek.SUNDAY;
            default:
                return DayOfTheWeek.NONE;
        }
    }
}