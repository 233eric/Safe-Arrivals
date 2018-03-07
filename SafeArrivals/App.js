import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import MapView from 'react-native-maps';
import { AppRegistry, TextInput, Button, Alert } from 'react-native';

export default class DisplayAnImage extends Component {
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
        <View style = {{position: 'absolute', top: 50, backgroundColor: 'rgba(0,0,0,0.5)'}}>
          <View style = {{flexDirection: 'row'}}>
            <View>
              <TextInput
                style={styles.text}
                placeholder="Where you AT!!!"
                onChangeText={(text) => this.setState({text})}
              />
              <TextInput
                style={styles.text}
                placeholder="Where you GOIN!!!"
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
    alignItems: 'center'
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
});