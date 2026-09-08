<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="uk">

<head>
    <title>Admin page</title>
    <meta charset="UTF-8">
    <script>
        let doc_id;
        let pat_id;
    </script>
    <style>
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
        }
        td {
            border: 1px solid #000;
            padding: 5px;
        }
        .button {
            color: #fff;
            background-color: cornflowerblue;
            font-size: 14px;
        }
        nav {
            display: block;
            width: 100%;
            margin: 0 auto 3%;
        }
        ul {
            list-style: none;
            margin: 0 auto;
        }
        .two ul {
            background: aliceblue;
            overflow: hidden;
            padding: 0;
        }
        .two li {
            float: left;
        }
        .two input {
            display: block;
            font-size: 16px;
            border-right: 1px solid cornflowerblue;
            background: cornflowerblue;
            color: #ffffff;
        }
        .two input:hover {
            background: blue;
            color: #ffffff;
        }
    </style>
</head>

<body>

<h2>Всі лікарі:</h2>

<table id="table_doctor"></table>

<nav class="two">
    <ul>
        <li><input type="button" value="Додати" class="button" onclick="onClickButtonAddDoctor()"></li>
        <li><input type="button" value="Оновити" class="button" onclick="onClickButtonUpdateDoctor()"></li>
        <li><input type="button" value="Видалити" class="button" onclick="onClickButtonDeleteDoctor()"></li>
        <li><input type="button" value="Згенерувати пароль" class="button" onclick="onClickButtonGeneratePasswordDoctor()"></li>
    </ul>
</nav>

<table id="table_message_doctor"></table>

<h2>Всі пацієнти:</h2>

<table id="table_patient"></table>

<nav class="two">
    <ul>
        <li><input type="button" value="Додати" class="button" onclick="onClickButtonAddPatient()"></li>
        <li><input type="button" value="Оновити" class="button" onclick="onClickButtonUpdatePatient()"></li>
        <li><input type="button" value="Видалити" class="button" onclick="onClickButtonDeletePatient()"></li>
        <li><input type="button" value="Згенерувати пароль" class="button" onclick="onClickButtonGeneratePasswordPatient()"></li>
    </ul>
</nav>

<table id="table_message_patient"></table>

<%-----------------------------------------Add/Update/Delete/Generate password/Message DOCTOR-----------------------------------------%>

