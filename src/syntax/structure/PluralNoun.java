package syntax.structure;

import syntax.function.Adjective;
import syntax.function.Function;
import syntax.function.Noun;
import syntax.function.Noun2;
import syntax.setting.Settings;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class PluralNoun extends Inflected implements Noun, Noun2 {

    public PluralNoun(Function base, Function suffix) {
        super(base, suffix);
    }

    public static Function make(Function base, Function suffix) {
        if (!((Structure) base).isInflectionLocked()) {
            String suff = ((Suffix) suffix).getMorpheme().getTag().toString();
            if ((base instanceof Noun /*|| base instanceof Adjective*/ || base instanceof Participle) && suff.equals("PLU")) {
                return new PluralNoun(base, suffix);
            }
        }
        return null;
    }

    @Override
    public Function duplicate() {
        return new PluralNoun(super.getBase().duplicate(), super.getSuffix().duplicate());
    }

    @Override
    public String toString() {
        if (Settings.printCode == 0) {
            return "[PluralNoun " + super.getBase() + " " + super.getSuffix() + "]";
        } else if (Settings.printCode == 1) {
            return super.getBase() + " " + super.getSuffix();
        } else if (Settings.printCode == 2 || Settings.printCode == 3 || Settings.printCode == 4) {
            return super.getBase().toString() + super.getSuffix().toString();
        } else {
            return "";
        }
    }

    @Override
    public Structure getStructure() {
        return this;
    }

    @Override
    public Structure getDelegate() {
        return super.getBase().getDelegate();
    }

    @Override
    public boolean checkMandatory() {
        return getBase().checkMandatory();
    }
}
