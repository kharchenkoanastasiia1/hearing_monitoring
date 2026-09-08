<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="uk">

<head>
    <title>Patient page</title>
    <meta charset="UTF-8">
    <script>
        let pat_username;
    </script>
    <style>
        h1{
            color: blue;
        }
        h2{
            color: blue;
        }
        body {
            background-color: aliceblue;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #000;
            border-collapse: collapse;
        }
        th {
            font-weight: bold;
            padding: 5px;
            background: cornflowerblue;
            border: 1px solid #000;
            color: #fff;
            font-size: 18px;
        }
        .thSlide{
            padding: 5px;
            background: aliceblue;
            border: 1px solid aliceblue;
        }
        td {
            border: 1px solid #000;
            padding: 5px;
            font-size: 18px;
        }
        .button {
            color: #fff;
            background-color: cornflowerblue;
            font-size: 18px;
            width: 100%;
        }
        .button_ok{
            color: #fff;
            background-color: cornflowerblue;
            font-size: 18px;
        }
    </style>
</head>

<body>
<h2 id="root_name" name="<sec:authentication property="principal.username" />"></h2>
<br>
<table id="table_audiogram"></table>

<input type="button" value="Написати повідомлення" class="button" onclick="setMessageForUser()">
<br><br>
<input type="button" value="Переглянути повідомлення" class="button" onclick="getMessageByUser()">
<br><br>
<div id="base_div"></div>

<script>
    let h2 = document.getElementById("root_name");
    pat_username = h2.getAttribute("name");
    h2.innerHTML = "Вітаємо " + pat_username + "!";
</script>

<script>
    function setMessageForUser(){
        fetch('/hearing_monitoring/patient/addMessagePatient?usernamePatient='+pat_username)
            .then(response => response.text())
            .then(html => {
                document.body.innerHTML = html;
            })
            .catch(error => {
                console.error('Произошла ошибка:', error);
            });
    }
</script>

<script>
    function addAudiogram(audiogram_str){
        fetch('/hearing_monitoring/patient/audiogram', {
            method: 'POST',
            body: audiogram_str
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
                console.log(data);
            });
    }
</script>

<script>
    function addMedian(median){
        fetch('/hearing_monitoring/patient/median', {
            method: 'POST',
            body: median
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
                console.log(data);
            });
    }
</script>

<script>
    function selectDateExamination(){
        fetch('/hearing_monitoring/patient/info/lastDate?usernamePatient='+pat_username)
            .then(response => response.json())
            .then(data => {
                console.log(data.lastDateAudiogram);
                console.log(data.lastDateMedian);
                AndroidFunction.getLastDateExamination(data.lastDateAudiogram, data.lastDateMedian, pat_username);
            });
    }
</script>

<script>
    function getMessageByUser(){
        fetch('/hearing_monitoring/message/showPatientMessagesByUsername?usernamePatient='+pat_username)
            .then(response => response.json())
            .then(data => {
                labels = ["Текст повідомлення", "Дата"];
                createTableMessage(labels, data, "base_div");
            })
            .catch(error => {
                console.error('Произошла ошибка:', error);
            });
    }

    function createTableMessage(labels, data, id_div){
        let base_div = document.getElementById(id_div);
        base_div.innerHTML = "";

        let table = document.createElement("table");

        let tr = document.createElement('tr');
        for(let lab of labels){
            let th = document.createElement('th');
            th.textContent = lab;
            tr.appendChild(th);
        }
        table.appendChild(tr);

        for(let d of data){
            let tr = document.createElement('tr');
            tr.setAttribute('id', d.id);

            let td1 = document.createElement('td');
            td1.textContent = d.text;
            tr.appendChild(td1);
            let td2 = document.createElement('td');
            td2.textContent = d.date;
            tr.appendChild(td2);

            table.appendChild(tr);
        }
        let tr2 = document.createElement('tr');
        let inp5 = document.createElement('input');
        inp5.setAttribute('type', "button");
        inp5.setAttribute('value', "OK");
        inp5.setAttribute('onClick', "window.location.reload()");
        inp5.setAttribute('class', "button_ok")
        tr2.appendChild(inp5);
        table.appendChild(tr2);

        base_div.appendChild(table);
    }
</script>

</body>

</html>