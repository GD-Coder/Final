/* @ngInject */
class LoginService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl

    this.loggedIn = false
    this.credentials = {}
    this.user_id
  }

  getUserByCredentials (username, password) {
    return this.$http
      .post(`${this.apiUrl}/user/username/${username}`, password)
      .then(result => {
        if(result.data.password === password) {
        this.loggedIn = true
        this.credentials = { username: result.data.username, password: result.data.password }
        this.user_id = result.data.id
        console.log(this.user_id)
        console.log(result.data)
        return result.data
      }
        else
        { alert('user does not exist, please register') }
    })

  }

  postUser (wrapper) {
    return this.$http
      .post(`${this.apiUrl}/user`, JSON.stringify(wrapper))
        .then(result => {
          this.loggedIn = true
          this.credentials = { username: result.data.username, password: result.data.password }
          this.user_id = result.data.id

          return result.data
        })
  }
}

export default LoginService
