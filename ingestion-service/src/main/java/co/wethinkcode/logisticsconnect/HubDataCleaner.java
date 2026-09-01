package co.wethinkcode.logisticsconnect;

import java.util.List;

public class HubDataCleaner {
    public void clean(List<Hub> hubs){
        for (Hub hub : hubs){
            if (isMissing(hub.getId())) {
                hub.setId(null);
            }else hub.setId(hub.getId().trim());

            if (isMissing(hub.getProvince())){
                hub.setProvince(null);
            }else hub.setProvince(hub.getProvince().trim());

            if (isMissing(hub.getSortingCenter())){
                hub.setSortingCenter(null);
            }else hub.setSortingCenter(hub.getSortingCenter().trim());

            if (isMissing(hub.isActive().toString())){
                hub.setActive(null);
            }else hub.setActive(trueOrFalse(hub.isActive().toString()));
        }
    }
    private boolean trueOrFalse(String status){
        return status.equals("y") || status.equals("yes") ||
                status.equals("1") || status.equals("true") || status.equals("t");
    }
    private boolean isMissing(String value){
        return value == null || value.trim().isEmpty()
                || value.trim().equals("na") ||
                value.trim().equals("n/a");
    }
}
