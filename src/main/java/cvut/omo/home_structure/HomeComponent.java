package cvut.omo.home_structure;

import cvut.omo.data_collections.visitor.SmartHomeVisitor;

public interface HomeComponent {
    String accept(SmartHomeVisitor visitor);
    boolean isNull();
}
