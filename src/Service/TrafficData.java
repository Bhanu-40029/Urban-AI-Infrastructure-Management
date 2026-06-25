package Service;

public class TrafficData {

    private String areaName;
    private String roadName;
    private int trafficVolume;
    private double averageSpeed;
    private double congestionLevel;
    private int incidentReports;
    private int pedestrianCount;
    private String weather;

    public TrafficData(String areaName,
                       String roadName,
                       int trafficVolume,
                       double averageSpeed,
                       double congestionLevel,
                       int incidentReports,
                       int pedestrianCount,
                       String weather) {

        this.areaName = areaName;
        this.roadName = roadName;
        this.trafficVolume = trafficVolume;
        this.averageSpeed = averageSpeed;
        this.congestionLevel = congestionLevel;
        this.incidentReports = incidentReports;
        this.pedestrianCount = pedestrianCount;
        this.weather = weather;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getRoadName() {
        return roadName;
    }

    public int getTrafficVolume() {
        return trafficVolume;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getCongestionLevel() {
        return congestionLevel;
    }

    public int getIncidentReports() {
        return incidentReports;
    }

    public int getPedestrianCount() {
        return pedestrianCount;
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public String toString() {

        return areaName + " | " +
               roadName + " | " +
               trafficVolume + " | " +
               averageSpeed + " | " +
               congestionLevel + " | " +
               incidentReports + " | " +
               pedestrianCount + " | " +
               weather;
    }
}