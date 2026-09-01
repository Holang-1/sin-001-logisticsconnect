package co.wethinkcode.logisticsconnect;

public class Hub {
    private final String id;
    private final String province;
    private final String sortingCenter;
    private boolean active;

    public Hub(String id, String province, String sortingCenter, boolean active){
        if (id.isEmpty() || province.isEmpty() || sortingCenter.isEmpty()){
            throw new IllegalArgumentException("String can't be EMPTY!!!");
        }

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
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
