package co.wethinkcode.logisticsconnect;

public class Hub {
    private final String id;
    private final String province;
    private final String sortingCenter;
    private Object active;

    public Hub(String id, String province, String sortingCenter, Object active){
        this.active = active;
        this.id = id;
        this.province = province;
        this.sortingCenter = sortingCenter;

    }

    public String getId() {
        return id;
    }
    public String getProvince() {
        return province;
    }
    public String getSortingCenter() {
        return sortingCenter;
    }
    public Object isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
