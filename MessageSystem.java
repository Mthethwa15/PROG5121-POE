import java.util.Random;
public class MessageSystem {

    // Attributes
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // Constructor
    public MessageSystem(int messageNumber, String recipient, String message) {
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
            System.out.println("Message sent");
        }
    }

    // Generate Message Hash
    private String generateMessageHash() {

        // First two digits of Message ID
        String firstTwoDigits = messageId.substring(0, 2);

        // Split message into words
        String[] words = message.trim().split("\\s+");

        // First and last words
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Build hash
        return (firstTwoDigits + ":" +
                messageNumber + ":" +
                firstWord + lastWord).toUpperCase();
    }

    // Display all details
    public void displayMessageDetails() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Message Number: " + messageNumber);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Message Hash: " + messageHash);
    }

    // Main Method
    public static void main(String[] args) {

        MessageSystem msg = new MessageSystem(
                0,
                "+27831234567",
                "Hi Thanks"
        );

        msg.displayMessageDetails();
    }
}
