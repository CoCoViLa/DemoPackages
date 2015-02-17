class And extends Base {
    /*@ specification And super Base {
    double aGains, aCosts, ap, aAvPen, aAvPenC, aOutcome;
    double bGains, bCosts, bp, bAvPen, bAvPenC, bOutcome;
    String aDescription, bDescription;

    alias a = (Gains, aCosts, ap, aAvPen, aAvPenC, aOutcome, aDescription, oSelected);
    alias b = (Gains, bCosts, bp, bAvPen, bAvPenC, bOutcome, bDescription, oSelected);
    alias out = (Gains, Costs, p, avPen, avPenC, Outcome, Description, oSelected);
    
    Costs = aCosts + bCosts;
    p = ap * bp;
    avPen = aAvPen + bAvPen;
    avPenC = ( ap*(1-bp)*(aAvPen+bAvPenC) + (1-ap)*bp*(aAvPenC+bAvPen) + (1-ap)*(1-bp)*(aAvPenC+bAvPenC) ) / ( 1-ap*bp );
    
    aDescription, bDescription -> Description {stringAnd};

    }@*/

    public String stringAnd(String a, String b) {
        return aDescription + "&" + bDescription;
    }
}







