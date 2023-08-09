// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';



    // fetch('/pie-chart')
    // .then(response => response.json())
    // .then(data => alert(JSON.stringify(data)));
let fetchedJsonString;
let  jsonArray;
fetch('pie-chart')
    .then(response => response.json())
    .then(data => JSON.stringify(data))
    .then(jsonString => {
      fetchedJsonString = jsonString; // Store the JSON string in the variable
      jsonArray = JSON.parse(fetchedJsonString);
      var ctx = document.getElementById("myPieChart");
      var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: [jsonArray[0].name, jsonArray[1].name, jsonArray[2].name, jsonArray[3].name,jsonArray[4].name],
          datasets: [{
            data: [parseFloat(jsonArray[0].revenue.replace(/,/g, '')), parseFloat(jsonArray[1].revenue.replace(/,/g, '')), parseFloat(jsonArray[2].revenue.replace(/,/g, '')),parseFloat(jsonArray[3].revenue.replace(/,/g, '')),parseFloat(jsonArray[4].revenue.replace(/,/g, ''))],
            backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745','#800080'],
          }],
        },
      });
    });

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
