package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns;

/**
 * Created by lalu on 2/8/2017.
 */

public class NavDrawerItem {
    private String _Tripno;
    private String _source;
    private String _destination ;
    private String _tIME;

    public NavDrawerItem(String _Tripno, String _source, String _destination, String _tIME) {
        this._Tripno = _Tripno;
        this._source = _source;
        this._destination = _destination;
        this._tIME = _tIME;
    }

    public String get_Tripno() {
        return _Tripno;
    }

    public void set_Tripno(String _Tripno) {
        this._Tripno = _Tripno;
    }

    public String get_source() {
        return _source;
    }

    public void set_source(String _source) {
        this._source = _source;
    }

    public String get_destination() {
        return _destination;
    }

    public void set_destination(String _destination) {
        this._destination = _destination;
    }

    public String get_tIME() {
        return _tIME;
    }

    public void set_tIME(String _tIME) {
        this._tIME = _tIME;
    }
}
