package com.loam.framework.core.catalog.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogSubCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryOut;
import com.loam.framework.core.catalog.restservice.account.CatalogAccountOperations;
import com.loam.framework.core.catalog.restservice.category.CatalogCategoryOperations;
import com.loam.framework.core.catalog.restservice.coin.CatalogCoinOperations;
import com.loam.framework.core.catalog.restservice.color.CatalogColorOperations;
import com.loam.framework.core.catalog.restservice.common.CommonRestOperations;
import com.loam.framework.core.catalog.restservice.common.GenericConsultaRequest;
import com.loam.framework.core.catalog.restservice.common.RestConstants;
import com.loam.framework.core.catalog.restservice.contact.CatalogContactMethodOperations;
import com.loam.framework.core.catalog.restservice.exception.CustomHandlerException;
import com.loam.framework.core.catalog.restservice.menu.CatalogMenuOperations;
import com.loam.framework.core.catalog.restservice.product.CatalogProductOperations;
import com.loam.framework.core.catalog.restservice.rol.CatalogRolOperations;
import com.loam.framework.core.catalog.restservice.subcategory.CatalogSubCategoryOperations;
import com.loam.framework.core.catalog.services.account.CatalogAccountService;
import com.loam.framework.core.catalog.services.category.CatalogCategoryService;
import com.loam.framework.core.catalog.services.coin.CatalogCoinService;
import com.loam.framework.core.catalog.services.color.CatalogColorService;
import com.loam.framework.core.catalog.services.contact.CatalogContactMethodService;
import com.loam.framework.core.catalog.services.menu.CatalogMenuService;
import com.loam.framework.core.catalog.services.product.CatalogProductService;
import com.loam.framework.core.catalog.services.rol.CatalogRolService;
import com.loam.framework.core.catalog.services.subcategory.CatalogSubCategoryService;

@RestController
@RequestMapping(value = RestConstants.CAT_CORP_PATH)
public class CatalogManagerService {

	@Autowired
	private CatalogAccountService catalogAccountService;
	@Autowired
	private CatalogCategoryService catalogCategoryService;
	@Autowired
	private CatalogCoinService catalogCoinService;
	@Autowired
	private CatalogColorService catalogColorService;
	@Autowired
	private CatalogContactMethodService catalogContactMethodService;
	@Autowired
	private CatalogMenuService catalogMenuService;
	@Autowired
	private CatalogProductService catalogProductService;
	@Autowired
	private CatalogRolService catalogRolService;
	@Autowired
	private CatalogSubCategoryService catalogSubCategoryService;
	@Autowired
	private CommonProperties commonProperties;
	
