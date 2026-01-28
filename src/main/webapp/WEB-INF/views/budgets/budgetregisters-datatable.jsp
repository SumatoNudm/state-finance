<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>

<!-- <table id="budgetTable" class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Start Date</th>
        <th>End Date</th>
    </tr>
    </thead>
</table> -->

<div class="row">
    <div class="col-md-12 table-header text-left">Budget Registers</div>
    <div class="col-md-12 report-table-container">
        <table class="table table-bordered table-hover" id="budgetTable">
            <thead>
                <tr>
                    <th>
                        <spring:message code="lbl.tenantId" text="ULB" />
                    </th>
                    <th>
                        <spring:message code="lbl.budgetRegisterNumber" text="Register No." />
                    </th>
                    <th>
                        <spring:message code="lbl.budgetRegisterName" text="Name" />
                    </th>
                    <th>
                        <spring:message code="lbl.currentFy" text="Financial Year" />
                    </th>

                </tr>
            </thead>
        </table>
    </div>
</div>

<!-- DataTables CSS -->
<link rel="stylesheet"
    href="<c:url value='/resources/global/css/jquery/plugins/datatables/jquery.dataTables.min.css' context='/statefinance'/>" />
<link rel="stylesheet"
    href="<c:url value='/resources/global/css/jquery/plugins/datatables/dataTables.bootstrap.min.css' context='/statefinance'/>" />
<link rel="stylesheet"
    href="<c:url value='/resources/global/css/jquery/plugins/datatables/buttons.bootstrap.min.css' context='/statefinance'/>" />

<!-- jQuery (must be loaded before DataTables) -->
<script src="<c:url value='/resources/global/js/jquery/jquery.js' context='/statefinance'/>"></script>

<!-- DataTables JS -->
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/jquery.dataTables.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/dataTables.bootstrap.js' context='/statefinance'/>">
</script>

<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/dataTables.buttons.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/buttons.bootstrap.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/jszip.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/pdfmake.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/vfs_fonts.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/buttons.html5.min.js' context='/statefinance'/>">
</script>
<script
    src="<c:url value='/resources/global/js/jquery/plugins/datatables/extensions/buttons/buttons.print.min.js' context='/statefinance'/>">
</script>

<!-- DataTable Init -->
<script>
    $(document).ready(function () {
        $('#budgetTable').DataTable({
            dom: 'frt<"row"<"col-md-6"l><"col-md-6"i>>p',
            processing: true,
            serverSide: true,
            ajax: {
                url: '<c:url value="/views/budgets/data"/>',
                type: 'GET'
            },
            columns: [{
                    data: 'tenantId'
                },
                {
                    data: 'budgetRegisterNumber'
                },
                {
                    data: 'budgetRegisterName'
                },
                {
                    data: 'currentFy'
                }
            ]
        });
    });
</script>