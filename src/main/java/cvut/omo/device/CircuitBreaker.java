package cvut.omo.device;

import cvut.omo.data_collections.consumption.ConsumptionCollection;

public class CircuitBreaker extends HomeAppliances{

    public HomeAppliances homeAppliances;

    public CircuitBreaker(double lifeTime) {super(lifeTime);}

    @Override
    protected void identify() {
        this.sourceTypes.add(SourceType.NOT_CONSUME);
        ConsumptionCollection.getInstance().put(this);
    }

    @Override
    public Documentation getDocumentation(){
        if(this.homeDeviceState instanceof BrokenState && this.documentation == null){
            this.documentation = new Documentation();
            this.documentation.setInstructionsForTheDevice(InstructionType.BUY);
            this.documentation.setWarrantyCard("Circuit warranty card that does not guarantee");
        }
        return documentation;
    }

    @Override
    public void turnOn(){}

    @Override
    public void turnOff(){}

    @Override
    public void goIntoPauseMode(){}

    @Override
    public void run(){}

    @Override
    public void accept() {}

}
