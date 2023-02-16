<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="PasswordManager">
    <meta name="author" content="Mariusz Plaskota">

    <title>Password Manager</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/theme/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/theme/css/sb-admin-2.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<c:url value="/theme/vendor/datatables/dataTables.bootstrap4.min.css"/>" rel="stylesheet">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <%@ include file="header.jsp"%>

        <c:forEach items="${account.roleList}" var="role">
            <c:if test="${role.getRole_id() == 1}">
                <%@ include file="admin-panel.jsp"%>
            </c:if>
        </c:forEach>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Operations
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-fw fa-folder"></i>
                <span>Manager</span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <!--<h6 class="collapse-header">Login Screens:</h6>-->
                    <a class="collapse-item" href="/site/listById">Show data</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><c:out value="${account.account_name}"/>&nbsp;<c:out value="${account.account_surname}"/></span>
                            <img class="img-profile rounded-circle" src="<c:url value="/theme/img/undraw_profile.svg"/>">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/account/edit">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="/account/editpassword">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Change password
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Event viewer
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/dashboard/logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Log out
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <%@ include file="heading.jsp"%>
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <a href="/account/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                        <i class="fas fa-download fa-sm text-white-50"></i> Add new account</a>
                </div>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">List of logs</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Lp</th>
                                    <th>Number</th>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Password</th>
                                    <th>Strong auth</th>
                                    <th>Lock</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Lp</th>
                                    <th>Number</th>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Password</th>
                                    <th>Strong auth</th>
                                    <th>Lock</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    Integer counter = (Integer) application.getAttribute("hitCounter");
                                    counter = 0;
                                %>
                                <c:forEach items="${accounts}" var="account">
                                    <%
                                        counter++;
                                        application.setAttribute("hitCounter",counter);
                                    %>
                                    <tr>
                                        <td><%=counter%></td>
                                        <td>
                                            <c:if test="${account.account_id == 1}">${account.account_number}</c:if>
                                            <c:if test="${account.account_id != 1}"><a href='/account/editAdm/${account.account_number}'>${account.account_number}</a></c:if>
                                        </td>
                                        <td>${account.account_name}</td>
                                        <td>${account.account_surname}</td>
                                        <td>${account.account_email}</td>
                                        <td>${account.account_phone}</td>
                                        <td><a href='/account/editpasswordAdm/${account.account_number}'>***</a></td>
                                        <td><input disabled type="checkbox" name="accountStrongAuth" id="accountStrongAuth" <c:if test="${account.account_strong_auth==true}">checked=checked</c:if>></td>
                                        <td><input disabled type="checkbox" name="accountStrongActive" id="accountStrongActive" <c:if test="${account.account_password_blk==true}">checked=checked</c:if>></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <%@ include file="footer.jsp"%>

</body>
</html>