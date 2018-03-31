import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import MapView from 'react-native-maps';
import { Animated, AppRegistry, TextInput, Button, Alert, TouchableHighlight, TouchableOpacity, TouchableNativeFeedback, TouchableWithoutFeedback,} from 'react-native';


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
    markers: [
      {
        coordinate: {
          latitude: 34.0430,
          longitude: -118.2673,
        },
        title: "Best Place",
        description: "This is the best place in Portland",
      },
    ]
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
  move = (loc, dest) => {
    alert("start:"+ loc + dest)
  }

  render() {
    return (

      <View style={styles.container}>
        
        <MapView style={styles.map}
          region={{
            latitude:34.040203,
            longitude:-118.284030,
            latitudeDelta: 0.0922,
            longitudeDelta:0.1
          }}
        >
          {this.state.markers.map((marker, index) => {
            return (
              <MapView.Marker key={index} coordinate={marker.coordinate}>
                  <View style={styles.marker} />
              </MapView.Marker>
            );
          })}
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