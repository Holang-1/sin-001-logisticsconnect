package co.wethinkcode.logisticsconnect;

public class Hub {
    private String id;
    private String province;
    private String sortingCenter;
    private Object active;

    public Hub(String id, String province, String sortingCenter, Object active){
        this.active = active;
        this.id = id;
        this.province = province;
        this.sortingCenter = sortingCenter;

    }

    public void setId(String id) {
        this.id = id;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setSortingCenter(String sortingCenter) {
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
    public void setActive(Object active) {
        this.active = active;
    }
}
