// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';



    // fetch('/pie-chart')
    // .then(response => response.json())
    // .then(data => alert(JSON.stringify(data)));

fetch('pie-chart')
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data));
        let labels = data.map(a => a.name);
        let revenue = data.map(a => parseFloat(a['revenue'].replace(/,/g, '')));
        const backgroundColors = generateRandomHexColors(labels.length);
        // console.log(labels);
        // console.log(revenue);
        // console.log(backgroundColors);


        let ctx = document.getElementById("myPieChart");
        let myPieChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    data: revenue,
                    backgroundColor: backgroundColors,
                }],
            },
        });
    });



function generateRandomHexColor() {
    return '#' + Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0');
}

function generateRandomHexColors(count) {
    let backgroundColor = ['#007bff', '#dc3545', '#ffc107', '#28a745', '#800080'];

    while (backgroundColor.length < count) {
        const newColor = generateRandomHexColor();
        if (!backgroundColor.includes(newColor)) {
            backgroundColor.push(newColor);
        }
    }

    return backgroundColor;
}

/*var objectsFetch = fetch('/pie-chart').then(response => response.json()).then(data => JSON.stringify(data))
alert(objectsFetch)*/
// let labelsPie = objectsFetch.map(a => a.name);
// alert(labelsPie);

// Pie Chart Example


// var ctx = document.getElementById("myPieChart");
// var myPieChart = new Chart(ctx, {
//   type: 'pie',
//   data: {
//     labels: ["Blue", "Red", "Yellow", "Green","Purple"],
//     datasets: [{
//       data: [12.21, 15.58, 11.25, 8.32,10.2],
//       backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745','#800080'],
//     }],
//   },
// });
