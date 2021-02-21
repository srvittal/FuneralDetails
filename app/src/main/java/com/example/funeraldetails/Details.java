package com.example.funeraldetails;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


public class Details {

    public static String detailsThithi(Calendar cal){
        Calendar swisscal = (Calendar) cal.clone();
        swisscal.add(Calendar.HOUR_OF_DAY,-2);

        int Day = swisscal.get(Calendar.DAY_OF_MONTH);
        int Month = swisscal.get(Calendar.MONTH) + 1;
        int Year = swisscal.get(Calendar.YEAR);
        int Hour = swisscal.get(Calendar.HOUR_OF_DAY);
        int Min = swisscal.get(Calendar.MINUTE);
        double Sec = 01;

        SweDate JD = new SweDate();
        double[] julDay = JD.getJDfromUTC(Year,Month,Day,Hour,Min,Sec,true,true);

        double julDate = julDay[1];

        int isun = SweConst.SE_SUN;
        int imoon = SweConst.SE_MOON;

        double sunLong = getLong(isun, julDate);
        double moonLong = getLong(imoon, julDate);

        return calculateThithi(getDiff(sunLong,moonLong));
    }

    private static double getLong(int iplanet, double julDate){
        SwissEph sw = new SwissEph();
        sw.swe_set_ephe_path(null);
        int iflag = SweConst.SEFLG_SWIEPH;
        double[] xx = new double[6];
        StringBuffer serr = new StringBuffer("string buffer error");
        sw.swe_calc_ut(julDate,
                iplanet,
                iflag,
                xx,
                serr);
        if (xx[0] < 0) {
            xx[0] = xx[0] + 360;
        }
        return xx[0]-24;
    }

    public static double getDiff(double sunLon, double moonLon){
        double diff = moonLon - sunLon;
        if (diff < 0)
            diff = diff + 360;
        return diff;
    }

    public static String calculateThithi(double diff){
        String thithi = "";
        int ti = (int) (diff / 12);
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

   public static String SdDay(Calendar calc) {
        Calendar cal = (Calendar) calc.clone();
        cal.add(Calendar.DAY_OF_MONTH, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String date = sdf.format(cal.getTime());
        return date;
    }


    public static String getTMonth(Calendar cal, int amount){
        Calendar swisscal = (Calendar) cal.clone();
        String dTthe = detailsThithi(swisscal);
        swisscal.add(Calendar.MONTH,amount);
        String str = "";
        String tThe = "";
        int i;

        if (amount == 1 || amount == 3){
            i = -5;
        } else if (amount == 7 || amount == 9){
            i = -10;
        } else{
            i = -15;
        }

        for (swisscal.add(Calendar.DAY_OF_MONTH, i); !tThe.contentEquals(dTthe); swisscal.add(Calendar.DAY_OF_MONTH, 1)) {
                tThe = detailsThithi(swisscal);
        }


        if (tThe.contentEquals(dTthe)){
            swisscal.add(Calendar.DAY_OF_MONTH,-1);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            str = sdf.format(swisscal.getTime());
        }
        return str;
    }

}

