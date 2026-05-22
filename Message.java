import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Message {

    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    static ArrayList<String> sentMessages = new ArrayList<>();
    static int totalMessages = 0;

    // Constructor
    public Message(String messageID, String recipientCell, String messageText) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Method 1: Check Message ID
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Method 2: Check Recipient Cell
    public String checkRecipientCell() {

        if (recipientCell.length() <= 10 && recipientCell.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted.";
        }
    }

    // Method 3: Create Message Hash
    public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2).toUpperCase()
                + ":" +
                totalMessages +
                ":" +
                firstWord.toUpperCase()
                + lastWord.toUpperCase();
    }

    // Method 4: Send Message
    public String SentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println("""
                Choose an option:
                1. Send Message
                2. Store Message
                3. Disregard Message
                """);

        int choice = input.nextInt();

        switch (choice) {

            case 1:
                sentMessages.add(messageText);
                totalMessages++;
                return "Message successfully sent.";

            case 2:
                storeMessage();
                return "Message successfully stored.";

            case 3:
                return "Message disregarded.";

            default:
                return "Invalid option.";
        }
    }

    // Method 5: Print Messages
    public String printMessages() {

        StringBuilder result = new StringBuilder();

        for (String msg : sentMessages) {
            result.append(msg).append("\n");
        }

        return result.toString();
    }

    // Method 6: Return Total Messages
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Method 7: Store Messages in JSON
    public void storeMessage() {

        try {

            FileWriter file = new FileWriter("storedMessages.json", true);

            file.write("{\n");
            file.write("\"MessageID\": \"" + messageID + "\",\n");
            file.write("\"Recipient\": \"" + recipientCell + "\",\n");
            file.write("\"Message\": \"" + messageText + "\",\n");
            file.write("\"MessageHash\": \"" + messageHash + "\"\n");
            file.write("}\n");

            file.close();

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Message ID: ");
        String id = input.nextLine();

        System.out.print("Enter Recipient Cell Number: ");
        String cell = input.nextLine();

        System.out.print("Enter Message: ");
        String text = input.nextLine();

        Message msg = new Message(id, cell, text);

        // Check Message ID
        if (msg.checkMessageID()) {
            System.out.println("Message ID captured successfully.");
        } else {
            System.out.println("Message ID exceeds 10 characters.");
        }

        // Check Cell Number
        System.out.println(msg.checkRecipientCell());

        // Display Hash
        System.out.println("Message Hash: " + msg.messageHash);

        // Send/Store/Disregard
        System.out.println(msg.SentMessage());

        // Print Messages
        System.out.println("\nMessages Sent:");
        System.out.println(msg.printMessages());

        // Total Messages
        System.out.println("Total Messages Sent: " + msg.returnTotalMessages());
    }
}
