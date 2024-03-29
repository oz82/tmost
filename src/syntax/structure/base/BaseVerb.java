package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.function.Verb;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class BaseVerb extends Base implements Verb {

    public BaseVerb(Object head) {
        super(head);
    }

    @Override
    public Function duplicate() {
        return new BaseVerb((Stem) super.getHead());
    }

    @Override
    public boolean checkSubcat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSubcat() {
        return ((Stem) super.getHead()).getSubcat();
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
