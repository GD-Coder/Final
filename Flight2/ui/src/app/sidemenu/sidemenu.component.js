import templateUrl from './sidemenu.component.html'

/* @ngInject */
class SidemenuController {
  constructor ($log, $map, color, login, $interval, sidemenu, $window) {
    const self = this
    this.$window = $window
    this.search = false
    this.showItinerary = false
    $log.debug('Sidemenu')
    this.wrapper = {}

    this.listdata = {}

    $map.getAllFlights()
      .then((data) => { self.listdata = data })

    this.updateFlights = () => {
      if (self.search === false) {
        $map.getAllFlights()
          .then((data) => {
            self.listdata = data
          })
      } else {
        self.searchFlights(self.wrapper.username, self.wrapper.password)
      }
    }

    this.searchFlights = (origin, destination) => {
      self.wrapper = {
        username: origin,
        password: destination
      }
      $map.searchFlights(self.wrapper)
        .then(result => {
          self.listdata = result
          console.log(result)
        })
    }

    this.searchToggle = () => {
      this.search = !this.search
      console.log(this.search)
    }

    this.bookItinerary = () => {
      self.wrapper = {
        user_id: login.user_id,
        itinerary: self.listdata
      }
      sidemenu.postItinerary(self.wrapper)
        .then(data => console.log(data))
    }

    this.loggedIn = () => { return login.loggedIn }

    this.getRandomColor = (index) => { return color.getRandomColor(index) }

    this.interval = $interval(this.updateFlights, 30000)

    this.backClick = () => {
      this.showItinerary = false
      this.interval = $interval(this.updateFlights, 30000)
    }
    this.logout = () => {
      return this.$window.location.reload()
    }
    this.getItinerary = () => {
      $interval.cancel(this.interval)
      self.showItinerary = true
      self.listdata = []
      let temp = { origin: '', destination: '', layover: 0, flightTime: 0 }
      sidemenu.getItineraries(login.user_id)
      .then(data => {
        data.forEach(args => {
          temp.origin = args.flights[0].origin
          args.flights.forEach(flight => {
            temp.layover += flight.offset
            temp.flightTime += flight.flightTime
          })
          temp.destination = args.flights.slice(-1)[0].destination
          self.listdata.push(temp)
          temp = { origin: '', destination: '', layover: 0, flightTime: 0 }
        })
      })
    }
  }
}

export default {
  templateUrl,
  controller: SidemenuController,
  controllerAs: '$sideCtrl'
}
