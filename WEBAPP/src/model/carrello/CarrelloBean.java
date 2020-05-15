package model.carrello;

import java.util.Objects;

public class CarrelloBean {
    private String id;
    private double totale;


    public CarrelloBean(String id, double totale) {
        this.id = id;
        this.totale = totale;
    }

    public CarrelloBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarrelloBean that = (CarrelloBean) o;
        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "id='" + id + '\'' +
                ", totale=" + totale +
                '}';
    }
}