	@RequestMapping(value = RestConstants.MENU_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogMenuOut(@RequestBody MaintainCatalogMenuIn catalogMenuIn) throws Exception{
		MaintainCatalogMenuOut maintainCatalogMenuOut = null;
		String dataResponse = null;
		try {
			 if(catalogMenuIn == null || catalogMenuIn.getCatalogMenu() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogMenuOut = catalogMenuService.createCatalogMenuService(catalogMenuIn);
			dataResponse = CatalogMenuOperations.getResponseObj(maintainCatalogMenuOut.getCatalogMenu());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.MENU_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogMenuOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogMenuOut consultCatalogMenuOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogMenuOut = catalogMenuService.consultCatalogMenuService(request.getId(), request.getIdStart(), request.getIdEnd(), request.getOrdenar(), request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogMenuOperations.getResponse(consultCatalogMenuOut.getCatalogMenu());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CONTACT_METHOD_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogContactMethodOut(@RequestBody MaintainCatalogContactMethodIn catalogContactMethodRequest) throws Exception{
		MaintainCatalogContactMethodOut maintainCatalogContactMethodOut = null;
		String dataResponse = null;
		try {
			 if(catalogContactMethodRequest == null || catalogContactMethodRequest.getCatalogContactMethod() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogContactMethodOut = catalogContactMethodService.createCatalogContactMethodService(catalogContactMethodRequest);
			dataResponse = CatalogContactMethodOperations.getResponseObj(maintainCatalogContactMethodOut.getCatalogContactMethod());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CONTACT_METHOD_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogContactMethodOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogContactMethodOut consultCatalogContactMethodOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogContactMethodOut = catalogContactMethodService.consultCatalogContactMethodService(request.getId(), request.getIdStart(), request.getIdEnd(), request.getOrdenar(), request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogContactMethodOperations.getResponse(consultCatalogContactMethodOut.getCatalogContactMethod());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.ROL_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogRolOut(@RequestBody MaintainCatalogRolIn rolRequest) throws Exception{
		MaintainCatalogRolOut maintainCatalogRolOut = null;
		String dataResponse = null;
		try {
			 if(rolRequest == null || rolRequest.getCatalogRol() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogRolOut = catalogRolService.maintainCatalogRolService(rolRequest);
			dataResponse = CatalogRolOperations.getResponseObj(maintainCatalogRolOut.getCatalogRol());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.ROL_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET}, produces="application/json")
	public @ResponseBody ResponseEntity<String> consultCatalogRolOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogRolOut consultCatalogRolOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogRolOut = catalogRolService.consultCatalogRolService(request.getId(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogRolOperations.getResponse(consultCatalogRolOut);
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUENTA_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogCuentaOut(@RequestBody MaintainCatalogAccountIn accountRequest) throws Exception{
		MaintainCatalogAccountOut maintainCatalogAccountOut = null;
		String dataResponse = null;
		try {
			 if(accountRequest == null || accountRequest.getCatalogAccount() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogAccountOut = catalogAccountService.createCatalogAccountService(accountRequest);
			dataResponse = CatalogAccountOperations.getResponseObj(maintainCatalogAccountOut.getCatalogAccount());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUENTA_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogCuentaOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogAccountOut consultCatalogAccountOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogAccountOut = catalogAccountService.consultCatalogAccountService(request.getId(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogAccountOperations.getResponse(consultCatalogAccountOut.getCatalogAccount());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.MONEDA_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogMonedaOut(@RequestBody MaintainCatalogCoinIn coinRequest) throws Exception{
		MaintainCatalogCoinOut maintainCatalogCoinOut = null;
		String dataResponse = null;
		try {
			 if(coinRequest == null || coinRequest.getCatalogCoin() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogCoinOut = catalogCoinService.createCatalogCoinService(coinRequest);
			dataResponse = CatalogCoinOperations.getResponseObj(maintainCatalogCoinOut.getCatalogCoin());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.MONEDA_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogMonedaOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogCoinOut consultCatalogCoinOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogCoinOut = catalogCoinService.consultCatalogCoinService(request.getId(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogCoinOperations.getResponse(consultCatalogCoinOut.getCatalogCoin());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}

	@RequestMapping(value = RestConstants.COLOR_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogColorOut(@RequestBody MaintainCatalogColorIn colorRequest) throws Exception{
		MaintainCatalogColorOut maintainCatalogColorOut = null;
		String dataResponse = null;
		try {
			 if(colorRequest == null || colorRequest.getCatalogColor() != null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogColorOut = catalogColorService.createCatalogColorService(colorRequest);
			dataResponse = CatalogColorOperations.getResponseObj(maintainCatalogColorOut.getCatalogColor());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.COLOR_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogColorOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogColorOut consultCatalogColorOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogColorOut = catalogColorService.consultCatalogColorService(request.getId(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogColorOperations.getResponse(consultCatalogColorOut.getCatalogColor());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.PRODUCTO_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogProductoOut(@RequestBody MaintainCatalogProductIn productRequest) throws Exception{
		MaintainCatalogProductOut maintainCatalogProductOut = null;
		String dataResponse = null;
		try {
			 if(productRequest == null || productRequest.getCatalogProduct() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogProductOut = catalogProductService.createCatalogProductService(productRequest);
			dataResponse = CatalogProductOperations.getResponseObj(maintainCatalogProductOut.getCatalogProduct());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.PRODUCTO_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogProductoOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogProductOut consultCatalogProductOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogProductOut = catalogProductService.consultCatalogProductService(request.getId(),request.getIdSub(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogProductOperations.getResponse(consultCatalogProductOut.getCatalogProduct());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.SUB_CATEGORIA_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogSubCategoryOut(@RequestBody MaintainCatalogSubCategoryIn subCategoryRequest) throws Exception{
		MaintainCatalogSubCategoryOut maintainCatalogSubCategoryOut = null;
		String dataResponse = null;
		try {
			 if(subCategoryRequest == null || subCategoryRequest.getCatalogSubCategory() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogSubCategoryOut = catalogSubCategoryService.createCatalogSubCategoryService(subCategoryRequest);
			dataResponse = CatalogSubCategoryOperations.getResponseObj(maintainCatalogSubCategoryOut.getCatalogSubCategory());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.SUB_CATEGORIA_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogSubCategoryOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogSubCategoryOut consultCatalogSubCategoryOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogSubCategoryOut = catalogSubCategoryService.consultCatalogSubCategoryService(request.getId(),request.getIdSub(), request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogSubCategoryOperations.getResponse(consultCatalogSubCategoryOut.getCatalogSubCategory());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CATEGORIA_CREAR, method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String>  createCatalogCategoryOut(@RequestBody MaintainCatalogCategoryIn categoryRequest) throws Exception{
		MaintainCatalogCategoryOut maintainCatalogCategoryOut = null;
		String dataResponse = null;
		try {
			 if(categoryRequest == null || categoryRequest.getCatalogCategory() == null){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(), commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 maintainCatalogCategoryOut = catalogCategoryService.createCatalogCategoryService(categoryRequest);
			dataResponse = CatalogCategoryOperations.getResponseObj(maintainCatalogCategoryOut.getCatalogCategory());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}

	@RequestMapping(value = RestConstants.CATEGORIA_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultCatalogCategoryOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultCatalogCategoryOut consultCatalogCategoryOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultCatalogCategoryOut = catalogCategoryService.consultCatalogCategoryService(request.getId(),request.getIdStart(),request.getIdEnd(),request.getOrdenar(),request.getDescripcion(), request.getStatusFlag());
			dataResponse = CatalogCategoryOperations.getResponse(consultCatalogCategoryOut.getCatalogCategory());
		} catch (CustomGenericException ex) {
			throw new CustomHandlerException(ex.getMessage(), ex.getExcepcionGenerica(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	public void setCatalogAccountService(CatalogAccountService catalogAccountService) {
		this.catalogAccountService = catalogAccountService;
	}

	public void setCatalogCategoryService(CatalogCategoryService catalogCategoryService) {
		this.catalogCategoryService = catalogCategoryService;
	}

	public void setCatalogCoinService(CatalogCoinService catalogCoinService) {
		this.catalogCoinService = catalogCoinService;
	}

	public void setCatalogColorService(CatalogColorService catalogColorService) {
		this.catalogColorService = catalogColorService;
	}

	public void setCatalogContactMethodService(CatalogContactMethodService catalogContactMethodService) {
		this.catalogContactMethodService = catalogContactMethodService;
	}

	public void setCatalogMenuService(CatalogMenuService catalogMenuService) {
		this.catalogMenuService = catalogMenuService;
	}

	public void setCatalogProductService(CatalogProductService catalogProductService) {
		this.catalogProductService = catalogProductService;
	}

	public void setCatalogRolService(CatalogRolService catalogRolService) {
		this.catalogRolService = catalogRolService;
	}

	public void setCatalogSubCategoryService(CatalogSubCategoryService catalogSubCategoryService) {
		this.catalogSubCategoryService = catalogSubCategoryService;
	}

	public void setCommonProperties(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
	}

}
