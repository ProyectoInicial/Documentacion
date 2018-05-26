package com.itsc.tms.web.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@Results({
    @Result(name = "stream", type = "stream", params = {"contentType",
        "text/html", "inputName", "inputStream"}),
    @Result(name = "streamjson", type = "stream", params = {"contentType",
        "application/json; charset=utf-8", "inputName", "inputStream"})})
public class CommonAction extends ActionSupport implements SessionAware,
        RequestAware, ServletResponseAware {

    private static final long serialVersionUID = 2968206981775201840L;

    private static final Logger log = Logger.getLogger(CommonAction.class);

    @SuppressWarnings("rawtypes")
    protected Map session;

    @SuppressWarnings("rawtypes")
    protected Map request;

    protected HttpServletResponse response;

    protected InputStream inputStream;

    protected static final String STREAM = "stream";
    protected static final String RESULT = "result";

    protected final String KEY_ORDEN = "sort[0][field]";
    protected final String TYPE_ORDEN = "sort[0][dir]";
    protected final String COLUMN_START = "columns[";
    protected final String COLUMN_END = "][name]";
    protected String sorter;
    protected String columOrder;
    protected String[] idColumnOrder;

    protected final String FILTER_OPER = "filter[filters][0][operator]";
    protected final String FILTER_VAL = "filter[filters][0][value]";
    protected final String FILTER_FIELD = "filter[filters][0][field]";
    protected final String FILTER_LOGIC = "filter[logic]";
    protected String filterOperator;
    protected String filterValue;
    protected String filterField;
    protected String filterLogic;

    /**
     * Test usuario
     */
    protected final String TEST_USER = "user";
    protected String user;

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @SuppressWarnings("unchecked")
    public void setRequest(Map request) {
        this.request = request;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public final InputStream getInputStream() {
        return inputStream;
    }

    public final void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    protected void streamIt(JsonObject messages) {
        try {
            inputStream = new ByteArrayInputStream(messages.toString()
                    .getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("streamIt", e);
            inputStream = new ByteArrayInputStream(messages.toString()
                    .getBytes());
        }
    }

    public void setValoresOrder() {

        HttpServletRequestWrapper request = (HttpServletRequestWrapper) ServletActionContext.getRequest();
        Map parametros = request.getParameterMap();

        idColumnOrder = (String[]) parametros.get(KEY_ORDEN);
        if (idColumnOrder != null) {
            sorter = ((String[]) parametros.get(TYPE_ORDEN))[0];
            columOrder = ((String[]) parametros.get(KEY_ORDEN))[0];
        }
        if (parametros.get(FILTER_VAL) != null) {
            filterOperator = ((String[]) parametros.get(FILTER_OPER))[0];
            filterValue = ((String[]) parametros.get(FILTER_VAL))[0];
            filterField = ((String[]) parametros.get(FILTER_FIELD))[0];
            filterLogic = ((String[]) parametros.get(FILTER_LOGIC))[0];
        }
    }

    public String getKeyForClaveOrder(String id) {
        StringBuilder stm = new StringBuilder(COLUMN_START);
        return stm.append(id).append(COLUMN_END).toString();
    }

    private boolean isLogActive() {
        Properties properties = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(new File("/herramientas/apps/intranet/sumando/MDM_fastcash/fastcash.properties"));
            properties.load(input);
            input.close();
            return Boolean.valueOf(properties.getProperty("log.active"));
        } catch (Exception ex) {
            if (log.isDebugEnabled()) {
                log.error("Error al determinar si se generará el log de la aplicación.", ex);
            }
            return false;
        }
    }

    public void logInfo(Logger log, String message) {
        if (log.isInfoEnabled() && isLogActive()) {
            log.info(message);
        }
    }

    public void logInfo(Logger log, String message, Throwable t) {
        if (log.isInfoEnabled() && isLogActive()) {
            log.info(message, t);
        }
    }

    public void logError(Logger log, String message) {
        if (log.isDebugEnabled() && isLogActive()) {
            log.error(message);
        }
    }

    public void logError(Logger log, String message, Throwable t) {
        if (log.isDebugEnabled() && isLogActive()) {
            log.error(message);
        }
    }

    public void logDebug(Logger log, String message) {
        if (log.isDebugEnabled() && isLogActive()) {
            log.error(message);
        }
    }

    public void logDebug(Logger log, String message, Throwable t) {
        if (log.isDebugEnabled() && isLogActive()) {
            log.error(message);
        }
    }
}
