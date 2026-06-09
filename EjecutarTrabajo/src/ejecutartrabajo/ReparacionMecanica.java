package ejecutartrabajo;

public class ReparacionMecanica extends Trabajo {

    private double precioMaterial;

    public ReparacionMecanica(int id, String descripcion) {
        super(id, descripcion, 0, "pendiente", 0, 14);
        this.precioMaterial = 0;
    }

    public double getPrecioMaterial() {
        return precioMaterial;
    }

    public void setPrecioMaterial(double precioMaterial) {
        this.precioMaterial = precioMaterial;
    }

    public String aumentarPrecioMat(double precio) {
        if (estado.equals("finalizado")) {
            return "El trabajo ya esta finalizado, no se puede modificar el precio del material.";
        }
        this.precioMaterial += precio;
        return "Precio material actualizado: $" + String.format("%.2f", this.precioMaterial);
    }

    public double calcularCostoTrabajo() {
        return (numeroHoras * 30) + (precioMaterial * 1.1);
    }

    public String toString() {
        return "[Reparacion Mecanica] ID: " + id + " | Descripcion: " + descripcion + " | Horas: " + numeroHoras
                + " | Estado: " + estado + " | Costo: $" + String.format("%.2f", calcularCostoTrabajo())
                + " | Plazo: " + plazo + " dias" + " | Material: $" + String.format("%.2f", precioMaterial);
    }
}
