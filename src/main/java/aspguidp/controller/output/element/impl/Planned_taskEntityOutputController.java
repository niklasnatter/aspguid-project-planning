package aspguidp.controller.output.element.impl;

import aspguidp.controller.output.element.EntityOutputController;
import aspguidp.service.data.DataServicePool;
import aspguidp.service.data.model.Entity;
import aspguidp.service.output.Planned_taskEntityDataServicePool;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * Controller class for an entity output component.
 * <p>
 * An entity output component is assigned to a specific output entity. For each output entity of the application, there
 * exists a {@link DataServicePool} instance, which holds service instances and other data related information for the
 * output entity.
 * <p>
 * An entity output component contains a list view, which displays all entities, which are managed by the
 * {@link aspguidp.service.data.manager.EntityDataManager} of the data service pool of the entity which is assigned to
 * the entity output component.
 * <p>
 * Additionally, a entity output component can be assigned to a condition atom. If the condition atom is not empty,
 * the output component is only displayed, if the current displayed answer set contains the condition atom of the
 * output component ({@link aspguidp.service.output.condition.ConditionStatementService}).
 */
public class Planned_taskEntityOutputController extends EntityOutputController {
    @FXML
    private Node rootNode;
    @FXML
    private ListView<Entity> entityList;

    /**
     * Initialization method which is called when the respective .fxml file is loaded.
     * <p>
     * This method initializes a condition statement handler with the condition atom of the output component,
     * to hide the output component when the condition atom is not present in the currently displayed
     * answer set.
     * <p>
     * Furthermore, this method initializes the list view of the entity output component with the data service pool
     * of the entity of the entity output component.
     */
    @FXML
    protected void initialize() {
        this.initializeConditionStatementHandler("", this.rootNode);

        DataServicePool dataServicePool = Planned_taskEntityDataServicePool.getInstance();
        this.initializeListView(dataServicePool, this.entityList);
    }
}
