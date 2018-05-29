<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:set var="version" value="${pageScope.random}"/>
<script src="./resources/jquery/js/jquery.min.js"></script>	
<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./resources/jquery/js/custom-datatable/jquery.datatable.js"></script>
<script type="text/javascript" src="./resources/jquery/js/custom-datatable/jquery.custom-datatable.js"></script>
<link rel="stylesheet" type="text/css" href="resources/jquery/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap_t.css">
<!-- Modal Start here-->
<div class="modal fade bs-example-modal-sm" id="myPleaseWait" tabindex="-1"
    role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-time">
                    </span>Please Wait
                 </h4>
            </div>
            <div class="modal-body">
                <div class="progress">
                    <div class="progress-bar progress-bar-info
                    progress-bar-striped active"
                    style="width: 100%">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal ends Here -->