package com.loam.framework.core.catalog.persistence.menu.vo;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;

public class CatalogMenuVo {

	protected int idMenu;
	protected String descMenu;
	protected String urlMenu;
	protected int idMenuPadre;
	protected String descMenuPadre;
	protected String typeMenu;
	protected ElementosComunesVo elementosComunesVo;

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getDescMenu() {
		return descMenu;
	}

	public void setDescMenu(String descMenu) {
		this.descMenu = descMenu;
	}

	public String getUrlMenu() {
		return urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public int getIdMenuPadre() {
		return idMenuPadre;
	}

	public void setIdMenuPadre(int idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}

	public String getDescMenuPadre() {
		return descMenuPadre;
	}

	public void setDescMenuPadre(String descMenuPadre) {
		this.descMenuPadre = descMenuPadre;
	}

	public String getTypeMenu() {
		return typeMenu;
	}

	public void setTypeMenu(String typeMenu) {
		this.typeMenu = typeMenu;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
