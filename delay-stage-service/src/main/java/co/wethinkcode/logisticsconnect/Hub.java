package co.wethinkcode.logisticsconnect;

import java.util.Objects;

public class Hub {
    private String id;
    private String province;
    private String sortingCenter;

    public Hub(){

    }
    public Hub(String id, String province, String sortingCenter){

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
                && Objects.equals(sortingCenter, hub.sortingCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, province, sortingCenter);
    }
}
