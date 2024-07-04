package egovframework.example.admin.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;

@Service("adminSystemManageService")
public class AdminSystemManageServiceImpl implements AdminSystemManageService{
	
	@Resource(name = "adminSystemMapper")
	private AdminSystemMapper mapper;
	
	
	@Override
	public boolean authorizationUser(String id) {
		return mapper.authorizationUser(id);
	}
	/**
	 * 사이트 관리
	 */
	@Override
	public void insertSite(SiteManageDTO site) {
		mapper.insertSite(site);
	}

	@Override
	public SiteManageDTO selectSiteById(String siteId) {
		return mapper.selectSiteById(siteId);
	}

	@Override
	public void updateSite(SiteManageDTO site) {
		mapper.updateSite(site);
	}

	@Override
	public void deleteSite(String siteId) {
		mapper.deleteSite(siteId);
	}

	@Override
	public List<SiteManageDTO> selectSitesAll() {
		return mapper.selectSitesAll();
	}
	
	@Override
	public boolean checkSiteByUrl(String url) {
		return mapper.checkSiteByUrl(url);
	}

	/**
	 * 약관 관리
	 */
	@Override
	public void insertTerms(TermsManageDTO terms) {
		mapper.insertTerms(terms);
	}

	@Override
	public TermsManageDTO selectTermsById(String termsId) {
		return mapper.selectTermsById(termsId);
	}

	@Override
	public void updateTerms(TermsManageDTO terms) {
		mapper.updateTerms(terms);
	}

	@Override
	public void deleteTerms(String termsId) {
		mapper.deleteTerms(termsId);
	}

	@Override
	public List<TermsManageDTO> selectTermsByConditions(Map<String, String> conditions) {
		return mapper.selectTermsByConditions(conditions);
	}

	/**
	 * 공통코드 관리
	 */
	@Override
	public void insertCommonCode(CommonCodesDTO commonCode) {
		mapper.insertCommonCode(commonCode);
	}

	@Override
	public CommonCodesDTO selectCommonCodeById(String codeId) {
		return mapper.selectCommonCodeById(codeId);
	}

	@Override
	public void updateCommonCode(CommonCodesDTO commonCode) {
		mapper.updateCommonCode(commonCode);
	}

	@Override
	public void deleteCommonCode(String codeId) {
		mapper.deleteCommonCode(codeId);
	}

	@Override
	public List<CommonCodesDTO> selectCommonCodesByConditions(Map<String, String> conditions) {
		return mapper.selectCommonCodesByConditions(conditions);
	}

	@Override
	public void insertSubCommonCode(SubCommonCodesDTO subCommonCode) {
		mapper.insertSubCommonCode(subCommonCode);
	}
	/**
	 * 하위코드 관리
	 */
	@Override
	public SubCommonCodesDTO selectSubCommonCodeById(String subCodeId) {
		return mapper.selectSubCommonCodeById(subCodeId);
	}

	@Override
	public void updateSubCommonCode(SubCommonCodesDTO subCommonCode) {
		mapper.updateSubCommonCode(subCommonCode);
	}

	@Override
	public void deleteSubCommonCode(String subCodeId) {
		mapper.deleteSubCommonCode(subCodeId);
	}

	@Override
	public List<SubCommonCodesDTO> selectSubCommonCodesByConditions(Map<String, String> conditions) {
		return mapper.selectSubCommonCodesByConditions(conditions);
	}
	
	/**
	 * IP 관리
	 */

	@Override
	public void insertIp(IpTableDTO ipTable) {
		mapper.insertIp(ipTable);
	}

	@Override
	public IpTableDTO selectIpById(String allowedIp) {
		return mapper.selectIpById(allowedIp);
	}

	@Override
	public void updateIp(IpTableDTO ipTable) {
		mapper.updateIp(ipTable);
	}

	@Override
	public void deleteIp(String allowedIp) {
		mapper.deleteIp(allowedIp);
	}

	@Override
	public List<IpTableDTO> selectIpsAll() {
		return mapper.selectIpsAll();
	}
}
