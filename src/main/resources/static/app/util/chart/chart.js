const React = require('react');
const BarChart = require("react-chartjs").Bar;

var myChart = React.createClass({
  render: function() {
    var labels = this.props.labels;
    var data = this.props.data;
    var chartData = {
      labels: labels,
      datasets: [{
          label: '# of Votes',
          data: data,
          backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
      }]
    };
    var options = {
      scales: {
          yAxes: [{
              ticks: {
                  beginAtZero:true
              }
          }]
      }
    };
    return (
      <div className='col-sm-8 col-sm-offset-2'>
        <BarChart data={chartData} options={options} height="500" />
      </div>
    );
  }
});

module.exports = myChart;
