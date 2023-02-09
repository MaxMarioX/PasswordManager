<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="PasswordManager">
    <meta name="author" content="Mariusz Plaskota">

    <title>Password Manager</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/theme/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/theme/css/sb-admin-2.min.css"/>" rel="stylesheet">
</head>

<body id="page-top">

<c:if test="${not empty Message}">
    <script>
        window.addEventListener("load", function() {
            alert("${Message}")
        })
    </script>
</c:if>

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <%@ include file="header.jsp"%>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Administration
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <!-- DLA ADMINISTRATORA-->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span>Components</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <!--<h6 class="collapse-header">Components:</h6>-->
                    <a class="collapse-item" href="/account/listAll">User accounts</a>
                    <a class="collapse-item" href="#">Permissions</a>
                    <a class="collapse-item" href="/log/listAllAdm">Event log</a>
                </div>
            </div>
        </li>

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

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->

                    <div class="topbar-divider d-none d-sm-block"></div>

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
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="/account/editpassword">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Change password
                            </a>
                            <a class="dropdown-item" href="/log/listById">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Event viewer
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
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

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Profile data</h6>
                    </div>
                    <div class="card-body">
                        <form method="post" action="/account/edit">
                            <input type="hidden" name="id" value=""/>
                            <div class="form-group">
                                <label for="accountID">Account ID</label>
                                <input disabled value="<c:out value="${account.account_number}"/>" name="accountID" type="text" class="form-control" id="accountID" placeholder="Account ID">
                            </div>
                            <div class="form-group">
                                <label for="accountName">Name</label>
                                <input value="<c:out value="${account.account_name}"/>" name="accountName" type="text" class="form-control" id="accountName" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <label for="accountSurname">Surname</label>
                                <input value="<c:out value="${account.account_surname}"/>" name="accountSurname" type="text" class="form-control" id="accountSurname" placeholder="Surname">
                            </div>
                            <div class="form-group">
                                <label for="accountEmail">Email</label>
                                <input value="<c:out value="${account.account_email}"/>" name="accountEmail" type="text" class="form-control" id="accountEmail" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label for="accountPhone">Phone</label>
                                <input value="<c:out value="${account.account_phone}"/>" name="accountPhone" type="text" class="form-control" id="accountPhone" placeholder="Phone">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="accountStrongAuth" id="accountStrongAuth" <c:if test="${account.account_strong_auth==true}">checked=checked</c:if>>
                                    </div>
                                </div>
                                <label type="text" class="form-control">Strong authentication</label>
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form>
                    </div>
                </div>

                <!-- Content Row -->
                <div class="row">

                </div>

                <!-- Content Row -->

                <div class="row">

                    <!-- Content Row -->
                    <div class="row">

                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@ include file="footer.jsp"%>
</body>
</html>