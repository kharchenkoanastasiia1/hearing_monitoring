<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="uk">

<head>
    <title>Doctor page</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        let username_doctor;
        let pat_id;
        let pat_username;
        let patients;                           //collectin patients
        let augiograms_by_patient;              //collection audiograms
        let augiograms_by_patient_frequency;    //collection audiograms by frequency
        let current_index_audiogram;
        let current_index_frequency;
        let frequency = ['125 Гц', '250 Гц', '500 Гц', '1000 Гц', '2000 Гц', '3000 Гц', '4000 Гц', '8000 Гц'];
        let left_ear;
        let right_ear;
        let norm_for_detail;
    </script>
    <style>
        h2{
            color: blue;
        }
        h4{
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
        }
        .buttonSlide{
            color: #fff;
            background-color: cornflowerblue;
            font-size: 22px;
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
        .h2Slide{
            color: blue;
            text-align: center;
        }
        .h3Slide{
            color: blue;
            text-align: center;
        }
        .graphic{
            height: 300px;
            width: 50%;
            margin: auto;
        }
        select{
            font-size: 16px;
            background: cornflowerblue;
            color: #ffffff;
        }
        option{
            font-size: 16px;
            background: cornflowerblue;
            color: #ffffff;
        }
    </style>
</head>

<body>

<h2 id="root_name" name="<sec:authentication property="principal.username" />"></h2>
<br>
<table id="table_patient"></table>

<nav class="two">
    <ul>
        <li><input type="button" value="Переглянути аудіограми" class="button" onclick="getAudiogramsByUser()"></li>
        <li><input type="button" value="Динаміка лікування" class="button" onclick="getDynamicsTreatmentByUser()"></li>
        <li><input type="button" value="Написати повідомлення" class="button" onclick="setMessageForUser()"></li>
        <li><input type="button" value="Переглянути повідомлення" class="button" onclick="getMessageByUser()"></li>
    </ul>
</nav>
<br>
<div id="base_div">
    <div id="audiograms_patient"></div>
</div>
<div id="base_div_detail"></div>

<script>
    let h2 = document.getElementById("root_name");
    username_doctor = h2.getAttribute("name");
    //h2.innerHTML = "Всі пацієнти " + username_doctor + ":";
    h2.innerHTML = "Всі пацієнти:";
</script>

<%--------------------------------------Table Patients By Doctor--------------------------------------%>

<script>
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
            tr.setAttribute('name', d.username);

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
            // td4.textContent = d.sex;
            if(d.sex === 0){
                td4.textContent = "чоловіча";
            }else if(d.sex === 1){
                td4.textContent = "жіноча";
            }
            tr.appendChild(td4);

            table.appendChild(tr);
        }
    }

    fetch('/hearing_monitoring/doctor/allDoctorsPatients?usernameDoctor='+username_doctor)
        .then(response => response.json())
        .then(data => {
            labels = ["Ім'я", "Прізвище", "Вік", "Стать"];
            patients = data;
            createTablePatient(labels, data, 'table_patient');
        });
</script>

<%--------------------------------------Listener by Table Patients--------------------------------------%>

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
            pat_username = eventPat.target.parentNode.getAttribute('name');
        }
    }
    let tableListPat = document.getElementById('table_patient');
    let preventPat;
    tableListPat.addEventListener("click", selectRowPat );
</script>


<%----------------------------------------------Button Audiogram---------------------------------------------%>


