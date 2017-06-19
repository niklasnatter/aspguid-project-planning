package aspguidp.controller.output.element.impl;

import aspguidp.controller.output.element.ValueOutputController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Controller class for a value output component.
 * <p>
 * A value output component contains an arbitrary number of output value labels. Each output value label is assigned
 * to an output value. For each output value of the application exists a {@link aspguidp.service.data.DataServicePool}
 * instance, which holds service instances and other data related information for the output value.
 * <p>
 * An output value label displays the value which is managed by the {@link aspguidp.service.data.manager.ValueDataManager}
 * of the data service pool of the assigned output value.
 * <p>
 * Additionally, a value output component can be assigned to a condition atom. If the condition atom is not empty,
 * the output component is only displayed, if the current displayed answer set contains the condition atom of the
 * output component ({@link aspguidp.service.output.condition.ConditionStatementService}).
 */
public class Utilization_warningValueOutputController extends ValueOutputController {
    @FXML
    private Node rootNode;

    /**
     * Initialization method which is called when the respective .fxml file is loaded.
     * <p>
     * This method initializes a condition statement handler with the condition atom of the output component,
     * to hide the output component when the condition atom is not present in the currently displayed
     * answer set.
     * <p>
     * Furthermore, this method initializes all output value labels of the value output component with the data service
     * pool of the respective output value.
     */
    @FXML
    protected void initialize() {
        this.initializeConditionStatementHandler("bad_utilization", this.rootNode);
        
    }
}
