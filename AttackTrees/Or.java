class Or extends Base {
    /*@ specification Or super Base {
    double aGains, aCosts, ap, aAvPen, aAvPenC, aOutcome;
    double bGains, bCosts, bp, bAvPen, bAvPenC, bOutcome;
    String aDescription, bDescription;
    boolean aSelected, bSelected;

    alias a = (Gains, aCosts, ap, aAvPen, aAvPenC, aOutcome, aDescription, aSelected);
    alias b = (Gains, bCosts, bp, bAvPen, bAvPenC, bOutcome, bDescription, bSelected);
    alias out = (Gains, Costs, p, avPen, avPenC, Outcome, Description, oSelected);

    aOutcome, bOutcome, aCosts, bCosts -> Costs {selectValue};
    aOutcome, bOutcome, ap, bp -> p {selectValue};
    aOutcome, bOutcome, aAvPen, bAvPen -> avPen {selectValue};
    aOutcome, bOutcome, aAvPenC, bAvPenC -> avPenC {selectValue};
    
    aDescription, bDescription -> Description {stringOr};

    aOutcome, bOutcome, oSelected -> aSelected {aSelectedValue};
    aOutcome, bOutcome, oSelected -> bSelected {bSelectedValue};
    }@*/

    public double selectValue(double aOutcome, double bOutcome, double aValue, double bValue) {
        if(aOutcome > bOutcome) return aValue;
        return bValue;
    }

    public String stringOr(String a, String b) {
        return "(" + aDescription + "+" + bDescription + ")";
    }

    public boolean aSelectedValue(double a, double b, boolean selected) {
        if(selected && (a>b)) return true;
        return false;
    }
 
    public boolean bSelectedValue(double a, double b, boolean selected) {
        if(selected && (a<=b)) return true;
        return false;
    }
}