<script>
    function getAudiogramsByUser(){
        if(pat_username !== undefined){
            fetch('/hearing_monitoring/doctor/allAudiogramsPatient?usernamePatient='+pat_username)
                .then(response => response.json())
                .then(data => {
                    augiograms_by_patient = data;
                    current_index_audiogram = 0;
                    createTableAudigram(augiograms_by_patient[current_index_audiogram], "base_div", "base_div_detail");
                });
        }
        else{
            alert("Виберіть пацієнта для дії");
        }
    }

    function onClickButtonPreviousAudiogram(){
        if(current_index_audiogram !== 0){
            current_index_audiogram = current_index_audiogram - 1;

            let div_detail = document.getElementById('base_div_detail');
            div_detail.innerHTML = "";

            BuildChart(augiograms_by_patient[current_index_audiogram], 'graphic_canvas');
        }
    }

    function onClickButtonNextAudiogram(){
        if(current_index_audiogram < (augiograms_by_patient.length - 1)){
            current_index_audiogram = current_index_audiogram + 1;

            let div_detail = document.getElementById('base_div_detail');
            div_detail.innerHTML = "";

            BuildChart(augiograms_by_patient[current_index_audiogram], 'graphic_canvas');
        }
    }

    function onClickButtonDetailPersonal(id_div_detail){
        fetch('/hearing_monitoring/doctor/median?usernamePatient='+pat_username+'&idAudiogram='+current_index_audiogram)
            .then(response => response.json())
            .then(data => {
                norm_for_detail = data;
                labels_for_graphic = ["Персональна (ліве вухо)", "Аудіограма (ліве вухо)", "Персональна (праве вухо)", "Аудіограма (праве вухо)"]
                CreateDetailMedian(id_div_detail, labels_for_graphic);
            });
    }

    function onClickButtonDetailPopulation(id_div_detail){
        fetch('/hearing_monitoring/doctor/ageNorm?patientId='+pat_id+'&idAudiogram='+current_index_audiogram)
            .then(response => response.json())
            .then(data => {
                norm_for_detail = data;
                labels_for_graphic = ["Популяційна (ліве вухо)", "Аудіограма (ліве вухо)", "Популяційна (праве вухо)", "Аудіограма (праве вухо)"]
                CreateDetailMedian(id_div_detail, labels_for_graphic);
            });
    }

    function createTableAudigram(data, id_div, id_div_detail){
        let base_div = document.getElementById(id_div);
        base_div.innerHTML = "";

        let div_detail = document.getElementById(id_div_detail);
        div_detail.innerHTML = "";

        let div_graphic = document.createElement('audiograms_patient');
        let h2 = document.createElement('h2');
        h2.innerText = data.date;
        h2.setAttribute('class', 'h2Slide');
        h2.setAttribute('id', 'h2_slide');
        div_graphic.appendChild(h2);

        let div = document.createElement('div');
        div.setAttribute('id', 'div_canvas');
        let graphic = document.createElement('canvas');
        graphic.setAttribute('id', 'graphic_canvas');
        div.appendChild(graphic);
        div_graphic.appendChild(div);

        let table = document.createElement('table');
        let th = document.createElement('th');
        th.setAttribute('class', 'thSlide');
        let inp1 = document.createElement('input');
        inp1.setAttribute('type', "button");
        inp1.setAttribute('value', "<");
        inp1.setAttribute('onClick', "onClickButtonPreviousAudiogram()");
        inp1.setAttribute('class', "buttonSlide")
        th.appendChild(inp1);
        let inp3 = document.createElement('input');
        inp3.setAttribute('type', "button");
        inp3.setAttribute('value', "Персональна");
        inp3.setAttribute('onClick', "onClickButtonDetailPersonal('audiogram_detail')");
        inp3.setAttribute('class', "buttonSlide")
        th.appendChild(inp3);
        let inp4 = document.createElement('input');
        inp4.setAttribute('type', "button");
        inp4.setAttribute('value', "Популяційна");
        inp4.setAttribute('onClick', "onClickButtonDetailPopulation('audiogram_detail')");
        inp4.setAttribute('class', "buttonSlide")
        th.appendChild(inp4);
        let inp2 = document.createElement('input');
        inp2.setAttribute('type', "button");
        inp2.setAttribute('value', ">");
        inp2.setAttribute('onClick', "onClickButtonNextAudiogram()");
        inp2.setAttribute('class', "buttonSlide")
        th.appendChild(inp2);
        table.appendChild(th);

        div_graphic.appendChild(table);

        base_div.appendChild(div_graphic);

        BuildChart(data, 'graphic_canvas');
    }

    function CreateDetailMedian(id_div, labels_for_graphic){
        let div_detail = document.getElementById('base_div_detail');
        div_detail.innerHTML = "";

        let div_detail_audiogram = document.createElement(id_div)

        let table = document.createElement('table');
        let th = document.createElement('th');
        th.setAttribute('class', 'thSlide');

        let div1 = document.createElement('div');
        div1.setAttribute('id', 'div_canvas_median_1');
        let graphic1 = document.createElement('canvas');
        graphic1.setAttribute('id', 'graphic_median_1');
        div1.appendChild(graphic1);
        th.appendChild(div1);

        let h4_1 = document.createElement('h4');
        h4_1.innerHTML = norm_for_detail.resultAnalizeLeft;
        th.appendChild(h4_1);

        let div2 = document.createElement('div');
        div2.setAttribute('id', 'div_canvas_median_2');
        let graphic2 = document.createElement('canvas');
        graphic2.setAttribute('id', 'graphic_median_2');
        div2.appendChild(graphic2);
        th.appendChild(div2);

        let h4_2 = document.createElement('h4');
        h4_2.innerHTML = norm_for_detail.resultAnalizeRight;
        th.appendChild(h4_2);

        table.appendChild(th);
        div_detail_audiogram.appendChild(table);

        div_detail.appendChild(div_detail_audiogram);

        BuildChartNorm(norm_for_detail.left, augiograms_by_patient[current_index_audiogram].left, 'graphic_median_1', 'div_canvas_median_1', labels_for_graphic[0], labels_for_graphic[1]);
        BuildChartNorm(norm_for_detail.right, augiograms_by_patient[current_index_audiogram].right, 'graphic_median_2', 'div_canvas_median_2', labels_for_graphic[2], labels_for_graphic[3]);
    }

    function BuildChart(data, id_canvas) {
        labels = ['125', '250', '500', '1000', '2000', '3000', '4000', '8000'];
        values_left = data.left;
        values_right = data.right;

        let graph = document.getElementById(id_canvas);
        graph.remove();
        let div = document.getElementById('div_canvas');
        div.setAttribute('class', 'graphic');
        let graphic = document.createElement('canvas');
        graphic.setAttribute('id', id_canvas);
        div.appendChild(graphic);

        let div_graphic = document.getElementById('h2_slide');
        div_graphic.innerText = data.date;

        var ctx = document.getElementById(id_canvas).getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels, // Наши метки
                datasets: [{
                    label: "Ліве вухо", // Название рядов
                    data: values_left, // Значения
                    backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                        'rgba(255, 99, 132, 0.7)'
                    ],
                    borderColor: [ // Добавляем произвольный цвет обводки
                        'rgba(255,99,132, 1)'
                    ],
                    borderWidth: 1 // Задаем ширину обводки секций
                },
                    {
                        label: "Праве вухо", // Название рядов
                        data: values_right, // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(54, 162, 235, 0.7)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "max possible",
                        data: [100, 100, 100, 100, 100, 100, 100, 100], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "min possible",
                        data: [0, 0, 0, 0, 0, 0, 0, 0], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    }]
            },
            options: {
                responsive: true, // Даем Chart.js указание реагировать правильно.
                maintainAspectRatio: false, // Добавляем эту строку, чтобы избежать переключения на полноразмерный вид (высоту/ширину)
                scales: {
                    y:{
                        ticks: {
                            beginAtZero: true,
                            reverse: true
                        }
                    }
                },
                plugins: {
                    legend: {
                        labels: {
                            filter: function(legendItem, chartData) {
                                return (legendItem.datasetIndex !== 2 && legendItem.datasetIndex !== 3);
                            }
                        }
                    },
                    datalabels: {
                        anchor: 'end',    /* Возможные варианты:
								'center' (по умолчанию): центр элемента
								'start': нижняя граница элемента
								'end': верхняя граница элемента
								*/
                        align: 'top',
                        formatter: Math.round,
                        font: {
                            weight: 'bold', // Жирность
                            size: '30' // Размер
                        }
                    }
                }
            }
        });
        return myChart;
    }

    function BuildChartNorm(values_left, values_right, id_canvas, id_parent_canvas, label_norm, label_audiogram) {
        labels = ['125', '250', '500', '1000', '2000', '3000', '4000', '8000'];

        let graph = document.getElementById(id_canvas);
        graph.remove();
        let div = document.getElementById(id_parent_canvas);
        div.setAttribute('class', 'graphic');
        let graphic = document.createElement('canvas');
        graphic.setAttribute('id', id_canvas);
        div.appendChild(graphic);

        var ctx = document.getElementById(id_canvas).getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels, // Наши метки
                datasets: [{
                    label: label_norm, // Название рядов
                    data: values_left, // Значения
                    backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                        'rgba(250, 50, 50, 0.7)'
                    ],
                    borderColor: [ // Добавляем произвольный цвет обводки
                        'rgba(250, 5, 5, 1)'
                    ],
                    borderWidth: 1 // Задаем ширину обводки секций
                },
                    {
                        label: label_audiogram, // Название рядов
                        data: values_right, // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(150, 255, 156, 0.7)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(55, 191, 62, 1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "max possible",
                        data: [100, 100, 100, 100, 100, 100, 100, 100], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "min possible",
                        data: [0, 0, 0, 0, 0, 0, 0, 0], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    }]
            },
            options: {
                responsive: true, // Даем Chart.js указание реагировать правильно.
                maintainAspectRatio: false, // Добавляем эту строку, чтобы избежать переключения на полноразмерный вид (высоту/ширину)
                scales: {
                    yAxisID: [{
                        ticks: {
                            beginAtZero: true,
                        },
                    }]
                },
                plugins: {
                    legend: {
                        labels: {
                            filter: function(legendItem, chartData) {
                                return (legendItem.datasetIndex !== 2 && legendItem.datasetIndex !== 3);
                            }
                        }
                    },
                    datalabels: {
                        anchor: 'end',
                        align: 'top',
                        formatter: Math.round,
                        font: {
                            weight: 'bold', // Жирность
                            size: '30' // Размер
                        }
                    }
                }
            }
        });
        return myChart;
    }
