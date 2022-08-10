package com.softtwig.crm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtwig.crm.bo.AdminUserBO;
import com.softtwig.crm.bo.BaseBO;
import com.softtwig.crm.bo.LeadsSearchCriteria;
import com.softtwig.crm.exception.SalesBizException;
import com.softtwig.crm.exception.SalesBizLogger;
import com.softtwig.crm.security.ControllerUtils;
import com.softtwig.crm.service.AdminService;
import com.softtwig.crm.service.CampaignsService;
import com.softtwig.crm.service.LeadService;
import com.softtwig.crm.utils.ErrorCodes;
import com.softtwig.crm.utils.PaginationClass;
import com.softtwig.crm.vo.CampaignsDO;
import com.softtwig.crm.vo.LeadsDO;

@Controller
public class LeadMuthuController extends ControllerUtils implements Serializable {
	
	private static final long serialVersionUID = -7566828223244087438L;

	private static final SalesBizLogger LOGGER = SalesBizLogger.getLogger(LeadMuthuController.class);

	@Autowired
	private LeadService leadService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CampaignsService campaignsService;
	
	@RequestMapping(value = "/create-lead", method = RequestMethod.GET)
	public String leadcreate(Model model, HttpServletRequest request) throws SalesBizException {

		long loginId = getUserSecurity().getLoginId();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}