<script>
    function onClickButtonAddDoctor(){
        fetch('/hearing_monitoring/admin/addDoctor')
            .then(response => response.text())
            .then(html => {
                document.body.innerHTML = html;
            })
            .catch(error => {
                console.error('Произошла ошибка:', error);
            });
    }

    function onClickButtonUpdateDoctor(){
        if(doc_id !== undefined){
            fetch('/hearing_monitoring/admin/updateDoctor?doctorId='+doc_id)
                .then(response => response.text())
                .then(html => {
                    document.body.innerHTML = html;
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть лікаря для дії");
        }
    }

    function onClickButtonDeleteDoctor(){
        if(doc_id !== undefined){
            fetch('/hearing_monitoring/admin/deleteDoctor?doctorId='+doc_id)
                .then(response => response.text())
                .then(html => {
                    window.location.reload();
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть лікаря для дії");
        }
    }

    function onClickButtonGeneratePasswordDoctor(){
        if(doc_id !== undefined){
            fetch('/hearing_monitoring/admin/generatePasswordDoctor?doctorId='+doc_id)
                .then(response => response.text())
                .then(html => {
                    window.location.reload();
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть лікаря для дії");
        }
    }

    function onClickButtonSelectMessagesDoctor(){
        if(doc_id !== undefined){
            fetch('/hearing_monitoring/message/showDoctor?doctorId='+doc_id)
                .then(response => response.json())
                .then(data => {
                    labels = ["Текст повідомлення", "Дата", "Статус"];
                    createTableMessage(labels, data, 'table_message_doctor');
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть лікаря для дії");
        }
    }

    function createTableDoctor(labels, data, id_table){
        let table = document.getElementById(id_table);

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
            td1.textContent = d.name;
            tr.appendChild(td1);
            let td2 = document.createElement('td');
            td2.textContent = d.surname;
            tr.appendChild(td2);
            let td3 = document.createElement('td');
            td3.textContent = d.email;
            tr.appendChild(td3);
            let td4 = document.createElement('td');
            td4.textContent = d.username;
            tr.appendChild(td4);

            table.appendChild(tr);
        }
        // let tr2 = document.createElement('tr');
        // let inp1 = document.createElement('input');
        // inp1.setAttribute('type', "button");
        // inp1.setAttribute('value', "Додати");
        // inp1.setAttribute('onClick', "onClickButtonAddDoctor()");
        // inp1.setAttribute('class', "button")
        // tr2.appendChild(inp1);
        // let inp2 = document.createElement('input');
        // inp2.setAttribute('type', "button");
        // inp2.setAttribute('value', "Оновити");
        // inp2.setAttribute('onClick', "onClickButtonUpdateDoctor()");
        // inp2.setAttribute('class', "button")
        // tr2.appendChild(inp2);
        // let inp3 = document.createElement('input');
        // inp3.setAttribute('type', "button");
        // inp3.setAttribute('value', "Видалити");
        // inp3.setAttribute('onClick', "onClickButtonDeleteDoctor()");
        // inp3.setAttribute('class', "button")
        // tr2.appendChild(inp3);
        // let inp4 = document.createElement('input');
        // inp4.setAttribute('type', "button");
        // inp4.setAttribute('value', "Згенерувати пароль");
        // inp4.setAttribute('onClick', "onClickButtonGeneratePasswordDoctor()");
        // inp4.setAttribute('class', "button")
        // tr2.appendChild(inp4);
        // let inp5 = document.createElement('input');
        // inp5.setAttribute('type', "button");
        // inp5.setAttribute('value', "Переглянути повідомлення");
        // inp5.setAttribute('onClick', "onClickButtonSelectMessagesDoctor()");
        // inp5.setAttribute('class', "button")
        // tr2.appendChild(inp5);
        // table.appendChild(tr2);
    }
    fetch('/hearing_monitoring/doctor/doctors')
    .then(response => response.json())
    .then(data => {
        labels = ["Ім'я", "Прізвище", "email", "username"];
        createTableDoctor(labels, data, 'table_doctor');
    });
</script>

<%-----------------------------------------Listener table DOCTOR-----------------------------------------%>

<script>
    function selectRowDoc(eventDoc){
        if(eventDoc.target.tagName === 'TD'){
            if(preventDoc !== undefined){
                if(preventDoc.style.background === "cyan" && preventDoc !== eventDoc.target.parentNode){
                    preventDoc.style.background = "aliceblue";
                }
            }
            eventDoc.target.parentNode.style.background = "cyan";
            preventDoc = eventDoc.target.parentNode;
            doc_id = eventDoc.target.parentNode.getAttribute('id');
        }
    }
    let tableListDoc = document.getElementById('table_doctor');
    let preventDoc;
    tableListDoc.addEventListener("click", selectRowDoc );
</script>

<%-----------------------------------------Add/Update/Delete/Generate password/Message PATIENT-----------------------------------------%>

<script>
    function onClickButtonAddPatient(){
        fetch('/hearing_monitoring/admin/addPatient')
            .then(response => response.text())
            .then(html => {
                document.body.innerHTML = html;
            })
            .catch(error => {
                console.error('Произошла ошибка:', error);
            });
    }

    function onClickButtonUpdatePatient(){
        if(pat_id !== undefined){
            fetch('/hearing_monitoring/admin/updatePatient?patientId='+pat_id)
                .then(response => response.text())
                .then(html => {
                    document.body.innerHTML = html;
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть пацієнта для дії");
        }
    }

    function onClickButtonDeletePatient(){
        if(pat_id !== undefined){
            fetch('/hearing_monitoring/admin/deletePatient?patientId='+pat_id)
                .then(response => response.text())
                .then(html => {
                    window.location.reload();
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть пацієнта для дії");
        }
    }

    function onClickButtonGeneratePasswordPatient(){
        if(pat_id !== undefined){
            fetch('/hearing_monitoring/admin/generatePasswordPatient?patientId='+pat_id)
                .then(response => response.text())
                .then(html => {
                    window.location.reload();
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть пацієнта для дії");
        }
    }

    function onClickButtonSelectMessagesPatient(){
        if(pat_id !== undefined){
            fetch('/hearing_monitoring/message/showPatient?patientId='+pat_id)
                .then(response => response.json())
                .then(data => {
                    labels = ["Текст повідомлення", "Дата", "Статус"];
                    createTableMessage(labels, data, 'table_message_patient');
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        }
        else{
            alert("Виберіть пацієнта для дії");
        }
    }

    function createTablePatient(labels, data, id_table){
        let table = document.getElementById(id_table);

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
            td1.textContent = d.name;
            tr.appendChild(td1);
            let td2 = document.createElement('td');
            td2.textContent = d.surname;
            tr.appendChild(td2);
            let td3 = document.createElement('td');
            td3.textContent = d.age;
            tr.appendChild(td3);
            let td4 = document.createElement('td');
            if(d.sex === 0){
                td4.textContent = 'чоловіча';
            } else if(d.sex === 1){
                td4.textContent = 'жіноча';
            }
            tr.appendChild(td4);
            let td5 = document.createElement('td');
            td5.textContent = d.email;
            tr.appendChild(td5);
            let td6 = document.createElement('td');
            td6.textContent = d.username;
            tr.appendChild(td6);

            table.appendChild(tr);
        }
        // let tr2 = document.createElement('tr');
        // let inp1 = document.createElement('input');
        // inp1.setAttribute('type', "button");
        // inp1.setAttribute('value', "Додати");
        // inp1.setAttribute('onClick', "onClickButtonAddPatient()");
        // inp1.setAttribute('class', "button")
        // tr2.appendChild(inp1);
        // let inp2 = document.createElement('input');
        // inp2.setAttribute('type', "button");
        // inp2.setAttribute('value', "Оновити");
        // inp2.setAttribute('onClick', "onClickButtonUpdatePatient()");
        // inp2.setAttribute('class', "button")
        // tr2.appendChild(inp2);
        // let inp3 = document.createElement('input');
        // inp3.setAttribute('type', "button");
        // inp3.setAttribute('value', "Видалити");
        // inp3.setAttribute('onClick', "onClickButtonDeletePatient()");
        // inp3.setAttribute('class', "button")
        // tr2.appendChild(inp3);
        // let inp4 = document.createElement('input');
        // inp4.setAttribute('type', "button");
        // inp4.setAttribute('value', "Згенерувати пароль");
        // inp4.setAttribute('onClick', "onClickButtonGeneratePasswordPatient()");
        // inp4.setAttribute('class', "button")
        // tr2.appendChild(inp4);
        // let inp5 = document.createElement('input');
        // inp5.setAttribute('type', "button");
        // inp5.setAttribute('value', "Переглянути повідомлення");
        // inp5.setAttribute('onClick', "onClickButtonSelectMessagesPatient()");
        // inp5.setAttribute('class', "button")
        // tr2.appendChild(inp5);
        // table.appendChild(tr2);
    }

    fetch('/hearing_monitoring/patient/patients')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            labels = ["Ім'я", "Прізвище", "Вік", "Стать", "email", "username"];
            createTablePatient(labels, data, 'table_patient');
        });
</script>

<%-----------------------------------------Listener table PATIENT-----------------------------------------%>

<script>
    function selectRowPat(eventPat){
        if(eventPat.target.tagName === 'TD'){
            if(preventPat !== undefined){
                if(preventPat.style.background === "cyan" && preventPat !== eventPat.target.parentNode){
                    preventPat.style.background = "aliceblue";
                }
            }
            eventPat.target.parentNode.style.background = "cyan";
            preventPat = eventPat.target.parentNode;
            pat_id = eventPat.target.parentNode.getAttribute('id');
        }
    }
    let tableListPat = document.getElementById('table_patient');
    let preventPat;
    tableListPat.addEventListener("click", selectRowPat );
</script>

<%-----------------------------------------Table message-----------------------------------------%>

<script>
    function createTableMessage(labels, data, id_table){
        let table = document.getElementById(id_table);

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
            let td3 = document.createElement('td');
            td3.textContent = d.status;
            tr.appendChild(td3);

            table.appendChild(tr);
        }
        let tr2 = document.createElement('tr');
        let inp5 = document.createElement('input');
        inp5.setAttribute('type', "button");
        inp5.setAttribute('value', "OK");
        inp5.setAttribute('onClick', "window.location.reload()");
        inp5.setAttribute('class', "button")
        tr2.appendChild(inp5);
        table.appendChild(tr2);
    }
</script>

</body>

</html>