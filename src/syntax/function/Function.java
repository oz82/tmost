package syntax.function;

import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public interface Function {
    Function duplicate();
    Structure getStructure();
    Structure getDelegate();
    boolean checkMandatory();
}
