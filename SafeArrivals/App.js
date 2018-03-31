import React from 'react';
import {
  ActivityIndicator,
  AsyncStorage,
  Button,
  StatusBar,
  StyleSheet,
  View,
  Text,
  Image,
  TextInput,
} from 'react-native';
import { StackNavigator, SwitchNavigator } from 'react-navigation';
import Map from "./Map.js"
import { webWeights  } from 'react-native-typography'

class HomeScreen extends React.Component {
  static navigationOptions = {
    title: 'SafeArrivals',
  };

  render() {
    return (
      <View style={{flex: 1}}>
        <View style={{flex: 1, backgroundColor: "#707070"}}>
          <Text style={styles.title}>SafeArrivals</Text>
          <Image
          style={{width: 300, height: 300, alignSelf: 'center', marginTop: 90}}
          source={require('./logo.png')}
          />
          <Button color="#000000" title="Click Here To Begin" onPress={this._signInAsync} />
        </View>
      </View>
    );
  }

  _signInAsync = async () => {
    await AsyncStorage.setItem('userToken', 'abc');
    this.props.navigation.navigate('App');
  };
}

class MapScreen extends React.Component {
  static navigationOptions = {
    title: 'SafeArrivals',
  };

  render() {
    return (
        <Map />
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: "#000000"
  },
  title: {
    fontFamily: 'Cochin',
    textAlign: 'center',
    fontWeight: 'bold',
    fontSize: 70,
    marginTop: 25,
    color: "black",
  }
});

const AppStack = StackNavigator({ Map: MapScreen });
const AuthStack = StackNavigator({ Home: HomeScreen });

export default SwitchNavigator(
  {
    Home: HomeScreen,
    App: AppStack,
  },
  {
    initialRouteName: 'Home',
  }
);
