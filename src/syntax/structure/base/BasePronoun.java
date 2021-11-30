package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Pronoun;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BasePronoun extends Base implements Pronoun {

    public BasePronoun(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BasePronoun((Stem) super.getHead());
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
