package egovframework.example.admin.system.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsManageDTO;

@Mapper("adminSystemMapper")
public interface AdminSystemMapper {
	
	boolean authorizationUser(String id);
	/*
	 * 사이트 관리
	 */
    void insertSite(SiteManageDTO site);
    SiteManageDTO selectSiteById(String siteId);
    void updateSite(SiteManageDTO site);
    void deleteSite(String siteId);
    List<SiteManageDTO> selectSitesAll();
    boolean checkSiteByUrl(String url);

	/*
	 * 약관 관리
	 */
    void insertTerms(TermsManageDTO terms);
    TermsManageDTO selectTermsById(String termsId);
    void updateTerms(TermsManageDTO terms);
    void deleteTerms(String termsId);
    List<TermsManageDTO> selectTermsAll();

	/*
	 * 공통코드 관리
	 */
    void insertCommonCode(CommonCodesDTO commonCode);
    CommonCodesDTO selectCommonCodeById(String codeId);
    void updateCommonCode(CommonCodesDTO commonCode);
    void deleteCommonCode(String codeId);
    List<CommonCodesDTO> selectCommonCodesByConditions(Map<String, String> conditions);

	/*
	 * 하위 공통 코드 관리
	 */
    void insertSubCommonCode(SubCommonCodesDTO subCommonCode);
    SubCommonCodesDTO selectSubCommonCodeById(String subCodeId);
    void updateSubCommonCode(SubCommonCodesDTO subCommonCode);
    void deleteSubCommonCode(String subCodeId);
    List<SubCommonCodesDTO> selectSubCommonCodesByConditions(Map<String, String> conditions);

	/*
	 * IP 관리
	 */
    void insertIp(IpTableDTO ipTable);
    IpTableDTO selectIpById(String allowedIp);
    void updateIp(IpTableDTO ipTable);
    void deleteIp(String allowedIp);
    List<IpTableDTO> selectIpsAll();
}