package syntax.structure.base;

import morphology.Stem;
import syntax.function.Adjective;
import syntax.function.Function;
import syntax.function.Noun;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseAdjective extends Base implements Adjective, Noun {

    public BaseAdjective(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseAdjective((Stem) super.getHead());
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
