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




// $.ajax({
//     url:  'http://192.168.1.105:8080/member/authentication/info',
//     type: 'get',
//     xhrFields: {
//         withCredentials: true
//     },
//     headers: {
//         'Authorization': sessionStorage.getItem('cookie')
//     },
//     crossDomain: true,
//     success: function (data) {
//         console.log(data.data.info);
//         $("#name").val(data.data.info.realName);
//         $("#idcard").val(data.data.info.idNumber);
//         $("#picImg1").attr('src',data.data.info.negativeUrl);
//         $("#picImg2").attr('src',data.data.info.positiveUrl);
//     } 
//     });
  


