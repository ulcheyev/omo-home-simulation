package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeReportVisitor;

/**
 * Class for entities in house.
 */
public interface HomeComponent {

    /**
     *
     * @param visitor visitor to accept
     * @return necessary object, depends on implementation
     */
    Object accept(SmartHomeReportVisitor visitor);

    /**
     *
     * @return true, if null.
     */
    boolean isNull();
}
