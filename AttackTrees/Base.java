class Base {
    /*@ specification Base {
    double Gains;
    double Costs;
    double p;
    double avPen;
    double avPenC;
    double Outcome;
    String Description;
    String Selected;
    boolean oSelected;

    Outcome = -Costs + p*(Gains - avPen) -(1-p)*avPenC;

    oSelected -> Selected {setSelected};
    }@*/

    public String setSelected(boolean selected) {
        if(selected) return "*";
        return null;
    }
}


