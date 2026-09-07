package co.wethinkcode.logisticsconnect;

import java.util.HashMap;
import java.util.Map;

public class DelayStageService {

    private final Map<String, DelayStage> delayStages = new HashMap<>();

    public DelayStageService() {
        // Temporarily add stages
        this.addStage("H-001", 1);
        this.addStage("H-002", 2);
        this.addStage("H-003", 3);
    }

    public DelayStage getDelayStage(String hubId) {
        return delayStages.get(hubId);
    }
    void addStage(String hubID, int stage){
        DelayStage delayStage = new DelayStage(hubID, stage);
        this.delayStages.put(hubID, delayStage);
    }
}
