package com.example.licenta.Auxiliary;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class alertFrame {

         public static int CONFIRMATION=1;
        public static int INFORMATION=2;
        private int input;
        String message="Are you sure about this?";
    public alertFrame(int inp){
        input=inp;
        run();
    }
    public alertFrame(String message, int inp){
        input=inp;
        this.message=message;
        run();
    }

    public  void run(){
        if(input==CONFIRMATION){
            confirmation();
        } else if (input==INFORMATION) {
            inforamtion();
        }
    }

    public void confirmation(){
        // Creează un obiect Alert de tip CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(message);
        String cssPath = getClass().getResource("/com/example/licenta/style/tema.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(cssPath);

        // Adaugă butoanele de acțiune (Da sau Nu)
        ButtonType buttonTypeYes = new ButtonType("YES");
        ButtonType buttonTypeNo = new ButtonType("NO");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Așteaptă până când utilizatorul dă clic pe un buton de acțiune și obține rezultatul
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Yes();
            } else if (buttonType == buttonTypeNo) {
                No();
            }
        });
    }

    public void inforamtion(){
        // Creează un obiect Alert de tip CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        String cssPath = getClass().getResource("/com/example/licenta/style/tema.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(cssPath);

        // Adaugă butoanele de acțiune (Da sau Nu)
        ButtonType buttonTypeOK = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOK);

        // Așteaptă până când utilizatorul dă clic pe un buton de acțiune și obține rezultatul
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeOK) {
                ok();
            }
        });
    }

    public void ok(){

        }


    public void Yes() {

    }


    public void No() {

    }



}
