package deprecated.solids_2d.material;

public enum Materials {

    Thallium(8, 11.8, 0.675, 0.057, 0.00481),
    Cesium(1.7, 1.88, 0.905, 0.481, 0.256),
    Arsenic(8, 5.73, 1.4, 0.244, 0.0426),
    Lead(16, 11.3, 1.41, 0.124, 0.011),
    Indium(11, 7.31, 1.5, 0.206, 0.0282),
    Rubidium(2.4, 1.53, 1.57, 1.02, 0.667),
    Selenium(10, 4.82, 2.08, 0.431, 0.0894),
    Bismuth(32, 9.78, 3.27, 0.335, 0.0342),
    Europium(18, 5.24, 3.43, 0.655, 0.125),
    Ytterbium(24, 6.57, 3.65, 0.556, 0.0846),
    Barium(13, 3.51, 3.7, 1.06, 0.301),
    Gold(78, 19.3, 4.04, 0.209, 0.0108),
    Plutonium(96, 19.8, 4.84, 0.244, 0.0123),
    Cerium(34, 6.69, 5.08, 0.76, 0.114),
    Praseodymium(37, 6.64, 5.57, 0.839, 0.126),
    Cadmium(50, 8.65, 5.78, 0.668, 0.0773),
    Neodymium(41, 7.01, 5.85, 0.834, 0.119),
    Hafnium(78, 13.3, 5.86, 0.44, 0.0331),
    Lanthanum(37, 6.15, 6.02, 0.98, 0.159),
    Promethium(46, 7.26, 6.33, 0.872, 0.12),
    Thorium(79, 11.7, 6.74, 0.575, 0.049),
    Samarium(50, 7.35, 6.8, 0.925, 0.126),
    Lutetium(67, 9.84, 6.81, 0.692, 0.0703),
    Terbium(56, 8.22, 6.81, 0.829, 0.101),
    Tin(50, 7.31, 6.84, 0.936, 0.128),
    Tellurium(43, 6.24, 6.89, 1.1, 0.177),
    Gadolinium(55, 7.9, 6.96, 0.881, 0.112),
    Dysprosium(61, 8.55, 7.13, 0.834, 0.0976),
    Holmium(64, 8.79, 7.28, 0.827, 0.0941),
    Erbium(70, 9.07, 7.72, 0.852, 0.0939),
    Platinum(168, 21.4, 7.83, 0.365, 0.017),
    Thulium(74, 9.32, 7.94, 0.852, 0.0914),
    Silver(85, 10.5, 8.1, 0.772, 0.0736),
    Antimony(55, 6.7, 8.21, 1.23, 0.183),
    Lithium(4.9, 0.535, 9.16, 17.1, 32),
    Palladium(121, 12, 10.1, 0.837, 0.0696),
    Zirconium(67, 6.51, 10.3, 1.58, 0.243),
    Sodium(10, 0.968, 10.3, 10.7, 11),
    Uranium(208, 19.1, 10.9, 0.573, 0.0301),
    Tantalum(186, 16.6, 11.2, 0.671, 0.0403),
    Niobium(105, 8.57, 12.3, 1.43, 0.167),
    Calcium(20, 1.55, 12.9, 8.32, 5.37),
    Yttrium(64, 4.47, 14.3, 3.2, 0.716),
    Copper(130, 8.96, 14.5, 1.62, 0.181),
    Zinc(108, 7.14, 15.1, 2.12, 0.297),
    Silicon(47, 2.33, 20.2, 8.66, 3.72),
    Vanadium(128, 6.11, 20.9, 3.43, 0.561),
    Tungsten(411, 19.2, 21.4, 1.11, 0.0576),
    Rhenium(463, 21, 22, 1.05, 0.0499),
    Rhodium(275, 12.4, 22.1, 1.77, 0.143),
    Nickel(200, 8.91, 22.5, 2.52, 0.283),
    Iridium(528, 22.6, 23.4, 1.04, 0.046),
    Cobalt(209, 8.9, 23.5, 2.64, 0.296),
    Scandium(74, 2.98, 24.8, 8.31, 2.78),
    Titanium(116, 4.51, 25.7, 5.71, 1.27),
    Magnesium(45, 1.74, 25.9, 14.9, 8.57),
    Aluminum(70, 2.7, 25.9, 9.6, 3.56),
    Manganese(198, 7.47, 26.5, 3.55, 0.475),
    Iron(211, 7.87, 26.8, 3.4, 0.432),
    Molybdenum(329, 10.3, 32, 3.11, 0.303),
    Ruthenium(447, 12.4, 36.1, 2.92, 0.236),
    Chromium(279, 7.19, 38.8, 5.4, 0.751),
    Beryllium(287, 1.85, 155, 84, 45.5);

    double e_module;
    double density;
    double specific_stiffness;
    double e_over_density_squared;
    double e_over_density_cubed;


    Materials(double i, double v, double v1, double v2, double v3) {
        e_module = i;
        density = v;
        specific_stiffness = v1;
        e_over_density_squared = v2;
        e_over_density_cubed = v3;
    }

    public void setE_module(double e_module) {
        this.e_module = e_module;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setSpecific_stiffness(double specific_stiffness) {
        this.specific_stiffness = specific_stiffness;
    }

    public void setE_over_density_squared(double e_over_density_squared) {
        this.e_over_density_squared = e_over_density_squared;
    }

    public void setE_over_density_cubed(double e_over_density_cubed) {
        this.e_over_density_cubed = e_over_density_cubed;
    }

    public Material gen_material(Materials m){
        return new Material(m.e_module,0.25);
    }
}
