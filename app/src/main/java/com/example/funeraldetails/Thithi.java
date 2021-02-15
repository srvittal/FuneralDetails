package com.example.funeraldetails;


import android.util.Log;

import java.util.Calendar;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


public class Thithi {
    static String thithi;
    static double diff, Sec,julDate;
    static double[] julDay = new double[2];
    static int isun, imoon, iflag,Year,Month,Day,Hour,Min,ti;

    public static String detailsThithi(int Year, int Month, int Day, int Hour, int Min ){

        Sec = 01;
        Log.v("detailsThithi","Year:" + Year);
        Log.v("detailsThithi","Month:" + Month);
        Log.v("detailsThithi","Day:" + Day);
        Log.v("detailsThithi","Hour:" + Hour);
        Log.v("detailsThithi","Min:" + Min);


        int UtHour = Hour - 2;
        SweDate JD = new SweDate();
        julDay = JD.getJDfromUTC(Year,Month,Day,UtHour,Min,Sec,true,true);
        Log.v("detailsThithi","julDay:" + julDay);


        julDate = julDay[1];
        Log.v("detailsThithi","julDate:" + julDate);

        isun = SweConst.SE_SUN;
        imoon = SweConst.SE_MOON;
        iflag = SweConst.SEFLG_SWIEPH;

        double sunLong = getLong(isun) - 24;
        double moonLong = getLong(imoon) - 24;
        if (sunLong < 0) {
            sunLong = sunLong + 360;
        }
        if (moonLong < 0) {
            moonLong = moonLong + 360;
        }

        Log.v("detailsThithi","sunLong:" + julDate);
        Log.v("detailsThithi","moonLong:" + julDate);

        return calculateThithi(getDiff(sunLong,moonLong));
    }

    private static double getLong(int iplanet){
        SwissEph sw = new SwissEph();
        sw.swe_set_ephe_path(null);
        double[] xx = new double[6];
        StringBuffer serr = new StringBuffer("string buffer error");
        sw.swe_calc_ut(julDate,
                iplanet,
                iflag,
                xx,
                serr);
        return xx[0];
    }

    public static double getDiff(double sunLon, double moonLon){
        diff = moonLon - sunLon;
        if (diff < 0)
            diff = diff + 360;
        return diff;
    }

    public static String calculateThithi(double diff){
        ti = (int) (diff / 12);
        if (ti < 0){
            ti = 0;
        }
        String[] tithiNames =
                {
                        " Pratipat",
                        " Dvitiya",
                        " Tritiya",
                        " Chaturthi",
                        " Panchami",
                        " Shashti",
                        " Saptami",
                        " Ashtami",
                        " Navami",
                        " Dashami",
                        " Ekadashi",
                        " Dvadashi",
                        " Trayodashi",
                        " Chaturdashi",
                        " Purnima",
                        " Pratipat",
                        " Dvitiya",
                        " Tritiya",
                        " Chaturthi",
                        " Panchami",
                        " Shashti",
                        " Saptami",
                        " Ashtami",
                        " Navami",
                        " Dashami",
                        " Ekadashi",
                        " Dvadashi",
                        " Trayodashi",
                        " Chaturdashi",
                        " Amavasya" };
        if (ti < 15)
            thithi = "Shukla Paksha" + tithiNames[ti];
        else if (15 <= ti)
            thithi = "Krishna Paksha" + tithiNames[ti];
        return thithi;
    }


}

