package aspguidp.controller.input.element.impl;

import aspguidp.controller.input.element.ValueInputController;
import aspguidp.service.input.Project_managementTime_unitValueDataServicePool;
import aspguidp.service.input.Project_managementMax_project_durationValueDataServicePool;
import aspguidp.service.input.Project_managementMax_employee_countValueDataServicePool;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for a value input component.
 * <p>
 * A value input component contains an arbitrary number of stacked input value fields. Each input valued field is
 * assigned to an input value. For each input value of the application there exists a
 * {@link aspguidp.service.data.DataServicePool} class, which holds service instances and other related information for
 * the input value.
 * <p>
 * A stacked input value field allows the user to set the value which is managed by the
 * {@link aspguidp.service.data.manager.ValueDataManager} of the data service pool of the assigned input value.
 * Each stacked input value field of an value input component is initialized by setting up the validation
 * ({@link aspguidp.service.input.validation.ValidationService}) of the input field, setting up an autocomplete popup
 * ({@link aspguidp.controller.component.AutocompletePopupController}) for the input field, binding the value of the
 * value data manager to the input field and registering the execution status and the data status of the input field
 * to the status managers of the application ({@link aspguidp.gui.status.DataStatusManager}, {@link aspguidp.gui.status.ExecutionStatusManager}).
 */
public class Project_managementValueInputController extends ValueInputController {
    @FXML
    private TextField time_unitInputField;
    @FXML
    private TextField time_unitBackgroundField;
    @FXML
    private TextField max_project_durationInputField;
    @FXML
    private TextField max_project_durationBackgroundField;
    @FXML
    private TextField max_employee_countInputField;
    @FXML
    private TextField max_employee_countBackgroundField;

    /**
     * Initialization method which is called when the respective .fxml file is loaded.
     * <p>
     * This method initializes all stacked input value fields of the value input component with the data service
     * pool of the respective input value.
     */
    @FXML
    protected void initialize() {
        this.initializeValueField(Project_managementTime_unitValueDataServicePool.getInstance(), this.time_unitInputField, this.time_unitBackgroundField);
        this.initializeValueField(Project_managementMax_project_durationValueDataServicePool.getInstance(), this.max_project_durationInputField, this.max_project_durationBackgroundField);
        this.initializeValueField(Project_managementMax_employee_countValueDataServicePool.getInstance(), this.max_employee_countInputField, this.max_employee_countBackgroundField);
    }
}
