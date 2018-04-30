package team.ecut.shenyou.client.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestProtocol {
    //public static final String regEx = "^<[A-Z0-9]{4,6},[\\S]{4,},[\\S]{3,}>$";
    /**
     * We used class DelimiterBasedFrameDecoder with ">" for packet disassembly,
     * Therefore no need to test the end with ">"
     */

    //public static final String regEx = "^<([GAME0-9]{4}||[SERV]{6}),[A-Z0-7#]{4,},[A-Z0-9#:]{3,}$";//not end with ">"
    public static final String regEx = "^<[GAME0-9]{4},[A-Z0-7#]{4,},[A-Z0-9#:]{3,}$";//not end with ">"
    //public static final String regEx = "^<[A-Z0-9]{4,6},[\\S]{4,},[\\S]{3,}[^>]+$";//not end with ">"
    public static final Pattern pattern = Pattern.compile(regEx);

    public static boolean testProtocol(String protocol){
        Matcher matcher = pattern.matcher(protocol);
        boolean rs = matcher.matches();
        //System.out.println("TestProtocol.class: " + protocol + " ----> " + rs);
        return rs;
    }

    public static int endType(String params){
        //example: <GAME,SERVER,END#1xxx    #A     #B>
        final String regEx1 = "^END#1[0-9]{3}#[0-9]+#[0-9]+$";

        //example: <GAME,SERVER,END#21xx      #C     #D     #E    #F>
        final String regEx2 = "^END#21[0-9]{2}#[0-9]+#[0-9]+#[0|1]#[0-9]+:[0-9]+$";

        //example: <GAME,SERVER,END#G    #H>
        final String regEx3 = "^END#[0|1]#[0-9]+:[0-9]+$";

        final Pattern pattern1 = Pattern.compile(regEx1);
        final Pattern pattern2 = Pattern.compile(regEx2);
        final Pattern pattern3 = Pattern.compile(regEx3);

        Matcher matcher1 = pattern1.matcher(params);
        Matcher matcher2 = pattern2.matcher(params);
        Matcher matcher3 = pattern3.matcher(params);

        if(matcher1.matches()){
            return 1;
        }
        if(matcher2.matches()){
            return 2;
        }
        if(matcher3.matches()){
            return 3;
        }
        //System.err.println("Unexpected end info: " + params);
        return 0;

    }
}
