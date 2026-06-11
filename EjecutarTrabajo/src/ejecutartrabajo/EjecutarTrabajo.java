package ejecutartrabajo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EjecutarTrabajo {

    static ArrayList<Trabajo> trabajos = new ArrayList<>();
    static int contadorId = 0;
    static Scanner sc = new Scanner(System.in);
    static final String ARCHIVO = "trabajos.dat";

    public static void main(String[] args) {
        cargarDatos();

        int opcion;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Registrar trabajo");
            System.out.println("2. Aumentar horas");
            System.out.println("3. Aumentar coste de piezas");
            System.out.println("4. Finalizar trabajo");
            System.out.println("5. Mostrar trabajo");
            System.out.println("6. Consultar plazo");
            System.out.println("7. Mostrar todos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: menuRegistrarTrabajo(); break;
                case 2: menuAumentarHoras(); break;
                case 3: menuAumentarCostePiezas(); break;
                case 4: menuFinalizarTrabajo(); break;
                case 5: menuMostrarTrabajo(); break;
                case 6: menuConsultarPlazo(); break;
                case 7: menuMostrarTodos(); break;
                case 0:
                    guardarDatos();
                    System.out.println("Datos guardados. Saliendo...");
                    break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void guardarDatos() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
            oos.writeObject(trabajos);
            oos.writeInt(contadorId);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("No se encontro archivo previo. Iniciando desde cero.");
            return;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO));
            trabajos = (ArrayList<Trabajo>) ois.readObject();
            contadorId = ois.readInt();
            ois.close();
            System.out.println("Datos cargados correctamente. Trabajos encontrados: " + trabajos.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    static void menuRegistrarTrabajo() {
        System.out.println("\n--- Registrar Trabajo ---");
        System.out.println("1. Reparacion Mecanica");
        System.out.println("2. Reparacion Chapas y Pintura");
        System.out.println("3. Revision");
        System.out.print("Seleccione tipo: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo no valido.");
            return;
        }

        System.out.print("Ingrese descripcion: ");
        String desc = sc.nextLine();

        Trabajo t;
        switch (tipo) {
            case 1: t = new ReparacionMecanica(contadorId, desc); break;
            case 2: t = new ReparacionChapasPintura(contadorId, desc); break;
            default: t = new Revision(contadorId, desc); break;
        }

        trabajos.add(t);
        System.out.println("Trabajo registrado con ID: " + contadorId);
        contadorId++;
    }

    static void menuAumentarHoras() {
        System.out.println("\n--- Aumentar Horas ---");
        Trabajo t = buscarTrabajo();
        if (t == null) return;

        System.out.print("Horas a agregar: ");
        double horas = sc.nextDouble();
        sc.nextLine();
        System.out.println(t.aumentarHoras(horas));
    }

    static void menuAumentarCostePiezas() {
        System.out.println("\n--- Aumentar Coste de Piezas ---");
        Trabajo t = buscarTrabajo();
        if (t == null) return;

        System.out.print("Precio a agregar: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.println(t.aumentarPrecioMat(precio));
    }

    static void menuFinalizarTrabajo() {
        System.out.println("\n--- Finalizar Trabajo ---");
        Trabajo t = buscarTrabajo();
        if (t == null) return;

        if (t.getEstado().equals("finalizado")) {
            System.out.println("El trabajo ya estaba finalizado.");
        } else {
            t.setEstado("finalizado");
            System.out.println("Trabajo finalizado correctamente.");
        }
    }

    static void menuMostrarTrabajo() {
        System.out.println("\n--- Mostrar Trabajo ---");
        Trabajo t = buscarTrabajo();
        if (t == null) return;
        System.out.println(t.toString());
    }

    static void menuConsultarPlazo() {
        System.out.println("\n--- Consultar Plazo ---");
        Trabajo t = buscarTrabajo();
        if (t == null) return;
        System.out.println("Plazo maximo: " + t.getPlazo() + " dias.");
    }

    static void menuMostrarTodos() {
        System.out.println("\n--- Todos los Trabajos ---");
        if (trabajos.isEmpty()) {
            System.out.println("No hay trabajos registrados.");
            return;
        }
        for (Trabajo t : trabajos) {
            System.out.println(t.toString());
        }
    }

    static Trabajo buscarTrabajo() {
        if (trabajos.isEmpty()) {
            System.out.println("No hay trabajos registrados.");
            return null;
        }
        System.out.print("Ingrese el ID del trabajo: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Trabajo t : trabajos) {
            if (t.getId() == id) return t;
        }
        System.out.println("No se encontro un trabajo con ese ID.");
        return null;
    }
}