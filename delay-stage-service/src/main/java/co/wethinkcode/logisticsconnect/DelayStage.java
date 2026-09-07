package co.wethinkcode.logisticsconnect;

public class DelayStage {
    private final String hubID;
    private int stage;

    public DelayStage(String hubID, int stage){
        if (hubID.isBlank() || hubID.isEmpty()){
            throw new IllegalArgumentException("HUB ID CAN'T BE EMPTY");
        }
        if (stage < 0 || stage > 8 ){
            throw new IllegalArgumentException("STAGE SHOULD BE BETWEEN 0 AND 8");
        }

        this.hubID = hubID;
        this.stage = stage;
    }
    public String getHubID(){
        return this.hubID;
    }
    public int getStage(){
        return this.stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }

}
