package com.loam.framework.core.security.restservice.common;

public class RestConstants {
	public static final String CAT_CORP_PATH = "/CorporateCatalog";
	public static final String ROL_CONSULTAR = "/Rol/GenericConsultaRequest";
	public static final String ROL_CREAR = "/Rol";
	public static final String ROL_MODIFICAR = "/Rol";
	
	public static final String CATEGORIA_CONSULTAR = "/Category/{idCategory}";
	public static final String CATEGORIA_CREAR = "/Category";
	public static final String CATEGORIA_MODIFICAR = "/Category";
	
	public static final String CUENTA_CONSULTAR = "/Account/{idAccount}";
	public static final String CUENTA_CREAR = "/Account";
	public static final String CUENTA_MODIFICAR = "/Account";
	
	public static final String MONEDA_CONSULTAR = "/Coin/{idCoin}";
	public static final String MONEDA_CREAR = "/Coin";
	public static final String MONEDA_MODIFICAR = "/Coin";
	
	public static final String COLOR_CONSULTAR = "/Color/{idColor}";
	public static final String COLOR_CREAR = "/Color";
	public static final String COLOR_MODIFICAR = "/Color";
	
	public static final String PRODUCTO_CONSULTAR = "/Product/{idProduct}";
	public static final String PRODUCTO_CREAR = "/Product";
	public static final String PRODUCTO_MODIFICAR = "/Product";
	
	public static final String SUB_CATEGORIA_CONSULTAR = "/SubCategory/{idSubCategory}";
	public static final String SUB_CATEGORIA_CREAR = "/SubCategory";
	public static final String SUB_CATEGORIA_MODIFICAR = "/SubCategory";
	
	public static final String INFO_CUENTA_PATH = "/InformationAccount";
	public static final String INFO_CUENTA_CONSULTAR = "/Information/{idAccount}";
	public static final String INFO_CUENTA_CREAR = "/Information/Account";
	public static final String INFO_CUENTA_MODIFICAR = "/Information/Account";
	
	public static final String INFO_USUARIO_CONSULTAR = "/Information/{idUser}";
	public static final String INFO_USUARIO_CREAR = "/Information/User";
	public static final String INFO_USUARIO_MODIFICAR = "/Information/User";
	
	public static final String SEGURIDAD_PATH = "/CorporateSecurity";
	public static final String SEGURIDAD_CONSULTAR_LOGIN = "/Login/{alias}/{pass}";
	public static final String SEGURIDAD_CONSULTAR_TOKEN = "/Token/{idToken}";
	public static final String SEGURIDAD_CONSULTAR_ALIAS = "/Alias/{idAlias}";
	public static final String SEGURIDAD_CONSULTAR_EMAIL = "/Email/{idEmail}";
	
	public static final String INFO_ALIAS_CONSULTAR = "/Information/{idAlias}";
	public static final String INFO_ALIAS_CREAR = "/Information/Alias";
	public static final String INFO_ALIAS_MODIFICAR = "/Information/Alias";
}
