package com.shanke.excel;

public class HtSpbmMx {
	private String lc;// 栏次
	private String spbm;// 商品编码
	private String spmc;// 商品名称
	private String sm;// 说明
	private String zzssl;// 增值税税率
	private String gjz;// 关键字
	private String zzstsgl;// 增值税特殊管理
	private String tjjbm;// 统计局编码或国民行业代码
	private String bbh;// 版本号
	private String qysj;// 启用时间
	private String gxsj;// 更新时间
	private String p_spbm;// 上级商品编码

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	public String getSpmc() {
		return spmc;
	}

	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getZzssl() {
		return zzssl;
	}

	public void setZzssl(String zzssl) {
		this.zzssl = zzssl;
	}

	public String getGjz() {
		return gjz;
	}

	public void setGjz(String gjz) {
		this.gjz = gjz;
	}

	public String getZzstsgl() {
		return zzstsgl;
	}

	public void setZzstsgl(String zzstsgl) {
		this.zzstsgl = zzstsgl;
	}

	public String getTjjbm() {
		return tjjbm;
	}

	public void setTjjbm(String tjjbm) {
		this.tjjbm = tjjbm;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	public String getQysj() {
		return qysj;
	}

	public void setQysj(String qysj) {
		this.qysj = qysj;
	}

	public String getGxsj() {
		return gxsj;
	}

	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}

	public String getP_spbm() {
		return p_spbm;
	}

	public void setP_spbm(String p_spbm) {
		this.p_spbm = p_spbm;
	}

	@Override
	public String toString() {
		return "HtSpbmMx [lc=" + lc + ", spbm=" + spbm + ", spmc=" + spmc
				+ ", sm=" + sm + ", zzssl=" + zzssl + ", gjz=" + gjz
				+ ", zzstsgl=" + zzstsgl + ", tjjbm=" + tjjbm + ", bbh=" + bbh
				+ ", qysj=" + qysj + ", gxsj=" + gxsj + ", p_spbm=" + p_spbm
				+ "]";
	}
}
