package de.jawb.tools.jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OsUtil {

    public static OsInfo getOsInfo(){
        return new OsInfo();
    }

    /**
     * Gibt das aktuelle Betriebsystem dieses Rechners.
     *
     * @return aktuelles Betriebsystem.
     */
    public static OsType getOS() {
        String osName = getProperty("os.name");

        String os = osName.toLowerCase();
        if (os.contains("win")) {
            return OsType.WINDOWS;
        } else if (os.contains("mac") || os.contains("darwin")) {
            return OsType.MAC;
        } else if (os.contains("nux") || os.contains("nix")) {
            return OsType.LINUX;
        }
        return OsType.UNKNOWN;
    }

    public static String getOsVersion(){
        return getProperty("os.version");
    }

    public static String getOsArchitecture(){
        return getProperty("os.arch");
    }

    private static String getProperty(String key){
        String value = System.getProperty(key);
        if(value == null){
            return "unknown";
        }
        return value;
    }

    public static String getOsAdditionalInfo(){
        OsType type = OsUtil.getOS();

        if(type == OsType.LINUX){
            try {
                return (new BufferedReader(new InputStreamReader((new ProcessBuilder("lsb_release", "-sd")).start().getInputStream()))).readLine();
            } catch (Exception e){
            }
        }

        return "";
    }
}
