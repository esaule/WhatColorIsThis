package com.godrik.whatcoloristhis;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


class ColorTeller {


    	private static final Map<String, String> colorNames;
	// CSS colors  - complete list
	static {
	    Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("aliceblue", "#f0f8ff");
		tempMap.put("antiquewhite", "#faebd7");
		tempMap.put("cyan", "#00ffff");
		tempMap.put("aquamarine", "#7fffd4");
		tempMap.put("azure", "#f0ffff");
		tempMap.put("beige", "#f5f5dc");
		tempMap.put("bisque", "#ffe4c4");
		tempMap.put("black", "#000000");
		tempMap.put("blanchedalmond", "#ffebcd");
		tempMap.put("blue", "#0000ff");
		tempMap.put("blueviolet", "#8a2be2");
		tempMap.put("brown", "#a52a2a");
		tempMap.put("burlywood", "#deb887");
		tempMap.put("cadetblue", "#5f9ea0");
		tempMap.put("chartreuse", "#7fff00");
		tempMap.put("chocolate", "#d2691e");
		tempMap.put("coral", "#ff7f50");
		tempMap.put("cornflowerblue", "#6495ed");
		tempMap.put("cornsilk", "#fff8dc");
		tempMap.put("crimson", "#dc143c");
		tempMap.put("darkblue", "#00008b");
		tempMap.put("darkcyan", "#008b8b");
		tempMap.put("darkgoldenrod", "#b8860b");
		tempMap.put("darkgrey", "#a9a9a9");
		tempMap.put("darkgray", "#a9a9a9");
		tempMap.put("darkgreen", "#006400");
		tempMap.put("darkkhaki", "#bdb76b");
		tempMap.put("darkmagenta", "#8b008b");
		tempMap.put("darkolivegreen", "#556b2f");
		tempMap.put("darkorange", "#ff8c00");
		tempMap.put("darkorchid", "#9932cc");
		tempMap.put("darkred", "#8b0000");
		tempMap.put("darksalmon", "#e9967a");
		tempMap.put("darkseagreen", "#8fbc8f");
		tempMap.put("darkslateblue", "#483d8b");
		tempMap.put("darkslategrey", "#2f4f4f");
		tempMap.put("darkslategray", "#2f4f4f");
		tempMap.put("darkturquoise", "#00ced1");
		tempMap.put("darkviolet", "#9400d3");
		tempMap.put("deeppink", "#ff1493");
		tempMap.put("deepskyblue", "#00bfff");
		tempMap.put("dimgrey", "#696969");
		tempMap.put("dimgray", "#696969");
		tempMap.put("dodgerblue", "#1e90ff");
		tempMap.put("firebrick", "#b22222");
		tempMap.put("floralwhite", "#fffaf0");
		tempMap.put("forestgreen", "#228b22");
		tempMap.put("magenta", "#ff00ff");
		tempMap.put("gainsboro", "#dcdcdc");
		tempMap.put("ghostwhite", "#f8f8ff");
		tempMap.put("gold", "#ffd700");
		tempMap.put("goldenrod", "#daa520");
		tempMap.put("grey", "#808080");
		tempMap.put("gray", "#808080");
		tempMap.put("green", "#008000");
		tempMap.put("greenyellow", "#adff2f");
		tempMap.put("honeydew", "#f0fff0");
		tempMap.put("hotpink", "#ff69b4");
		tempMap.put("indianred", "#cd5c5c");
		tempMap.put("indigo", "#4b0082");
		tempMap.put("ivory", "#fffff0");
		tempMap.put("khaki", "#f0e68c");
		tempMap.put("lavender", "#e6e6fa");
		tempMap.put("lavenderblush", "#fff0f5");
		tempMap.put("lawngreen", "#7cfc00");
		tempMap.put("lemonchiffon", "#fffacd");
		tempMap.put("lightblue", "#add8e6");
		tempMap.put("lightcoral", "#f08080");
		tempMap.put("lightcyan", "#e0ffff");
		tempMap.put("lightgoldenrodyellow", "#fafad2");
		tempMap.put("lightgrey", "#d3d3d3");
		tempMap.put("lightgray", "#d3d3d3");
		tempMap.put("lightgreen", "#90ee90");
		tempMap.put("lightpink", "#ffb6c1");
		tempMap.put("lightsalmon", "#ffa07a");
		tempMap.put("lightseagreen", "#20b2aa");
		tempMap.put("lightskyblue", "#87cefa");
		tempMap.put("lightslategrey", "#778899");
		tempMap.put("lightsteelblue", "#b0c4de");
		tempMap.put("lightyellow", "#ffffe0");
		tempMap.put("lime", "#00ff00");
		tempMap.put("limegreen", "#32cd32");
		tempMap.put("linen", "#faf0e6");
		tempMap.put("maroon", "#800000");
		tempMap.put("mediumaquamarine", "#66cdaa");
		tempMap.put("mediumblue", "#0000cd");
		tempMap.put("mediumorchid", "#ba55d3");
		tempMap.put("mediumpurple", "#9370db");
		tempMap.put("mediumseagreen", "#3cb371");
		tempMap.put("mediumslateblue", "#7b68ee");
		tempMap.put("mediumspringgreen", "#00fa9a");
		tempMap.put("mediumturquoise", "#48d1cc");
		tempMap.put("mediumvioletred", "#c71585");
		tempMap.put("midnightblue", "#191970");
		tempMap.put("mintcream", "#f5fffa");
		tempMap.put("mistyrose", "#ffe4e1");
		tempMap.put("moccasin", "#ffe4b5");
		tempMap.put("navajowhite", "#ffdead");
		tempMap.put("navy", "#000080");
		tempMap.put("oldlace", "#fdf5e6");
		tempMap.put("olive", "#808000");
		tempMap.put("olivedrab", "#6b8e23");
		tempMap.put("orange", "#ffa500");
		tempMap.put("orangered", "#ff4500");
		tempMap.put("orchid", "#da70d6");
		tempMap.put("palegoldenrod", "#eee8aa");
		tempMap.put("palegreen", "#98fb98");
		tempMap.put("paleturquoise", "#afeeee");
		tempMap.put("palevioletred", "#db7093");
		tempMap.put("papayawhip", "#ffefd5");
		tempMap.put("peachpuff", "#ffdab9");
		tempMap.put("peru", "#cd853f");
		tempMap.put("pink", "#ffc0cb");
		tempMap.put("plum", "#dda0dd");
		tempMap.put("powderblue", "#b0e0e6");
		tempMap.put("purple", "#800080");
		tempMap.put("red", "#ff0000");
		tempMap.put("rosybrown", "#bc8f8f");
		tempMap.put("royalblue", "#4169e1");
		tempMap.put("saddlebrown", "#8b4513");
		tempMap.put("salmon", "#fa8072");
		tempMap.put("sandybrown", "#f4a460");
		tempMap.put("seagreen", "#2e8b57");
		tempMap.put("seashell", "#fff5ee");
		tempMap.put("sienna", "#a0522d");
		tempMap.put("silver", "#c0c0c0");
		tempMap.put("skyblue", "#87ceeb");
		tempMap.put("slateblue", "#6a5acd");
		tempMap.put("slategrey", "#708090");
		tempMap.put("slategray", "#708090");
		tempMap.put("snow", "#fffafa");
		tempMap.put("springgreen", "#00ff7f");
		tempMap.put("steelblue", "#4682b4");
		tempMap.put("tan", "#d2b48c");
		tempMap.put("teal", "#008080");
		tempMap.put("thistle", "#d8bfd8");
		tempMap.put("tomato", "#ff6347");
		tempMap.put("turquoise", "#40e0d0");
		tempMap.put("violet", "#ee82ee");
		tempMap.put("wheat", "#f5deb3");
		tempMap.put("white", "#ffffff");
		tempMap.put("whitesmoke", "#f5f5f5");
		tempMap.put("yellow", "#ffff00");
		tempMap.put("yellowgreen", "#9acd32");

		colorNames = Collections.unmodifiableMap(tempMap);
	}


