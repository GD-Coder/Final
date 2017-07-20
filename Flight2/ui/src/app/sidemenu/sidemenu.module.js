import sidemenuComponent from './sidemenu.component.js'
import mapService from '../map/map.service'
import loginService from '../login/login.service'
import sidemenuService from './sidemenu.service'


export default
  angular
    .module('flight.sidemenu', [])
    .component('sidemenu', sidemenuComponent)
    .service('$map', mapService)
    .service('login', loginService)
    .service('sidemenu', sidemenuService)
    .name
