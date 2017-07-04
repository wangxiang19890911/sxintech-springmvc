package cn.xsintech.model;

import java.security.Principal;

/**
 * ユーザ情報を格納
 *
 * @author 502411724
 *
 */
public class User implements Principal {

	private String employeeCode;

	// 上長
	protected Long escalateTo;

	// グループID
	protected Long groupid;

	// 会社コード
	protected String companyid;

	// グループ名
	protected String groupname;

	// 会社名
	protected String companyname;

	// 所属カテゴリ名
	protected String categoryName;

	protected boolean locked;

	protected String passWord;

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * グループIDのゲッター
	 *
	 * @return
	 */
	public Long getGroupID() {
		return groupid;
	}

	/**
	 * グループIDのセッター
	 *
	 * @param groupid
	 */
	public void setGroupID(Long groupid) {
		this.groupid = groupid;
	}

	/**
	 * 会社コードのゲッター
	 *
	 * @return
	 */
	public String getCompanyID() {
		return companyid;
	}

	/**
	 * 会社コードのセッター
	 *
	 * @param companyid
	 */
	public void setCompanyID(String companyid) {
		this.companyid = companyid;
	}

	/**
	 * グループ名のゲッター
	 *
	 * @return
	 */
	public String getGroupName() {
		return groupname;
	}

	/**
	 * グループ名称のセッター
	 *
	 * @param groupname
	 */
	public void setGroupName(String groupname) {
		this.groupname = groupname;
	}

	/**
	 * 会社名のゲッター
	 *
	 * @return
	 */
	public String getCompanyname() {
		return companyname;
	}

	/**
	 * 会社名のセッター
	 *
	 * @param companyname
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	/**
	 * 上長情報のゲッター
	 *
	 * @return
	 */
	public Long getEscalateTo() {
		return escalateTo;
	}

	/**
	 * 上長情報のセッター
	 *
	 * @param escalateTo
	 */
	public void setEscalateTo(Long escalateTo) {
		this.escalateTo = escalateTo;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
