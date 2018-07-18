package com.shanke.utils.idea;

/**
 * <pre>
 * Description: 
 *       IDEA加密解密算法
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author yjw
 * @date 2015-9-1 上午9:23:18
 */
public class Idea {

	private int maxim = 65537;
	private String Key;
	private long[][] z = new long[7][10];
	private long[][] DK = new long[7][10];

	// 将 0xA1 转成10进制 10*16+1,原来的 t16_10
	public int hex2Num(char ch) {
		if ((ch >= '0') && (ch <= '9'))
			return ch - '0';
		return 10 + ch - 'A';
	}

	public char num2Hex(int i) {
		if (i <= 9)
			return (char) ('0' + i);
		return (char) ('A' + i - 10);
	}

	public String num2HexStr(long a) {
		byte k;
		String result = "";
		if (a == 0)
			return "0";
		while (a > 0) {
			k = (byte) (a % 16);
			// result.Insert(0,num2Hex(k));
			result = num2Hex(k) + result;
			a /= 16;
		}
		return result;
	}

	public String decodeStr16(String s) {
		String result = "";
		char v;
		int wLen = s.length() / 2;
		for (int i = 0; i < wLen; i++) {
			v = (char) (hex2Num(s.charAt(i * 2)) * 16 + hex2Num(s
					.charAt(i * 2 + 1)));
			result += v;
		}
		return result;
	}

	public String encodeStr16(String s) {
		String result = "";
		char ch = 0;
		char out[] = new char[3];
		out[2] = 0;

		int len = s.length();
		for (int i = 0; i < len; i++) {
			ch = s.charAt(i);
			out[0] = num2Hex(ch / 16);
			result += out[0];
			out[1] = num2Hex(ch % 16);
			result += out[1];
		}
		return result;
	}

	public long SR(long a, int i)// 右移
	{
		return (a >> i);
	}

	public long SL(long a, int i)// 按16位值左移
	{
		return ((a << i) & 0xFFFF); // (a % (2^ (16-b))) * (2^b);
	}

	// 原来vb算法mul_old效率太低，重写
	public long mul(long a, long b) {
		if (a == 0)
			return (maxim - b) & 0xFFFF;
		if (b == 0)
			return (maxim - a) & 0xFFFF;

		b = a * b;
		a = b >> 16;
		b = b & 0xFFFF; // 低16位

		if (b < a)
			b += maxim;
		return (b - a) & 0xFFFF;
	}

	public void subkey() {
		long[] s = new long[54];
		int i, j;
		int len = Key.length();

		for (i = 0; i < 8; i++) {
			s[i] = 0;// 实际的key只用了前8个字符，vc里GetAt会出错，所以要处理；
			if (len <= i * 4)
				continue;

			s[i] = hex2Num(Key.charAt(i * 4)) << 12;
			s[i] += hex2Num(Key.charAt(i * 4 + 1)) << 8;
			s[i] += hex2Num(Key.charAt(i * 4 + 2)) << 4;
			s[i] += hex2Num(Key.charAt(i * 4 + 3));
		}

		for (i = 8; i < 54; i++) {
			if ((i + 2) % 8 == 0)
				s[i] = (SL(s[i - 7], 9) + SR(s[i - 14], 7)) & 0xFFFF;
			else if ((i + 1) % 8 == 0)
				s[i] = (SL(s[i - 15], 9) + SR(s[i - 14], 7)) & 0xFFFF;
			else
				s[i] = (SL(s[i - 7], 9) + SR(s[i - 6], 7)) & 0xFFFF;
		}

		for (i = 1; i < 10; i++) {
			for (j = 1; j < 7; j++)
				z[j][i] = s[6 * (i - 1) + j - 1];
		}
	}

	public long inv(long a) {
		long n1 = maxim, n2 = a, b1 = 0, b2 = 1;
		long q, r, t;

		if (a == 0)
			return 0;
		do {
			r = n1 % n2;
			q = n1 / n2;// 整数
			if (r == 0)
				break;
			n1 = n2;
			n2 = r;
			t = b2;
			b2 = b1 - q * b2;
			b1 = t;
		} while (r != 0);
		if (b2 < 0)
			b2 = maxim + b2;
		return b2;
	}

