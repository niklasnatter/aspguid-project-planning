package aspguidp.controller.input.element.impl;

import aspguidp.controller.input.element.EntityInputController;
import aspguidp.service.data.DataServicePool;
import aspguidp.service.data.model.Entity;
import aspguidp.service.input.TaskEntityDataServicePool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller class for a entity input component.
 * <p>
 * An entity input component is assigned to a specific input entity. For each input entity of the application, there
 * exists a {@link DataServicePool} class, which holds service instances and other data related information for the
 * input entity.
 * <p>
 * An entity input component contains a stacked input field which is used to input a new entity, a button which is
 * used to store the inputted entity to the entity input component, a list view which displays the stored entities
 * of the entity input component and a list view count label which is used to display the count of entities which
 * are stored to the entity input component.
 * <p>
 * The stacked input field of an entity input component is initialized by setting up the validation
 * ({@link aspguidp.service.input.validation.ValidationService}) for the input field, setting up an autocomplete popup
 * ({@link aspguidp.controller.component.AutocompletePopupController}) for the input field and setting up a key handler
 * for the input field.
 * <p>
 * The button of an entity input component is initialized in such a way, that it is only clickable, when the content
 * of the stacked input field is validated as valid. If the button is clicked, the input of the stacked input field
 * is converted to an {@link Entity} and added to the {@link aspguidp.service.data.manager.EntityDataManager} of the
 * service pool instance of the entity which is assigned to the entity input component.
 * <p>
 * The list view of an entity input component displays all entities, which are managed by the entity data manager of
 * the service pool of the entity which is assigned to the entity input component. The list view uses
 * {@link aspguidp.gui.javafx.RemovableListCell} instance, to enable the deletion of entities from the entity data
 * manager.
 * <p>
 * The list view count label of an entity input component displays the count of entities which is managed by the
 * entity data manager of the service pool of the entity which is assigned to the entity input component.
 * Additionally, a validation error class is set to the list view count label, if the count of entities contradicts
 * the min and max entity count of the entity input component.
 * <p>
 * Finally, an entity input component has an execution status and a data status which are registered to the respective
 * status managers of the application ({@link aspguidp.gui.status.DataStatusManager}, {@link aspguidp.gui.status.ExecutionStatusManager}).
 */
public class TaskEntityInputController extends EntityInputController {
    @FXML
    private TextField backgroundField;
    @FXML
    private TextField inputField;
    @FXML
    private Button inputButton;
    @FXML
    private ListView<Entity> entityList;
    @FXML
    private Label entityCountLabel;

    /**
     * Initialization method which is called when the respective .fxml file is loaded.
     * <p>
     * This method initializes all child components of the entity input component with the data service pool of the
     * entity of the entity input component.
     * <p>
     * In the first step, the stacked input field of the entity input component is initialized by initializing
     * the focus propagation of the stacked field, initializing the validation of the stacked field, initializing
     * a key press handler for the stacked field and setting up the autocomplete popup for the stacked field.
     * <p>
     * In the second step, the input button is initialized, the list view which is used to display entities which
     * were inputted by the user is set up and the list view count label is initialized.
     * <p>
     * In the last step, the execution status and the data status of the entity input component is registered to
     * the status managers of the application.
     */
    @FXML
    protected void initialize() {
        DataServicePool dataServicePool = TaskEntityDataServicePool.getInstance();
        int inputCountMin = 1;
        int inputCountMax = 2147483647;

        this.initializeFocusPropagationHandler(this.inputField, this.backgroundField);
        this.initializeInputFieldValidation(dataServicePool, this.inputField, this.backgroundField);
        this.initializeInputFieldKeyPressHandler(this.inputField, this.inputButton);
        this.initializeInputFieldAutocompletePopup(dataServicePool, this.inputField);

        this.initializeInputButton(dataServicePool, this.inputField, this.backgroundField, this.inputButton);
        this.initializeListView(dataServicePool, this.entityList);
        this.initializeListViewCountLabel(dataServicePool, this.entityCountLabel, inputCountMin, inputCountMax);

        this.registerInputElementStatus(dataServicePool, inputCountMin, inputCountMax);
    }
}
