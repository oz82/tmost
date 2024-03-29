package syntax.structure.base;

import lang_specific.LexicalEntry;
import morphology.MorphoAnalysis;
import syntax.function.Adjective;
import syntax.function.Function;
import syntax.structure.Structure;

import java.util.ArrayList;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class CompoundAdjective extends Base implements Adjective {
    private ArrayList<MorphoAnalysis> member;
    private LexicalEntry entry;

    public CompoundAdjective(LexicalEntry entry, ArrayList<MorphoAnalysis> member, Object head) {
        super(head);
        this.entry = entry;
        this.member =  member;
    }

    @Override
    public Function duplicate() {
        return null;
    }

    @Override
    public Structure getStructure() {
        return null;
    }

    @Override
    public Structure getDelegate() {
        return null;
    }

    @Override
    public boolean checkMandatory() {
        return false;
    }
}
