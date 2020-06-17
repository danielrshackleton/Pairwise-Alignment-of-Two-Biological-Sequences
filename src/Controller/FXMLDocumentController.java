/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Protein;
import Model.ProteinAlignment;
import Model.Blosum;
import Model.InvalidAlphabetException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 *
 * @author Daniel
 */
public class FXMLDocumentController implements Initializable {
    
    File firstProtein;
    File secondProtein;
    ObservableList blosumList = FXCollections.observableArrayList();
    ObservableList gapPenaltyList = FXCollections.observableArrayList();
    ObservableList affinePenaltyList = FXCollections.observableArrayList();
    ArrayList<Image> slidesList = new ArrayList<>();
    FileChooser fileChooser = new FileChooser();
    
    int i = 0;
    
    @FXML
    private Button loadFirstProteinButton;
    @FXML
    private Button loadSecondProteinButton;
    @FXML
    private ChoiceBox blosumChoiceBox;
    @FXML
    private CheckBox affineCheckBox;
    @FXML
    private CheckBox localCheckBox;
    @FXML
    private Button startAlignmentButton;
    @FXML
    private TextArea firstSequenceTextArea;
    @FXML
    private TextArea secondSequenceTextArea;
    @FXML
    private ChoiceBox affineGapChoiceBox;
    @FXML
    private ChoiceBox gapPenaltyChoiceBox;
    @FXML
    private TextArea myAlignment;
    @FXML
    private Text sequence1Values;
    @FXML
    private Text sequence2Values;
    @FXML
    private Text scoreValue;
    @FXML
    private Text alignmentText;
    @FXML
    private Button openTutorialButton;
    @FXML
    private Pane tutorialModal;
    @FXML
    private Button closeModalButton;
    @FXML
    private Button previousImageButton;
    @FXML
    private Button nextImageButton;
    @FXML
    private ImageView imgView;
    @FXML
    private Label firstProteinLabel;
    @FXML
    private Label secondProteinLabel;
    @FXML
    private Button clearFirstProteinButton;
    @FXML
    private Button clearSecondProteinButton;
    
    private void loadBlosumValues(){
        blosumList.removeAll(blosumList);
        blosumList.addAll(45,62,80);
        blosumChoiceBox.getItems().addAll(blosumList);
        blosumChoiceBox.setValue(62);
    }
    
    private void loadGapPenaltyValues(){
        gapPenaltyList.removeAll(gapPenaltyList);
        gapPenaltyList.addAll(1,5,8,10,20,50);
        gapPenaltyChoiceBox.getItems().addAll(gapPenaltyList);
        gapPenaltyChoiceBox.setValue(8);
    }
    
    private void loadAffinePenaltyValues(){
        affinePenaltyList.removeAll(affinePenaltyList);
        affinePenaltyList.addAll(1,2,5,10);
        affineGapChoiceBox.getItems().addAll(affinePenaltyList);
        affineGapChoiceBox.setValue(2);
    }
    
    @FXML
    private void loadFirstProteinButtonHandle(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Fasta File");
        Node node = (Node) event.getSource();
        File file = chooser.showOpenDialog(node.getScene().getWindow());
        try {
            firstProtein = file;
            if (firstProtein != null){
                firstSequenceTextArea.setDisable(true);
                firstProteinLabel.setText(file.getName());
            }
        } catch(Exception e){
            System.out.println("Invalid file");
        }
    }
    
    @FXML
    private void loadSecondProteinButtonHandle(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Fasta File");
        Node node = (Node) event.getSource();
        File file = chooser.showOpenDialog(node.getScene().getWindow());
        try {
            secondProtein = file;
            if (secondProtein != null){
                secondSequenceTextArea.setDisable(true);
                secondProteinLabel.setText(file.getName());
            }
        } catch(Exception e){
            System.out.println("Invalid file");
        }
    }
    
