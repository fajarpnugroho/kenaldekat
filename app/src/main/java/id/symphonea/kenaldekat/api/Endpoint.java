package id.symphonea.kenaldekat.api;

public enum Endpoint {
    PRODUCTION("http://api.pemiluapi.org/", "Production"),
    MOCKING("http://private-anon-dcea3a867-candidatepilkadaapi.apiary-mock.com/", "Mocking");

    private final String url;
    private final String name;

    Endpoint(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() { return url; }

}
