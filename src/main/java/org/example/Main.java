package org.example;

import org.example.entity.User;
import org.example.sender.NotificationSender;
import org.example.service.EmailNotificationService;
import org.example.service.SMSNotificationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String emailUsername = "fathi.sahla97@gmail.com";
        String emailPassword = "vwxiknbtxevugelv";

        String twilioAccountSid = "ACd3f5805f297e51800a27f9d67d73987a";
        String twilioAuthToken = "e23778ae0543141a50d0be2d325092f3";
        String twilioPhoneNumber = "+16788905449";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter user email: ");
        String userEmail = scanner.nextLine();

        System.out.print("Enter user phone number: ");
        String userPhoneNumber = scanner.nextLine();

        User user = new User(userName, userEmail, userPhoneNumber);

        System.out.print("Select notification method (1 for Email, 2 for SMS, 3 for Both): ");
        int choice = scanner.nextInt();

        String emailMessage = "Hi " + user.getUsername() + ", this is an email notification...!";
        String smsMessage = "Hi " + user.getUsername() + ", this is an SMS notification...!";

        NotificationSender notificationSender = new NotificationSender();

        if (choice == 1 || choice == 3) {
            EmailNotificationService emailNotificationService = new EmailNotificationService(emailUsername, emailPassword);
            notificationSender.addNotificationService(emailNotificationService);
            notificationSender.sendNotification(user, emailMessage);
        }

        if (choice == 2 || choice == 3) {
            SMSNotificationService smsNotificationService = new SMSNotificationService(twilioAccountSid, twilioAuthToken, twilioPhoneNumber);
            notificationSender.addNotificationService(smsNotificationService);
            notificationSender.sendNotification(user, smsMessage);
        }
    }
}
