package aspguidp.service.input;

import aspguidp.helper.TemplateHelper;
import aspguidp.service.core.CoreServicePool;
import aspguidp.service.data.DataServicePool;
import aspguidp.service.data.manager.EntityDataManager;
import aspguidp.service.data.manager.ValueDataManager;
import aspguidp.service.data.manager.impl.SimpleEntityDataManager;
import aspguidp.service.data.manager.impl.SimpleValueDataManager;
import aspguidp.service.data.model.EntityFactory;
import aspguidp.service.data.model.impl.TemplateEntityFactory;
import aspguidp.service.data.template.TemplatePart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton data service pool of a specific entity/value.
 * <p>
 * This class is generated for each input entity, output entity, input value and output value of the application by
 * the aspguid compiler. It provides methods to access data related services, managers and other data related
 * information for the entity/value.
 */
public class Depends_onEntityDataServicePool extends DataServicePool {
    private static Depends_onEntityDataServicePool instance;

    private Map<String, AttributeDefinition> attributeDefinitionMap;
    private List<TemplatePart> displayTemplateParts;
    private List<TemplatePart> atomTemplateTemplateParts;
    private EntityDataManager entityDataManagerInstance;
    private ValueDataManager valueDataManagerInstance;
    private EntityFactory representationFactoryInstance;

    private Depends_onEntityDataServicePool() {}

    /**
     * @return singleton service pool instance of this class
     */
    public static Depends_onEntityDataServicePool getInstance() {
        if (instance == null) {
            instance = new Depends_onEntityDataServicePool();
        }
        return instance;
    }

    /**
     * Method to access the attribute definitions for the entity/value of the service pool instance.
     *
     * @return attribute definitions for the entity/value of the service pool instance.
     */
    private Map<String, AttributeDefinition> getAttributeDefinitionMap() {
        if (this.attributeDefinitionMap == null) {
            this.attributeDefinitionMap = new HashMap<>();
            this.attributeDefinitionMap.put("task1", new AttributeDefinition("task1", "task1", "task[0]"));
            this.attributeDefinitionMap.put("task2", new AttributeDefinition("task2", "task2", "task[0]"));
        }
        return this.attributeDefinitionMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePart> getDisplayTemplateParts() {
        if (this.displayTemplateParts == null) {
            this.displayTemplateParts = TemplateHelper.getTemplateParts(
                    "::task1 depends on ::task2",
                    s -> this.getAttributeDefinitionMap().get(s).getName(),
                    s -> this.getAttributeDefinitionMap().get(s).getValueSource()
            );
        }
        return this.displayTemplateParts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePart> getAtomTemplateParts() {
        if (this.atomTemplateTemplateParts == null) {
            this.atomTemplateTemplateParts = TemplateHelper.getTemplateParts(
                    "depends_on(::task1,::task2)",
                    s -> this.getAttributeDefinitionMap().get(s).getName(),
                    s -> this.getAttributeDefinitionMap().get(s).getValueSource()
            );
        }
        return this.atomTemplateTemplateParts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityDataManager getEntityDataManager() {
        if (this.entityDataManagerInstance == null) {
            this.entityDataManagerInstance = new SimpleEntityDataManager(this.getEntityFactory());
            CoreServicePool.getInstance().getInputAtomHub().registerAtomSupplier(this.entityDataManagerInstance);
            CoreServicePool.getInstance().getInputAtomHub().registerAtomConsumer(this.entityDataManagerInstance);
        }
        return this.entityDataManagerInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueDataManager getValueDataManager() {
        if (this.valueDataManagerInstance == null) {
            this.valueDataManagerInstance = new SimpleValueDataManager(this.getEntityFactory());
            CoreServicePool.getInstance().getInputAtomHub().registerAtomSupplier(this.valueDataManagerInstance);
            CoreServicePool.getInstance().getInputAtomHub().registerAtomConsumer(this.valueDataManagerInstance);
        }
        return this.valueDataManagerInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFactory getEntityFactory() {
        if (this.representationFactoryInstance == null) {
            this.representationFactoryInstance = new TemplateEntityFactory(this.getDisplayTemplateParts(), this.getAtomTemplateParts());
        }
        return this.representationFactoryInstance;
    }
}
