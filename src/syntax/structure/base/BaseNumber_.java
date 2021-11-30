package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Number_;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseNumber_ extends Base implements Number_ {

    public BaseNumber_(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseNumber_((Stem) super.getHead());
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
