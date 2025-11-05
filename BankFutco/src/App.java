import java.util.Scanner;

import model.Account;
import services.AccountService;

public class App {
    private static AccountService accountService = new AccountService(); // Repositorio nulo para este ejemplo

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenu(sc, "Loans");
                        break;
                    case "4":
                        runCrudMenu(sc, "Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[" + entityName + "] Crear - placeholder (pedir datos e invocar servicio)");
                    // Pedir datos por consola (ejemplo para Account)
                    System.out.print("Ingrese ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Ingrese email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Ingrese teléfono: ");
                    String telefono = sc.nextLine().trim();
                    System.out.print("Ingrese tipo de cuenta: ");
                    String tipo = sc.nextLine().trim();
                    System.out.print("Ingrese dirección: ");
                    String direccion = sc.nextLine().trim();

                    Account account = new Account(id, nombre, email, telefono, tipo, direccion);
                    accountService.save(account);
                    break;

                case "2":
                    System.out.print("[" + entityName + "] Leer por id - ingrese id: ");
                    String idSearch = sc.nextLine().trim();
                    System.out.println("Buscar " + entityName + " con id=" + idSearch + " - placeholder");
                    accountService.findById(idSearch).ifPresentOrElse(
                        acc -> System.out.println("Encontrado: " + acc),
                        () -> System.out.println(entityName + " con id=" + idSearch + " no encontrado.")
                    );
                    break;

                case "3":
                    System.out.println("[" + entityName + "] Listar todos - placeholder");
                    accountService.findAll().stream().forEach(System.out::println);
                    break;

                case "4":
                    System.out.print("[" + entityName + "] Actualizar - ingrese id: ");
                    String idUp = sc.nextLine().trim();
                    System.out.println("Actualizar " + entityName + " id=" + idUp + " - placeholder");

                    System.out.print("Ingrese nuevo nombre: ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Ingrese nuevo email: ");
                    String newEmail = sc.nextLine().trim();
                    System.out.print("Ingrese nuevo teléfono: ");
                    String newPhone = sc.nextLine().trim();
                    System.out.print("Ingrese nuevo tipo de cuenta: ");
                    String newType = sc.nextLine().trim();
                    System.out.print("Ingrese nueva dirección: ");
                    String newAddress = sc.nextLine().trim();

                    Account updateAccount = new Account(idUp, newName, newEmail, newPhone, newType, newAddress);
                    accountService.save(updateAccount);
                    break;

                case "5":
                    System.out.print("[" + entityName + "] Eliminar - ingrese id: ");
                    String idDel = sc.nextLine().trim();
                    System.out.println("Eliminar " + entityName + " id=" + idDel + " - placeholder");
                    accountService.deleteById(idDel);
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
}
}