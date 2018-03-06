import React, { Component } from 'react';
import { StyleSheet, View, Image } from 'react-native';
import MapView from 'react-native-maps';

export default class DisplayAnImage extends Component {
  render() {
    return (
      <View style={styles.container}>
        <MapView style={styles.map}
          region={{
            latitude:37.7749,
            longitude:-122.4194,
            latitudeDelta: 0.0922,
            longitudeDelta:0.1
          }}
        >
        </MapView>
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
  }
});
