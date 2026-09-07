package co.wethinkcode.logisticsconnect;

import java.util.HashMap;
import java.util.Map;

public class DelayStageService {

    private final Map<String, DelayStage> delayStages;

    public DelayStageService() {
        delayStages = new HashMap<>();
    }

    public DelayStage getDelayStage(String hubId) {
        return delayStages.get(hubId);
    }
    public void addStage(String hubID, int stage){
        DelayStage delayStage = new DelayStage(hubID, stage);
        this.delayStages.put(hubID, delayStage);
    }
}
