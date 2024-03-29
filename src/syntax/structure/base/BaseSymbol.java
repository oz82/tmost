package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Symbol;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseSymbol extends Base implements Symbol {

    public BaseSymbol(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseSymbol((Stem) super.getHead());
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