</script>

<%----------------------------------------------Button Dynamics treatment---------------------------------------------%>

<script>
    function getDynamicsTreatmentByUser(){
        if(pat_username !== undefined){
            fetch('/hearing_monitoring/doctor/allAudiogramsPatientFrequency?usernamePatient='+pat_username)
                .then(response => response.json())
                .then(data => {
                    augiograms_by_patient_frequency = data;
                    current_index_frequency = 0;
                    createDynamicsTreatment(augiograms_by_patient_frequency, current_index_frequency, "base_div");
                });
        }
        else{
            alert("Select patient for action");
        }
    }

    function onClickButtonPreviousFrequency(){
        if(current_index_frequency !== 0){
            current_index_frequency = current_index_frequency - 1;
            BuildChartFrequency(augiograms_by_patient_frequency, current_index_frequency, 'graphic_canvas');
        }
    }

    function onClickButtonNextFrequency(){
        if(current_index_frequency < (frequency.length - 1)){
            current_index_frequency = current_index_frequency + 1;
            BuildChartFrequency(augiograms_by_patient_frequency, current_index_frequency, 'graphic_canvas');
        }
    }

    function handleFrequency(){
        let selectElement = document.getElementById('select_freq');
        current_index_frequency = Number(selectElement.value);
        BuildChartFrequency(augiograms_by_patient_frequency, current_index_frequency, 'graphic_canvas');
    }

    function createDynamicsTreatment(data, index, id_div){
        let base_div = document.getElementById(id_div);
        base_div.innerHTML = "";

        let div_detail = document.getElementById('base_div_detail');
        div_detail.innerHTML = "";

        let div_graphic = document.createElement('audiograms_patient');
        let h2 = document.createElement('h2');
        h2.innerText = frequency[index];
        h2.setAttribute('class', 'h2Slide');
        h2.setAttribute('id', 'h2_slide');
        div_graphic.appendChild(h2);

        let div = document.createElement('div');
        div.setAttribute('id', 'div_canvas');
        let graphic = document.createElement('canvas');
        graphic.setAttribute('id', 'graphic_canvas');
        div.appendChild(graphic);
        div_graphic.appendChild(div);

        let table = document.createElement('table');
        let th = document.createElement('th');
        th.setAttribute('class', 'thSlide');
        let inp1 = document.createElement('input');
        inp1.setAttribute('type', "button");
        inp1.setAttribute('value', "<");
        inp1.setAttribute('onClick', "onClickButtonPreviousFrequency()");
        inp1.setAttribute('class', "buttonSlide")
        th.appendChild(inp1);
        let select = document.createElement('select');
        select.setAttribute('id', 'select_freq');
        select.setAttribute('name', 'select_freq');
        select.setAttribute('onchange', 'handleFrequency()')
        let option1 = document.createElement('option');
        option1.setAttribute('value', '0');
        option1.textContent = frequency[0];
        select.appendChild(option1);
        let option2 = document.createElement('option');
        option2.setAttribute('value', '1');
        option2.textContent = frequency[1];
        select.appendChild(option2);
        let option3 = document.createElement('option');
        option3.setAttribute('value', '2');
        option3.textContent = frequency[2];
        select.appendChild(option3);
        let option4 = document.createElement('option');
        option4.setAttribute('value', '3');
        option4.textContent = frequency[3];
        select.appendChild(option4);
        let option5 = document.createElement('option');
        option5.setAttribute('value', '4');
        option5.textContent = frequency[4];
        select.appendChild(option5);
        let option6 = document.createElement('option');
        option6.setAttribute('value', '5');
        option6.textContent = frequency[5];
        select.appendChild(option6);
        let option7 = document.createElement('option');
        option7.setAttribute('value', '6');
        option7.textContent = frequency[6];
        select.appendChild(option7);
        let option8 = document.createElement('option');
        option8.setAttribute('value', '7');
        option8.textContent = frequency[7];
        select.appendChild(option8);
        th.appendChild(select);

        // let inp3 = document.createElement('input');
        // inp3.setAttribute('type', "button");
        // inp3.setAttribute('value', "---------");
        // inp3.setAttribute('class', "buttonSlide")
        // th.appendChild(inp3);

        let inp2 = document.createElement('input');
        inp2.setAttribute('type', "button");
        inp2.setAttribute('value', ">");
        inp2.setAttribute('onClick', "onClickButtonNextFrequency()");
        inp2.setAttribute('class', "buttonSlide")
        th.appendChild(inp2);
        table.appendChild(th);

        div_graphic.appendChild(table);

        base_div.appendChild(div_graphic);

        BuildChartFrequency(data, index, 'graphic_canvas');
    }

    function getFrequency(data, index){
        switch(index){
            case 0: left_ear = data.map(function (e) { return e.f125Left; }); right_ear = data.map(function (e) { return e.f125Right; }); break;
            case 1: left_ear = data.map(function (e) { return e.f250Left; }); right_ear = data.map(function (e) { return e.f250Right; }); break;
            case 2: left_ear = data.map(function (e) { return e.f500Left; }); right_ear = data.map(function (e) { return e.f500Right; }); break;
            case 3: left_ear = data.map(function (e) { return e.f1000Left; }); right_ear = data.map(function (e) { return e.f1000Right; }); break;
            case 4: left_ear = data.map(function (e) { return e.f2000Left; }); right_ear = data.map(function (e) { return e.f2000Right; }); break;
            case 5: left_ear = data.map(function (e) { return e.f3000Left; }); right_ear = data.map(function (e) { return e.f3000Right; }); break;
            case 6: left_ear = data.map(function (e) { return e.f4000Left; }); right_ear = data.map(function (e) { return e.f4000Right; }); break;
            case 7: left_ear = data.map(function (e) { return e.f8000Left; }); right_ear = data.map(function (e) { return e.f8000Right; }); break;
        }
    }

    function BuildChartFrequency(data,  index, id_canvas) {
        labels = data.map(function (e) {
            return e.date;
        });

        getFrequency(data, index);

        let graph = document.getElementById(id_canvas);
        graph.remove();
        let div = document.getElementById('div_canvas');
        graphic = document.createElement('canvas');
        div.setAttribute('class', 'graphic');
        graphic.setAttribute('id', 'graphic_canvas');
        div.appendChild(graphic);

        let div_graphic = document.getElementById('h2_slide');
        div_graphic.innerText = frequency[index];

        var ctx = document.getElementById(id_canvas).getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels, // Наши метки
                datasets: [{
                    label: "Ліве вухо", // Название рядов
                    data: left_ear, // Значения
                    backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                        'rgba(255, 99, 132, 0.7)'
                    ],
                    borderColor: [ // Добавляем произвольный цвет обводки
                        'rgba(255,99,132, 1)'
                    ],
                    borderWidth: 1 // Задаем ширину обводки секций
                },
                    {
                        label: "Праве вухо", // Название рядов
                        data: right_ear, // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(54, 162, 235, 0.7)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "max possible",
                        data: [100, 100, 100, 100, 100, 100, 100, 100], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    },
                    {
                        label: "min possible",
                        data: [0, 0, 0, 0, 0, 0, 0, 0], // Значения
                        backgroundColor: [ // Задаем произвольные цвета (0.2 - прозрачность)
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderColor: [ // Добавляем произвольный цвет обводки
                            'rgba(0, 0, 0, 0.1)'
                        ],
                        borderWidth: 1 // Задаем ширину обводки секций
                    }]
            },
            options: {
                responsive: true, // Даем Chart.js указание реагировать правильно.
                maintainAspectRatio: false, // Добавляем эту строку, чтобы избежать переключения на полноразмерный вид (высоту/ширину)
                plugins: {
                    legend: {
                        labels: {
                            filter: function(legendItem, chartData) {
                                return (legendItem.datasetIndex !== 2 && legendItem.datasetIndex !== 3);
                            }
                        }
                    },
                    datalabels: {
                        anchor: 'end',    /* Возможные варианты:
								'center' (по умолчанию): центр элемента
								'start': нижняя граница элемента
								'end': верхняя граница элемента
								*/
                        align: 'top',
                        formatter: Math.round,
                        font: {
                            weight: 'bold', // Жирность
                            size: '30' // Размер
                        }
                    }
                }
            }
        });
        return myChart;
    }
</script>

<%---------------------------------------------Button Send message-------------------------------------------%>

<script>
    function setMessageForUser(){
        if(pat_username !== undefined){
            fetch('/hearing_monitoring/doctor/addMessage?usernamePatient='+pat_username+'&doctorUsername='+username_doctor)
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
</script>

<%---------------------------------------------Button See message-------------------------------------------%>

<script>
    function getMessageByUser(){
        fetch('/hearing_monitoring/message/showDoctorMessagesByUsername?usernameDoctor='+username_doctor)
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
        inp5.setAttribute('class', "button")
        tr2.appendChild(inp5);
        table.appendChild(tr2);

        base_div.appendChild(table);
    }
</script>

</body>
</html>