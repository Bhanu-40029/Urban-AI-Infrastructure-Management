package Service;

public class Asset {
	 private int assetId;
	    private String assetName;
	    private String assetType;
	    private String location;
	    private String status;

	    public Asset(int assetId, String assetName, String assetType,
	                 String location, String status) {

	        this.assetId = assetId;
	        this.assetName = assetName;
	        this.assetType = assetType;
	        this.location = location;
	        this.status = status;
	    }

	    public int getAssetId() {
	        return assetId;
	    }

	    public String getAssetName() {
	        return assetName;
	    }

	    public String getAssetType() {
	        return assetType;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "Asset ID: " + assetId +
	               ", Name: " + assetName +
	               ", Type: " + assetType +
	               ", Location: " + location +
	               ", Status: " + status;
	    }

}
