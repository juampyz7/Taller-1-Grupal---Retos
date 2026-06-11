package ejecutartrabajo;

import java.io.Serializable;

public class Trabajo implements Serializable {

    protected int id;
    protected String descripcion;
    protected double numeroHoras;
    protected String estado;
    protected double costoTrabajo;
    protected int plazo;

    public Trabajo(int id, String descripcion, double numeroHoras, String estado, double costoTrabajo, int plazo) {
        this.id = id;
        this.descripcion = descripcion;
        this.numeroHoras = numeroHoras;
        this.estado = estado;
        this.costoTrabajo = costoTrabajo;
        this.plazo = plazo;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getNumeroHoras() {
        return numeroHoras;
    }

    public String getEstado() {
        return estado;
    }

    public double getCostoTrabajo() {
        return costoTrabajo;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumeroHoras(double numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public void setCostoTrabajo(double costoTrabajo) {
        this.costoTrabajo = costoTrabajo;
    }

    public String aumentarHoras(double horas) {
        if (estado.equals("finalizado")) {
            return "El trabajo ya esta finalizado, no se pueden agregar horas.";
        }
        this.numeroHoras += horas;
        return "Horas actualizadas: " + this.numeroHoras;
    }

    public String aumentarPrecioMat(double precio) {
        return "Este tipo de trabajo no tiene coste de material.";
    }

    public double calcularCostoTrabajo() {
        return numeroHoras * 30;
    }

    public String toString() {
        return "ID: " + id + " | Descripcion: " + descripcion + " | Horas: " + numeroHoras
                + " | Estado: " + estado + " | Costo: $" + String.format("%.2f", calcularCostoTrabajo())
                + " | Plazo: " + plazo + " dias";
    }
}
