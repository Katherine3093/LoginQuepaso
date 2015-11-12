package com.example.katherine.noviembre;

/**
 * Created by katherine on 8/11/15.
 */
public class QuePaso {
    private boolean pinchazo;
    private boolean cadena;
    private boolean cambio;
    private boolean aire;
    private boolean frenos;

    public void setPinchazo(boolean pinchazo) {
        this.pinchazo = pinchazo;
    }

    public void setCadena(boolean cadena) {
        this.cadena = cadena;
    }

    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }

    public void setAire(boolean aire) {
        this.aire = aire;
    }

    public void setFrenos(boolean frenos) {
        this.frenos = frenos;
    }

    public boolean isPinchazo() {
        return pinchazo;
    }

    public boolean isCadena() {
        return cadena;
    }

    public boolean isCambio() {
        return cambio;
    }

    public boolean isAire() {
        return aire;
    }

    public boolean isFrenos() {
        return frenos;
    }
}
