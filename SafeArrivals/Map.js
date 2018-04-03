import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import MapView from 'react-native-maps';
import { Animated, AppRegistry, TextInput, Button, Alert, TouchableHighlight, TouchableOpacity, TouchableNativeFeedback, TouchableWithoutFeedback,} from 'react-native';
import Polyline from '@mapbox/polyline';

class FadeInView extends React.Component {
  state = {
    fadeAnim: new Animated.Value(20),
    expanded: true,
  }
  toggle(){
      let initialValue    = this.state.expanded? 20 : 500,
          finalValue      = this.state.expanded? 500 : 20;

      this.setState({
          expanded : !this.state.expanded
      });

      this.state.fadeAnim.setValue(initialValue);
      Animated.spring(
          this.state.fadeAnim,
          {
              toValue: finalValue
          }
      ).start();
  }
  

  
  
  
  render() {
    let { fadeAnim } = this.state;
    
    return (
      <Animated.View                 // Special animatable View
        style={{
          ...this.props.style,
          height: fadeAnim,        
        }}
      >
        <View style = {styles.menu}>
            <TouchableHighlight onPress={this.toggle.bind(this)} underlayColor="white">
              <View style={styles.button}>
                <Text style={styles.buttonText}>DIRECTIONS</Text>
              </View>
            </TouchableHighlight>
          </View>
        {this.props.children}
      </Animated.View>
    );
  }
}
export default class Map extends Component {

  state = {
    location: '',
    destination: '',
    markers: [],
    coords: [],
    polylines: [],
  }
  componentWillMount() {
    this.index = 0;
    this.animation = new Animated.Value(0);
  }
  handleLocation = (text) => {
    this.setState({location: text})
  }
  handleDestination = (text) => {
    this.setState({destination: text})
  }

  get12(d1a, d1b, d2b, d2a){
    let theURL = "https://safe-arrivals.appspot.com/?p1a="+d1a.toUpperCase()+"&p1b="+ d1b.toUpperCase() + "&p2b="+d2b.toUpperCase+"&p2a="+d2a.toUpperCase
    //let theURL = "https://safe-arrivals.appspot.com/?p1a=SUNNY%20BRAE%20AVE&p1b=LANARK%20ST&p2b=SANTA%20MONICA%20BLVD&p2a=MORENO%20DR"
    fetch(theURL, {
         method: 'GET'
      })
      .then((response) => response.json())
      .then((responseJson) => {
        var a = responseJson.path[0].lat;
         //alert(a);
         let latlons = [];
         let d = responseJson.path;
          for (i = 0; i < d.length; i++){
            if(i == 0 || i == d.length-1){
              this.state.markers.push({
                uniqueId : 0,
                  latitude: parseFloat(d[i].lat),
                  longitude: parseFloat(d[i].lon)
              })
            }
            latlons.push({
              "latitude": d[i].lat,
              "longitude": d[i].lon
            });
          }
         console.log(responseJson)
         this.setState({
            coords: latlons
         })
      })
      .catch((error) => {
         console.error(error);
      });
  }
  async getDirections(startLoc, destinationLoc) {
        try {
            let resp = await fetch(`https://maps.googleapis.com/maps/api/directions/json?origin=${ startLoc }&destination=${ destinationLoc }`)
            let respJson = await resp.json();
            let points = Polyline.decode(respJson.routes[0].overview_polyline.points);
            let coords = points.map((point, index) => {
                return  {
                    latitude : point[0],
                    longitude : point[1]
                }
            })
            this.state.polylines.push(coords);
            return coords
        } catch(error) {
            alert(error)
            return error
        }
    }

  move = (loc, dest) => {
    if (loc != "" && dest != ""){
      var a = loc.split(", ")[0].replace(/ /g,"%20");
      var b = loc.split(", ")[1].replace(/ /g,"%20");
      var c = dest.split(", ")[0].replace(/ /g,"%20");
      var d = dest.split(", ")[1].replace(/ /g,"%20");
      this.get12(a,b,c,d) 
    }
    this.forceUpdate();
  }
  render() {
    var markers = this.state.markers || [];
    var coordinates = this.state.coords || [];
    var polylines = this.state.polylines || [];
    return (
      <View style={styles.container}>
        <MapView style={styles.map}
          initialRegion={{
            latitude:34.040203,
            longitude:-118.284030,
            latitudeDelta: 0.0922,
            longitudeDelta:0.1
          }}
        >
          {markers.map(marker => (
            <MapView.Marker
              key={marker.uniqueId}
              coordinate={{
                latitude: marker.latitude,
                longitude: marker.longitude
              }}
            >
            </MapView.Marker>
          ))}
          <MapView.Polyline 
            coordinates={coordinates}
            strokeWidth={2}
            strokeColor="blue"/>
        </MapView>
        <View style = {styles.nav}>
          <View style = {{flexDirection: 'row'}}>
            <View>
              <TextInput
                style={styles.text}
                placeholder="Location"
                returnKeyLabel = {"next"}
                onChangeText={this.handleLocation}
              />
              <TextInput
                style={styles.text}
                placeholder="Destination"
                returnKeyLabel = {"next"}
                onChangeText={this.handleDestination}
              />
            </View>
            <Button 
              style = {{flex: 0, width: 20}}
              onPress={() => this.move(this.state.location, this.state.destination)}
              title="Go!"
              color="#841584"
            />
          </View>
        </View>
        <FadeInView 
          ref={component => this._mainMenu = component}
        >
        </FadeInView>
      </View>
    );
  }
}
const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    top: 0,
    left: 0,
    bottom: 0,
    right: 0,
    justifyContent: 'flex-end',
    flexDirection: 'column',
    flex: 1
  },
  map: {
    position: 'absolute',
    top: 0,
    left: 0,
    bottom: 0,
    right: 0
  },
  text: { 
    backgroundColor:'rgba(0,0,0,0.4)', 
    width: 300,
    borderColor: 'grey',
    borderWidth: 1,
    borderRadius: 5,
    padding: 5,
    color: 'white',
  },
  nav: {
    position: 'absolute', 
    top: 0, 
    padding: 40, 
    backgroundColor: 'rgba(0,0,0,1)',   
    flex: 2, 
  },
  button: {
    marginBottom: 30,
    alignItems: 'center',
    backgroundColor: 'rgba(255,255,255,0.7)',
    width: 100
    
  },
  menu: {
    backgroundColor:'black', 
    alignItems: 'center', 
    height: 1000
  },
  marker: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: "rgba(130,4,150, 0.9)",
  },
  markerWrap: {
    alignItems: "center",
    justifyContent: "center",
  },
  marker: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: "rgba(130,4,150, 0.9)",
  },
  ring: {
    width: 24,
    height: 24,
    borderRadius: 12,
    backgroundColor: "rgba(130,4,150, 0.3)",
    position: "absolute",
    borderWidth: 1,
    borderColor: "rgba(130,4,150, 0.5)",
  },
});