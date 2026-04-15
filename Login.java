





import java.util.Scanner;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation (NO regex)
    public boolean checkPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Cell phone validation (NO regex)
    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Register method
    public void register(Scanner sc) {

        System.out.println("=== REGISTER ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (checkUserName(username)) {
            System.out.println("Username successfully captured.");
            storedUsername = username;
        } else {
            System.out.println("Username is not correctly formatted.");
            return; // stop if invalid
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (checkPassword(password)) {
            System.out.println("Password successfully captured.");
            storedPassword = password;
        } else {
            System.out.println("Password is not correctly formatted.");
            return;
        }

        System.out.print("Enter cell phone (+27...): ");
        String phone = sc.nextLine();

        if (checkPhone(phone)) {
            System.out.println("Cell phone number successfully added.");
            storedPhone = phone;
        } else {
            System.out.println("Cell phone number incorrectly formatted.");
            return;
        }

        System.out.println("Registration successful!\n");
    }

    // Login method
    public void login(Scanner sc) {

        System.out.println("=== LOGIN ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            System.out.println("Welcome back! Login successful.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}


}

   

