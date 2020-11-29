package com.example.vt251club;

public class Town {

    private String _town;
    private String _county;
    private String _zip;
    private int _established;
    private int _acres;
    private double _latitude;
    private double _longitude;

    public Town(String town,
                String county,
                String zip,
                int established,
                int acres,
                double latitude,
                double longitude)
    {
        _town = town;
        _county = county;
        _zip = zip;
        _established = established;
        _acres =  acres;
        _latitude = latitude;
        _longitude = longitude;
    }

    public Town()
    {
        _town = "";
        _county = "";
        _zip = "";
        _established = 0;
        _acres =  0;
        _latitude = -7.0;
        _longitude = 4.0;
    }



    public String get_town() {
        return _town;
    }

    public String get_county() {
        return _county;
    }

    public String get_zip() {
        return _zip;
    }

    public int get_established() {
        return _established;
    }

    public int get_acres() {
        return _acres;
    }

    public double get_latitude() {
        return _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_town(String _town) {
        this._town = _town;
    }

    public void set_county(String _county) {
        this._county = _county;
    }

    public void set_zip(String _zip) {
        this._zip = _zip;
    }

    public void set_established(int _established) {
        this._established = _established;
    }

    public void set_acres(int _acres) {
        this._acres = _acres;
    }

    public void set_latitude(float _latitude) {
        this._latitude = _latitude;
    }

    public void set_longitude(float _longitude) {
        this._longitude = _longitude;
    }
}
