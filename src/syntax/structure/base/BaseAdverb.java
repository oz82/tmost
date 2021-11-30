package syntax.structure.base;

import morphology.Stem;
import syntax.function.Adverb;
import syntax.function.Function;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseAdverb extends Base implements Adverb {

    public BaseAdverb(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseAdverb((Stem) super.getHead());
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
