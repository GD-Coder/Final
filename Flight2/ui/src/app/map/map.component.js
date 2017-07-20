import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []

  constructor ($map, $scope, $interval, locations) {
    this.$map = $map
    const self = this

    this.interval
    this.testloc = {}

    this.getFlights = function () {
      self.paths = []
      $map.getAllLocations()
        .then((data) => {
          self.testloc = data
          $map.getAllFlights()
            .then((data) => {
              data.forEach(args => {
                const path = self.generatePath(args, self.testloc)
                self.addPath(path.origin, path.destination, '#FF8990')
              })
            })
        })
    }

    this.getFlights()
    this.interval = $interval(self.getFlights, 30000)
  }

  generatePath (flight, locations) {
    const path = {
      origin: {},
      destination: {}
    }
    locations.forEach(loc => {
      if (loc.city.toUpperCase() === flight.origin.toUpperCase()) {
        path.origin = loc
      }
      if (loc.city.toUpperCase() === flight.destination.toUpperCase()) {
        path.destination = loc
      }
    })
    return path
  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b, color) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: color,
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true
    })
  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}
