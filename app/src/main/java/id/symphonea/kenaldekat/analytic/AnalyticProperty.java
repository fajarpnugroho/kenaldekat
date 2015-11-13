package id.symphonea.kenaldekat.analytic;

public enum AnalyticProperty {
    PRODUCTION("production", "UA-70057926-1");

    private String key;
    private String value;

    AnalyticProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() { return value; }
}
