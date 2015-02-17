class Root extends Base {
    /*@ specification Root super Base {
    alias in = (Gains, Costs, p, avPen, avPenC, Outcome, Description, oSelected);

    Outcome -> oSelected {selectedValue};

    }@*/

    public boolean selectedValue(double outcome) {
        if(outcome > 0) return true;
        return false;
    }
}