	// 根据加密数组，构造解密数组
	public void dekey() {
		int j;
		// memset(DK,0,sizeof(DK));//数组清零，防止部分未设值
		DK = new long[7][10];
		for (j = 1; j < 10; j++) {
			DK[1][10 - j] = inv(z[1][j]);
			DK[4][10 - j] = inv(z[4][j]);
			if ((j == 1) || (j == 9)) {
				DK[2][10 - j] = (65536 - z[2][j]) & 0xFFFF;
				DK[3][10 - j] = (65536 - z[3][j]) & 0xFFFF;
				continue;
			}
			DK[2][10 - j] = (65536 - z[3][j]) & 0xFFFF;
			DK[3][10 - j] = (65536 - z[2][j]) & 0xFFFF;
		}

		for (j = 1; j < 10; j++) {
			DK[5][9 - j] = z[5][j];
			DK[6][9 - j] = z[6][j];
		}
		return;
	}

	public String idea(String s, Boolean bEncode) {
		long[][] magic = new long[7][10];
		long inp[] = new long[4];
		long oup[] = new long[4];
		long x[] = new long[4];
		long kk, a, t1, t2;
		String s1, s2;
		int i, j;

		if (bEncode)
			magic = z;// 加密使用z数组；
		else
			magic = DK;

		for (i = 0; i < 4; i++) // 分割输入数据
		{
			inp[i] = 0;
			x[i] = inp[i];
			if (s.length() <= i * 4)
				continue;
			inp[i] = hex2Num(s.charAt(i * 4)) << 12;
			inp[i] += hex2Num(s.charAt(i * 4 + 1)) << 8;
			inp[i] += hex2Num(s.charAt(i * 4 + 2)) << 4;
			inp[i] += hex2Num(s.charAt(i * 4 + 3));
			x[i] = inp[i];
		}

		for (int r = 1; r <= 8; r++) {
			x[0] = mul(x[0], magic[1][r]);
			x[3] = mul(x[3], magic[4][r]);
			x[1] = (x[1] + magic[2][r]) & 0xFFFF;
			x[2] = (x[2] + magic[3][r]) & 0xFFFF;
			kk = mul(magic[5][r], x[0] ^ x[2]);
			t1 = mul(magic[6][r], (kk + (x[1] ^ x[3])) & 0xFFFF);
			t2 = (kk + t1) & 0xFFFF;
			x[0] ^= t1;
			x[3] ^= t2;
			a = x[1] ^ t2;
			x[1] = x[2] ^ t1;
			x[2] = a;
		}
		oup[0] = mul(x[0], magic[1][9]);
		oup[3] = mul(x[3], magic[4][9]);
		oup[1] = (x[2] + magic[2][9]) & 0xFFFF;
		oup[2] = (x[1] + magic[3][9]) & 0xFFFF;

		s2 = "";
		for (i = 0; i < 4; i++) {
			s1 = num2HexStr(oup[i]);
			for (j = s1.length(); j < 4; j++)
				s1 = "0" + s1;
			s2 += s1;
		}
		return s2;
	}

	public void init(String MiYao) {
		Key = MiYao.substring(0, 8);// 实际只用到了 头8个字符
		subkey();
		dekey();
		return;
	}

	/**
	 * <pre>
	 * Description: 
	 *         解密
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-1 上午9:24:58
	 * @param MiWen
	 *            密文
	 * @param MiYao
	 *            秘钥
	 * @return 明文
	 */
	public String decode(String MiWen, String MiYao) {
		String Cur, MingWen = "";
		int i, seg;

		init(MiYao);
		seg = MiWen.length() / 16;
		for (i = 0; i < seg; i++) {
			Cur = MiWen.substring(i * 16, i * 16 + 16);
			MingWen += idea(Cur, false);// 解密
		}

		MingWen = decodeStr16(MingWen);// 解码
		MingWen = rightTrim(MingWen);// 去掉尾部空格
		return MingWen;
	}

