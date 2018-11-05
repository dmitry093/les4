package chat;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ChatController {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField, fontSizeField;

    @FXML
    ToggleButton buttonBold, buttonItalic;

    @FXML
    ChoiceBox fontFamilies;

    @FXML
    Button buttonSend;

    private Double fontSize;
    private String fontFamily;

    public void initialize() {
        textArea.setFont(Font.font("SansSerif", FontWeight.NORMAL, 32));
        fontSizeField.setPrefWidth(50);
        fontSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        fontSizeField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setFontProperties();
            }
        });
        fontSizeField.setText("32");
        fontSize = 32.0;
        List<String> fonts = javafx.scene.text.Font.getFamilies();
        fontFamilies.setItems(FXCollections.observableArrayList(fonts));
        fontFamilies.getSelectionModel().select(fonts.indexOf("SansSerif"));
        fontFamily = "SansSerif";
    }

    public void sendMsg() {
        textArea.appendText("you: " + textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }

    public void setFontProperties() {
        fontSize = Double.valueOf(fontSizeField.getText());
        fontFamily = (String) fontFamilies.getValue();
        Boolean isBold = buttonBold.selectedProperty().getValue();
        Boolean isItalic = buttonItalic.selectedProperty().getValue();
        if (isBold) {
            if (isItalic) {
                textArea.setFont(Font.font(fontFamily, FontWeight.BOLD, FontPosture.ITALIC, fontSize));
                buttonItalic.setSelected(true);
            } else {
                textArea.setFont(Font.font(fontFamily, FontWeight.BOLD, FontPosture.REGULAR, fontSize));
                buttonItalic.setSelected(false);
            }
            buttonBold.setSelected(true);
        } else {
            if (isItalic) {
                textArea.setFont(Font.font(fontFamily, FontWeight.NORMAL, FontPosture.ITALIC, fontSize));
                buttonItalic.setSelected(true);
            } else {
                textArea.setFont(Font.font(fontFamily, FontWeight.NORMAL, FontPosture.REGULAR, fontSize));
                buttonItalic.setSelected(false);
            }
            buttonBold.setSelected(false);
        }
    }
}
