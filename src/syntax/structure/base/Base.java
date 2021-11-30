package syntax.structure.base;

import morphology.Stem;
import syntax.function.Function;
import syntax.setting.Settings;
import syntax.structure.Structure;
import syntax.structure.Syntheme;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class Base extends Syntheme {

    public Base(Object head) {
        super(head);
    }

    public String getSubcat() {
        return ((Stem) super.getHead()).getSubcat();
    }

    @Override
    public String toString() {
        if (Settings.printCode == 0) {
            return "[" + this.getClass().getSimpleName() + " " + ((Stem) super.getHead()).getLexical() + "]";
        } else if (Settings.printCode == 1) {
            return ((Stem) super.getHead()).getLexical();
        } else if (Settings.printCode == 2 || Settings.printCode == 3 || Settings.printCode == 4) {
            return ((Stem) super.getHead()).getSurface();
        } else {
            return "";
        }
    }

    public String getBaseLexical() {
        return ((Stem) super.getHead()).getLexical();
    }

    public String getBaseState() {
        return ((Stem) super.getHead()).getState();
    }
}
