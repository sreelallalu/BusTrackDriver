package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Databae;

/**
 * Created by lalu on 2/5/2017.
 */
public class Trip2 {
    private String tripCode;
    private String agencyID;
    private String agencyName;
    private String agencyProprietor;
    private String busId;
    private String busName;
    private String busNumber;
    private String source;
    private String sourceTime;
    private String destination;
    private String destinationTime;
    private String _tripid;
    private String _sala;

    public String get_sala() {
        return _sala;
    }

    public void set_sala(String _sala) {
        this._sala = _sala;
    }

    public String get_tripid() {
        return _tripid;
    }

    public void set_tripid(String _tripid) {
        this._tripid = _tripid;
    }

    public Trip2(String tripid, String tripCode, String agencyID, String agencyName, String agencyProprietor, String busId, String busName, String busNumber, String source, String sourceTime, String destination, String destinationTime,String Salary) {
        this.tripCode = tripCode;
        this.agencyID = agencyID;
        this.agencyName = agencyName;
        this.agencyProprietor = agencyProprietor;
        this.busId = busId;
        this.busName = busName;
        this.busNumber = busNumber;
        this.source = source;
        this.sourceTime = sourceTime;
        this.destination = destination;
        this.destinationTime = destinationTime;
        this._tripid=tripid;
        this._sala=Salary;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyProprietor() {
        return agencyProprietor;
    }

    public void setAgencyProprietor(String agencyProprietor) {
        this.agencyProprietor = agencyProprietor;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }
}
