import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import MapView from 'react-native-maps';
import { Animated, AppRegistry, TextInput, Button, Alert} from 'react-native';

export default class Map extends Component {
  constructor(props) {
    super(props);
    this.state = {text: ''};
  }

  _onPressButton() {
    Alert.alert('This is backends job')
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
          <MapView.Circle
            center={{
              latitude:34.040203,
              longitude:-118.284030
            }}
            radius={150}
            fillColor={"rgba(255,0,0,0.5)"}
            strokeColor={"rgba(255,0,0,0.5)"}
          />
        </MapView>
        <View style = {styles.nav}>
          <View style = {{flexDirection: 'row'}}>
            <View>
              <TextInput
                style={styles.text}
                placeholder="Where you AT!!!"
                onChangeText={(text) => this.setState({text})}
              />
              <TextInput
                style={styles.text}
                placeholder="Where you TRYIN TO ARRIVE SAFELY!!!"
                onChangeText={(text) => this.setState({text})}
              />
            </View>
            <Button
              onPress={this._onPressButton}
              title="GOGO"
              color="#841584"
            />
          </View>
        </View>
        <View style = {styles.menu}>
          <Text style = {{alignSelf: 'center'}}>Drag up or Down</Text>
        </View>
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
    width: 300
  },
  nav: {
    position: 'absolute', 
    top: 0, 
    padding: 30, 
    backgroundColor: 'rgba(0,0,0,1)',    
  },
  menu: {
    height: 50, 
    backgroundColor: 'rgba(0,0,0,0.3)', 
  }
});