package egovframework.example.admin.system.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
import egovframework.example.cmmn.service.PaginationVO;

public interface AdminSystemManageService {
	
	boolean authorizationUser(String id);
	/*
	 * 사이트 관리
	 */
    String insertSite(SiteManageDTO site);
    SiteManageDTO selectSiteById(String siteId);
    boolean updateSite(SiteManageDTO site);
    void deleteSite(String siteId);
    SiteListDTO selectSitesAll(SiteManageDTO data);
    boolean checkSiteByUrl(String url);
    
    void saveSiteManage(SiteManageDTO siteManage, MultipartFile topLogoImage, MultipartFile bottomLogoImage, MultipartFile mainImage) throws IOException;

	/*
	 * 약관 관리
	 */
    String insertTerms(TermsManageDTO terms);
    TermsManageDTO selectTermsById(String termsId);
    String updateTerms(TermsManageDTO terms);
    void deleteTerms(String termsId);
    List<TermsManageDTO> selectTermsAll(TermsManageDTO data);

	/*
	 * 공통코드 관리
	 */
    void insertCommonCode(CommonCodesDTO commonCode);
    CommonCodesDTO selectCommonCodeById(String codeId);
    void updateCommonCode(CommonCodesDTO commonCode);
    void deleteCommonCode(String codeId);
    CommonCodeListDTO selectCommonCodesByConditions(CommonCodesDTO data);
    boolean checkCommonCodeById(String codeId);

	/*
	 * 하위 공통 코드 관리
	 */
    void insertSubCommonCode(SubCommonCodesDTO subCommonCode);
    SubCommonCodesDTO selectSubCommonCodeById(String subCodeId);
    void updateSubCommonCode(SubCommonCodesDTO subCommonCode);
    void deleteSubCommonCode(String subCodeId);
    SubCommonCodeListDTO selectSubCommonCodesByConditions(SubCommonCodesDTO data);
    boolean checkSubCommonCodeById(String codeId);

	/*
	 * IP 관리
	 */
    void insertIp(IpTableDTO ipTable);
    IpTableDTO selectIpById(String allowedIp);
    void updateIp(IpTableDTO ipTable);
    void deleteIp(String allowedIp);
    IpTableListDTO selectIpsAll(IpTableDTO data);
}