		if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if (null != request.getParameter("infoMessage")) {
			model.addAttribute("infoMessage", request.getParameter("infoMessage"));
		}
		if (null != request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		if (null != request.getParameter("functionType") && !request.getParameter("functionType").isEmpty()) {
			model.addAttribute("functionType", request.getParameter("functionType"));
		} else {
			model.addAttribute("functionType", "add");
		}

		model.addAttribute("leadObj", new LeadsDO());
		List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
		userBOList = adminService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}
		List<CampaignsDO> campaignList = new ArrayList<>();
		campaignList = campaignsService.retrieveUser();
		if (null != campaignList && 0 != campaignList.size()) {
			model.addAttribute("campaignList", campaignList);
		}
		return "create-lead";

	}
	@RequestMapping(value = "/create-lead", method = RequestMethod.POST)
	public String createLead(@Valid @ModelAttribute("leadObj") LeadsDO lead, BindingResult result,
			HttpServletRequest request, Model model) throws SalesBizException {
		try {
			long loginId = getUserSecurity().getLoginId();
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}

			if (result.hasErrors()) {
				return "create-lead";
			}

			lead.setIsDeleted(false);
			lead.setCreated(new Date());
			LeadsDO newlead = leadService.createLead(lead);

			if (0 != newlead.getLeadId()) {
				model.addAttribute("successMessage", messageSource.getMessage("Lead.Creation", null, null));
			} else {
				model.addAttribute("errorMessage", "Error");
			}

		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add Lead has failed:" + ex.getMessage());
			}
			LOGGER.info("Add Lead has failed:" + ex.getMessage());
		}
		return "redirect:/view-leads";
	}
	public List<Map> listLead(Model model, HttpServletRequest req) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();

		List<Map> listlead = new ArrayList<Map>();
		try {
			listlead = leadService.listLead();
			System.out.println(listlead);
			if (null != listlead) {
				model.addAttribute("leadslistdo", listlead);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("LeadList retrival has  failed:" + e.getMessage());
			}
			LOGGER.info("LeadList retrival has failed:" + e.getMessage());
		} finally {
			LeadMuthuController.LOGGER.exit();
		}

		return listlead;
  
}
	@RequestMapping(value = "/view-leads", method = RequestMethod.GET)
	public String viewLead(HttpServletRequest request, Model model, HttpSession session) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();
		try {
			String paging = null;
			if (null != request.getParameter("successMessage")) {
				model.addAttribute("successMessage", request.getParameter("successMessage"));
			}
			if (null != request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}
			if (null != request.getParameter("page")) {
				paging = request.getParameter("page");
			}
			LeadsDO leadDO = new LeadsDO();
			model.addAttribute("leadsDO", leadDO);
			//model.addAttribute("leadslistdo", leadDO);
			leadPagination(leadDO, paging, request, model);
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("view lead: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view lead : Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}

		return "view-leads";
	}
	private void leadPagination(LeadsDO leadDO, String paging, HttpServletRequest request, Model model) throws SalesBizException {
		BaseBO baseBO = new BaseBO();
		long count = 0;
		long totalleadCount = 0;
		int page = 1;
		int maxRecord = 10;
		if (null != paging) {
			page = Integer.parseInt(paging);
		}
	 	count = leadService.leadCount(leadDO);
		if (0 != count) {
			totalleadCount = count;
		}
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		baseBO.setRecordIndex(startingRecordIndex);
		baseBO.setMaxRecord(maxRecord);
		baseBO.setPagination("pagination");
		leadDO.setBasebo(baseBO);
		List<LeadsDO> leadsDOList = new ArrayList<LeadsDO>();
		leadsDOList = leadService.viewLead(leadDO);
		model.addAttribute("leadObj", new LeadsDO());
		List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
		userBOList = adminService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}
		List<CampaignsDO> campaignList = new ArrayList<>();
		campaignList = campaignsService.retrieveUser();
		if (null != campaignList && 0 != campaignList.size()) {
			model.addAttribute("campaignList", campaignList);
		}
		if (null != leadsDOList && !leadsDOList.isEmpty() && leadsDOList.size() > 0) {
			model.addAttribute("leadslistdo",
		PaginationClass.paginationLimitedRecords(page, leadsDOList, maxRecord, totalleadCount));
		}
	}

	private int paginationPageValues(int pageid, int recordPerPage) {
		int pageRecords = 0;
		if (pageid == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (pageid - 1) * recordPerPage + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;

	}
	@RequestMapping(value = "/search-leads", method = RequestMethod.POST)
	public String searchLead(@ModelAttribute("leadsDO") LeadsSearchCriteria searchBO,
			HttpServletRequest request, Model model, HttpSession session) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();
		try {
			long loginId = 0;
			if (null != getUserSecurity()) {
				loginId = getUserSecurity().getLoginId();
			}
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}
			if (null != request.getParameter("successMessage")) {
				model.addAttribute("successMessage", request.getParameter("successMessage"));
			}
			if (null != request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}
			long totalLeadsCount = 0;
			int page = 1;
			int maxRecord = 0;
			String Record = messageSource.getMessage("record.page.limit", null, null);
			if (null != Record) {
				maxRecord = Integer.parseInt(Record);
			}
			String paging = null;
			if (null != request.getParameter("page")) {
				paging = request.getParameter("page");
				page = Integer.parseInt(paging);
			}
			// count
			long count = 0;
			count = leadService.leadsCount(searchBO);
			if (0 < count) {
				totalLeadsCount = count;
			} else {
				model.addAttribute("errorMessage", "No search Results Found!");
				return "redirect:/view-leads";
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			searchBO.setRecordIndex(startingRecordIndex);
			searchBO.setMaxRecord(maxRecord);
			List<LeadsDO> leadDOList = new ArrayList<LeadsDO>();
			leadDOList = leadService.retrieveSearchLead(searchBO);
			model.addAttribute("leadObj", new LeadsDO());
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = adminService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
			List<CampaignsDO> campaignList = new ArrayList<>();
			campaignList = campaignsService.retrieveUser();
			if (null != campaignList && 0 != campaignList.size()) {
				model.addAttribute("campaignList", campaignList);
			}
			if (null != leadDOList && !leadDOList.isEmpty() && leadDOList.size() > 0) {
				model.addAttribute("leadslistdo",
						PaginationClass.paginationLimitedRecords(page, leadDOList, maxRecord, totalLeadsCount));
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("view lead: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view lead : Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}

		return "view-leads";
	}
	@RequestMapping(value = "/view-leads-details", method = RequestMethod.GET)
	public String viewLeadDetails(Model model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("leadslistdo") LeadsDO leadsDO1) {

		LeadMuthuController.LOGGER.entry();
		try {
			if (null != request.getParameter("leadId")) {
				String id = request.getParameter("leadId");
				int leadId = Integer.parseInt(id);
				leadsDO1.setLeadId(leadId);
		}
			leadsDO1= leadService.viewLeadDetails(leadsDO1);
			if (0 != leadsDO1.getLeadId()) {
				model.addAttribute("leadslistdo", leadsDO1);
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("view lead details: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view lead details: Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}
		return "view-leads-details";
	}
	@RequestMapping(value = "/edit-lead", method = RequestMethod.GET)
	public String editLead(Model model, HttpServletRequest request) {
		LeadsDO lead = new LeadsDO();
		LeadMuthuController.LOGGER.entry();
		try {
			String ids = request.getParameter("leadId");
			int id = Integer.parseInt(ids);
			lead = leadService.retriveLeadDetails(id);
			model.addAttribute("leadObj", lead);
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = adminService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
			List<CampaignsDO> campaignList = new ArrayList<>();
			campaignList = campaignsService.retrieveUser();
			if (null != campaignList && 0 != campaignList.size()) {
				model.addAttribute("campaignList", campaignList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("lead retrive History failed:" + e.getMessage());
			}
			LOGGER.info("lead retrive History failed:" + e.getMessage());
		} finally {
			LeadMuthuController.LOGGER.exit();
		}
		return "edit-lead";
	}
	
	@RequestMapping(value = "/edit-lead", method = RequestMethod.POST)
	public String UpdateLead(@Valid @ModelAttribute("leadObj") LeadsDO leadsDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, Model model) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();
		try {
			if (result.hasErrors()) {
				return "edit-lead";
			}
			leadsDO.setIsDeleted(false);
			boolean status = leadService.updateLead(leadsDO);

			if (status) {
				model.addAttribute("successMessage", messageSource.getMessage("Lead.Update", null, null));
				
			} else {
				model.addAttribute("errorMessage", messageSource.getMessage("Lead.Error", null, null));
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Edit has failed" + ex.getMessage());
			}
			LOGGER.info("Edit has failed:" + ex.getMessage());
		}
		
		LeadMuthuController.LOGGER.exit();
		return "redirect:/view-leads";
	}

	@RequestMapping(value = "/delete-lead", method = RequestMethod.GET)
	public String deleteLead(Model model, HttpServletRequest request, HttpSession session) {
		boolean status = false;
		LeadMuthuController.LOGGER.entry();
		try {
			LeadsDO leaddo = new LeadsDO();
			if (null != request.getParameter("leadId")) {
				String id = request.getParameter("leadId");
				int leadId = Integer.parseInt(id);
				leaddo.setLeadId(leadId);
			}
			status = leadService.deleteLead(leaddo);
			if (status) {
				model.addAttribute("successMessage", "Lead Successfully deleted!");
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("delete lead : Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("delete lead details: Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}
		return "redirect:/view-leads";
	}
	@RequestMapping(value = "report-leads", method = RequestMethod.GET)
	public String reportviewLead(HttpServletRequest request, Model model, HttpSession session) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();
		try {
			String paging = null;
			if (null != request.getParameter("successMessage")) {
				model.addAttribute("successMessage", request.getParameter("successMessage"));
			}
			if (null != request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}
			if (null != request.getParameter("page")) {
				paging = request.getParameter("page");
			}
			LeadsDO leadDO = new LeadsDO();
			model.addAttribute("searchreport", leadDO);
			leadPagination1(leadDO, paging, request, model);
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("view lead: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view lead : Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}

		return "report-leads";
	}

	@RequestMapping(value = "report-leads", method = RequestMethod.POST)
	public String reportsearchLead(@ModelAttribute("searchreport") LeadsSearchCriteria searchBO,
			HttpServletRequest request, Model model, HttpSession session) throws SalesBizException {
		LeadMuthuController.LOGGER.entry();
		try {
			long loginId = 0;
			if (null != getUserSecurity()) {
				loginId = getUserSecurity().getLoginId();
			}
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}
			if (null != request.getParameter("successMessage")) {
				model.addAttribute("successMessage", request.getParameter("successMessage"));
			}
			if (null != request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}
			long totalLeadsCount = 0;
			int page = 1;
			int maxRecord = 0;
			String Record = messageSource.getMessage("record.page.limit", null, null);
			if (null != Record) {
				maxRecord = Integer.parseInt(Record);
			}
			String paging = null;
			if (null != request.getParameter("page")) {
				paging = request.getParameter("page");
				page = Integer.parseInt(paging);
			}
			// count
			long count = 0;
			count = leadService.leadsCount(searchBO);
			if (0 < count) {
				totalLeadsCount = count;
			} else {
				model.addAttribute("errorMessage", "No search Results Found!");
				return "redirect:/report-leads";
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			searchBO.setRecordIndex(startingRecordIndex);
			searchBO.setMaxRecord(maxRecord);
			List<LeadsDO> leadDOList = new ArrayList<LeadsDO>();
			leadDOList = leadService.retrieveSearchLead(searchBO);
			model.addAttribute("leadObj", new LeadsDO());
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = adminService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
			List<CampaignsDO> campaignList = new ArrayList<>();
			campaignList = campaignsService.retrieveUser();
			if (null != campaignList && 0 != campaignList.size()) {
				model.addAttribute("campaignList", campaignList);
			}
			if (null != leadDOList && !leadDOList.isEmpty() && leadDOList.size() > 0) {
				model.addAttribute("leadslistdo",
						PaginationClass.paginationLimitedRecords(page, leadDOList, maxRecord, totalLeadsCount));
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("view lead: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view lead : Exception \t" + e);
			}
		} finally {
			LeadMuthuController.LOGGER.exit();
		}

		return "report-leads";
	}
	private void leadPagination1(LeadsDO leadDO, String paging, HttpServletRequest request, Model model) throws SalesBizException {
		BaseBO baseBO = new BaseBO();
		long count = 0;
		long totalleadCount = 0;
		int page = 1;
		int maxRecord = 10;
		if (null != paging) {
			page = Integer.parseInt(paging);
		}
	//	count = leadService.leadCount(leadDO);
		if (0 != count) {
			totalleadCount = count;
		}
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		baseBO.setRecordIndex(startingRecordIndex);
		baseBO.setMaxRecord(maxRecord);
		baseBO.setPagination("pagination");
		leadDO.setBasebo(baseBO);
		List<LeadsDO> leadsDOList = new ArrayList<LeadsDO>();
	//	leadsDOList = leadService.viewLead(leadDO);
		model.addAttribute("leadObj", new LeadsDO());
		List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
		userBOList = adminService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}
		List<CampaignsDO> campaignList = new ArrayList<>();
		campaignList = campaignsService.retrieveUser();
		if (null != campaignList && 0 != campaignList.size()) {
			model.addAttribute("campaignList", campaignList);
		}
		if (null != leadsDOList && !leadsDOList.isEmpty() && leadsDOList.size() > 0) {
			model.addAttribute("leadslistdo",
		PaginationClass.paginationLimitedRecords(page, leadsDOList, maxRecord, totalleadCount));
		}
	}
	
	
	
	@RequestMapping(value="/email-check",method=RequestMethod.GET)
	@ResponseBody
	public boolean Emailcheck(@RequestParam String emailId) throws SalesBizException{
		
	boolean status=leadService.EmailCheck(emailId);

	return status;
	}
	
	@RequestMapping(value="/mobile-check",method=RequestMethod.GET)
	@ResponseBody
	public boolean Mobilecheck(@RequestParam String mobile) throws SalesBizException{
		
	long phone=Long.parseLong(mobile);
		
	boolean status=leadService.Mobilecheck(phone);

	return status;
	}
	
}
