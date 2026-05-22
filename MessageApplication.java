import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MessageApplication {

    private String messageId;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // Static counter for total messages sent
    private static int totalMessagesSent = 0;

    // Constructor
    public MessageApplication(int messageNumber, String recipient, String message) {
        this.messageId = generateMessageId();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;

        validateMessage();
        this.messageHash = generateMessageHash();
    }

    // Generate random 10-digit Message ID
    private String generateMessageId() {
        Random random = new Random();
        long number = 1000000000L +
                (long)(random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Validate message length
    private void validateMessage() {
        if (message.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
        } else {
            System.out.println("Message ready");
        }
    }

    // Generate Message Hash
    private String generateMessageHash() {

        String firstTwoDigits = messageId.substring(0, 2);

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (firstTwoDigits + ":" +
                messageNumber + ":" +
                firstWord + lastWord).toUpperCase();
    }

    // Send Message
    public void sendMessage() {
        System.out.println("Message successfully sent");
        totalMessagesSent++;

        displayMessageDetails();
    }

    // Disregard Message
    public void disregardMessage() {
        System.out.println("Press 0 to delete the message");
        System.out.println("Message deleted");
    }

    // Store Message in JSON file
    public void storeMessage() {

        String jsonData = "{\n" +
                "  \"MessageID\": \"" + messageId + "\",\n" +
                "  \"MessageHash\": \"" + messageHash + "\",\n" +
                "  \"Recipient\": \"" + recipient + "\",\n" +
                "  \"Message\": \"" + message + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("stored_messages.json", true)) {

            file.write(jsonData);
            file.write("\n");

            System.out.println("Message successfully stored");

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Display full message details
    public void displayMessageDetails() {

        System.out.println("\n----- Message Details -----");
        System.out.println("Message ID: " + messageId);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }

    // Display total messages sent
    public static void displayTotalMessages() {
        System.out.println("\nTotal messages sent: " + totalMessagesSent);
    }

    // Main Method
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // User input
        System.out.print("Enter recipient number: ");
        String recipient = input.nextLine();

        System.out.print("Enter message: ");
        String message = input.nextLine();

        // Create message object
        MessageApplication app =
                new MessageApplication(0, recipient, message);

        // Menu
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");

        int choice = input.nextInt();

        switch (choice) {

            case 1:
                app.sendMessage();
                break;

            case 2:
                app.disregardMessage();
                break;

            case 3:
                app.storeMessage();
                break;

            default:
                System.out.println("Invalid option");
        }

        // Display total messages
        MessageApplication.displayTotalMessages();

        input.close();
    }
}