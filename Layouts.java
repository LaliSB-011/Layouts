package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Layouts extends Application {

    private Pane panelComponentes; // contenedor dinámico

    @Override
    public void start(Stage stagePrincipal) {

        // VENTANA PRINCIPAL
        Label etiqueta = new Label("Selecciona un layout:");

        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll(
                "FlowPane",
                "BorderPane",
                "GridPane",
                "VBox"
        );
        combo.setValue("FlowPane"); // valor inicial

        VBox rootPrincipal = new VBox(10);
        rootPrincipal.setPadding(new Insets(15));
        rootPrincipal.setAlignment(Pos.CENTER);
        rootPrincipal.getChildren().addAll(etiqueta, combo);

        Scene escenaPrincipal = new Scene(rootPrincipal, 260, 150);
        stagePrincipal.setTitle("Selector de Layout");
        stagePrincipal.setScene(escenaPrincipal);
        stagePrincipal.show();

        // SEGUNDA VENTANA
        Stage ventanaComponentes = new Stage();

        panelComponentes = new FlowPane(10, 10); // layout inicial con espacios
        agregarComponentesBasicos();
        panelComponentes.setStyle("-fx-background-color: #d0e7ff;");

        Scene escena2 = new Scene(panelComponentes, 420, 300);
        ventanaComponentes.setTitle("Componentes");
        ventanaComponentes.setScene(escena2);
        ventanaComponentes.show();

        // EVENTO DEL COMBO
        combo.setOnAction(e -> {

            String seleccion = combo.getValue();

            if (seleccion.equals("FlowPane")) {
                panelComponentes = new FlowPane(10, 10);
                panelComponentes.setStyle("-fx-background-color: #d0e7ff;");
                agregarComponentesBasicos();
            }

            else if (seleccion.equals("BorderPane")) {
                BorderPane bp = new BorderPane();
                bp.setPadding(new Insets(10));
                bp.setStyle("-fx-background-color: #d4f4dd;");


                bp.setTop(new Button("ARRIBA"));
                bp.setBottom(new Button("ABAJO"));
                bp.setLeft(new Button("IZQUIERDA"));
                bp.setRight(new Button("DERECHA"));
                bp.setCenter(new Button("CENTRO"));

                panelComponentes = bp;
            }

            else if (seleccion.equals("GridPane")) {
                GridPane gp = new GridPane();
                gp.setHgap(10);
                gp.setVgap(10);
                gp.setPadding(new Insets(10));
                gp.setStyle("-fx-background-color: #fff3b0;");

                gp.add(new Button("1"), 0, 0);
                gp.add(new Button("2"), 1, 0);
                gp.add(new Button("3"), 0, 1);
                gp.add(new Button("4"), 1, 1);

                panelComponentes = gp;
            }

            else if (seleccion.equals("VBox")) {
                panelComponentes = new VBox(10);
                ((VBox) panelComponentes).setPadding(new Insets(10));
                agregarComponentesBasicos();
                panelComponentes.setStyle("-fx-background-color: #ffd6e7;");
            }

            // 🔄actualizar
            escena2.setRoot(panelComponentes);
        });
    }

    // COMPONENTES BÁSICOS
    private void agregarComponentesBasicos() {
        if (panelComponentes instanceof Pane) {
            ((Pane) panelComponentes).getChildren().addAll(
                    new Button("Botón 1"),
                    new Button("Botón 2"),
                    new TextField("Texto"),
                    new CheckBox("Opción"),
                    new Label("Etiqueta")
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}