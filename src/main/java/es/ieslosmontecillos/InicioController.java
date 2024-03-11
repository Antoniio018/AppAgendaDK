package es.ieslosmontecillos;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class InicioController {

    private DataUtil dataUtil;

    private ObservableList olProv;
    private ObservableList olPers;
    private ObservableList olUsers;


    @FXML
    private Label avisoTxt;

    @FXML
    private PasswordField password;

    private Pane rootMain = new Pane();
    private Pane getRootMain(){return rootMain;}
    @FXML
    private HBox passwHBox;
    @FXML
    private Button exitBtn;
    @FXML
    private HBox userHBox;
    @FXML
    private HBox btnHBox;
    @FXML
    private Button enterBtn;
    @FXML
    private TextField username;
    @FXML
    private View inicio;


    private void iniciaApp() {
        try {
            //inicio.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AgendaView.fxml"));
            Pane rootAgendaView = fxmlLoader.load();
            rootMain.getChildren().remove(inicio);
            rootMain.getChildren().add(rootAgendaView);
            AgendaViewController agendaViewController = fxmlLoader.getController();
            agendaViewController.setDataUtil(dataUtil);
            agendaViewController.setOlProvincias(olProv);
            agendaViewController.setOlPersonas(olPers);
            agendaViewController.cargarTodasPersonas();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void setRootMain(Pane rootMain) {
        this.rootMain = rootMain;
    }

    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public void setOlProv(ObservableList olProv) {
        this.olProv = olProv;
    }

    public void setOlPers(ObservableList olPers) {
        this.olPers = olPers;
    }

    public void setOlUsers(ObservableList olUsers) {
        this.olUsers = olUsers;
    }

    @FXML
    public void enterCredentials(ActionEvent actionEvent) {
        Usuario u;
        String user;
        String passw;
        boolean login = false;
        for (int i = 0; i < olUsers.size(); i++) {
            u = (Usuario) olUsers.get(i);
            user = username.getText();
            passw = password.getText();
            if(user.equals(u.getUsername()) && passw.equals(u.getPassword())){
                login = true;
            }
            if(login)
                iniciaApp();
            else
                avisoTxt.setVisible(true);
            //System.out.println(u.getUsername() + " -> " + u.getPassword());
        }
    }


}
