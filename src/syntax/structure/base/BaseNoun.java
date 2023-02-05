package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Noun;
import syntax.function.Noun2;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseNoun extends Base implements Noun, Noun2 {

    public BaseNoun(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseNoun((Stem) super.getHead());
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
