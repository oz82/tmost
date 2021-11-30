package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Postposition;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BasePostposition extends Base implements Postposition {

    public BasePostposition(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BasePostposition((Stem) super.getHead());
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