    private int colorFromHex(String hexStr) throws IllegalArgumentException {
	if (hexStr.length() != 7 || !hexStr.startsWith("#")) {
	    throw new IllegalArgumentException("Hex colors must follow the format '#000000' through '#FFFFFF");
	}
	int red = Integer.valueOf(hexStr.substring(1, 3), 16);
	int green = Integer.valueOf(hexStr.substring(3, 5), 16);
	int blue = Integer.valueOf(hexStr.substring(5, 7), 16);

	//System.out.println("rgb="+red+" "+green+" "+blue);
	
	return (red<<16)+(green<<8)+blue;
    }

    private float distanceRGB(int rgb1, int rgb2) {
	int rdiff = ((rgb1&0x00FF0000)>>16) - ((rgb2&0x00FF0000)>>16);
	int gdiff = ((rgb1&0x0000FF00)>>8)  - ((rgb2&0x0000FF00)>>8);
	int bdiff = ((rgb1&0x000000FF))     - ((rgb2&0x000000FF));

	//System.out.println("diff=" + rdiff+ " "+gdiff+" "+bdiff);
	
	return (float)Math.sqrt((double)(rdiff*rdiff+gdiff*gdiff+bdiff*bdiff));
    }


    
    

    
    public String tell(int rgb) {

	float dist = 256*256*3;
	String guess = "";

	for (String name : colorNames.keySet()) {
	    String val = colorNames.get(name);

	    //System.out.println("considering "+name+"="+val+" "+rgb );

	    int valrgb = colorFromHex(val);

	    float locdist = distanceRGB(rgb, valrgb);

	    //System.out.println("valrgb="+valrgb+" distance="+locdist);
	    
	    if (locdist < dist) {
		//System.out.println("new dist: "+locdist);
		//System.out.println("");
		guess = name;
		dist = locdist;
	    }
	}
			
	return guess;
    }

    // public static void main(String args[]) {
    // 	ColorTeller ct = new ColorTeller();

    // 	System.out.println(ct.tell(0x00FF00FF));
    // }
}
