package Service;

public class EmergencyRequest {

    private int requestId;
    private String location;
    private String emergencyType;
    private int priority;

    public EmergencyRequest(int requestId,
                            String location,
                            String emergencyType,
                            int priority) {

        this.requestId = requestId;
        this.location = location;
        this.emergencyType = emergencyType;
        this.priority = priority;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getLocation() {
        return location;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {

        return "Request ID : " + requestId +
               "\nLocation : " + location +
               "\nType : " + emergencyType +
               "\nPriority : " + priority +
               "\n";
    }
}