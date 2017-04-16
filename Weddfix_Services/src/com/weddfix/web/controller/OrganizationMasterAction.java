package com.weddfix.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.services.CommonMasterService;

public class OrganizationMasterAction extends ActionSupport implements
		ServletRequestAware {
	HttpServletRequest servletRequest = null;
	private static final long serialVersionUID = 1L;
	private List<OrganizationFormBean> gridModel;
	// get how many rows we want to have into the grid - rowNum attribute in the
	// grid
	private Integer rows = 0;
	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;
	// Your Total Pages
	private Integer total = 0;
	// All Records
	private Integer records = 0;
	// sorting order - asc or desc
	private String sord;
	// get index row - i.e. user click to sort.
	private String sidx;

	private String roleList;

	CommonMasterService commonMasterService = new CommonMasterService();
	OrganizationFormBean organizationMasterFormBean;

	private String oper;
	private String grid;
	private Long id;
	private String orgName;
	private String orgDesc;
	private Boolean isActive;

	public String populateOrganizationMasterList() {
		return populateOrganizationMasterData();
	}

	public String populateOrganizationMasterData() {
		try {
			List<OrganizationFormBean> users = organizationMasterListFromDB();
			if (users != null && getSord() != null
					&& getSord().equalsIgnoreCase("asc")) {
				// Collections.sort(users, null);
			}
			if (getSord() != null && "desc".equalsIgnoreCase(getSord())) {
				// Collections.sort(users, null);
				Collections.reverse(users);
			}
			setRecord(users.size());
			int to = (getRows() * getPage());
			if (to > getRecord())
				to = getRecord();

			setGridModel(users);
			setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));

			if (hasActionMessages() || hasActionErrors()) {
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	private List<OrganizationFormBean> organizationMasterListFromDB() {
		// MOCKED List for now
		List<OrganizationFormBean> users = new ArrayList<OrganizationFormBean>();

		List<OrganizationFormBean> organizationMasterList = null;
		CommonMasterService commonMasterService = new CommonMasterService();
		organizationMasterList = commonMasterService
				.loadOrganizationMasterList();

		// BankMasterBean bankMasterBean[] = new BankMasterBean[users.size()];
		OrganizationFormBean organizationMasterFormBean = new OrganizationFormBean();
		Iterator<?> iterator = organizationMasterList.iterator();
		while (iterator.hasNext()) {
			Object[] obj = (Object[]) iterator.next();
			organizationMasterFormBean = new OrganizationFormBean();
			organizationMasterFormBean
					.setId((Long.parseLong(obj[0].toString())));
			organizationMasterFormBean.setOrgName((((obj[1].toString()))));
			organizationMasterFormBean.setOrgDesc((((obj[2].toString()))));
			organizationMasterFormBean.setIsActive((Boolean.parseBoolean(obj[3]
					.toString())));
			users.add(organizationMasterFormBean);
		}

		return users;
	}

	public String editOrganizationMasterList() {
		HttpSession sess = servletRequest.getSession(true);
		if (oper.equalsIgnoreCase("add")) {
			organizationMasterFormBean = new OrganizationFormBean();
			organizationMasterFormBean.setId(id);
			organizationMasterFormBean.setOrgName(orgName);
			organizationMasterFormBean.setOrgDesc(orgDesc);
			organizationMasterFormBean.setIsActive(isActive);
			organizationMasterFormBean.setCreatedBy(Long.parseLong(sess
					.getAttribute("userId").toString()));
			organizationMasterFormBean.setCreatedDate(new Date());
			commonMasterService
					.addOrganizationMaster(organizationMasterFormBean);
		} else if (oper.equalsIgnoreCase("edit")) {
			commonMasterService.updateOrganizationMaster(getId(), getOrgName(),
					getOrgDesc(), getIsActive(),
					Long.parseLong(sess.getAttribute("userId").toString()));
		} else if (oper.equalsIgnoreCase("del")) {
			commonMasterService.deleteOrganizationMaster(getId());
		}

		return SUCCESS;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecord() {
		return records;
	}

	public void setRecord(Integer records) {
		this.records = records;
		if (this.records > 0 && this.rows > 0) {
			this.total = (int) Math.ceil((double) this.records
					/ (double) this.rows);
		} else {
			this.total = 0;
		}
	}

	public List<OrganizationFormBean> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<OrganizationFormBean> gridModel) {
		this.gridModel = gridModel;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public void setSearchField(String searchField) {
	}

	public void setSearchString(String searchString) {
	}

	public void setSearchOper(String searchOper) {
	}

	public void setLoadonce(boolean loadonce) {
	}

	public void setSession(Map<String, Object> session) {
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMaritalStatusList() {
		return roleList;
	}

	public void setMaritalStatusList(String maritalStatusList) {
		this.roleList = maritalStatusList;
	}

	public String getRoleList() {
		return roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

}