	/**
	 * <pre>
	 * Description: 
	 *        加密
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-1 上午9:24:44
	 * @param MingWen
	 *            明文
	 * @param MiYao
	 *            秘钥
	 * @return 密文
	 */
	public String encode(String MingWen, String MiYao) {
		String out = "";
		String cur = "";
		int i = 0;
		int seg = 0;

		init(MiYao);// 初始化密钥数组
		i = MingWen.length() % 8;
		if (i != 0)
			for (; i < 8; i++)
				MingWen += " ";// 空格补足8的倍数
		seg = MingWen.length() / 8;
		for (i = 0; i < seg; i++) {
			cur = MingWen.substring(i * 8, i * 8 + 8);
			cur = encodeStr16(cur);// 编码
			cur = idea(cur, true);// 加密
			out += cur;
		}
		return out;
	}

	// 去除字符串右边的空格
	public static String rightTrim(String str) {
		int length = str.length();
		char[] data = str.toCharArray();
		int lastNotSpace = 0;
		for (int i = length - 1; i > 0; i--) {
			if (data[i] != ' ') {
				lastNotSpace = i;
				break;
			}
		}
		return str.substring(0, lastNotSpace + 1);
	}

	private static void test(int i) {
		String MiYao = "";
		String MiWen = "";
		String MingWen = "";
		switch (i) {
		case 0:
			MiYao = "FAE4F9A0C918654F83A292097C968F2D";
			MiWen = "450F84E32FBD5D3BA49CDFF0ED13DCCD4F210321D296EE1B39BD9CA8225A5266642E61077F23580D92DDF62F9BC618BA93AE881424F96A4B7A2253822FE9B84A15236E8C90D155D9D4F3481D7DB87C2915236E8C90D155D9C376D0F09914362741354F46D68CD391A34B0548ABF83667";
			MingWen = "     142701595314585201508072015070120150731010000101010101010           0.00            .00           0.002";
			break;
		case 1:
			MiYao = "FFF9BAC02B4D4770B6B33F2E8CBE21D1";
			MiWen = "7B23ED8ED1B7BC9E6D3FC56C480717D9AD95DDF8C76649AE4D570D6F9B1062FCB6F901BDB16D7E76CA0A652324EEA9A1A8D4D80D8656A09AB04044E0E75FE0AB7202B3D63EF57B505107251C85A151EA7202B3D63EF57B50351B39E13C5F4773E6DC08E5E9216F4390A64B4EF43ADAF9";
			MingWen = "     140109110279612201508072015070120150731010000101010101010           0.00            .00           0.000";
			break;
		case 2:
			MiYao = "2A948984A43E81DF6AE5349C75C8AB4D";
			MiWen = "1006152C0CFDC0B865112308ED32876BA5C35D6BCAB666E772897304F5A5C46993913135481EE0EB334A317D90C3DEFC701473F668393CCFC344AA771A89209CC88B287065B6168EAF3CE5417D81EAF2C88B287065B6168E658C4F3A2060B3A44BDC43730E399E58FFF1404DED787353";
			MingWen = "     140114792239019201508072015070120150731010000101010101010           0.00            .00           0.002";
			break;
		default:
			MiYao = "2A948984A43E81DF6AE5349C75C8AB4D";
			MiWen = "65112308ED32876B";
			MingWen = "11479223";
			break;
		}
		Idea idea = new Idea();
		System.out.println("case:" + i);
		System.out.println("密文：" + MiWen);
		String encode = idea.encode(MingWen, MiYao);
		System.out.println("加密：" + encode + "  " + encode.equals(MiWen));
		String decode = idea.decode(MiWen, MiYao);
		System.out.println("明文：" + MingWen);
		System.out.println("解密：" + decode + " " + decode.equals(MingWen));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			// test(new Random().nextInt(4));
			test(i);
		}
	/*	Idea idea = new Idea();
		String MiYao = "11111111";
		String MingWen = "121";
		String miwen = "121";

		miwen = idea.encode(MingWen, MiYao);
		System.out.println(miwen);*/

	}
}