package de.jawb.tools.jvm;

public class OsInfo {

    public final OsType type;
    public final String version;
    public final String info;
    public final String architecture;

    OsInfo() {
        type = OsUtil.getOS();
        version = OsUtil.getOsVersion();
        architecture = OsUtil.getOsArchitecture();
        info = OsUtil.getOsAdditionalInfo();
    }

    @Override
    public String toString() {
        return "OSInfo{" +
                "type=" + type +
                ", version='" + version + '\'' +
                ", architecture='" + architecture + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
