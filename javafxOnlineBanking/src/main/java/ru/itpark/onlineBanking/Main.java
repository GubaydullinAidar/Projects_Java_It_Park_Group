package ru.itpark.onlineBanking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.itpark.onlineBanking.app.OnlineBankingRestTemp;
import ru.itpark.onlineBanking.controllers.*;
import ru.itpark.onlineBanking.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    private OnlineBankingRestTemp onlineBankingRestTemp = new OnlineBankingRestTemp();

    private BorderPane rootWindow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String token = tokenValid();
        if (token != null) {
            User user = onlineBankingRestTemp.getUser(token);
            initRootWindow(user);
            showMainWindow(user);
        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/loginWindow.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("/images/login.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

            LoginWindowController controller = loader.getController();
            controller.setMain(this);
            controller.setOnlineBankingRestTemp(onlineBankingRestTemp);
        }
    }

    private String tokenValid() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\token.txt"));
            String token = in.readLine();
            in.close();
            if (onlineBankingRestTemp.tokenValid(token)) {
                return token;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void initRootWindow(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/rootWindow.fxml"));
            rootWindow = loader.load();
            Scene scene = new Scene(rootWindow);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Online Banking");
            primaryStage.getIcons().add(new Image("/images/banking.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

            RootWindowController controller = loader.getController();
            controller.setMain(this);
            controller.setUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainWindow(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/mainWindow.fxml"));
            AnchorPane mainWindow = loader.load();
            rootWindow.setCenter(mainWindow);

            MainWindowController controller = loader.getController();
            controller.setUser(user);
            controller.setMain(this);
            controller.setOnlineBankingRestTemp(onlineBankingRestTemp);
            controller.getSavingsBalance();
            controller.getPrimaryBalance();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSignupWindow(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/signUpWindow.fxml"));
            AnchorPane signupWindow = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Окно регистрации");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Stage primaryStage = new Stage();
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("/images/registration.png"));
            Scene scene = new Scene(signupWindow);
            dialogStage.setScene(scene);

            SignupWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);
            controller.setOnlineBankingRestTemp(onlineBankingRestTemp);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRefillDebitWindow(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/refillDebitWindow.fxml"));
            AnchorPane refillDebitWindow = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Окно пополнения/списания");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Stage primaryStage = new Stage();
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("/images/banking.png"));
            Scene scene = new Scene(refillDebitWindow);
            dialogStage.setScene(scene);

            RefillDebitWindowController controller = loader.getController();
            controller.setUser(user);
            controller.setOnlineBankingRestTemp(onlineBankingRestTemp);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProfileWindow(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/profileWindow.fxml"));
            AnchorPane profileWindow = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Личный кабинет");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Stage primaryStage = new Stage();
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("/images/profile.png"));
            Scene scene = new Scene(profileWindow);
            dialogStage.setScene(scene);

            ProfileWindowController controller = loader.getController();
            controller.setUser(user);
            controller.setOnlineBankingRestTemp(onlineBankingRestTemp);
            controller.init();

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToken(String token) {
        try {
            FileWriter writer = new FileWriter("C:\\token.txt", false);
            writer.write(token);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
