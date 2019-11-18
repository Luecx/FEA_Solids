package solids_2d.material;

public enum Materials {

    Aluminum(69,0.33),
    Aluminum_Bronze(	120,0.30),
    Beryllium(	287,0.03),
    Bronze(	110,0.34),
    Copper(	117,0.36),
    Gold(74	,0.42),
    Magnesium(	45,0.35),
    Molybdenum(329	,0.32),
    Nickel(	170,0.31),
    Platinum(160	,0.39),
    Plutonium(	97,0.18),
    Silver(	72,0.37),
    StainlessSteel304L(	200,0.3),
    Tin(	47,0.33),
    Titanium(105	,0.3),
    Tungsten(	400,0.28),
    Uranium(	170,0.21);

    double young;
    double poisson;

    Materials(double young, double poisson) {
        this.young = young;
        this.poisson = poisson;
    }

    public double getYoung() {
        return young;
    }

    public void setYoung(double young) {
        this.young = young;
    }

    public double getPoisson() {
        return poisson;
    }

    public void setPoisson(double poisson) {
        this.poisson = poisson;
    }

    public Material gen_material() {
        return new Material(young * 1E6, 0.25);
    }
}
