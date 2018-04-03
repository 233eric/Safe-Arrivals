const http = require('http');
var reqProm = require('request-promise');
var request = require('request');

var express = require('express')
var app = express()

// respond with "hello world" when a GET request is made to the homepage
app.get('/', function (req, res) {
  let theURL = "http://safe-arrivals.appspot.com/?p1a=SUNNY%20BRAE%20AVE&p1b=LANARK%20ST&p2b=SANTA%20MONICA%20BLVD&p2a=MORENO%20DR"
  request(theURL, function (error, response, body) {
    if (!error && response.statusCode == 200) {
      let latlons = [];
      d = JSON.parse(body);
      d = d.path;
      for (i = 0; i < d.length; i++){
        latlons.push({
          "latitude": d[i].lat,
          "longitude": d[i].lon
        });
      }
      console.log(latlons);
      res.send(latlons);
    }
  })
})


app.listen(3000, () => console.log('Example app listening on port 3000!'));
