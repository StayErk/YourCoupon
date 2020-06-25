package model.pacchetto;

import model.hotel.HotelBean;
import model.restaurant.RestaurantBean;
import model.tour.TourBean;

import java.util.ArrayList;

public class PacchettoRaw {
    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getPersone() {
        return persone;
    }

    public void setPersone(int persone) {
        this.persone = persone;
    }

    public HotelBean getHotel() {
        return hotel;
    }

    public void setHotel(HotelBean hotel) {
        this.hotel = hotel;
    }

    public ArrayList<RestaurantBean> getRistoranti() {
        return restaurants;
    }

    public void setRistoranti(ArrayList<RestaurantBean> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<TourBean> getTour() {
        return tours;
    }

    public void setTour(ArrayList<TourBean> tours) {
        this.tours = tours;
    }

    public PacchettoRaw() {
    }

    @Override
    public String toString() {
        return "PacchettoRaw{" +
                "durata=" + durata +
                ", persone=" + persone +
                ", hotel=" + hotel +
                ", restaurants=" + restaurants +
                ", tours=" + tours +
                ", costo=" + costo +
                '}';
    }

    private int durata;
    private int persone;
    private HotelBean hotel;
    private ArrayList<RestaurantBean> restaurants;
    private ArrayList<TourBean> tours;

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    private double costo;

    public PacchettoRaw(int durata, int persone, HotelBean hotel, ArrayList<RestaurantBean> restaurants, ArrayList<TourBean> tours, double costo) {
        this.durata = durata;
        this.persone = persone;
        this.hotel = hotel;
        this.restaurants = restaurants;
        this.tours = tours;
        this.costo = costo;
    }
}
