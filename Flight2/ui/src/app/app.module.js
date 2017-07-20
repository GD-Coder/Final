import flightMap from './map/map.module'
import login from './login/login.module'
import sidemenu from './sidemenu/sidemenu.module'
import apiUrl from './api.url'
import appComponent from './app.component.js'

export default
  angular
    .module('flight', [
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      'ui.router',

      flightMap,
      login,
      sidemenu
    ])
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .name
