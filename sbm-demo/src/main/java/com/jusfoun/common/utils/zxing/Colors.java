package com.jusfoun.common.utils.zxing;

import java.util.Random;

/**
 * 说明：
 * 常用的RGB值和颜色对照表，参考：<a>http://blog.csdn.net/zhuimengzh/article/details/6953155</a>.
 * <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月14日 下午3:36:26
 */
public enum Colors {
	Snow(255, 250, 250, 0xFFFFFAFA), //
	GhostWhite(248, 248, 255, 0xFFF8F8FF), //
	WhiteSmoke(245, 245, 245, 0xFFF5F5F5), //
	Gainsboro(220, 220, 220, 0xFFDCDCDC), //
	FloralWhite(255, 250, 240, 0xFFFFFAF0), //
	OldLace(253, 245, 230, 0xFFFDF5E6), //
	Linen(250, 240, 230, 0xFFFAF0E6), //
	AntiqueWhite(250, 235, 215, 0xFFFAEBD7), //
	PapayaWhip(255, 239, 213, 0xFFFFEFD5), //
	BlanchedAlmond(255, 235, 205, 0xFFFFEBCD), //
	Bisque(255, 228, 196, 0xFFFFE4C4), //
	PeachPuff(255, 218, 185, 0xFFFFDAB9), //
	NavajoWhite(255, 222, 173, 0xFFFFDEAD), //
	Moccasin(255, 228, 181, 0xFFFFE4B5), //
	Cornsilk(255, 248, 220, 0xFFFFF8DC), //
	Ivory(255, 255, 240, 0xFFFFFFF0), //
	LemonChiffon(255, 250, 205, 0xFFFFFACD), //
	Seashell(255, 245, 238, 0xFFFFF5EE), //
	Honeydew(240, 255, 240, 0xFFF0FFF0), //
	MintCream(245, 255, 250, 0xFFF5FFFA), //
	Azure(240, 255, 255, 0xFFF0FFFF), //
	AliceBlue(240, 248, 255, 0xFFF0F8FF), //
	lavender(230, 230, 250, 0xFFE6E6FA), //
	LavenderBlush(255, 240, 245, 0xFFFFF0F5), //
	MistyRose(255, 228, 225, 0xFFFFE4E1), //
	White(255, 255, 255, 0xFFFFFFFF), //
	Black(0, 0, 0, 0xFF000000), //
	DarkSlateGray(47, 79, 79, 0xFF2F4F4F), //
	DimGrey(105, 105, 105, 0xFF696969), //
	SlateGrey(112, 128, 144, 0xFF708090), //
	LightSlateGray(119, 136, 153, 0xFF778899), //
	Grey(190, 190, 190, 0xFFBEBEBE), //
	LightGray(211, 211, 211, 0xFFD3D3D3), //
	MidnightBlue(25, 25, 112, 0xFF191970), //
	NavyBlue(0, 0, 128, 0xFF000080), //
	CornflowerBlue(100, 149, 237, 0xFF6495ED), //
	DarkSlateBlue(72, 61, 139, 0xFF483D8B), //
	SlateBlue(106, 90, 205, 0xFF6A5ACD), //
	MediumSlateBlue(123, 104, 238, 0xFF7B68EE), //
	LightSlateBlue(132, 112, 255, 0xFF8470FF), //
	MediumBlue(0, 0, 205, 0xFF0000CD), //
	RoyalBlue(65, 105, 225, 0xFF4169E1), //
	Blue(0, 0, 255, 0xFF0000FF), //
	DodgerBlue(30, 144, 255, 0xFF1E90FF), //
	DeepSkyBlue(0, 191, 255, 0xFF00BFFF), //
	SkyBlue(135, 206, 235, 0xFF87CEEB), //
	LightSkyBlue(135, 206, 250, 0xFF87CEFA), //
	SteelBlue(70, 130, 180, 0xFF4682B4), //
	LightSteelBlue(176, 196, 222, 0xFFB0C4DE), //
	LightBlue(173, 216, 230, 0xFFADD8E6), //
	PowderBlue(176, 224, 230, 0xFFB0E0E6), //
	PaleTurquoise(175, 238, 238, 0xFFAFEEEE), //
	DarkTurquoise(0, 206, 209, 0xFF00CED1), //
	MediumTurquoise(72, 209, 204, 0xFF48D1CC), //
	Turquoise(64, 224, 208, 0xFF40E0D0), //
	Cyan(0, 255, 255, 0xFF00FFFF), //
	LightCyan(224, 255, 255, 0xFFE0FFFF), //
	CadetBlue(95, 158, 160, 0xFF5F9EA0), //
	MediumAquamarine(102, 205, 170, 0xFF66CDAA), //
	Aquamarine(127, 255, 212, 0xFF7FFFD4), //
	DarkGreen(0, 100, 0, 0xFF006400), //
	DarkOliveGreen(85, 107, 47, 0xFF556B2F), //
	DarkSeaGreen(143, 188, 143, 0xFF8FBC8F), //
	SeaGreen(46, 139, 87, 0xFF2E8B57), //
	MediumSeaGreen(60, 179, 113, 0xFF3CB371), //
	LightSeaGreen(32, 178, 170, 0xFF20B2AA), //
	PaleGreen(152, 251, 152, 0xFF98FB98), //
	SpringGreen(0, 255, 127, 0xFF00FF7F), //
	LawnGreen(124, 252, 0, 0xFF7CFC00), //
	Green(0, 255, 0, 0xFF00FF00), //
	Chartreuse(127, 255, 0, 0xFF7FFF00), //
	MedSpringGreen(0, 250, 154, 0xFF00FA9A), //
	GreenYellow(173, 255, 47, 0xFFADFF2F), //
	LimeGreen(50, 205, 50, 0xFF32CD32), //
	YellowGreen(154, 205, 50, 0xFF9ACD32), //
	ForestGreen(34, 139, 34, 0xFF228B22), //
	OliveDrab(107, 142, 35, 0xFF6B8E23), //
	DarkKhaki(189, 183, 107, 0xFFBDB76B), //
	PaleGoldenrod(238, 232, 170, 0xFFEEE8AA), //
	LtGoldenrodYello(250, 250, 210, 0xFFFAFAD2), //
	LightYellow(255, 255, 224, 0xFFFFFFE0), //
	Yellow(255, 255, 0, 0xFFFFFF00), //
	Gold(255, 215, 0, 0xFFFFD700), //
	LightGoldenrod(238, 221, 130, 0xFFEEDD82), //
	Goldenrod(218, 165, 32, 0xFFDAA520), //
	DarkGoldenrod(184, 134, 11, 0xFFB8860B), //
	RosyBrown(188, 143, 143, 0xFFBC8F8F), //
	IndianRed(205, 92, 92, 0xFFCD5C5C), //
	SaddleBrown(139, 69, 19, 0xFF8B4513), //
	Sienna(160, 82, 45, 0xFFA0522D), //
	Peru(205, 133, 63, 0xFFCD853F), //
	Burlywood(222, 184, 135, 0xFFDEB887), //
	Beige(245, 245, 220, 0xFFF5F5DC), //
	Wheat(245, 222, 179, 0xFFF5DEB3), //
	SandyBrown(244, 164, 96, 0xFFF4A460), //
	Tan(210, 180, 140, 0xFFD2B48C), //
	Chocolate(210, 105, 30, 0xFFD2691E), //
	Firebrick(178, 34, 34, 0xFFB22222), //
	Brown(165, 42, 42, 0xFFA52A2A), //
	DarkSalmon(233, 150, 122, 0xFFE9967A), //
	Salmon(250, 128, 114, 0xFFFA8072), //
	LightSalmon(255, 160, 122, 0xFFFFA07A), //
	Orange(255, 165, 0, 0xFFFFA500), //
	DarkOrange(255, 140, 0, 0xFFFF8C00), //
	Coral(255, 127, 80, 0xFFFF7F50), //
	LightCoral(240, 128, 128, 0xFFF08080), //
	Tomato(255, 99, 71, 0xFFFF6347), //
	OrangeRed(255, 69, 0, 0xFFFF4500), //
	Red(255, 0, 0, 0xFFFF0000), //
	HotPink(255, 105, 180, 0xFFFF69B4), //
	DeepPink(255, 20, 147, 0xFFFF1493), //
	Pink(255, 192, 203, 0xFFFFC0CB), //
	LightPink(255, 182, 193, 0xFFFFB6C1), //
	PaleVioletRed(219, 112, 147, 0xFFDB7093), //
	Maroon(176, 48, 96, 0xFFB03060), //
	MediumVioletRed(199, 21, 133, 0xFFC71585), //
	VioletRed(208, 32, 144, 0xFFD02090), //
	Magenta(255, 0, 255, 0xFFFF00FF), //
	Violet(238, 130, 238, 0xFFEE82EE), //
	Plum(221, 160, 221, 0xFFDDA0DD), //
	Orchid(218, 112, 214, 0xFFDA70D6), //
	MediumOrchid(186, 85, 211, 0xFFBA55D3), //
	DarkOrchid(153, 50, 204, 0xFF9932CC), //
	DarkViolet(148, 0, 211, 0xFF9400D3), //
	BlueViolet(138, 43, 226, 0xFF8A2BE2), //
	Purple(160, 32, 240, 0xFFA020F0), //
	MediumPurple(147, 112, 219, 0xFF9370DB), //
	Thistle(216, 191, 216, 0xFFD8BFD8), //
	Snow1(255, 250, 250, 0xFFFFFAFA), //
	Snow2(238, 233, 233, 0xFFEEE9E9), //
	Snow3(205, 201, 201, 0xFFCDC9C9), //
	Snow4(139, 137, 137, 0xFF8B8989), //
	Seashell1(255, 245, 238, 0xFFFFF5EE), //
	Seashell2(238, 229, 222, 0xFFEEE5DE), //
	Seashell3(205, 197, 191, 0xFFCDC5BF), //
	Seashell4(139, 134, 130, 0xFF8B8682), //
	AntiqueWhite1(255, 239, 219, 0xFFFFEFDB), //
	AntiqueWhite2(238, 223, 204, 0xFFEEDFCC), //
	AntiqueWhite3(205, 192, 176, 0xFFCDC0B0), //
	AntiqueWhite4(139, 131, 120, 0xFF8B8378), //
	Bisque1(255, 228, 196, 0xFFFFE4C4), //
	Bisque2(238, 213, 183, 0xFFEED5B7), //
	Bisque3(205, 183, 158, 0xFFCDB79E), //
	Bisque4(139, 125, 107, 0xFF8B7D6B), //
	PeachPuff1(255, 218, 185, 0xFFFFDAB9), //
	PeachPuff2(238, 203, 173, 0xFFEECBAD), //
	PeachPuff3(205, 175, 149, 0xFFCDAF95), //
	PeachPuff4(139, 119, 101, 0xFF8B7765), //
	NavajoWhite1(255, 222, 173, 0xFFFFDEAD), //
	NavajoWhite2(238, 207, 161, 0xFFEECFA1), //
	NavajoWhite3(205, 179, 139, 0xFFCDB38B), //
	NavajoWhite4(139, 121, 94, 0xFF8B795E), //
	LemonChiffon1(255, 250, 205, 0xFFFFFACD), //
	LemonChiffon2(238, 233, 191, 0xFFEEE9BF), //
	LemonChiffon3(205, 201, 165, 0xFFCDC9A5), //
	LemonChiffon4(139, 137, 112, 0xFF8B8970), //
	Cornsilk1(255, 248, 220, 0xFFFFF8DC), //
	Cornsilk2(238, 232, 205, 0xFFEEE8CD), //
	Cornsilk3(205, 200, 177, 0xFFCDC8B1), //
	Cornsilk4(139, 136, 120, 0xFF8B8878), //
	Ivory1(255, 255, 240, 0xFFFFFFF0), //
	Ivory2(238, 238, 224, 0xFFEEEEE0), //
	Ivory3(205, 205, 193, 0xFFCDCDC1), //
	Ivory4(139, 139, 131, 0xFF8B8B83), //
	Honeydew1(240, 255, 240, 0xFFF0FFF0), //
	Honeydew2(224, 238, 224, 0xFFE0EEE0), //
	Honeydew3(193, 205, 193, 0xFFC1CDC1), //
	Honeydew4(131, 139, 131, 0xFF838B83), //
	LavenderBlush1(255, 240, 245, 0xFFFFF0F5), //
	LavenderBlush2(238, 224, 229, 0xFFEEE0E5), //
	LavenderBlush3(205, 193, 197, 0xFFCDC1C5), //
	LavenderBlush4(139, 131, 134, 0xFF8B8386), //
	MistyRose1(255, 228, 225, 0xFFFFE4E1), //
	MistyRose2(238, 213, 210, 0xFFEED5D2), //
	MistyRose3(205, 183, 181, 0xFFCDB7B5), //
	MistyRose4(139, 125, 123, 0xFF8B7D7B), //
	Azure1(240, 255, 255, 0xFFF0FFFF), //
	Azure2(224, 238, 238, 0xFFE0EEEE), //
	Azure3(193, 205, 205, 0xFFC1CDCD), //
	Azure4(131, 139, 139, 0xFF838B8B), //
	SlateBlue1(131, 111, 255, 0xFF836FFF), //
	SlateBlue2(122, 103, 238, 0xFF7A67EE), //
	SlateBlue3(105, 89, 205, 0xFF6959CD), //
	SlateBlue4(71, 60, 139, 0xFF473C8B), //
	RoyalBlue1(72, 118, 255, 0xFF4876FF), //
	RoyalBlue2(67, 110, 238, 0xFF436EEE), //
	RoyalBlue3(58, 95, 205, 0xFF3A5FCD), //
	RoyalBlue4(39, 64, 139, 0xFF27408B), //
	Blue1(0, 0, 255, 0xFF0000FF), //
	Blue2(0, 0, 238, 0xFF0000EE), //
	Blue3(0, 0, 205, 0xFF0000CD), //
	Blue4(0, 0, 139, 0xFF00008B), //
	DodgerBlue1(30, 144, 255, 0xFF1E90FF), //
	DodgerBlue2(28, 134, 238, 0xFF1C86EE), //
	DodgerBlue3(24, 116, 205, 0xFF1874CD), //
	DodgerBlue4(16, 78, 139, 0xFF104E8B), //
	SteelBlue1(99, 184, 255, 0xFF63B8FF), //
	SteelBlue2(92, 172, 238, 0xFF5CACEE), //
	SteelBlue3(79, 148, 205, 0xFF4F94CD), //
	SteelBlue4(54, 100, 139, 0xFF36648B), //
	DeepSkyBlue1(0, 191, 255, 0xFF00BFFF), //
	DeepSkyBlue2(0, 178, 238, 0xFF00B2EE), //
	DeepSkyBlue3(0, 154, 205, 0xFF009ACD), //
	DeepSkyBlue4(0, 104, 139, 0xFF00688B), //
	SkyBlue1(135, 206, 255, 0xFF87CEFF), //
	SkyBlue2(126, 192, 238, 0xFF7EC0EE), //
	SkyBlue3(108, 166, 205, 0xFF6CA6CD), //
	SkyBlue4(74, 112, 139, 0xFF4A708B), //
	LightSkyBlue1(176, 226, 255, 0xFFB0E2FF), //
	LightSkyBlue2(164, 211, 238, 0xFFA4D3EE), //
	LightSkyBlue3(141, 182, 205, 0xFF8DB6CD), //
	LightSkyBlue4(96, 123, 139, 0xFF607B8B), //
	SlateGray1(198, 226, 255, 0xFFC6E2FF), //
	SlateGray2(185, 211, 238, 0xFFB9D3EE), //
	SlateGray3(159, 182, 205, 0xFF9FB6CD), //
	SlateGray4(108, 123, 139, 0xFF6C7B8B), //
	LightSteelBlue1(202, 225, 255, 0xFFCAE1FF), //
	LightSteelBlue2(188, 210, 238, 0xFFBCD2EE), //
	LightSteelBlue3(162, 181, 205, 0xFFA2B5CD), //
	LightSteelBlue4(110, 123, 139, 0xFF6E7B8B), //
	LightBlue1(191, 239, 255, 0xFFBFEFFF), //
	LightBlue2(178, 223, 238, 0xFFB2DFEE), //
	LightBlue3(154, 192, 205, 0xFF9AC0CD), //
	LightBlue4(104, 131, 139, 0xFF68838B), //
	LightCyan1(224, 255, 255, 0xFFE0FFFF), //
	LightCyan2(209, 238, 238, 0xFFD1EEEE), //
	LightCyan3(180, 205, 205, 0xFFB4CDCD), //
	LightCyan4(122, 139, 139, 0xFF7A8B8B), //
	PaleTurquoise1(187, 255, 255, 0xFFBBFFFF), //
	PaleTurquoise2(174, 238, 238, 0xFFAEEEEE), //
	PaleTurquoise3(150, 205, 205, 0xFF96CDCD), //
	PaleTurquoise4(102, 139, 139, 0xFF668B8B), //
	CadetBlue1(152, 245, 255, 0xFF98F5FF), //
	CadetBlue2(142, 229, 238, 0xFF8EE5EE), //
	CadetBlue3(122, 197, 205, 0xFF7AC5CD), //
	CadetBlue4(83, 134, 139, 0xFF53868B), //
	Turquoise1(0, 245, 255, 0xFF00F5FF), //
	Turquoise2(0, 229, 238, 0xFF00E5EE), //
	Turquoise3(0, 197, 205, 0xFF00C5CD), //
	Turquoise4(0, 134, 139, 0xFF00868B), //
	Cyan1(0, 255, 255, 0xFF00FFFF), //
	Cyan2(0, 238, 238, 0xFF00EEEE), //
	Cyan3(0, 205, 205, 0xFF00CDCD), //
	Cyan4(0, 139, 139, 0xFF008B8B), //
	DarkSlateGray1(151, 255, 255, 0xFF97FFFF), //
	DarkSlateGray2(141, 238, 238, 0xFF8DEEEE), //
	DarkSlateGray3(121, 205, 205, 0xFF79CDCD), //
	DarkSlateGray4(82, 139, 139, 0xFF528B8B), //
	Aquamarine1(127, 255, 212, 0xFF7FFFD4), //
	Aquamarine2(118, 238, 198, 0xFF76EEC6), //
	Aquamarine3(102, 205, 170, 0xFF66CDAA), //
	Aquamarine4(69, 139, 116, 0xFF458B74), //
	DarkSeaGreen1(193, 255, 193, 0xFFC1FFC1), //
	DarkSeaGreen2(180, 238, 180, 0xFFB4EEB4), //
	DarkSeaGreen3(155, 205, 155, 0xFF9BCD9B), //
	DarkSeaGreen4(105, 139, 105, 0xFF698B69), //
	SeaGreen1(84, 255, 159, 0xFF54FF9F), //
	SeaGreen2(78, 238, 148, 0xFF4EEE94), //
	SeaGreen3(67, 205, 128, 0xFF43CD80), //
	SeaGreen4(46, 139, 87, 0xFF2E8B57), //
	PaleGreen1(154, 255, 154, 0xFF9AFF9A), //
	PaleGreen2(144, 238, 144, 0xFF90EE90), //
	PaleGreen3(124, 205, 124, 0xFF7CCD7C), //
	PaleGreen4(84, 139, 84, 0xFF548B54), //
	SpringGreen1(0, 255, 127, 0xFF00FF7F), //
	SpringGreen2(0, 238, 118, 0xFF00EE76), //
	SpringGreen3(0, 205, 102, 0xFF00CD66), //
	SpringGreen4(0, 139, 69, 0xFF008B45), //
	Green1(0, 255, 0, 0xFF00FF00), //
	Green2(0, 238, 0, 0xFF00EE00), //
	Green3(0, 205, 0, 0xFF00CD00), //
	Green4(0, 139, 0, 0xFF008B00), //
	Chartreuse1(127, 255, 0, 0xFF7FFF00), //
	Chartreuse2(118, 238, 0, 0xFF76EE00), //
	Chartreuse3(102, 205, 0, 0xFF66CD00), //
	Chartreuse4(69, 139, 0, 0xFF458B00), //
	OliveDrab1(192, 255, 62, 0xFFC0FF3E), //
	OliveDrab2(179, 238, 58, 0xFFB3EE3A), //
	OliveDrab3(154, 205, 50, 0xFF9ACD32), //
	OliveDrab4(105, 139, 34, 0xFF698B22), //
	DarkOliveGreen1(202, 255, 112, 0xFFCAFF70), //
	DarkOliveGreen2(188, 238, 104, 0xFFBCEE68), //
	DarkOliveGreen3(162, 205, 90, 0xFFA2CD5A), //
	DarkOliveGreen4(110, 139, 61, 0xFF6E8B3D), //
	Khaki1(255, 246, 143, 0xFFFFF68F), //
	Khaki2(238, 230, 133, 0xFFEEE685), //
	Khaki3(205, 198, 115, 0xFFCDC673), //
	Khaki4(139, 134, 78, 0xFF8B864E), //
	LightGoldenrod1(255, 236, 139, 0xFFFFEC8B), //
	LightGoldenrod2(238, 220, 130, 0xFFEEDC82), //
	LightGoldenrod3(205, 190, 112, 0xFFCDBE70), //
	LightGoldenrod4(139, 129, 76, 0xFF8B814C), //
	LightYellow1(255, 255, 224, 0xFFFFFFE0), //
	LightYellow2(238, 238, 209, 0xFFEEEED1), //
	LightYellow3(205, 205, 180, 0xFFCDCDB4), //
	LightYellow4(139, 139, 122, 0xFF8B8B7A), //
	Yellow1(255, 255, 0, 0xFFFFFF00), //
	Yellow2(238, 238, 0, 0xFFEEEE00), //
	Yellow3(205, 205, 0, 0xFFCDCD00), //
	Yellow4(139, 139, 0, 0xFF8B8B00), //
	Gold1(255, 215, 0, 0xFFFFD700), //
	Gold2(238, 201, 0, 0xFFEEC900), //
	Gold3(205, 173, 0, 0xFFCDAD00), //
	Gold4(139, 117, 0, 0xFF8B7500), //
	Goldenrod1(255, 193, 37, 0xFFFFC125), //
	Goldenrod2(238, 180, 34, 0xFFEEB422), //
	Goldenrod3(205, 155, 29, 0xFFCD9B1D), //
	Goldenrod4(139, 105, 20, 0xFF8B6914), //
	DarkGoldenrod1(255, 185, 15, 0xFFFFB90F), //
	DarkGoldenrod2(238, 173, 14, 0xFFEEAD0E), //
	DarkGoldenrod3(205, 149, 12, 0xFFCD950C), //
	DarkGoldenrod4(139, 101, 8, 0xFF8B658B), //
	RosyBrown1(255, 193, 193, 0xFFFFC1C1), //
	RosyBrown2(238, 180, 180, 0xFFEEB4B4), //
	RosyBrown3(205, 155, 155, 0xFFCD9B9B), //
	RosyBrown4(139, 105, 105, 0xFF8B6969), //
	IndianRed1(255, 106, 106, 0xFFFF6A6A), //
	IndianRed2(238, 99, 99, 0xFFEE6363), //
	IndianRed3(205, 85, 85, 0xFFCD5555), //
	IndianRed4(139, 58, 58, 0xFF8B3A3A), //
	Sienna1(255, 130, 71, 0xFFFF8247), //
	Sienna2(238, 121, 66, 0xFFEE7942), //
	Sienna3(205, 104, 57, 0xFFCD6839), //
	Sienna4(139, 71, 38, 0xFF8B4726), //
	Burlywood1(255, 211, 155, 0xFFFFD39B), //
	Burlywood2(238, 197, 145, 0xFFEEC591), //
	Burlywood3(205, 170, 125, 0xFFCDAA7D), //
	Burlywood4(139, 115, 85, 0xFF8B7355), //
	Wheat1(255, 231, 186, 0xFFFFE7BA), //
	Wheat2(238, 216, 174, 0xFFEED8AE), //
	Wheat3(205, 186, 150, 0xFFCDBA96), //
	Wheat4(139, 126, 102, 0xFF8B7E66), //
	Tan1(255, 165, 79, 0xFFFFA54F), //
	Tan2(238, 154, 73, 0xFFEE9A49), //
	Tan3(205, 133, 63, 0xFFCD853F), //
	Tan4(139, 90, 43, 0xFF8B5A2B), //
	Chocolate1(255, 127, 36, 0xFFFF7F24), //
	Chocolate2(238, 118, 33, 0xFFEE7621), //
	Chocolate3(205, 102, 29, 0xFFCD661D), //
	Chocolate4(139, 69, 19, 0xFF8B4513), //
	Firebrick1(255, 48, 48, 0xFFFF3030), //
	Firebrick2(238, 44, 44, 0xFFEE2C2C), //
	Firebrick3(205, 38, 38, 0xFFCD2626), //
	Firebrick4(139, 26, 26, 0xFF8B1A1A), //
	Brown1(255, 64, 64, 0xFFFF4040), //
	Brown2(238, 59, 59, 0xFFEE3B3B), //
	Brown3(205, 51, 51, 0xFFCD3333), //
	Brown4(139, 35, 35, 0xFF8B2323), //
	Salmon1(255, 140, 105, 0xFFFF8C69), //
	Salmon2(238, 130, 98, 0xFFEE8262), //
	Salmon3(205, 112, 84, 0xFFCD7054), //
	Salmon4(139, 76, 57, 0xFF8B4C39), //
	LightSalmon1(255, 160, 122, 0xFFFFA07A), //
	LightSalmon2(238, 149, 114, 0xFFEE9572), //
	LightSalmon3(205, 129, 98, 0xFFCD8162), //
	LightSalmon4(139, 87, 66, 0xFF8B5742), //
	Orange1(255, 165, 0, 0xFFFFA500), //
	Orange2(238, 154, 0, 0xFFEE9A00), //
	Orange3(205, 133, 0, 0xFFCD8500), //
	Orange4(139, 90, 0, 0xFF8B5A00), //
	DarkOrange1(255, 127, 0, 0xFFFF7F00), //
	DarkOrange2(238, 118, 0, 0xFFEE7600), //
	DarkOrange3(205, 102, 0, 0xFFCD6600), //
	DarkOrange4(139, 69, 0, 0xFF8B4500), //
	Coral1(255, 114, 86, 0xFFFF7256), //
	Coral2(238, 106, 80, 0xFFEE6A50), //
	Coral3(205, 91, 69, 0xFFCD5B45), //
	Coral4(139, 62, 47, 0xFF8B3E2F), //
	Tomato1(255, 99, 71, 0xFFFF6347), //
	Tomato2(238, 92, 66, 0xFFEE5C42), //
	Tomato3(205, 79, 57, 0xFFCD4F39), //
	Tomato4(139, 54, 38, 0xFF8B3626), //
	OrangeRed1(255, 69, 0, 0xFFFF4500), //
	OrangeRed2(238, 64, 0, 0xFFEE4000), //
	OrangeRed3(205, 55, 0, 0xFFCD3700), //
	OrangeRed4(139, 37, 0, 0xFF8B2500), //
	Red1(255, 0, 0, 0xFFFF0000), //
	Red2(238, 0, 0, 0xFFEE0000), //
	Red3(205, 0, 0, 0xFFCD0000), //
	Red4(139, 0, 0, 0xFF8B0000), //
	DeepPink1(255, 20, 147, 0xFFFF1493), //
	DeepPink2(238, 18, 137, 0xFFEE1289), //
	DeepPink3(205, 16, 118, 0xFFCD1076), //
	DeepPink4(139, 10, 80, 0xFF8B0A50), //
	HotPink1(255, 110, 180, 0xFFFF6EB4), //
	HotPink2(238, 106, 167, 0xFFEE6AA7), //
	HotPink3(205, 96, 144, 0xFFCD6090), //
	HotPink4(139, 58, 98, 0xFF8B3A62), //
	Pink1(255, 181, 197, 0xFFFFB5C5), //
	Pink2(238, 169, 184, 0xFFEEA9B8), //
	Pink3(205, 145, 158, 0xFFCD919E), //
	Pink4(139, 99, 108, 0xFF8B636C), //
	LightPink1(255, 174, 185, 0xFFFFAEB9), //
	LightPink2(238, 162, 173, 0xFFEEA2AD), //
	LightPink3(205, 140, 149, 0xFFCD8C95), //
	LightPink4(139, 95, 101, 0xFF8B5F65), //
	PaleVioletRed1(255, 130, 171, 0xFFFF82AB), //
	PaleVioletRed2(238, 121, 159, 0xFFEE799F), //
	PaleVioletRed3(205, 104, 137, 0xFFCD6889), //
	PaleVioletRed4(139, 71, 93, 0xFF8B475D), //
	Maroon1(255, 52, 179, 0xFFFF34B3), //
	Maroon2(238, 48, 167, 0xFFEE30A7), //
	Maroon3(205, 41, 144, 0xFFCD2990), //
	Maroon4(139, 28, 98, 0xFF8B1C62), //
	VioletRed1(255, 62, 150, 0xFFFF3E96), //
	VioletRed2(238, 58, 140, 0xFFEE3A8C), //
	VioletRed3(205, 50, 120, 0xFFCD3278), //
	VioletRed4(139, 34, 82, 0xFF8B2252), //
	Magenta1(255, 0, 255, 0xFFFF00FF), //
	Magenta2(238, 0, 238, 0xFFEE00EE), //
	Magenta3(205, 0, 205, 0xFFCD00CD), //
	Magenta4(139, 0, 139, 0xFF8B008B), //
	Orchid1(255, 131, 250, 0xFFFF83FA), //
	Orchid2(238, 122, 233, 0xFFEE7AE9), //
	Orchid3(205, 105, 201, 0xFFCD69C9), //
	Orchid4(139, 71, 137, 0xFF8B4789), //
	Plum1(255, 187, 255, 0xFFFFBBFF), //
	Plum2(238, 174, 238, 0xFFEEAEEE), //
	Plum3(205, 150, 205, 0xFFCD96CD), //
	Plum4(139, 102, 139, 0xFF8B668B), //
	MediumOrchid1(224, 102, 255, 0xFFE066FF), //
	MediumOrchid2(209, 95, 238, 0xFFD15FEE), //
	MediumOrchid3(180, 82, 205, 0xFFB452CD), //
	MediumOrchid4(122, 55, 139, 0xFF7A378B), //
	DarkOrchid1(191, 62, 255, 0xFFBF3EFF), //
	DarkOrchid2(178, 58, 238, 0xFFB23AEE), //
	DarkOrchid3(154, 50, 205, 0xFF9A32CD), //
	DarkOrchid4(104, 34, 139, 0xFF68228B), //
	Purple1(155, 48, 255, 0xFF9B30FF), //
	Purple2(145, 44, 238, 0xFF912CEE), //
	Purple3(125, 38, 205, 0xFF7D26CD), //
	Purple4(85, 26, 139, 0xFF551A8B), //
	MediumPurple1(171, 130, 255, 0xFFAB82FF), //
	MediumPurple2(159, 121, 238, 0xFF9F79EE), //
	MediumPurple3(137, 104, 205, 0xFF8968CD), //
	MediumPurple4(93, 71, 139, 0xFF5D478B), //
	Thistle1(255, 225, 255, 0xFFFFE1FF), //
	Thistle2(238, 210, 238, 0xFFEED2EE), //
	Thistle3(205, 181, 205, 0xFFCDB5CD), //
	Thistle4(139, 123, 139, 0xFF8B7B8B), //
	Grey11(28, 28, 28, 0xFF1C1C1C), //
	Grey21(54, 54, 54, 0xFF363636), //
	Grey31(79, 79, 79, 0xFF4F4F4F), //
	Grey41(105, 105, 105, 0xFF696969), //
	Grey51(130, 130, 130, 0xFF828282), //
	Grey61(156, 156, 156, 0xFF9C9C9C), //
	Grey71(181, 181, 181, 0xFFB5B5B5), //
	Gray81(207, 207, 207, 0xFFCFCFCF), //
	Gray91(232, 232, 232, 0xFFE8E8E8), //
	DarkGrey(169, 169, 169, 0xFFA9A9A9), //
	DarkBlue(0, 0, 139, 0xFF00008B), //
	DarkCyan(0, 139, 139, 0xFF008B8B), //
	DarkMagenta(139, 0, 139, 0xFF8B008B), //
	DarkRed(139, 0, 0, 0xFF8B0000), //
	LightGreen(144, 238, 144, 0xFF90EE90);//

	private final int R;
	private final int G;
	private final int B;
	private final int RGB;

	private Colors(int r, int g, int b, int rGB) {
		R = r;
		G = g;
		B = b;
		RGB = rGB;
	}

	public int getR() {
		return R;
	}

	public int getG() {
		return G;
	}

	public int getB() {
		return B;
	}

	public int getRGB() {
		return RGB;
	}

	public static Colors random() {
		Colors[] values = values();
		Random random = new Random();
		return values[random.nextInt(values.length)];
	}
}