    @FXML
    private void startAlignmentButtonHandle(ActionEvent event) throws InvalidAlphabetException {
        try {
            Protein protein1;
            Protein protein2;
            if (firstProtein != null){
                protein1 = new Protein(firstProtein);
                
            } else {
               protein1 = new Protein(firstSequenceTextArea.getText().toUpperCase().replaceAll("\\s+","").toCharArray());
            }
            
            if (secondProtein != null){
                protein2 = new Protein(secondProtein);
            } else {
                protein2 = new Protein(secondSequenceTextArea.getText().toUpperCase().replaceAll("\\s+","").toCharArray());
            }
            
            ProteinAlignment alignment = new ProteinAlignment(protein1, protein2);
            alignment.setAffineGap(affineCheckBox.isSelected());
            alignment.setExtendedPenalty((int) affineGapChoiceBox.getSelectionModel().getSelectedItem());
            alignment.isGlobal(!localCheckBox.isSelected());
            Blosum.setBlosumMatrix((int) blosumChoiceBox.getSelectionModel().getSelectedItem());
            alignment.setGapPenalty((int) gapPenaltyChoiceBox.getSelectionModel().getSelectedItem());
        
            System.out.println(localCheckBox.isSelected());
            
            alignment.scoreArray(protein1, protein2);
            
            
            
            sequence1Values.setText("Sequence 1 : molecule - " + protein1.molecule + 
                    ", species - " + protein1.species);
            
            sequence2Values.setText("Sequence 2 : molecule - " + protein2.molecule + 
                    ", species - " + protein2.species);
            
            
            
            scoreValue.setText("Score : " + Integer.toString(alignment.getScore())
                + "      Match: " + String.format("%.2f%%", alignment.getPercentageMatch()));
            
            myAlignment.setText(alignment.getFirstSB().toString() + "\n");
            myAlignment.appendText(alignment.getMatchingSB().toString() + "\n");
            myAlignment.appendText(alignment.getSecondSB().toString());
            myAlignment.setStyle("-fx-font-family: 'monospaced';");
            myAlignment.setVisible(true);
            alignmentText.setVisible(true);
            

            
            
        
        
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setTitle("Error running sequence alignment!");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.show();
        }
    }
    private void disableButtons(){
        loadFirstProteinButton.disableProperty().bind(
    Bindings.isNotEmpty(firstSequenceTextArea.textProperty()));
        
        loadSecondProteinButton.disableProperty().bind(
    Bindings.isNotEmpty(secondSequenceTextArea.textProperty()));
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        disableButtons();
        loadBlosumValues();
        loadGapPenaltyValues();
        loadAffinePenaltyValues();
        myAlignment.setVisible(false);
        myAlignment.setEditable(false);
        alignmentText.setVisible(false);
        
    }    
    
    private void loadTutorialSlides(){
        
        
        Image img1 = new Image("slides/1.jpg");
        Image img2 = new Image("slides/2.jpg");
        Image img3 = new Image("slides/3.jpg");
        Image img4 = new Image("slides/4.jpg");
        Image img5 = new Image("slides/5.jpg");
        Image img6 = new Image("slides/6.jpg");
        Image img7 = new Image("slides/7.jpg");
        Image img8 = new Image("slides/8.jpg");
        Image img9 = new Image("slides/9.jpg");
        Image img10 = new Image("slides/10.gif");
        Image img11 = new Image("slides/11.jpg");
        Image img12 = new Image("slides/12.gif");
        Image img13 = new Image("slides/13.gif");
        Image img14 = new Image("slides/14.gif");
        Image img15 = new Image("slides/15.jpg");
        Image img16 = new Image("slides/16.gif");
        
        slidesList.add(img1);
        slidesList.add(img2);
        slidesList.add(img3);
        slidesList.add(img4);
        slidesList.add(img5);
        slidesList.add(img6);
        slidesList.add(img7);
        slidesList.add(img8);
        slidesList.add(img9);
        slidesList.add(img10);
        slidesList.add(img11);
        slidesList.add(img12);
        slidesList.add(img13);
        slidesList.add(img14);
        slidesList.add(img15);
        slidesList.add(img16);
        
        
        
    }

    @FXML
    private void openTutorial(ActionEvent event) {
        tutorialModal.setVisible(true);
        tutorialModal.setMouseTransparent(false);
        loadTutorialSlides();
        
        
        imgView.setImage(slidesList.get(i));
        
        if (i == 0){
            previousImageButton.setDisable(true);
        } else if (i == 15){
            nextImageButton.setDisable(true);
        }
        
            
        previousImageButton.setOnAction((ActionEvent previous) -> {
            
            i--;
            imgView.setImage(slidesList.get(i));
            
            nextImageButton.setDisable(false);
            if (i >= 1){
                previousImageButton.setDisable(false);
            } else {
                previousImageButton.setDisable(true);
            }
        });
        
        nextImageButton.setOnAction((ActionEvent next) -> {
            i++;
            imgView.setImage(slidesList.get(i));
            previousImageButton.setDisable(false);
            if (i <= 14){
                nextImageButton.setDisable(false);
            } else {
                nextImageButton.setDisable(true);
            }
        });
            
            
        
        
    }

    @FXML
    private void closeModal(ActionEvent event) {
        tutorialModal.setVisible(false);
        tutorialModal.setMouseTransparent(true);
    }

    @FXML
    private void previousImage(ActionEvent event) {
        
    }

    @FXML
    private void nextImage(ActionEvent event) {
    }

    @FXML
    private void clearFirstProtein(ActionEvent event) {
        firstProtein = null;
        firstProteinLabel.setText(null);
        firstSequenceTextArea.setText(null);
        firstSequenceTextArea.setDisable(false);
        
    }

    @FXML
    private void clearSecondProtein(ActionEvent event) {
        secondProtein = null;
        secondProteinLabel.setText(null);
        secondSequenceTextArea.setText(null);
        secondSequenceTextArea.setDisable(false);
    }
    
}
