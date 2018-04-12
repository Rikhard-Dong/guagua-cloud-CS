function checkname() {
    //			姓名
    var name = document.getElementById("name");
    if (name.value == '') {
        document
            .getElementById("nametip")
            .value = "*姓名不能为空";
        name.focus();
        return false;
    } else {
        var vname = (/^[a-zA-Z\u4e00-\u9fa5]{2,10}$/);

        if (!vname.test(name.value)) {
            document
                .getElementById("nametip")
                .value = "姓名必须是中文";
            name.focus();
            return false;
        }
    }
}
function checkphone() {

    //			电话
    var phone = document.getElementById('phone');
    if (phone.value == '') {
        document
            .getElementById("phonetip")
            .value = "电话不能为空";

        phone.focus();
        return false;
    } else {
        var phone_reg = /^13[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}|17[0-9]{1}[0-9]{8}$|14[0-9]{1}[0-9]{8}/;
        var tel_reg = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        if (!phone_reg.test(phone.value) && !tel_reg.test(phone.value)) {
            document
                .getElementById("phonetip")
                .value = "电话号码不合法";
            phone.focus();
            return false;
        }
    }
}

function checkidcard() {
    document
        .getElementById("nametip")
        .value = "";
};

function checkemail() {
    //			邮箱
    var email = document.getElementById('email');
    document
        .getElementById("phonetip")
        .value = "";
    if (email.value == '') {
        document
            .getElementById("emailtip")
            .value = "邮箱不能为空";
        email.focus();
        return false;
    } else {
        var vemail = /^[0-9a-zA-Z]+(?:[\_\.\-][a-z0-9\-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\.[a-zA-Z]+$/;
        if (!vemail.test(email.value)) {
            document
                .getElementById("emailtip")
                .value = "邮箱格式不正确";
            email.focus();
            return false;
        }
    }
}
function checkaddress() {
    //			地址
    var address = document.getElementById('address');
    document
        .getElementById("emailtip")
        .value = "";
    if (address.value == '') {
        document
            .getElementById("addresstip")
            .value("地址不能为空");
        address.focus();
        return false;
    } else {
        if (address.value.length < 8) {
            document
                .getElementById("addresstip")
                .value("地址至少8个字");
            address.focus();
            return false;
        }
    }

}


  


