package syntax.structure.base;

import morphology.Stem;
import syntax.function.Conjunction;
import syntax.function.Function;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseConjunction extends Base implements Conjunction {

    public BaseConjunction(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseConjunction((Stem) super.getHead());
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
