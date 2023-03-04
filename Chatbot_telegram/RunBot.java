package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.text.SimpleDateFormat;

public class RunBot extends TelegramLongPollingBot {

    Connection connection = null;

    public RunBot() {
        //        connect Database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // reg jdbc
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/CunSoft";
        String username = "root";
        String password = "";
        {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (connection != null) {
                    System.out.println("OK");
                } else {
                    System.out.println("Fail");
                }
            }
        }
    }
    private String userdata = null;

    public String getUserdata() {
        return userdata;
    }

    public void setUserdata(String userdata) {
        this.userdata = userdata;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String cmd = update.getMessage().getText();
        if (cmd.equals("/start") == false) {
            if (cmd.equals("/check")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Enter your ID"
                );
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                //                 Get time
                Message message = update.getMessage();
                long chatId = message.getChatId();

                // Get the time the message was sent
                long messageTime = message.getDate();

                // Convert the Unix timestamp to a Date object
                Date date = new Date(messageTime * 1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = sdf.format(date);
                // Print the date and time to the console
                System.out.println("Message received at: " + formattedDateTime);
//                Get user data
                userdata = update.getMessage().getText();
//                Split data from user

//                Insert to database
                String query = "INSERT INTO attendance (id, time) values (?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userdata);
                    preparedStatement.setString(2, formattedDateTime);
                    preparedStatement.executeUpdate();

                    // Print a message to the console
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("Check Attendance Successfully");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("ID does not exist!");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException ex) {
                        throw new RuntimeException(ex);
                    }
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "duyvku_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5951017605:AAEj2Vw706s1wtJk5ehZBiCUB7mo-_lCJig";
    }
}
