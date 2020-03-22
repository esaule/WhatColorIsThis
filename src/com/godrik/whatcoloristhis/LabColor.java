package com.godrik.whatcoloristhis;


class LabColor {
    public double l;
    public double a;
    public double b;

    public LabColor(){}
    public LabColor(double l, double a, double b) {
	this.l = l;
	this.a = a;
	this.b = b;
    }
	
    //
    // @brief This function converts a color in RGB to CIE's LAB.
    //
    // Internally, this function converts first to XYZ space.
    // This function is based upon: https://github.com/antimatter15/rgb-lab
    //
    // @param rgb: 32-bit argb encoding (ie, 4 8-bit channels encoded
    // in one int). This function ignores the alpha component.
    public static LabColor rgb2lab(int rgb) {
	double r = ((rgb & 0x00FF0000)>>16) / 255.0;
	double g = ((rgb & 0x0000FF00)>>8) / 255.0;
	double b = ((rgb & 0x000000FF)) / 255.0;
	    
	r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.4) : r / 12.92;
	g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.4) : g / 12.92;
	b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.4) : b / 12.92;
	    
	double x = (r * 0.4124 + g * 0.3576 + b * 0.1805) / 0.95047;
	double y = (r * 0.2126 + g * 0.7152 + b * 0.0722) / 1.00000;
	double z = (r * 0.0193 + g * 0.1192 + b * 0.9505) / 1.08883;
	    
	x = (x > 0.008856) ? Math.pow(x, 1.0/3.0) : (7.787 * x) + 16.0 / 116.0;
	y = (y > 0.008856) ? Math.pow(y, 1.0/3.0) : (7.787 * y) + 16.0 / 116.0;
	z = (z > 0.008856) ? Math.pow(z, 1.0/3.0) : (7.787 * z) + 16.0 / 116.0;
	    
	double lab_l = (116.0 * y) - 16.0;
	double lab_a = 500.0 * (x - y);
	double lab_b = 200.0 * (y - z);
	LabColor lab = new LabColor(lab_l, lab_a, lab_b);
	return lab;
    }

    //
    // @brief This function computes distance between two colors in
    // LAB colorspace
    //
    // This function is based upon: https://github.com/antimatter15/rgb-lab
    //
    // @return distance based on deltaE 1994
    public static double deltaE(LabColor a, LabColor b) {
	
	double c1 = Math.sqrt(a.a * a.a + a.b * a.b);
	double c2 = Math.sqrt(b.a * b.a + b.b * b.b);
	double deltaC = c1 - c2;
	double sl = 1.0;
	double sc = 1.0 + 0.045 * c1;
	double sh = 1.0 + 0.015 * c1;
	
	
	double deltaL = a.l - b.l;
	double deltaA = a.a - b.a;
	double deltaB = a.b - b.b;
	double deltaH = deltaA * deltaA + deltaB * deltaB - deltaC * deltaC;
	deltaH = ((deltaH < 0.0) ? 0.0 : Math.sqrt(deltaH));

	double deltaLKlsl = deltaL / (sl);
	double deltaCkcsc = deltaC / (sc);
	double deltaHkhsh = deltaH / (sh);

	double i = deltaLKlsl * deltaLKlsl + deltaCkcsc * deltaCkcsc + deltaHkhsh * deltaHkhsh;
	return ((i < 0.0) ? 0.0 : Math.sqrt(i));
    }
	
}
