import templateUrl from './login.component.html'

/* @ngInject */
class LoginController {
  constructor ($log, login) {
    $log.debug('AppController is a go.')

    this.loginClick = (username, password) => {
      login.getUserByCredentials(username, password)
    }

    this.registerClick = (username, password) => {
      const wrapper = { username: username, password: password }
      login.postUser(wrapper)
    }

    this.loggedIn = () => { return login.loggedIn }
  }
}

export default {
  templateUrl,
  controller: LoginController,
  controllerAs: '$loginCtrl'
}
