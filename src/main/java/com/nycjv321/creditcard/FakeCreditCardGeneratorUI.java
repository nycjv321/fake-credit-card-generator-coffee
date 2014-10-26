package com.nycjv321.creditcard;

import com.nycjv321.utilities.JavaFXUtilities;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

import static com.nycjv321.creditcard.CreditCard.*;
import static javafx.geometry.Pos.CENTER;

/**
 * Created by Javier on 10/25/2014.
 */
public class FakeCreditCardGeneratorUI extends Application implements JavaFXUtilities {
    private TextField generatedCreditCard;
    private TextField validateCreditCardButton;
    private Label creditCardLabel;

    private final ImageView visaImageView = createImageView(createImage("/" + VISA + ".jpg"), 50, true);
    private final ImageView failImageView = createImageView(createImage("/Fail.png"), 50, true);
    private final ImageView discoverImageView = createImageView(createImage("/" + DISCOVER + ".jpg"), 50, true);
    private final ImageView americanExpressImageView = createImageView(createImage("/" + AMERICAN_EXPRESS + ".png"), 50, true);
    private final ImageView masterCardImageView = createImageView(createImage("/" + MASTER_CARD + ".png"), 50, true);

    private static final int MAX_CREDIT_CARD_LENGTH = CreditCard.getMaximumLength();
    private static final int MIN_CREDIT_CARD_LENGTH = CreditCard.getMinimumLength();

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane tabPane = new TabPane();
        Tab generateTab = createTab("Generate");
        generateTab.setContent(createGenerateGrid());
        tabPane.getTabs().add(generateTab);
        Tab validateTab = createTab("Validate");
        validateTab.setContent(createValidateGrid());
        tabPane.getTabs().add(validateTab);

        tabPane.setPrefSize(320, 90);
        GridPane grid = createGrid();
        grid.add(tabPane, 0, 0);
        Scene scene = new Scene(grid, 325, 100);
        primaryStage.setTitle("Fake Credit Card Generator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Create the GridPane responsible for allowing the user to generate credit cards
     *
     * @return
     */
    private GridPane createGenerateGrid() {
        List<Object> types = FXCollections.observableArrayList();
        Collections.addAll(types, values());

        final ChoiceBox<Object> comboBox = createChoiceBox((ObservableList<Object>) types, DISCOVER);
        Button generateButton = createButton("Generate", event -> {
            generatedCreditCard.setText("");
            String creditCard = null;
            switch (CreditCard.parse(String.valueOf(comboBox.getSelectionModel().getSelectedItem()))) {
                case VISA:
                    creditCard = String.valueOf(FakeCreditCardGenerator.generateVisa());
                    break;
                case DISCOVER: {
                    creditCard = String.valueOf(FakeCreditCardGenerator.generateDiscover());
                    break;
                }
                case AMERICAN_EXPRESS:
                    creditCard = String.valueOf(FakeCreditCardGenerator.generateAmericanExpress());
                    break;
                case MASTER_CARD:
                    creditCard = String.valueOf(FakeCreditCardGenerator.generateMasterCard());
                    break;
            }
            generatedCreditCard.setText(creditCard);
        });
        generatedCreditCard = createTextField(Pos.CENTER);

        GridPane generateGrid = createGrid();
        generateGrid.add(comboBox, 0, 0);
        generateGrid.add(generateButton, 1, 0);
        generateGrid.add(generatedCreditCard, 0, 1, 2, 1);
        return generateGrid;
    }

    /**
     * Create the grid pane responsible for validating credit card strings
     *
     * @return
     */
    private GridPane createValidateGrid() {
        GridPane validateGrid = createGrid();
        validateCreditCardButton = createTextField(Pos.CENTER);

        setTextLimit(validateCreditCardButton, MAX_CREDIT_CARD_LENGTH);
        validateCreditCardButton.setOnKeyReleased(event ->
        {
            String creditCard = validateCreditCardButton.getText();
            // Only check for the credit card if it meets our maximum and minimum lengths for predefined credit card types
            if (creditCard.length() >= MIN_CREDIT_CARD_LENGTH &&
                    creditCard.length() <= MAX_CREDIT_CARD_LENGTH) {
                CreditCard creditCardType = FakeCreditCardGenerator.determineCard(creditCard);
                if (creditCardType == null) {
                    creditCardLabel.setGraphic(failImageView);
                } else {
                    switch (creditCardType) {
                        case VISA:
                            creditCardLabel.setGraphic(visaImageView);
                            break;
                        case DISCOVER:
                            creditCardLabel.setGraphic(discoverImageView);
                            break;
                        case AMERICAN_EXPRESS:
                            creditCardLabel.setGraphic(americanExpressImageView);
                            break;
                        case MASTER_CARD:
                            creditCardLabel.setGraphic(masterCardImageView);
                            break;
                    }
                }
            }
        });
        creditCardLabel = createLabel(100, 100, Pos.CENTER);
        validateGrid.add(creditCardLabel, 0, 0);
        validateGrid.add(validateCreditCardButton, 1, 0);
        return validateGrid;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 5, 5));
        return grid;
    }

}

