/* @ngInject */
class SidemenuService {
  constructor ($http, apiUrl, $map) {
    this.$http = $http
    this.apiUrl = apiUrl
  }

  postItinerary (wrapper) {
    return this.$http
      .post(`${this.apiUrl}/user/itinerary/book`, JSON.stringify(wrapper))
      .then(result => result.data)
  }

  getItineraries (id) {
    return this.$http
      .get(`${this.apiUrl}/user/itinerary/all/${id}`)
      .then(result => result.data)
  }

}
export default SidemenuService
