package ejecutartrabajo;

public class Revision extends Trabajo {

    public Revision(int id, String descripcion) {
        super(id, descripcion, 0, "pendiente", 0, 7);
    }

    public double calcularCostoTrabajo() {
        return (numeroHoras * 30) + 20;
    }

    public String toString() {
        return "[Revision] ID: " + id + " | Descripcion: " + descripcion + " | Horas: " + numeroHoras
                + " | Estado: " + estado + " | Costo: $" + String.format("%.2f", calcularCostoTrabajo())
                + " | Plazo: " + plazo + " dias";
    }
}
