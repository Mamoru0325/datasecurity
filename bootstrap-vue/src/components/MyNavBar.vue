<template>
    <nav class="navbar navbar-expand-lg navbar navbar-light" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <a class="navbar-brand" style="pointer-events: none">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <RouterLink type="btn btn-light" class="nav-link " aria-current="page" to="/"><a style="color:#000000">หน้าหลัก</a></RouterLink>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" 
                            data-bs-toggle="dropdown"  style="color:#000000">
                            เกมถอดรหัส
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><RouterLink class="dropdown-item" to="/game"><a class="dropdown-item" >หน้ารวม</a></RouterLink></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><RouterLink class="dropdown-item" to="/"><a class="dropdown-item" >ระดับง่าย</a></RouterLink></li>
                            <li><RouterLink class="dropdown-item" to="#"><a class="dropdown-item" >ระดับกลาง</a></RouterLink></li>
                            <li><RouterLink class="dropdown-item" to="#"><a class="dropdown-item" >ระดับยาก</a></RouterLink></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <RouterLink type="btn btn-light" class="nav-link" to="/score"><a style="color:#000000">อันดับคะแนน</a></RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink type="btn btn-light" class="nav-link" to="/about"><a style="color:#000000">เกี่ยวกับเรา</a></RouterLink>
                    </li>
                </ul>
                
                <RouterLink type="btn btn-light" class="nav-link" to="#"><a style="color:#000000">LogOut</a></RouterLink>

            </div>
        </div>
    </nav>
</template>

<script>
export default {
    name: 'MyNavBar'
}
</script>