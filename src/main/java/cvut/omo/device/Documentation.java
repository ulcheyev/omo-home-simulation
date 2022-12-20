package cvut.omo.device;

public class Documentation {

    protected String warrantyCard;
    protected InstructionType instructionType;

    public void setWarrantyCard(String warrantyCard){
        this.warrantyCard = warrantyCard;
    }

    public String getWarrantyCard(){
        return warrantyCard;
    }

    public InstructionType getInstructionsForTheDevice(){
        return instructionType;
    }

    public void setInstructionsForTheDevice(InstructionType instructionType){
        this.instructionType = instructionType;
    }

}
