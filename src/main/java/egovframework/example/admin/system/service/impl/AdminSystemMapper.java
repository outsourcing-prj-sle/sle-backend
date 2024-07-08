package egovframework.example.admin.system.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.admin.system.model.CommonCodeListDTO;
import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.IpTableListDTO;
import egovframework.example.admin.system.model.SiteListDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodeListDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsListDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.cmmn.service.ApiLog;
import egovframework.example.cmmn.service.PaginationVO;

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
    List<SiteManageDTO> selectSitesAll(SiteManageDTO data);
    boolean checkSiteByUrl(String url);
    int checkSiteCount(SiteManageDTO data);

	/*
	 * 약관 관리
	 */
    void insertTerms(TermsManageDTO terms);
    TermsManageDTO selectTermsById(String termsId);
    void updateTerms(TermsManageDTO terms);
    void deleteTerms(String termsId);
    List<TermsManageDTO> selectTermsAll(TermsManageDTO data);

	/*
	 * 공통코드 관리
	 */
    void insertCommonCode(CommonCodesDTO commonCode);
    CommonCodesDTO selectCommonCodeById(String codeId);
    void updateCommonCode(CommonCodesDTO commonCode);
    void deleteCommonCode(String codeId);
    List<CommonCodesDTO> selectCommonCodesByConditions(CommonCodesDTO data);
    boolean checkCommonCodeById(String codeId);
    int selectCommonCodesCount(CommonCodesDTO data);

	/*
	 * 하위 공통 코드 관리
	 */
    void insertSubCommonCode(SubCommonCodesDTO subCommonCode);
    SubCommonCodesDTO selectSubCommonCodeById(String subCodeId);
    void updateSubCommonCode(SubCommonCodesDTO subCommonCode);
    void deleteSubCommonCode(String subCodeId);
    List<SubCommonCodesDTO> selectSubCommonCodesByConditions(SubCommonCodesDTO data);
    boolean checkSubCommonCodeById(String codeId);
    int selectSubCommonCodeCount(SubCommonCodesDTO data);

	/*
	 * IP 관리
	 */
    void insertIp(IpTableDTO ipTable);
    IpTableDTO selectIpById(String allowedIp);
    void updateIp(IpTableDTO ipTable);
    void deleteIp(int id);
    List<IpTableDTO> selectIpsAll(IpTableDTO data);
    int selectIpCount();
    List<String> selectIpByUserId(String id);

    int selectIpLogCount(ApiLog log);
	List<ApiLog> selectIpLogAll(ApiLog log);
}