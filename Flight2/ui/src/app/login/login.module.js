import loginComponent from './login.component.js'
import LoginService from './login.service'

export default
  angular
    .module('flight.login', [])
    .component('login', loginComponent)
    .service('login', LoginService)
    .name
