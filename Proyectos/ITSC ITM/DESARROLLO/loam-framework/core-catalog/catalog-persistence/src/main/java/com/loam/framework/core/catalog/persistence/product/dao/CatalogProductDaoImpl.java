package com.loam.framework.core.catalog.persistence.product.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogProduct;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;
import com.loam.framework.core.catalog.persistence.product.vo.CatalogCategSubCategProdVo;

public class CatalogProductDaoImpl extends JdbcDao implements CatalogProductDao {
	private static final Logger log = Logger.getLogger(CatalogProductDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateProduct;
	private String qryModifyProduct;
	private String qryConsultProduct;
	private String qryConsultAllProduct;
	private String nextIdProduct;
	private String existIdProduct;
	private JdbcQueryParameters qryAllProduct;
	private JdbcQueryParameters qryCountAllProduct;
	private JdbcQueryParameters qryConsultProductBySubCateg;

	private static ParameterizedRowMapper<CatalogProduct> MAPPER_CAT_PRODUCT = new ParameterizedRowMapper<CatalogProduct>() {
		public CatalogProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogProduct obj = new CatalogProduct();
			obj.setIdProduct(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PRODUCT_CAT"))));
			obj.setNameProduct(rs.getString("NAME_PRODUCT_CAT"));
			obj.setDescProduct(rs.getString("DESC_PRODUCT_CAT"));
			obj.setIdSubCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_SUBCATEGORIE_CAT"))));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	private static ParameterizedRowMapper<CatalogCategSubCategProdVo> MAPPER_CAT_CATEG_SUCATEG_PRODUCT = new ParameterizedRowMapper<CatalogCategSubCategProdVo>() {
		public CatalogCategSubCategProdVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogCategSubCategProdVo obj = new CatalogCategSubCategProdVo();
			obj.setIdCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CATEGORIE_CAT"))));
			obj.setDescCategory(rs.getString("DESC_CATEGORIE_CAT"));
			obj.setIdSubCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_SUBCATEGORIE_CAT"))));
			obj.setDescSubCategory(rs.getString("DESC_SUBCATEGORIE_CAT"));
			obj.setIdProduct(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PRODUCT_CAT"))));
			obj.setNameProduct(rs.getString("NAME_PRODUCT_CAT"));
			obj.setIdAccountProduct(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ACCOUNT_PRODUCT"))));
			obj.setPrice(BigDecimal.valueOf(Long.valueOf(rs.getString("PRICE"))));
			obj.setIdCoin(BigInteger.valueOf(Long.valueOf(rs.getString("STOCK"))));
			obj.setStock(Long.valueOf(rs.getString("ID_COIN_CAT")));

			return obj;
		}

	};

	@SuppressWarnings("unused")
	@Override
	public CatalogProduct createCatalogProductDao(final CatalogProduct catalogProduct) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogProductDao...");
		}

		try {
			final int nextIdProd = jdbcTemplate.queryForInt(nextIdProduct);
			catalogProduct.setIdProduct(BigInteger.valueOf(nextIdProd));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateProduct);
					ps.setString(1, String.valueOf(catalogProduct.getIdProduct()));
					ps.setString(2, catalogProduct.getNameProduct());
					ps.setString(3, catalogProduct.getDescProduct());
					ps.setString(4, String.valueOf(catalogProduct.getIdSubCategory()));
					ps.setInt(5, catalogProduct.getStatusFlag());
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogProduct.getStartDt())));
					ps.setTimestamp(7, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogProduct.getLastUpdateDt())));
					ps.setString(8, catalogProduct.getLastUpdateUser());
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "createCatalogProductDao",
							"Ocurrio un error al hacer createCatalogProductDao "));
		}
		return catalogProduct;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogProduct modifyCatalogProductDao(final CatalogProduct catalogProduct) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogProductDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyProduct);
					ps.setString(1, catalogProduct.getNameProduct());
					ps.setString(2, catalogProduct.getDescProduct());
					ps.setString(3, String.valueOf(catalogProduct.getIdSubCategory()));
					ps.setInt(4, catalogProduct.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogProduct.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogProduct.getLastUpdateDt())));
					ps.setString(7, catalogProduct.getLastUpdateUser());
					ps.setString(8, String.valueOf(catalogProduct.getIdProduct()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "modifyCatalogProductDao",
							"Ocurrio un error al hacer modifyCatalogProductDao "));
		}
		return catalogProduct;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogProduct> consultCatalogProductDao(long idProduct, long idSubCategory, int startReg, int endReg,
			String order, String descProduct, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogProductDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllProduct.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllProduct.getOptionalParameters();
		if (idProduct != 0) {
			stb.append(" ").append(params.get("idProduct"));
			parameters.add(idProduct);
		}
		if (idSubCategory != 0) {
			stb.append(" ").append(params.get("idSubCatego"));
			parameters.add(idSubCategory);
		}
		if (descProduct != null && !descProduct.equals("")) {
			stb.append(" ").append(params.get("descProduct"));
			parameters.add("%" + descProduct.toUpperCase() + "%");
		}
		if (statusFlag <= 1) {
			stb.append(" ").append(params.get("statusFlag"));
			parameters.add(statusFlag);
		}
		if (order != null && !order.equals("")) {
			stb.append(" ").append(params.get("order"));
			if (order.toUpperCase().equals("ASC")) {
				stb.append(" ").append(params.get("asc"));
			} else {
				stb.append(" ").append(params.get("desc"));
			}
		}
		if (startReg != 0 || endReg != 0) {
			stb.append(" ").append(params.get("limite"));
			parameters.add(startReg);
			parameters.add(endReg);
		}

		if (qryAllProduct.getSqlLast() != null && !qryAllProduct.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllProduct.getSqlLast());
		}

		Object[] args = parameters.toArray();

		List<CatalogProduct> consultCatalogProductDao = null;

		try {
			consultCatalogProductDao = (List<CatalogProduct>) jdbcTemplate.query(stb.toString(), args,
					MAPPER_CAT_PRODUCT);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "consultCatalogProductDao",
							"Ocurrio un error al hacer consultCatalogProductDao "));
		}
		return consultCatalogProductDao;
	}

	@Override
	public int existCatalogProductDao(long idProduct) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogProductDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idProduct);
		Object[] args = parameters.toArray();
		int exisIdProduct = 0;

		try {
			exisIdProduct = jdbcTemplate.queryForInt(existIdProduct, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "existCatalogProductDao",
							"Ocurrio un error al hacer existCatalogProductDao "));
		}
		return exisIdProduct;
	}

	@Override
	public List<CatalogProduct> consultAllCatalogProductDao(long idSubCategory, int start, int end, int statusFlag)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultAllCatalogProductDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idSubCategory);
		parameters.add(start);
		parameters.add(end);
		Object[] args = parameters.toArray();
		List<CatalogProduct> consultAllCatalogProductDao = null;

		try {
			consultAllCatalogProductDao = (List<CatalogProduct>) jdbcTemplate.query(qryConsultAllProduct, args,
					MAPPER_CAT_PRODUCT);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "consultAllCatalogProductDao",
							"Ocurrio un error al hacer consultAllCatalogProductDao "));
		}
		return consultAllCatalogProductDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogCategSubCategProdVo> consultCategSubCategProdDao(long idSubCategory, int startReg, int endReg,
			String order, int statusFlag) throws Exception {
		StringBuffer stb = new StringBuffer(qryConsultProductBySubCateg.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryConsultProductBySubCateg.getOptionalParameters();
		if (idSubCategory != 0) {
			stb.append(" ").append(params.get("idSubCatego"));
			parameters.add(idSubCategory);
		}
		if (statusFlag <= 1) {
			stb.append(" ").append(params.get("statusFlag"));
			parameters.add(statusFlag);
		}
		if (order != null && !order.equals("")) {
			stb.append(" ").append(params.get("order"));
			if (order.toUpperCase().equals("ASC")) {
				stb.append(" ").append(params.get("asc"));
			} else {
				stb.append(" ").append(params.get("desc"));
			}
		}
		if (startReg != 0 || endReg != 0) {
			stb.append(" ").append(params.get("limite"));
			parameters.add(startReg);
			parameters.add(endReg);
		}

		if (qryConsultProductBySubCateg.getSqlLast() != null && !qryConsultProductBySubCateg.getSqlLast().equals("")) {
			stb.append(" ").append(qryConsultProductBySubCateg.getSqlLast());
		}

		Object[] args = parameters.toArray();

		List<CatalogCategSubCategProdVo> consultCatalogCategSubCategProdDao = null;

		try {
			consultCatalogCategSubCategProdDao = (List<CatalogCategSubCategProdVo>) jdbcTemplate.query(stb.toString(),
					args, MAPPER_CAT_CATEG_SUCATEG_PRODUCT);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "consultCategSubCategProdDao",
							"Ocurrio un error al hacer consultCategSubCategProdDao "));
		}
		return consultCatalogCategSubCategProdDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogProductDao(long idProduct, long idSubCategory, int startReg, int endReg, String order,
			String descProduct, int statusFlag) throws Exception {
		StringBuffer stb = new StringBuffer(qryCountAllProduct.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllProduct.getOptionalParameters();
		if (idProduct != 0) {
			stb.append(" ").append(params.get("idProduct"));
			parameters.add(idProduct);
		}
		if (idSubCategory != 0) {
			stb.append(" ").append(params.get("idSubCatego"));
			parameters.add(idSubCategory);
		}
		if (descProduct != null && !descProduct.equals("")) {
			stb.append(" ").append(params.get("descProduct"));
			parameters.add("%" + descProduct.toUpperCase() + "%");
		}
		if (statusFlag <= 1) {
			stb.append(" ").append(params.get("statusFlag"));
			parameters.add(statusFlag);
		}
		if (order != null && !order.equals("")) {
			stb.append(" ").append(params.get("order"));
			if (order.toUpperCase().equals("ASC")) {
				stb.append(" ").append(params.get("asc"));
			} else {
				stb.append(" ").append(params.get("desc"));
			}
		}
		if (startReg != 0 || endReg != 0) {
			stb.append(" ").append(params.get("limite"));
			parameters.add(startReg);
			parameters.add(endReg);
		}

		if (qryCountAllProduct.getSqlLast() != null && !qryCountAllProduct.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllProduct.getSqlLast());
		}

		int countCatalogProductDao = 0;
		Object[] args = parameters.toArray();
		try {
			countCatalogProductDao = jdbcTemplate.queryForInt(qryConsultProduct, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductDaoImpl", "countCatalogProductDao",
							"Ocurrio un error al hacer countCatalogProductDao "));
		}
		return countCatalogProductDao;
	}

	public void setQryCreateProduct(String qryCreateProduct) {
		this.qryCreateProduct = qryCreateProduct;
	}

	public void setQryModifyProduct(String qryModifyProduct) {
		this.qryModifyProduct = qryModifyProduct;
	}

	public void setQryConsultProduct(String qryConsultProduct) {
		this.qryConsultProduct = qryConsultProduct;
	}

	public void setQryConsultAllProduct(String qryConsultAllProduct) {
		this.qryConsultAllProduct = qryConsultAllProduct;
	}

	public void setNextIdProduct(String nextIdProduct) {
		this.nextIdProduct = nextIdProduct;
	}

	public void setExistIdProduct(String existIdProduct) {
		this.existIdProduct = existIdProduct;
	}

	public void setQryAllProduct(JdbcQueryParameters qryAllProduct) {
		this.qryAllProduct = qryAllProduct;
	}

	public void setQryCountAllProduct(JdbcQueryParameters qryCountAllProduct) {
		this.qryCountAllProduct = qryCountAllProduct;
	}

	public void setQryConsultProductBySubCateg(JdbcQueryParameters qryConsultProductBySubCateg) {
		this.qryConsultProductBySubCateg = qryConsultProductBySubCateg;
	}

}
