<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<script>
    $(function () {
        $.ajax({
            url: 'http://192.168.1.105:8080/user/profile/info',
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            data: {
                'authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjE4ODg4NjQzMDkzIiwiZXhwIjoxNTIxMzY1MDQxLCJ1c2VySWQiOjE1fQ.K9EsAGB6T2-MzLjiaAqKYhWU6izl8ckNeIvG1CWqeX0'
            },
            method: 'get',
            success: function (result) {
                console.info(result)
            }

        })
    })
</script>

</body>
</html>
