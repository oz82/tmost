package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Interjection;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseInterjection extends Base implements Interjection {

    public BaseInterjection(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseInterjection((Stem) super.getHead());
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
