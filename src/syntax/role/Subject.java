package syntax.role;

import syntax.function.Adjective;
import syntax.function.Function;
import syntax.function.Noun;
import syntax.function.Pronoun;
import syntax.setting.Settings;
import syntax.structure.PossessiveNoun;
import syntax.structure.Structure;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class Subject extends Role {

    private Subject(Function content, int serialNumber) {
        super(content, serialNumber);
    }

    public static Role make(Function content, int serialNumber) {
        if (content.getStructure() instanceof Adjective) {
            return null;
        }
        if (content.getStructure() instanceof Noun || content.getStructure() instanceof Pronoun || content.getStructure() instanceof PossessiveNoun) {
            return new Subject(content, serialNumber);
        }

        return null;
    }

    @Override
    public String toString() {
        if (Settings.printCode == 2 || Settings.printCode == 3 || Settings.printCode == 4) {
            return super.getContent().toString();
        } else {
            return "[Subject " + super.getContent() + "]";
        }
    }

    @Override
    public Function duplicate() {
        return new Subject(super.getContent().duplicate(), super.getSerialNumber());
    }

    @Override
    public String getString() {
        return super.getContent().toString();
    }

    @Override
    public Structure getStructure() {
        return null;
    }
}