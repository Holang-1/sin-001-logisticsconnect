package co.wethinkcode.logisticsconnect;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hub hub = (Hub) o;
        return Objects.equals(id, hub.id) && Objects.equals(province, hub.province)
                && Objects.equals(sortingCenter, hub.sortingCenter) && Objects.equals(active, hub.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, province, sortingCenter, active);
    }
}
