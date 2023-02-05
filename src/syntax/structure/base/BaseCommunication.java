package syntax.structure.base;

import morphology.Stem;
import syntax.function.Communication;
import syntax.function.Function;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseCommunication extends Base implements Communication  {

    public BaseCommunication(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseCommunication((Stem) super.getHead());
    }

    @Override
    public Structure getStructure() {
        return this;
    }

    @Override
    public Structure getDelegate() {
        return this;
    }

    @Override
    public boolean checkMandatory() {
        return true;
    }
}
