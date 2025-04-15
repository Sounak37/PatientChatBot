import java.util.*;

public class PatientChatBoxConsole {

    private static Map<String, String> knowledgeBase = new HashMap<>();

    // Initialize the knowledge base with some predefined questions and answers
    static {
        knowledgeBase.put("what is the cost of treatment", "The cost of treatment depends on the type of service required.");
        knowledgeBase.put("how long is the recovery time", "Recovery time varies based on the procedure and the patient's health condition.");
        knowledgeBase.put("what are the visiting hours", "Visiting hours are from 10 AM to 6 PM on weekdays and 12 PM to 5 PM on weekends.");
        knowledgeBase.put("can i get a consultation online", "Yes, online consultations are available via our telemedicine portal.");
        knowledgeBase.put("how can i book an appointment", "You can book an appointment through our website or by calling our helpline.");
    }

    // Method to calculate the similarity score between two phrases
    public static int calculateSimilarity(String userQuestion, String storedQuestion) {
        String[] userWords = userQuestion.split("\\s+");
        String[] storedWords = storedQuestion.split("\\s+");
        Set<String> wordSet = new HashSet<>(Arrays.asList(userWords));

        int matchCount = 0;
        for (String word : storedWords) {
            if (wordSet.contains(word)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    // Method to find the best matching answer
    public static String findBestAnswer(String userQuestion) {
        String bestMatch = null;
        int highestScore = 0;

        for (String question : knowledgeBase.keySet()) {
            int score = calculateSimilarity(userQuestion.toLowerCase(), question.toLowerCase());
            if (score > highestScore) {
                highestScore = score;
                bestMatch = question;
            }
        }

        if (bestMatch != null) {
            return knowledgeBase.get(bestMatch);
        } else {
            return "Sorry, I couldn't find an answer to your question. Please consult a healthcare professional.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient's Chat Box! Type your question below:");
        
        while (true) {
            System.out.print("You: ");
            String userQuestion = scanner.nextLine();

            if (userQuestion.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the Patient's Chat Box. Take care!");
                break;
            }

            String response = findBestAnswer(userQuestion);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }
}

