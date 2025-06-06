package syntax.structure;

import morphology.MorphoAnalysis;
import syntax.function.*;
import syntax.lexical.structure.Subcat;
import syntax.role.Complement;
import syntax.role.Role;
import syntax.setting.Settings;
import syntax.structure.base.Base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class SuperAdjective extends Syntheme implements Adjective, Noun {

    private MorphoAnalysis[] path;
    private int pathNo;
    private ArrayList<Complement> complement;
    private Subcat subcat;
    private int serialNumber;

    public SuperAdjective(Object head, MorphoAnalysis[] path, int pathNo) {
        super(head);
        this.path = path;
        this.pathNo = pathNo;
        complement = new ArrayList();
        subcat = processSubcat();
        serialNumber = 0;
    }

    public SuperAdjective(Object head, MorphoAnalysis[] path, int pathNo, ArrayList<Complement> complement, Subcat subcat, int serialNumber) {
        super(head);
        this.path = path;
        this.pathNo = pathNo;
        this.complement = complement;
        this.subcat = subcat;
        this.serialNumber = serialNumber;
    }

    private Subcat processSubcat() {
        String s = ((Base) super.getHead()).getSubcat();
        return new Subcat(s);
    }

    public static Function make(Object head, MorphoAnalysis[] path, int pathNo) {
        if (head instanceof Adjective) {
            return new SuperAdjective(head, path, pathNo);
        }
        return null;
    }

    public static SuperAdjective update(SuperAdjective sa, Function item) {
        ArrayList<SuperAdjective> list = new ArrayList();
        SuperAdjective copy = (SuperAdjective) sa.duplicate(); // forking copy
        if (item.getStructure() instanceof CasedNoun) {
            CasedNoun cn = (CasedNoun) item.getStructure();
            String suffTag = ((Suffix) cn.getSuffix()).getMorpheme().getTag().toString();
            if (copy.getHead() instanceof Base && Subcat.check(copy.getSubcat(), suffTag)) {//////////////////////
                Subcat.update(copy.getSubcat(), copy.getComplement(), suffTag);
                Complement c = (Complement) Complement.make(item, ++copy.serialNumber);
                copy.addComplement(c);
            } else {
                return null; // bu kod sonradan eklendi
            }
        } else if (item.getStructure() instanceof Noun) {
            String suffTag = "NOM";
            if (copy.getHead() instanceof Base && Subcat.check(copy.getSubcat(), suffTag)) {//////////////////////
                Subcat.update(copy.getSubcat(), copy.getComplement(), suffTag);
                Complement c = (Complement) Complement.make(item, ++copy.serialNumber);
                copy.addComplement(c);
            } else {
                return null; // bu kod sonradan eklendi
            }
        } else {
            return null; // bu kod sonradan eklendi
        }
        return copy;
    }

    @Override
    public Function duplicate() {
        ArrayList<Complement> copyComplement = null;
        Subcat copySubcat = null;

        if (complement != null) {
            copyComplement = new ArrayList();
            for (Complement c : complement) {
                if (c == null) {
                    copyComplement.add(null);
                } else {
                    copyComplement.add((Complement) c.duplicate());
                }
            }
        }
        if (subcat != null) {
            copySubcat = (Subcat) subcat.duplicate();
        }

        return new SuperAdjective(super.getHead(), this.path, this.pathNo, copyComplement, copySubcat, this.serialNumber);
    }

    @Override
    public String toString() {
        String sComplement;
        if (complement.isEmpty()) {
            sComplement = "[Complement null]";
        } else if (complement.size() == 1) {
            sComplement = complement.get(0).toString();
        } else {
            sComplement = complement.toString();
        }

        if (Settings.printCode == 0) {
            return "[SuperAdjective " + sComplement + " " + "[BaseAdjective " + super.getHead() + "]" + "]";
        } else if (Settings.printCode == 1) {
            if (complement.isEmpty()) {
                sComplement = "";
            } else {
                sComplement += " ";
            }
            return  sComplement + super.getHead();
        } else if (Settings.printCode == 2) {
            String separ = " | ", comp = "Complement:", s = "Adjective:";
            ArrayList<Role> roleList = new ArrayList();

            if (!complement.isEmpty()) {
                for (Complement c : complement) {
                    roleList.add(c);
                }
            }

            Collections.sort(roleList, new Comparator<Role>() {
                @Override
                public int compare(Role role1, Role role2) {
                    if (role1.getSerialNumber() > role2.getSerialNumber()) {
                        return -1;
                    } else if (role1.getSerialNumber() < role2.getSerialNumber()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            String result = "";
            for (Role r : roleList) {
                if (r instanceof Complement) {
                    result += comp + r + separ;
                }
            }
            return result + s + super.getHead().toString();
        }  else if (Settings.printCode == 3) {
            String separ = " ", comp = "(C)", s = "";
            ArrayList<Role> roleList = new ArrayList();

            if (!complement.isEmpty()) {
                for (Complement c : complement) {
                    roleList.add(c);
                }
            }

            Collections.sort(roleList, new Comparator<Role>() {
                @Override
                public int compare(Role role1, Role role2) {
                    if (role1.getSerialNumber() > role2.getSerialNumber()) {
                        return -1;
                    } else if (role1.getSerialNumber() < role2.getSerialNumber()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            String result = "";
            for (Role r : roleList) {
                if (r instanceof Complement) {
                    result += comp + r + separ;
                }
            }

            return result + s + super.getHead().toString();
        } else if (Settings.printCode == 4) {

            String separ = " ", comp = "", s = "";
            ArrayList<Role> roleList = new ArrayList();

            if (!complement.isEmpty()) {
                for (Complement c : complement) {
                    roleList.add(c);
                }
            }

            Collections.sort(roleList, new Comparator<Role>() {
                @Override
                public int compare(Role role1, Role role2) {
                    if (role1.getSerialNumber() > role2.getSerialNumber()) {
                        return -1;
                    } else if (role1.getSerialNumber() < role2.getSerialNumber()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            String result = "";
            for (Role r : roleList) {
                if (r instanceof Complement) {
                    result += comp + r + separ;
                }
            }

            return result + s + super.getHead().toString();
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
        return ((Function)super.getHead()).getDelegate();
    }

    @Override
    public boolean checkMandatory() {
        if (complement != null) {
            for (Complement comp : complement) {
                if (!comp.getContent().checkMandatory()) {
                    return false;
                }
            }
        }
        if (!((Function) getHead()).checkMandatory()) {
            return false;
        }
        if (subcat.isMandatory() && !subcat.isOk()) {
            return false;
        }
        return true;
    }

    public Subcat getSubcat() {
        return subcat;
    }

    public ArrayList<Complement> getComplement() {
        return complement;
    }

    public void addComplement(Complement c) {
        complement.add(c);
    }
}
