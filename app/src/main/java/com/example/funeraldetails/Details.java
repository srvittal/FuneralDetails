package com.example.funeraldetails;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


public class Details {
    static String thithi;
    static double diff, Sec,julDate;
    static double[] julDay = new double[2];
    static int Day,Month,Year,Hour,Min,isun, imoon, iflag,ti;

    public static String detailsThithi(Calendar swisscal){
        swisscal.add(Calendar.HOUR_OF_DAY,-2);

        Day = swisscal.get(Calendar.DAY_OF_MONTH);
        Month = swisscal.get(Calendar.MONTH);
        Year = swisscal.get(Calendar.YEAR);
        Hour = swisscal.get(Calendar.HOUR_OF_DAY);
        Min = swisscal.get(Calendar.MINUTE);
        Sec = 01;

        SweDate JD = new SweDate();
        julDay = JD.getJDfromUTC(Year,Month,Day,Hour,Min,Sec,true,true);

        julDate = julDay[1];

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
                        " Pradhamai",
                        " Dwithiya",
                        " Trithiya",
                        " Chathurthi",
                        " Panchami",
                        " Shashti",
                        " Sapthami",
                        " Ashtami",
                        " Navami",
                        " Dasami",
                        " Ekadasi",
                        " Dwadasi",
                        " Trayodasi",
                        " Chaturdasi",
                        " Pournami",
                        " Pradhamai",
                        " Dwithiya",
                        " Trithiya",
                        " Chathurthi",
                        " Panchami",
                        " Shashti",
                        " Sapthami",
                        " Ashtami",
                        " Navami",
                        " Dasami",
                        " Ekadasi",
                        " Dwadasi",
                        " Trayodasi",
                        " Chaturdasi",
                        " Amavasai" };
        if (ti < 15)
            thithi = "Shukla Paksha" + tithiNames[ti];
        else if (15 <= ti)
            thithi = "Krishna Paksha" + tithiNames[ti];
        return thithi;
    }

    public static String SdDay(Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String date = sdf.format(cal.getTime());
        return date;
    }
/**
 * work in progress to calculate 3rd Month
    public static String getTMonth(Calendar swisscal){
        swisscal.add(Calendar.MONTH,3);
        String tThe = detailsThithi(swisscal);
        MainActivity ma = new MainActivity();
        while (tThe == ma.Theethee){
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String date = sdf.format(swisscal.getTime());
            return date;
            swisscal.add(Calendar.DAY_OF_MONTH,1);
        }


    } */
}

