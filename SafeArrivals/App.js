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
        <View style={{flex: 1, backgroundColor: "#00ffff"}}>
          <Text style={styles.title}>SafeArrivals</Text>
        </View>
        <View style={{flex: 2, backgroundColor: "#00cdcd"}}>
          <Image
          style={{width: 225, height: 225, alignSelf: 'center', marginTop: 10}}
          source={require('./logo.png')}
          />

        </View>
        <View style={{flex: 3, backgroundColor: "#009a9a"}}>
           <Button title="Click Here To Begin" onPress={this._signInAsync} />
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
    textAlign: 'center',
    fontWeight: 'bold',
    fontSize: 70,
    marginTop: 20,
    color: "white",
    ...webWeights.semibold
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
