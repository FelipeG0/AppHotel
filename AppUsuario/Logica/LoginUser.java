package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginUser {
    private static Map<String, String> users = new HashMap<>();; // Almacena los usuarios y contraseñas
    private static File archivoUsers = new File("data/usuariosHuespedes.txt");

    public LoginUser() {
    }

    public static boolean register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);            

            // Actualizar el archivo de usuarios
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsers, true))) {
                writer.write(username + ";" + password);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Error al actualizar el archivo de usuarios.");
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
            return true;
        } else {
            System.out.println("Credenciales incorrectas. Por favor, verifique su usuario y contraseña.");
            return false;
        }
    }
}

