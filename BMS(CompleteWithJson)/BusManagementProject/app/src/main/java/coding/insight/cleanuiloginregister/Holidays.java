package coding.insight.cleanuiloginregister;

public class Holidays {

    private final String From;
    private final String To;
    private final String Bus;

    public Holidays(String From, String To, String Bus) {
        this.From = From;
        this.To = To;
        this.Bus = Bus;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }
    public String getBus() {
        return Bus;
    }
}