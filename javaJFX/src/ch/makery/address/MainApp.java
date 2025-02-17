package ch.makery.address;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.view.PersonOverviewController;
import ch.makery.address.view.RootLayoutController;
import ch.makery.address.MainApp;
import ch.makery.address.view.PersonEditDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Person> personData=FXCollections.observableArrayList();
    
    /**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Daniel", "Garcia"));
		personData.add(new Person("Antonio", "Otero"));
		personData.add(new Person("Nuria", "Casal"));
		personData.add(new Person("Marta", "Rodriguez"));
		personData.add(new Person("Silvia", "Robador"));
		personData.add(new Person("Antonio", "Cardador"));
		personData.add(new Person("Ana", "Mena"));
		personData.add(new Person("Diego", "Martin"));
		personData.add(new Person("Sonia", "Gonzalez"));
	}
	
	public ObservableList<Person> getPersonData() {
		return personData;
	}
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image("file:resources/images/custom-app-icon.png"));

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller acccess to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		File file = getPersonFilePath();
		if (file != null) {
			loadPersonDataFromFile(file);
		}
	}
    
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}

	}
    
    
    public void setPersonFilePath(File file) {
    	Preferences prefs=Preferences.userNodeForPackage(MainApp.class);
    	if(file !=null) {
    		prefs.put("filePath", file.getPath());
    		
    		//Update the stage title.
    		primaryStage.setTitle("AdressApp - " + file.getName());
    	}else {
    		prefs.remove("filePath");
    		
    		//Update the stage title.
    		primaryStage.setTitle("AddressApp");
    	}
    }
    
	public void loadPersonDataFromFile(File file) {
		try {
			JAXBContext context=JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um=context.createUnmarshaller();
			
			//Reading XML from the file and unmarshalling.
			PersonListWrapper wrapper=(PersonListWrapper) um.unmarshal(file);
			
			personData.clear();
			personData.addAll(wrapper.getPersons());
			
			//Save the file path to the registry.
			setPersonFilePath(file);
			
		}catch(Exception e) {
			
		}
	}

	public void savePersonFilePath(File file) {
		// TODO Auto-generated method stub
		try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersonas(personData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setPersonFilePath(file);

		} catch (Exception e) {

		}

	}
    
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
