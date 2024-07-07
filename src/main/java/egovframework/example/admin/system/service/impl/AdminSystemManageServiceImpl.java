package egovframework.example.admin.system.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.admin.system.model.CommonCodeListDTO;
import egovframework.example.admin.system.model.CommonCodesDTO;
import egovframework.example.admin.system.model.IpLogListDTO;
import egovframework.example.admin.system.model.IpTableDTO;
import egovframework.example.admin.system.model.IpTableListDTO;
import egovframework.example.admin.system.model.SiteListDTO;
import egovframework.example.admin.system.model.SiteManageDTO;
import egovframework.example.admin.system.model.SubCommonCodeListDTO;
import egovframework.example.admin.system.model.SubCommonCodesDTO;
import egovframework.example.admin.system.model.TermsListDTO;
import egovframework.example.admin.system.model.TermsManageDTO;
import egovframework.example.admin.system.service.AdminSystemManageService;
import egovframework.example.cmmn.service.ApiLog;
import egovframework.example.cmmn.service.PaginationVO;
import egovframework.example.idaminuser.dto.IdAdminUserListDTO;

@Service("adminSystemManageService")
public class AdminSystemManageServiceImpl implements AdminSystemManageService{
	
	@Resource(name = "adminSystemMapper")
	private AdminSystemMapper mapper;
	
	@Resource(name = "adminSiteIdGnrService")
	private EgovIdGnrService adminSiteIdGnrService;
	
	@Resource(name = "adminTermsIdGnrService")
	private EgovIdGnrService adminTermsIdGnrService;
	
	
	@Override
	public boolean authorizationUser(String id) {
		return mapper.authorizationUser(id);
	}
	/**
	 * 사이트 관리
	 */
	@Override
	public String insertSite(SiteManageDTO site) {
		try {
			site.setSiteId(adminSiteIdGnrService.getNextStringId());
			mapper.insertSite(site);
			return "success";
		}catch(Exception e) {
			return "Site Not Found";
		}

	}

	@Override
	public SiteManageDTO selectSiteById(String siteId) {
		return mapper.selectSiteById(siteId);
	}

	@Override
	public boolean updateSite(SiteManageDTO site) {
		if(mapper.selectSiteById(site.getSiteId()) != null || site.getSiteId() != null) {
			mapper.updateSite(site);
			return true;
		}
		return false;
	}

	@Override
	public void deleteSite(String siteId) {
		mapper.deleteSite(siteId);
	}

	@Override
	public SiteListDTO selectSitesAll(SiteManageDTO data) {
        return SiteListDTO.builder()
                .siteInfoList(mapper.selectSitesAll(data))
                .pageNo(data.getPageNo())
                .limit(data.getLimit())
                .totalCount(mapper.checkSiteCount())
                .build();
	}
	
	@Override
	public boolean checkSiteByUrl(String url) {
		return mapper.checkSiteByUrl(url);
	}

	
	/**
	 * 파일 저장
	 * @param siteManageDTO
	 * @param topLogoImage
	 * @param bottomLogoImage
	 * @param mainImage
	 * @throws IOException
	 */
	@Override
    public void saveSiteManage(SiteManageDTO siteManageDTO, MultipartFile topLogoImage, MultipartFile bottomLogoImage, MultipartFile mainImage) throws IOException {
        if (topLogoImage != null && !topLogoImage.isEmpty()) {
            String topLogoImagePath = saveFile(topLogoImage, "top");
            siteManageDTO.setTopLogoImage(topLogoImagePath);
        }
        if (bottomLogoImage != null && !bottomLogoImage.isEmpty()) {
            String bottomLogoImagePath = saveFile(bottomLogoImage, "bottom");
            siteManageDTO.setBottomLogoImage(bottomLogoImagePath);
        }
        if (mainImage != null && !mainImage.isEmpty()) {
            String mainImagePath = saveFile(mainImage, "main");
            siteManageDTO.setMainImage(mainImagePath);
        }
        insertSite(siteManageDTO);
    }

    private String saveFile(MultipartFile file, String folder) throws IOException {
    	String uploadDir = "src/main/webapp/api/" + folder;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath);
        return "api/" + folder + "/" + fileName;
    }
	/**
	 * 약관 관리
	 */
	@Override
	public String insertTerms(TermsManageDTO terms) {
		try {
			terms.setTermsId(adminTermsIdGnrService.getNextStringId());
			mapper.insertTerms(terms);
			return "success";
		}catch(Exception e) {
			return "SQL Error";
		}
		
	}

	@Override
	public TermsManageDTO selectTermsById(String termsId) {
		return mapper.selectTermsById(termsId);
	}

	@Override
	public String updateTerms(TermsManageDTO terms) {
		if(mapper.selectTermsById(terms.getTermsId()) != null || terms.getTermsId() != null) {
			mapper.updateTerms(terms);
			return "success";
		}
		return "Terms Not Found";
	}

	@Override
	public void deleteTerms(String termsId) {
		mapper.deleteTerms(termsId);
	}

	@Override
	public List<TermsManageDTO> selectTermsAll(TermsManageDTO data) {
		return mapper.selectTermsAll(data);
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
	public CommonCodeListDTO selectCommonCodesByConditions(CommonCodesDTO data) {
        return CommonCodeListDTO.builder()
                .codeInfoList(mapper.selectCommonCodesByConditions(data))
                .pageNo(data.getPageNo())
                .limit(data.getLimit())
                .totalCount(1)
                .build();
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
	public SubCommonCodeListDTO selectSubCommonCodesByConditions(SubCommonCodesDTO data) {
        return SubCommonCodeListDTO.builder()
                .subCodeList(mapper.selectSubCommonCodesByConditions(data))
                .pageNo(data.getPageNo())
                .limit(data.getLimit())
                .totalCount(1)
                .build();
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
	public IpTableListDTO selectIpsAll(IpTableDTO data) {
        return IpTableListDTO.builder()
                .ipInfoList(mapper.selectIpsAll(data))
                .pageNo(data.getPageNo())
                .totalCount(mapper.selectIpCount())
                .limit(data.getLimit())
                .build();
	}
	
	@Override
	public boolean checkCommonCodeById(String codeId) {
		// TODO Auto-generated method stub
		return mapper.checkCommonCodeById(codeId);
	}
	@Override
	public boolean checkSubCommonCodeById(String codeId) {
		// TODO Auto-generated method stub
		return mapper.checkSubCommonCodeById(codeId);
	}
	
	@Override
	public IpLogListDTO selectIpLogAll(ApiLog log){
		return IpLogListDTO.builder()
				.logList(mapper.selectIpLogAll(log))
				.pageNo(log.getPageNo())
				.recordCount(log.getLimit())
				.totalCount(mapper.selectIpLogCount())
				.build();
	}
}
