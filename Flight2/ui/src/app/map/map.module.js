import mapLocations from './map.locations'
import mapComponent from './map.component.js'
import mapService from './map.service'

export default
  angular
    .module('flight.map', ['ngMap'])
    .constant('locations', mapLocations)
    .component('flightMap', mapComponent)
    .service('$map', mapService)
    .name
